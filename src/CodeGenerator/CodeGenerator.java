package CodeGenerator;

import IRBuilder.*;

import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;
class Address{
    String AimReg;
    Integer Offset;
    Boolean IsDirect;
    String VirName;
    Address(String aimReg,Integer offset,Boolean isDirect,String virName){
        AimReg = aimReg;
        Offset = offset;
        IsDirect = isDirect;
        VirName  = virName;
    }
}
public class CodeGenerator {
    String StackPointerName = "sp";
    String ZeroRegister = "zero";
    HashMap<String,GlobalSection> GlobalVariables = new HashMap<>();
    HashMap<String,Integer> CurFuncVariables;
    HashMap<String,List<String>> AllFuncParams = new HashMap<>();
    List<FunctionSection> FunctionsCode = new ArrayList<>();
    List<IRModule> ModuleList;
    ArrayList<RegUsage> RegTable = new ArrayList<>();//记录作用
    HashMap<String,Integer> RegSerial = new HashMap<>();
    HashMap<String,Address> VirFuncVarPos;
    HashMap<String,String> IRToVir;
    //当寄存器调配不了的时候，会占用栈的调用
    IRModule Global;
    Integer StackTop;
    Integer StackBottom;
    HashMap<String,Integer> CurSavedUsed = new HashMap<>();
    Integer Cnt = 0;
    Integer Flag = 0;
    //人工对照表：
    // zero 0
    // ra 1
    // sp 2
    // gp 3
    // tp 4
    // t0-2 5-7
    // s0 8
    // s1 9
    // a0-1 10-11
    // a2-7 12-17
    // s2-11 18-27
    // t3-6 28-31
    //TODO 设置Link库
    //TODO 寄存器调配

    public void setModuleList(List<IRModule> moduleList) {
        ModuleList = moduleList;
    }

    public void CodeOutput(PrintStream Stream){
        Stream.println("\t.text");
        for(FunctionSection Func : FunctionsCode)
            Func.Output(Stream);
        for(Entry<String,GlobalSection> entry : GlobalVariables.entrySet())
            entry.getValue().Output(Stream);
    }
    void RegInitial(){
        for(int i = 0 ;i < 32;i++){
            RegUsage Usage;
            if(i == 0) Usage = new RegUsage("zero",i);
            else if(i == 1) Usage = new RegUsage("ra",i);
            else if(i == 2) Usage = new RegUsage("sp",i);
            else if(i == 3) Usage = new RegUsage("gp",i);
            else if(i == 4) Usage = new RegUsage("tp",i);
            else if(i <= 7) Usage = new RegUsage("t"+(i-5),i);
            else if(i <= 9) Usage = new RegUsage("s"+(i-8),i);
            else if(i<=17) Usage = new RegUsage("a"+(i-10),i);
            else if(i<=27) Usage = new RegUsage("s"+(i-16),i);
            else Usage = new RegUsage("t"+(i-25),i);
            RegSerial.put(Usage.RegName,Usage.Serial);
            RegTable.add(Usage);
        }
    }
    public void CodeGenerate(){
        RegInitial();
        GlobalSet();
        for(IRModule module:ModuleList){
            for(IRFunc func : module.getFuncSet()) FuncGen(func);
        }
    }
    void GlobalSet(){
        //设定函数参数 全局变量 以及 找到全局
        for(IRModule module:ModuleList){
            if(Objects.equals(module.getName(), "_global")) {
                Global = module;
                break;
            }
        }
        for(Entry<String,IRValue> entry : Global.getVarTable().entrySet()){
            Integer Size;
            if(Objects.equals(entry.getValue().getAsciz(), "")){
                if(Objects.equals(entry.getValue().getType(), "_string*")) Size = IRTypeToSize(entry.getValue().getType());
                else Size = 8;
            }
            else Size =  entry.getValue().getAsciz().length();
            GlobalSection NewSec = new GlobalSection(entry.getKey(),entry.getValue().getAsciz(),Size);
            GlobalVariables.put(entry.getKey(),NewSec);
        }
        for(IRModule module:ModuleList){
            for(IRFunc func : module.getFuncSet()){
                StackBottom = 0;
                List<String>  FuncParam = GetVirFuncParam(func);
                AllFuncParams.put(func.getFuncName(),FuncParam);
            }
        }
    }
    List<String> GetVirFuncParam(IRFunc func){
        List<String> FuncParam = new ArrayList<>();
        for(IRValue value : func.getParamList()) FuncParam.add(value.getName());
        return FuncParam;
    }
    void RegReset(){
        for(RegUsage usage : RegTable) usage.Type = UsageType.Idle;
    }
    void TranSection(String FuncName,BlockSection Start, BlockSection End){
        ICode SpDown = new ICode("addi",StackPointerName,StackPointerName,StackBottom.toString(),0);
        ICode SpUp = new ICode("addi",StackPointerName,StackPointerName,Integer.toString(-StackBottom),0);
        SCode RaIn = new SCode("sw","ra","sp",-4,0);
        LCode RaOut = new LCode("lw","ra","sp",-4,0);
        SCode S0In = new SCode("sw","s0","sp",-8,0);
        LCode S0Out = new LCode("lw","s0","sp",-8,0);
        SCode S1In = new SCode("sw","s1","sp",-12,0);
        LCode S1Out = new LCode("lw","s1","sp",-12,0);
        Start.CodeList.add(SpDown);
        Start.CodeList.add(RaIn);
        Start.CodeList.add(S0In);
        Start.CodeList.add(S1In);
        List<String> FuncFormParam = AllFuncParams.get(FuncName);
        for(int i = 0 ; i< FuncFormParam.size();i++) {
            String param = FuncFormParam.get(i);
            Address StackPos = GetVirtualReg(param);
            SCode ParamStore = new SCode("sw","a"+i,"sp",StackPos.Offset,0);
            Start.CodeList.add(ParamStore);
        }
        End.CodeList.add(S1Out);
        End.CodeList.add(S0Out);
        End.CodeList.add(RaOut);
        End.CodeList.add(SpUp);
    }
    void FuncGen(IRFunc irFunc){
        if(irFunc.getInline()) return;
        if(irFunc.getLinked()) return;
        RegReset();
        StackTop = 0;
        StackBottom = 0;
        IRToVir = new HashMap<>();
        VirFuncVarPos = new HashMap<>();
        FunctionSection NewFunc = new FunctionSection(irFunc.getFuncName());
        BlockSection Start = new BlockSection("");
        CurSavedUsed = new HashMap<>();
        StackBottom -= 16;
        BlockGen(irFunc.getStart(),NewFunc);
        BlockGen(irFunc.getEnd(),NewFunc);
        NewFunc.BlocksCode = RegDistribute(NewFunc.BlocksCode);
        BlockSection End = NewFunc.BlocksCode.get(NewFunc.BlocksCode.size()-1);
        TranSection(irFunc.getFuncName(),Start,End);
        NewFunc.Start = Start;
        StackOffsetFlush(NewFunc,StackTop-StackBottom);
        MCode Ret = new MCode("ret",0);
        End.CodeList.add(Ret);
        NewFunc.Start =  Start;
        FunctionsCode.add(NewFunc);
    }
    Integer IRTypeToSize(String Type){
        if(Objects.equals(Type, "i32")) return 4;
        else if(Objects.equals(Type, "i8")) return 4;
        else if(Type.contains("*")) return 4;
        else{
            for(IRModule module: ModuleList) if(Objects.equals(module.getName(), Type)) return module.getSize();
            return -1;
        }
    }
    List<BlockSection> RegDistribute(List<BlockSection> BlockList){
        List<BlockSection> NewBlockList = new ArrayList<>();
        for(BlockSection Block : BlockList){
            BlockSection NewBlock = new BlockSection(Block.BlockLable);
            for(BaseCode Code :Block.CodeList){
                if(Code instanceof ICode) {
                    ICode ImmCode = (ICode) Code;
                    if (Objects.equals(ImmCode.Rs, ".temp")) ImmCode.Rs = "t0";
                    else if (Objects.equals(ImmCode.Rs, ".temp1")) ImmCode.Rs = "t1";
                    else if(Objects.equals(ImmCode.Rs, ".zero")) ImmCode.Rs = "zero";
                    else {
                        Address RsAddr = VirFuncVarPos.get(ImmCode.Rs);
                        LCode RsLoad = new LCode("lw", "s0", "sp", RsAddr.Offset, Code.Line);
                        ImmCode.Rs = "s0";
                        NewBlock.CodeList.add(RsLoad);
                    }
                    if (Objects.equals(ImmCode.Rd, ".temp")){
                        ImmCode.Rd = "t0";
                        NewBlock.CodeList.add(ImmCode);
                    }
                    else if (Objects.equals(ImmCode.Rd, ".temp1")){
                        ImmCode.Rd = "t1";
                        NewBlock.CodeList.add(ImmCode);
                    }
                    else {
                        Address RdAddr = VirFuncVarPos.get(ImmCode.Rd);
                        SCode RdStore = new SCode("sw", "s0", "sp", RdAddr.Offset, Code.Line);
                        ImmCode.Rd = "s0";
                        NewBlock.CodeList.add(ImmCode);
                        NewBlock.CodeList.add(RdStore);
                    }

                }
                else if(Code instanceof SCode){
                    SCode StoreCode = (SCode) Code;
                    if(Objects.equals(StoreCode.Rs1,".temp1")){
                        StoreCode.Rs1 = "t1";
                        StoreCode.Imm = 0;
                    }
                    else  if(Objects.equals(StoreCode.Rs1,".temp")){
                        StoreCode.Rs1 = "t0";
                        StoreCode.Imm = 0;
                    }
                    else{
                        Address RsAddr = VirFuncVarPos.get(StoreCode.Rs1);
                        LCode RsLoad = new LCode("lw","s0","sp",RsAddr.Offset,Code.Line);
                        StoreCode.Rs1 = "s0";
                        NewBlock.CodeList.add(RsLoad);
                    }
                    if(Objects.equals(StoreCode.Rs2, ".temp")) {
                        StoreCode.Rs2 = "t0";
                        StoreCode.Imm = 0;
                        NewBlock.CodeList.add(StoreCode);
                    }
                    else{
                        Address PtrAddr = VirFuncVarPos.get(StoreCode.Rs2);
                        StoreCode.Rs2 = "sp";
                        StoreCode.Imm = PtrAddr.Offset;
                        NewBlock.CodeList.add(StoreCode);
                    }
                }
                else if(Code instanceof LCode){
                    LCode LoadCode = (LCode) Code;
                    if(Objects.equals(LoadCode.Rs, ".temp")) {
                        LoadCode.Rs = "t0";
                        LoadCode.Offset = 0;
                    }
                    else if(Objects.equals(LoadCode.Rs, ".temp1")) {
                        LoadCode.Rs = "t1";
                        LoadCode.Offset = 0;
                    }
                    else{
                        Address PtrAddr = VirFuncVarPos.get(LoadCode.Rs);
                        LoadCode.Rs = "sp";
                        LoadCode.Offset = PtrAddr.Offset;
                    }
                    if(Objects.equals(LoadCode.Rd, ".temp1")) {
                        LoadCode.Rd = "t1";
                        NewBlock.CodeList.add(LoadCode);
                    }
                    else if(Objects.equals(LoadCode.Rd, ".temp")) {
                        LoadCode.Rd = "t0";
                        NewBlock.CodeList.add(LoadCode);
                    }
                    else{
                        Address RdAddr = VirFuncVarPos.get(LoadCode.Rd);
                        LoadCode.Rd = "s0";
                        SCode RdStore = new SCode("sw","s0","sp",RdAddr.Offset,Code.Line);
                        NewBlock.CodeList.add(LoadCode);
                        NewBlock.CodeList.add(RdStore);
                    }

                }
                else if(Code instanceof RCode){
                    RCode OperationCode = (RCode) Code;
                    Address RdAddr = VirFuncVarPos.get(OperationCode.Rd);
                    if(Objects.equals(OperationCode.Rs1,".temp1")){
                        OperationCode.Rs1 = "t1";
                    }
                    else  if(Objects.equals(OperationCode.Rs1,".temp")){
                        OperationCode.Rs1 = "t0";
                    }
                    else{
                        Address Rs1Addr = VirFuncVarPos.get(OperationCode.Rs1);
                        LCode Rs1Load = new LCode("lw","s0","sp",Rs1Addr.Offset,Code.Line);
                        OperationCode.Rs1 = "s0";
                        NewBlock.CodeList.add(Rs1Load);
                    }
                    if(Objects.equals(OperationCode.Rs2,".temp")){
                        OperationCode.Rs2 = "t0";
                    }
                    else{
                        Address Rs2Addr = VirFuncVarPos.get(OperationCode.Rs2);
                        LCode Rs2Load = new LCode("lw","s1","sp",Rs2Addr.Offset,Code.Line);
                        OperationCode.Rs2 = "s1";
                        NewBlock.CodeList.add(Rs2Load);
                    }
                    if(Objects.equals(OperationCode.Rd,".temp")){
                        OperationCode.Rd = "t0";
                        NewBlock.CodeList.add(OperationCode);
                    }
                    else   if(Objects.equals(OperationCode.Rd,".temp1")){
                        OperationCode.Rd = "t1";
                        NewBlock.CodeList.add(OperationCode);
                    }
                    else {
                        SCode RdStore = new SCode("sw", "s0", "sp", RdAddr.Offset, Code.Line);
                        OperationCode.Rd = "s0";
                        NewBlock.CodeList.add(OperationCode);
                        NewBlock.CodeList.add(RdStore);
                    }
                }
                else if(Code instanceof BCode) {
                    BCode BranchCode = (BCode) Code;
                    Address Rs1Addr = VirFuncVarPos.get(BranchCode .Rs1);
                    LCode Rs1Load = new LCode("lw","s0","sp",Rs1Addr.Offset,Code.Line);
                    BranchCode.Rs1 = "s0";
                    NewBlock.CodeList.add(Rs1Load);
                    if(Objects.equals(BranchCode.Rs2,".zero")){
                        BranchCode.Rs2 = "zero";
                    }
                    else{
                        Address Rs2Addr = VirFuncVarPos.get(BranchCode .Rs2);
                        LCode Rs2Load = new LCode("lw","s1","sp",Rs2Addr.Offset,Code.Line);
                        BranchCode.Rs2 = "s1";
                        NewBlock.CodeList.add(Rs2Load);
                    }
                    NewBlock.CodeList.add(BranchCode);
                }
                else if(Code instanceof MCode){
                    MCode ManageCode = (MCode) Code;
                    NewBlock.CodeList.add(ManageCode);
                }
                else if(Code instanceof CCode){
                    CCode ControlCode = (CCode) Code;
                    NewBlock.CodeList.add(ControlCode);
                }
                else if(Code instanceof JCode){
                    JCode JumpCode = (JCode) Code;
                    NewBlock.CodeList.add(JumpCode);
                }
                else if(Code instanceof UCode){
                    UCode UpperCode = (UCode) Code;
                    if(Objects.equals(UpperCode.Rd,".temp")){
                        UpperCode.Rd = "t0";
                        NewBlock.CodeList.add(UpperCode);
                    }
                    else if(Objects.equals(UpperCode.Rd,".temp1")){
                        UpperCode.Rd = "t1";
                        NewBlock.CodeList.add(UpperCode);
                    }
                    else{
                        Address RdAddr = VirFuncVarPos.get(UpperCode.Rd);
                        SCode RdStore= new SCode("sw","s0","sp",RdAddr.Offset,Code.Line);
                        UpperCode.Rd = "s0";
                        NewBlock.CodeList.add(UpperCode);
                        NewBlock.CodeList.add(RdStore);
                    }

                }
                else{
                    PCode PesudoCode = (PCode) Code;
                    if(Objects.equals(PesudoCode.Rs, ".return")) PesudoCode.Rs = "a0";
                    else if(Objects.equals(PesudoCode.Rs,".temp")) PesudoCode.Rs = "t0";
                    else{
                        Address RsAddr = VirFuncVarPos.get(PesudoCode.Rs);
                        LCode RsLoad = new LCode("lw","s0","sp",RsAddr.Offset,Code.Line);
                        PesudoCode.Rs = "s0";
                        NewBlock.CodeList.add(RsLoad);
                    }
                    if(PesudoCode.Rd.length() > 6 && Objects.equals(PesudoCode.Rd.substring(0,6),".param")){
                        PesudoCode.Rd = "a"+PesudoCode.Rd.substring(6);
                        NewBlock.CodeList.add(PesudoCode);
                    }
                    else if(Objects.equals(PesudoCode.Rd,".return")){
                        PesudoCode.Rd = "a0";
                        NewBlock.CodeList.add(PesudoCode);
                    }
                    else {
                        Address RdAddr = VirFuncVarPos.get(PesudoCode.Rd);
                        SCode RdStore = new SCode("sw", "s0", "sp", RdAddr.Offset, Code.Line);
                        PesudoCode.Rd = "s0";
                        NewBlock.CodeList.add(PesudoCode);
                        NewBlock.CodeList.add(RdStore);
                    }
                }
            }
            NewBlockList.add(NewBlock);
        }
        return NewBlockList;
    }
    void StackOffsetFlush(FunctionSection Func,Integer StackSize){
        List<BlockSection> Blocks = new ArrayList<>();
        Blocks.add(Func.Start);
        Blocks.addAll(Func.BlocksCode);
        for(BlockSection Block : Blocks) {
            for (BaseCode Code : Block.CodeList){
                if (Code instanceof SCode ) {
                    SCode Store = (SCode) Code;
                    if (Objects.equals(Store.Rs2, "sp")) Store.Imm += StackSize;
                }
                else if(Code instanceof LCode) {

                    LCode Load = (LCode) Code;

                    if (Objects.equals(Load.Rs, "sp")) Load.Offset += StackSize;
                }
                else if(Code instanceof ICode){
                    ICode Imm = (ICode) Code;
                    String imm = Imm.Imm;
                    if(imm.length()>4 && imm.substring(0, 4).equals("(sp)")){
                        Imm.Imm = Integer.toString(Integer.parseInt(imm.substring(4))+StackSize);
                        Imm.Rs = "sp";
                    }
                }

            }
        }
    }
    Address GetVirtualReg(String User){
        if(IRToVir.containsKey(User)) return VirFuncVarPos.get(IRToVir.get(User));
        if(User.charAt(0) == '.'){
            Address NewStackPos = new Address("",0,Boolean.TRUE,User);
            return NewStackPos;
        }
        StackBottom -= 4;
        Address NewStackPos = new Address("sp",StackBottom,Boolean.FALSE,Integer.toString(++Cnt));
        IRToVir.put(User,NewStackPos.VirName);
        VirFuncVarPos.put(NewStackPos.VirName,NewStackPos);
        return NewStackPos;
    }
    IRFunc FindFunc(String FuncName){
        for(IRModule module:ModuleList) {
            if (module.FindFunc(FuncName) != null) return module.FindFunc(FuncName);
        }
        return Global.FindFunc(FuncName);
    }
    void BlockGen(IRBlock irBlock,FunctionSection func){
        if(irBlock == null) return;
   //     HashMap<Integer,String> StackTable = new HashMap<>();
        BlockSection NewBlockCode = new BlockSection(irBlock.getLabel());
  //      System.out.println(irBlock.getLabel());
        List<BaseInstr> InstrList = irBlock.getVarInstrList();
        InstrList.add(irBlock.getEndInstr());
        int InstrLine = 0;
        for(BaseInstr Instr : InstrList){
            ++InstrLine;
            if(Instr instanceof AllocaInstr){
                AllocaInstr NewIRAlloc = (AllocaInstr) Instr;
        //        StackTable.put(StackBottom, NewIRAlloc.Rd);
                Address Rd = GetVirtualReg(NewIRAlloc.Rd);
                StackBottom -= 4;
                ICode Addi = new ICode("addi",".temp",".zero","(sp)"+StackBottom,InstrLine);
                SCode Store = new SCode("sw",".temp",Rd.VirName,0,InstrLine);
                NewBlockCode.CodeList.add(Addi);
                NewBlockCode.CodeList.add(Store);
            }
            else if(Instr instanceof LoadInstr){
                LoadInstr NewIRLoad = (LoadInstr) Instr;
                Address RdAddress = GetVirtualReg(NewIRLoad.Rd);
                String TmpReg = ".temp";
                Address TmpAddr = GetVirtualReg(TmpReg);
                String Op;
                Op = "lw";
                if(NewIRLoad.IsPtrGlobal){
                    UCode NewLui = new UCode("lui", TmpAddr.VirName, "%hi("+ NewIRLoad.RsPtr+")",InstrLine);
                    ICode NewAddi = new ICode("addi",TmpAddr.VirName,TmpAddr.VirName,"%lo("+NewIRLoad.RsPtr+")",InstrLine);
                    LCode NewLoad = new LCode(Op, RdAddress.VirName,TmpAddr.VirName, 0,InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    NewBlockCode.CodeList.add(NewLoad);
                }
                else {
                    Address PtrAddress = GetVirtualReg(NewIRLoad.RsPtr);
                    LCode PtrLoad = new LCode(Op,TmpAddr.VirName,PtrAddress.VirName,0,InstrLine);
                    LCode NewLoad = new LCode(Op, RdAddress.VirName,TmpAddr.VirName , 0, InstrLine);
                    NewBlockCode.CodeList.add(PtrLoad);
                    NewBlockCode.CodeList.add(NewLoad);
                }
            }
            else if(Instr instanceof OperationInstr ){
                OperationInstr NewIROp= ( OperationInstr) Instr;
                String Op;
                Address Rd = GetVirtualReg(NewIROp.Rd);
                if(NewIROp.IsRsImm1 || NewIROp.IsRsImm2) {
                    String Imm = NewIROp.IsRsImm1 ? NewIROp.Rs1 : NewIROp.Rs2;
                    String ImmUser;
                    String TmpReg = ".temp";
                    if(NewIROp.IsRsImm1 ^ NewIROp.IsRsImm2)
                        ImmUser = TmpReg;
                    else ImmUser = NewIROp.Rd;
                    Address ImmAddr = GetVirtualReg(ImmUser);
                    long IntImm = Long.parseLong(Imm);
                    if(IntImm >= 4096){
                        UCode NewLui = new UCode("lui",ImmAddr.VirName,Long.toString(IntImm >> 12),InstrLine);
                        ICode NewLi = new ICode("addi", ImmAddr.VirName, ImmAddr.VirName, Long.toString(IntImm & 4095), InstrLine);
                        NewBlockCode.CodeList.add(NewLui);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    else {
                        Address Zero = GetVirtualReg(".zero");
                        ICode NewLi = new ICode("addi", ImmAddr.VirName, Zero.VirName, Imm, InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    if(NewIROp.IsRsImm1 ^ NewIROp.IsRsImm2) {
                        Address Rs = NewIROp.IsRsImm1 ? GetVirtualReg(NewIROp.Rs2) : GetVirtualReg(NewIROp.Rs1);
                        if(Objects.equals(NewIROp.Op, "icmp")){
                            if(Objects.equals(NewIROp.Mode, "ult")) {
                                Op = "slt";
                                RCode NewOp = new RCode(Op, Rd.VirName, Rs.VirName, ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                            }
                            else if(Objects.equals(NewIROp.Mode, "ugt")){
                                Op = "slt";
                                String Tmp;
                                Tmp  = Rs.VirName;
                                Rs.VirName = ImmAddr.VirName;
                                ImmAddr.VirName = Tmp;
                                RCode NewOp = new RCode(Op, Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                            }
                            else if(Objects.equals(NewIROp.Mode, "uge")){
                                Op = "slt";
                                RCode NewOp = new RCode(Op,  Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                ICode NewXor = new ICode("xori", Rd.VirName, Rd.VirName,"1",InstrLine);
                                NewBlockCode.CodeList.add(NewXor);
                            }
                            else if(Objects.equals(NewIROp.Mode, "ule")){
                                Op = "slt";
                                String Tmp;
                                Tmp  = Rs.VirName;
                                Rs.VirName = ImmAddr.VirName;
                                ImmAddr.VirName = Tmp;
                                RCode NewOp = new RCode(Op,Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                ICode NewXor = new ICode("xori",Rd.VirName,Rd.VirName,"1",InstrLine);
                                NewBlockCode.CodeList.add(NewXor);
                            }
                            else if(Objects.equals(NewIROp.Mode, "eq")){
                                Op = "sub";
                                RCode NewOp = new RCode(Op, Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                PCode NewSeqz = new PCode("seqz",Rd.VirName,Rd.VirName,InstrLine);
                                NewBlockCode.CodeList.add(NewSeqz);
                            }
                            else{
                                Op = "sub";
                                RCode NewOp = new RCode(Op,Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                PCode NewSnez = new PCode("snez",Rd.VirName,Rd.VirName,InstrLine);
                                NewBlockCode.CodeList.add(NewSnez);

                            }
                        }
                        else {
                            Op = NewIROp.Op;
                            RCode NewOp = new RCode(Op,Rd.VirName, Rs.VirName,ImmAddr.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                    }
                  //  RegFree(ImmRd);
                }
                else {
                    Address Rs1  = GetVirtualReg(NewIROp.Rs1);
                    Address Rs2 = GetVirtualReg(NewIROp.Rs2);
                  //  RegFree(Rs1);
                  //  RegFree(Rs2);
                    if(Objects.equals(NewIROp.Op, "icmp")){
                        if(Objects.equals(NewIROp.Mode, "ult")){
                            Op = "slt";
                            RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                        else if(Objects.equals(NewIROp.Mode, "ugt")){
                            Op = "slt";
                            String Tmp;
                            Tmp  = Rs1.VirName;
                            Rs1.VirName = Rs2.VirName;
                            Rs2.VirName = Tmp;
                            RCode NewOp = new RCode(Op,Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                        else if(Objects.equals(NewIROp.Mode, "uge")){
                            Op = "slt";
                            RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            ICode NewXor = new ICode("xori",Rd.VirName,Rd.VirName,"1",InstrLine);
                            NewBlockCode.CodeList.add(NewXor);
                        }
                        else if(Objects.equals(NewIROp.Mode, "ule")){
                            Op = "slt";
                            String Tmp;
                            Tmp  = Rs1.VirName;
                            Rs1.VirName = Rs2.VirName;
                            Rs2.VirName = Tmp;
                            RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            ICode NewXor = new ICode("xori",Rd.VirName,Rd.VirName,"1",InstrLine);
                            NewBlockCode.CodeList.add(NewXor);
                        }
                        else if(Objects.equals(NewIROp.Mode, "eq")){
                            Op = "sub";
                            RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            PCode NewSeqz = new PCode("seqz",Rd.VirName,Rd.VirName,InstrLine);
                            NewBlockCode.CodeList.add(NewSeqz);
                        }
                        else{
                            Op = "sub";
                            RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            PCode NewSnez = new PCode("snez",Rd.VirName,Rd.VirName,InstrLine);
                            NewBlockCode.CodeList.add(NewSnez);

                        }
                    }
                    else {
                        Op = NewIROp.Op;
                        RCode NewOp = new RCode(Op, Rd.VirName, Rs1.VirName, Rs2.VirName, InstrLine);
                        NewBlockCode.CodeList.add(NewOp);
                    }
                }
            }
            else if(Instr instanceof StoreInstr){
                StoreInstr NewIRStore = (StoreInstr)  Instr;
                String Op;
                Address Ptr;
                Address Rs;
                Op = "sw";
                if(NewIRStore.IsPtrGlobal){
                    Ptr = GetVirtualReg(".temp");
                    UCode NewLui = new UCode("lui",Ptr.VirName ,"%hi("+NewIRStore.Ptr+")",InstrLine);
                    ICode NewAddi = new ICode("addi",Ptr.VirName ,Ptr.VirName,"%lo("+NewIRStore.Ptr+")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                }
                else{
                    Address TmpAddr = GetVirtualReg(".temp");
                    Ptr = GetVirtualReg(NewIRStore.Ptr);
                    LCode PtrLoad = new LCode("lw",TmpAddr.VirName,Ptr.VirName,0,InstrLine);
                    NewBlockCode.CodeList.add(PtrLoad);
                    Ptr = TmpAddr;
                }
                if(NewIRStore.IsRsGlobal){
                    Rs = GetVirtualReg(".temp1");
                    UCode NewLui = new UCode("lui",Rs.VirName ,"%hi("+NewIRStore.Rs+")",InstrLine);
                    ICode NewAddi = new ICode("addi",Rs.VirName ,Rs.VirName ,"%lo("+NewIRStore.Rs+")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                }
                else {
                    Rs = GetVirtualReg(NewIRStore.Rs);
                }
                SCode NewStore = new SCode(Op,Rs.VirName,Ptr.VirName, 0,InstrLine);
                NewBlockCode.CodeList.add(NewStore);
            }
            else if(Instr instanceof GetelementInstr) {
                GetelementInstr NewIRGet = (GetelementInstr)  Instr;

                Integer RdSize = IRTypeToSize(NewIRGet.RdType);
                String TmpReg = ".temp";
                String TmpReg2 = ".temp1";
                Address Ptr = GetVirtualReg(NewIRGet.Ptr);
                if (NewIRGet.IsPtrGlobal) {
                    UCode NewLui = new UCode("lui", Ptr.VirName, "%hi(" + NewIRGet.Ptr + ")",InstrLine);
                    ICode NewAddi = new ICode("addi", Ptr.VirName, Ptr.VirName, "%lo(" + NewIRGet.Ptr + ")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                }
                Address Imm = GetVirtualReg(TmpReg);
                Address Index = GetVirtualReg(TmpReg2);
                Address Rd = GetVirtualReg(NewIRGet.Rd);
                Address IndexPtr = GetVirtualReg(NewIRGet.Index);
                if(Objects.equals(NewIRGet.Mode, "index")) {
                    Address Zero = GetVirtualReg(".zero");
                    LCode NewIndexLoad = new LCode("lw",Index.VirName,IndexPtr.VirName,0,InstrLine);
                    ICode NewLi = new ICode("addi",Imm.VirName,Zero.VirName, RdSize.toString(),InstrLine);
                    RCode NewMuli = new RCode("mul", Index.VirName, Index.VirName, Imm.VirName, InstrLine);
                    ICode NewAddi = new ICode("addi", Imm.VirName, Index.VirName, "4", InstrLine);
                 //   RCode NewAdd = new RCode("add", Imm.VirName, Index.VirName, Imm.VirName, InstrLine);
                    NewBlockCode.CodeList.add(NewIndexLoad);
                    NewBlockCode.CodeList.add(NewLi);
                    NewBlockCode.CodeList.add(NewMuli);
                    NewBlockCode.CodeList.add(NewAddi);
               //     NewBlockCode.CodeList.add(NewAdd);
                }
                else if(Objects.equals(NewIRGet.Mode, "offset")){
                        Address Zero = GetVirtualReg(".zero");
                        ICode NewOffAddi = new ICode("addi", Imm.VirName, Zero.VirName, NewIRGet.Offset.toString(), InstrLine);
                        NewBlockCode.CodeList.add(NewOffAddi);
                }
                RCode NewPtrAdd = new RCode("add", Rd.VirName,Ptr.VirName,Imm.VirName,InstrLine);
                NewBlockCode.CodeList.add(NewPtrAdd);
            }
            else if(Instr instanceof BranchInstr){
                BranchInstr NewIRBr = (BranchInstr)  Instr;
                if(Objects.equals(NewIRBr.Condition, "")){
                    JCode NewJump = new JCode("j", NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewJump);
                }
                else {

                    Address Condi = GetVirtualReg(NewIRBr.Condition);
                    Address Zero = GetVirtualReg(".zero");
                    BCode NewBrT = new BCode("beq", Condi.VirName, Zero.VirName, NewIRBr.Label2,InstrLine);
                    JCode NewBrF = new JCode("j", NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewBrT);
                    NewBlockCode.CodeList.add(NewBrF);
                }
            }
            else if(Instr instanceof FuncCallInstr){
                FuncCallInstr NewIRCall = (FuncCallInstr)  Instr;
          //      System.out.println(NewIRCall.FuncName);
                List<String> CallParams = NewIRCall.Param;
                for(int i = 0 ; i < CallParams.size();i++) {
                    String CallParamName = CallParams.get(i);
                    Address Rd = GetVirtualReg(".param"+i);
                    Address Rs = GetVirtualReg(CallParamName);
                    if(NewIRCall.IsGlobal.get(i)) {
                        UCode NewLui = new UCode("lui", Rs.VirName, "%hi(" + CallParamName + ")",InstrLine);
                        ICode NewAddi = new ICode("addi", Rs.VirName, Rs.VirName, "%lo(" + CallParamName + ")",InstrLine);
                        NewBlockCode.CodeList.add(NewLui);
                        NewBlockCode.CodeList.add(NewAddi);
                    }
                    PCode NewMv = new PCode("mv", Rd.VirName, Rs.VirName,InstrLine);
                    NewBlockCode.CodeList.add(NewMv);
                }
                CCode NewCall = new CCode("call", NewIRCall.FuncName,InstrLine);
                NewBlockCode.CodeList.add(NewCall);
                if(!Objects.equals(NewIRCall.Rd, "")){
                    Address Rd = GetVirtualReg(NewIRCall.Rd);
                    Address Ret = GetVirtualReg(".return");
                    PCode NewMv = new PCode("mv", Rd.VirName, Ret.VirName,InstrLine);
                    NewBlockCode.CodeList.add(NewMv);
                }
           //     TempOutStack(InStackTemp,FuncVarPos,NewBlockCode);
            }
            else if(Instr instanceof PhiInstr ){
            }
            else if(Instr instanceof ReturnInstr){
                //和call一样，需要交流
                ReturnInstr NewIRRet = ( ReturnInstr)  Instr;
                if(Objects.equals(NewIRRet.Type, "void")) break;
                Address Rs = GetVirtualReg(NewIRRet.Rs);
                Address Rd = GetVirtualReg(".return");
                PCode Move = new PCode("mv",Rd.VirName,Rs.VirName,InstrLine);
                NewBlockCode.CodeList.add(Move);
            }
        }
        func.BlocksCode.add(NewBlockCode);
        for(IRBlock Son :irBlock.getSubBlocks()) BlockGen(Son,func);
    }
}
