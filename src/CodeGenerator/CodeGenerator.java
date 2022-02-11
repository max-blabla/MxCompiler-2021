package CodeGenerator;

import IRBuilder.*;

import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;
class Address{
    String AimReg;
    Integer Offset;
    Address(String aimReg,Integer offset){
        AimReg = aimReg;
        Offset = offset;
    }
}
public class CodeGenerator {
    String StackPointerName = "sp";
    String ZeroRegister = "zero";
    HashMap<String,GlobalSection> GlobalVariables = new HashMap<>();
    HashMap<String,Integer> CurFuncVariables;
    HashMap<String,HashMap<String,Integer>> AllFuncParams = new HashMap<>();
  //  Integer RetVariable;
    List<FunctionSection> FunctionsCode = new ArrayList<>();
   // HashMap<String,HashMap<String,Integer>> AllLinkingFunc;
    List<IRModule> ModuleList;
    ArrayList<RegUsage> RegTable = new ArrayList<>();//记录作用
    HashMap<String,Integer> RegSerial = new HashMap<>();
    HashMap<String,Address> FuncVarPos;
    //当寄存器调配不了的时候，会占用栈的调用
    IRModule Global;
    Integer StackTop;
    Integer StackBottom;
    HashMap<String,Integer> CurSavedUsed = new HashMap<>();
    Integer Dirty = 18;
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
            GlobalSection NewSec = new GlobalSection(entry.getKey(),entry.getValue().getAsciz(),Size,entry.getValue().getWord());
            GlobalVariables.put(entry.getKey(),NewSec);
        }
        for(IRModule module:ModuleList){
            for(IRFunc func : module.getFuncSet()){
                HashMap<String,Integer> FuncParam = new HashMap<>();
                StackBottom = 0;
                FuncParamDistribute(func.getParamList().size(),func.getParamList(),FuncParam);
                AllFuncParams.put(func.getFuncName(),FuncParam);
            }
        }
    }
    void RegReset(){
        for(RegUsage usage : RegTable) usage.Type = UsageType.Idle;
    }
    void FuncGen(IRFunc irFunc){
        if(irFunc.getInline()) return;
        if(irFunc.getLinked()) return;
        RegReset();
        StackTop = 0;
        StackBottom  = -8;
        FuncVarPos = new HashMap<>();
        FunctionSection NewFunc = new FunctionSection(irFunc.getFuncName());
        BlockSection Start = new BlockSection("");
        CurSavedUsed = new HashMap<>();
        CurFuncVariables = AllFuncParams.get(irFunc.getFuncName());
       // RegDistribute("sp",FuncReg,"sp",NewFunc.Start);
        int Ra = -4;

        BlockGen(irFunc.getStart(),NewFunc);
        BlockGen(irFunc.getEnd(),NewFunc);
        ArrayDeque<BaseCode> CodeList = NewFunc.BlocksCode.get(NewFunc.BlocksCode.size()-1).CodeList;
        StackBottom -= CurSavedUsed.size() * 4;
        ICode SpDown = new ICode("addi","sp","sp",StackBottom.toString(),0);
        SCode RaStore = new SCode("sw","ra","sp", Ra,0);
        LCode RaLoad = new LCode("lw","ra","sp", Ra,0);
        ICode SpUp = new ICode("addi","sp","sp",Integer.toString(-StackBottom),0);
        MCode Ret = new MCode("ret",0);
        Start.CodeList.add(SpDown);
        Start.CodeList.add(RaStore);


        Integer Cnt = 0;
        for(String Key: CurSavedUsed.keySet()){
            SCode SavedStore = new SCode("sw",Key,"sp", StackBottom+Cnt,0);
            LCode SavedLoad = new LCode("lw",Key,"sp",StackBottom+Cnt,0);
            Start.CodeList.add(SavedStore);
            CodeList.add(SavedLoad);
            Cnt += 4;
        }
        CodeList.add(RaLoad);
        CodeList.add(SpUp);
        CodeList.add(Ret);
        NewFunc.Start =  Start;
        StackOffsetFlush(NewFunc,-StackBottom);
        FunctionsCode.add(NewFunc);
        //在头尾前塞一个
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
    String RegDistribute(String User,HashMap<String,Address> VarPos,String TarReg,BlockSection CurBlock){
        if(VarPos.containsKey(User)){
            Address Addr = VarPos.get(User);
            if(Addr.Offset > 0) return Addr.AimReg;
        }
        if(!Objects.equals(TarReg, "")){
            for(RegUsage usage : RegTable){
                if(Objects.equals(usage.RegName, TarReg)){
                    if(TarReg.charAt(0)=='s') CurSavedUsed.put(TarReg,0);
                    if(usage.Type != UsageType.Idle) {
                        StackBottom -= 4;
                        SCode Store = new SCode("sw",TarReg,"sp",StackBottom,0);
                        CurBlock.CodeList.add(Store);
                        Address NewAddr = new Address("sp",StackBottom);
                        VarPos.replace(usage.UserName,NewAddr);
                    }
                    usage.UserName = User;
                    usage.Type = UsageType.Busy;
                    Address NewAddr = new Address(usage.RegName, 1);
                    if(!Objects.equals(User, "")) VarPos.put(User,NewAddr);
                    return TarReg;
                }
            }
            return "";
        }
        else{
            for(RegUsage usage : RegTable){
                if(!( (usage.Serial <= 9 && usage.Serial >= 8) || (usage.Serial <= 27 && usage.Serial>=18)) ) continue;
                if(usage.Type == UsageType.Idle){
                    CurSavedUsed.put(usage.RegName, 0);
                    usage.UserName = User;
                    usage.Type = UsageType.Busy;
                    Address NewAddr = new Address(usage.RegName, 1);
                    if(!Objects.equals(User, "")) VarPos.put(User,NewAddr);
                    return usage.RegName;
                }
            }
/*            for(RegUsage usage : RegTable){
                if(!( (usage.Serial <= 7 && usage.Serial >= 5) || (usage.Serial <= 31 && usage.Serial>= 28)) ) continue;
                if(usage.Type == UsageType.Idle){
                    usage.UserName = User;
                    usage.Type = UsageType.Busy;
                    if(!Objects.equals(User, "")) VarPos.put(User,usage.Serial);
                    return usage.RegName;
                }
            }*/

            Dirty = (Dirty+1) %10 + 18;
            for(int i = 0; i< 10 ;i++){
                if(RegTable.get(Dirty).Type == UsageType.Stable)
                    Dirty = (Dirty+1) %10 + 18;
            }
            RegUsage Di = RegTable.get(Dirty);
            StackBottom -= 4;
            SCode Store = new SCode("sw",Di.RegName,"sp",StackBottom,0);
            CurBlock.CodeList.add(Store);
            Address NewReAddr = new Address("sp", StackBottom);
            VarPos.replace(Di.UserName,NewReAddr);
            Di .UserName = User;
            Di .Type = UsageType.Busy;
            if(!Objects.equals(User, "")){
                Address NewAddr = new Address(Di.RegName, 1);
                VarPos.put(User,NewAddr);
            }
            return Di.RegName;
        }
    }
    void FuncParamDistribute(Integer Num,List<IRValue> Param,HashMap<String,Integer> FuncPos){
        if(Num <= 8) for(IRValue param :Param)  FuncPos.put(param.getName(),FuncPos.size()+10);
        else{
            int StackParamNum = Num - 8;
            for(int i = 0 ; i<StackParamNum;i++) FuncPos.put(Param.get(i).getName(),-4*(StackParamNum-i));
            for(int i = 0 ; i<8;i++) FuncPos.put(Param.get(i+StackParamNum).getName(),i+1);
        }
    }

    void RegFree(String RegName){
        if(RegTable.get(RegSerial.get(RegName)).Type!=UsageType.Stable)
        RegTable.get(RegSerial.get(RegName)).Type = UsageType.Idle;
    }
    HashMap<String,Integer> TempInStack(HashMap<String,Integer> VarPos,BlockSection CurBlock){
        HashMap<String,Integer> InStackTemp = new HashMap<>();
        for(RegUsage usage : RegTable){
            if(!( (usage.Serial <= 7 && usage.Serial >= 5) || (usage.Serial <= 31 && usage.Serial>= 28)) ) continue;
            if(usage.Type != UsageType.Idle){
                usage.Type = UsageType.Idle;
                StackBottom -= 4;
                SCode Store = new SCode("sw",usage.RegName,"sp",StackBottom,0);
                CurBlock.CodeList.add(Store);
                VarPos.replace(usage.UserName,StackBottom);
                InStackTemp.put(usage.UserName,StackBottom);
            }
        }
        return InStackTemp;
    }
    void TempOutStack(HashMap<String,Integer> InStackTemp,HashMap<String,Integer> VarPos,BlockSection CurBlock){
        for(Entry<String,Integer> entry : InStackTemp.entrySet()){
            RegUsage Usage = RegTable.get(entry.getValue());
            String UserName = entry.getKey();
            Usage.Type = UsageType.Busy;
            LCode Load = new LCode("lw",Usage.RegName,"sp",VarPos.get(UserName),0);
            StackBottom += 4;
            CurBlock.CodeList.add(Load);
            VarPos.replace(UserName,Usage.Serial);
        }
    }
    void StackOffsetFlush(FunctionSection Func,Integer StackSize){
        List<BlockSection> Blocks = new ArrayList<>();
        Blocks.add(Func.Start);
        Blocks.addAll(Func.BlocksCode);
        for(BlockSection Block : Blocks) {
            for (BaseCode Code : Block.CodeList){
                if (Code instanceof SCode Store) { if (Objects.equals(Store.Rs2, "sp")) Store.Imm += StackSize;}
                else if(Code instanceof LCode Load) { if (Objects.equals(Load.Rs, "sp")) Load.Offset += StackSize;}

            }
        }
    }
    String StableRegDistribute(String User,HashMap<String,Integer> VarPos,String TarReg,BlockSection CurBlock){
        if(VarPos.containsKey(User)){
            Integer Pos = VarPos.get(User);
            if(Pos > 0) return RegTable.get(Pos).RegName;
        }
        if(!Objects.equals(TarReg, "")){
            for(RegUsage usage : RegTable){
                if(Objects.equals(usage.RegName, TarReg)){
                    if(TarReg.charAt(0)=='s') CurSavedUsed.put(TarReg,0);
                    if(usage.Type != UsageType.Idle) {
                        StackBottom -= 4;
                        SCode Store = new SCode("sw",TarReg,"sp",StackBottom,0);
                        CurBlock.CodeList.add(Store);
                        VarPos.replace(usage.UserName,StackBottom);
                    }
                    usage.UserName = User;
                    usage.Type = UsageType.Stable;
                    if(!Objects.equals(User, ""))  VarPos.put(User,usage.Serial);
                    return TarReg;
                }
            }
            return "";
        }
        else{
            for(RegUsage usage : RegTable){
                if(!( (usage.Serial <= 9 && usage.Serial >= 8) || (usage.Serial <= 27 && usage.Serial>=18)) ) continue;
                if(usage.Type == UsageType.Idle){
                    CurSavedUsed.put(usage.RegName, 0);
                    usage.UserName = User;
                    usage.Type = UsageType.Stable;
                    if(!Objects.equals(User, "")) VarPos.put(User,usage.Serial);
                    return usage.RegName;
                }
            }
            Dirty = (Dirty+1) %10 + 18;
            for(int i = 0; i< 10 ;i++){
                if(RegTable.get(Dirty).Type == UsageType.Stable)
                    Dirty = (Dirty+1) %10 + 18;
            }
            RegUsage Di = RegTable.get(Dirty);
            StackBottom -= 4;
            SCode Store = new SCode("sw",Di.RegName,"sp",StackBottom,0);
            CurBlock.CodeList.add(Store);
            VarPos.replace(Di .UserName,StackBottom);
            Di .UserName = User;
            Di .Type = UsageType.Stable;
            if(!Objects.equals(User, "")) VarPos.put(User,Di.Serial);
            return Di.RegName;
        }
    }
    void RegDistable(String Reg){
        RegTable.get(RegSerial.get(Reg)).Type = UsageType.Busy;
    }
    IRFunc FindFunc(String FuncName){
        for(IRModule module:ModuleList) {
            if (module.FindFunc(FuncName) != null) return module.FindFunc(FuncName);
         //   else if (module.FindFunc(module.getName() + "." + FuncName) != null)
         //       return module.FindFunc(module.getName() + "." + FuncName);
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
        if(Objects.equals(irBlock.getLabel(), "If-True-Stmt13")){
            int sd = 0 ;
        }
        for(BaseInstr Instr : InstrList){
            ++InstrLine;
            if(Instr instanceof AllocaInstr NewIRAlloc){
                StackBottom -= IRTypeToSize(NewIRAlloc.Type);
        //        StackTable.put(StackBottom, NewIRAlloc.Rd);
                Address NewAddr = new Address("sp",StackBottom);
                FuncVarPos.put(NewIRAlloc.Rd, NewAddr);
            }
            else if(Instr instanceof LoadInstr NewIRLoad){
                String RegName = RegDistribute(NewIRLoad.Rd,FuncVarPos,"",NewBlockCode);
                String Op;
                int Offset;
                Op = "lw";
                if(NewIRLoad.IsPtrGlobal){
                    UCode NewLui = new UCode("lui",RegName,"%hi("+NewIRLoad.RsPtr+")",InstrLine);
                    ICode NewAddi = new ICode("addi",RegName,RegName,"%lo("+NewIRLoad.RsPtr+")",InstrLine);
                 //   LCode NewLoad = new LCode(Op,RegName,RegName,0,InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                 //   NewBlockCode.CodeList.add(NewLoad);
                }
                else{
                    Address Addr = FuncVarPos.get(NewIRLoad.RsPtr);
                    String Ptr;
              //      System.out.println(InstrLine);
                    if(Addr.Offset>0) {
                        Ptr =  Addr.AimReg;
                        Offset = 0;
                        LCode NewLoad = new LCode(Op,RegName,Ptr, Offset,InstrLine);
                        NewBlockCode.CodeList.add(NewLoad);
                    }
                    else{
                        LCode NewPtrLoad = new LCode(Op,RegName,Addr.AimReg,Addr.Offset ,InstrLine);
                  //      LCode NewLoad = new LCode(Op,RegName,RegName, 0,InstrLine);
                        NewBlockCode.CodeList.add(NewPtrLoad);
                   //     NewBlockCode.CodeList.add(NewLoad);
                    }
                 //   RegFree(Ptr);
                }
            }
            else if(Instr instanceof OperationInstr NewIROp){
                String Op;

                if(NewIROp.IsRsImm1 || NewIROp.IsRsImm2) {
                    String Imm = NewIROp.IsRsImm1 ? NewIROp.Rs1 : NewIROp.Rs2;
                    String ImmUser;
                    if(NewIROp.IsRsImm1 ^ NewIROp.IsRsImm2){
                        ImmUser = "";
                    }
                    else ImmUser = NewIROp.Rd;
                    String ImmRd = RegDistribute(ImmUser, FuncVarPos,"",NewBlockCode);
                    String Rs;
                    int IntImm = Integer.parseInt(Imm);
                    if(IntImm >= 4096){
                        UCode NewLui = new UCode("lui",ImmRd,Integer.toString(IntImm >> 12),InstrLine);
                        ICode NewLi = new ICode("addi", ImmRd, ZeroRegister, Integer.toString(IntImm & 4095), InstrLine);
                        NewBlockCode.CodeList.add(NewLui);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    else {
                        ICode NewLi = new ICode("addi", ImmRd, ZeroRegister, Imm, InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    if(NewIROp.IsRsImm1 ^ NewIROp.IsRsImm2) {
                        Address Pos = NewIROp.IsRsImm1 ? FuncVarPos.get(NewIROp.Rs2) : FuncVarPos.get(NewIROp.Rs1);
                        if (Pos.Offset > 0) Rs = Pos.AimReg;
                        else {
                            Rs = RegDistribute(NewIROp.Rd, FuncVarPos, "", NewBlockCode);
                            LCode NewRsLoad = new LCode("lw", Rs, Pos.AimReg, Pos.Offset, InstrLine);
                            NewBlockCode.CodeList.add(NewRsLoad);
                        }
                  //      RegFree(Rs);

                        String Rd = RegDistribute(NewIROp.Rd,FuncVarPos,"",NewBlockCode);
                        if(Objects.equals(NewIROp.Op, "icmp")){
                            if(Objects.equals(NewIROp.Mode, "ult")) {
                                Op = "slt";
                                RCode NewOp = new RCode(Op, Rd,Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                            }
                            else if(Objects.equals(NewIROp.Mode, "ugt")){
                                Op = "slt";
                                String Tmp;
                                Tmp  = Rs;
                                Rs = ImmRd;
                                ImmRd = Tmp;
                                RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                            }
                            else if(Objects.equals(NewIROp.Mode, "uge")){
                                Op = "slt";
                                RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                ICode NewXor = new ICode("xori",Rd,Rd,"1",InstrLine);
                                NewBlockCode.CodeList.add(NewXor);
                            }
                            else if(Objects.equals(NewIROp.Mode, "ule")){
                                Op = "slt";
                                String Tmp;
                                Tmp  = Rs;
                                Rs = ImmRd;
                                ImmRd = Tmp;
                                RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                ICode NewXor = new ICode("xori",Rd,Rd,"1",InstrLine);
                                NewBlockCode.CodeList.add(NewXor);
                            }
                            else if(Objects.equals(NewIROp.Mode, "eq")){
                                Op = "sub";
                                RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                UCode NewSeqz = new UCode("seqz",Rd,Rd,InstrLine);
                                NewBlockCode.CodeList.add(NewSeqz);
                            }
                            else{
                                Op = "sub";
                                RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                                NewBlockCode.CodeList.add(NewOp);
                                UCode NewSnez = new UCode("snez",Rd,Rd,InstrLine);
                                NewBlockCode.CodeList.add(NewSnez);

                            }
                        }
                        else {
                            Op = NewIROp.Op;
                            RCode NewOp = new RCode(Op, Rd, Rs, ImmRd, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                    }
                  //  RegFree(ImmRd);
                }
                else {
                    String IRRs1 = NewIROp.Rs1;
                    Address Pos = FuncVarPos.get(IRRs1);
                    String Rs1;
                    if(Pos.Offset>0) Rs1 =  Pos.AimReg;
                    else{
                        Rs1 = RegDistribute(NewIROp.Rd,FuncVarPos,"",NewBlockCode);
                        LCode NewRsLoad = new LCode("lw",Rs1,Pos.AimReg,Pos.Offset,InstrLine);
                        NewBlockCode.CodeList.add(NewRsLoad);
                    }
                    String IRRs2 = NewIROp.Rs2;
                    Pos = FuncVarPos.get(IRRs2);
                    String Rs2;
                    if(Pos.Offset>0) Rs2 =  Pos.AimReg;
                    else{
                        Rs2 = RegDistribute(NewIROp.Rd,FuncVarPos,"",NewBlockCode);
                        LCode NewRsLoad = new LCode("lw",Rs2,Pos.AimReg, Pos.Offset,InstrLine);
                        NewBlockCode.CodeList.add(NewRsLoad);
                    }
                  //  RegFree(Rs1);
                  //  RegFree(Rs2);
                    String Rd = RegDistribute(NewIROp.Rd,FuncVarPos,"",NewBlockCode);
                    if(Objects.equals(NewIROp.Op, "icmp")){
                        if(Objects.equals(NewIROp.Mode, "ult")){
                            Op = "slt";
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                        else if(Objects.equals(NewIROp.Mode, "ugt")){
                            Op = "slt";
                            String Tmp;
                            Tmp  = Rs1;
                            Rs1 = Rs2;
                            Rs2 = Tmp;
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                        }
                        else if(Objects.equals(NewIROp.Mode, "uge")){
                            Op = "slt";
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            ICode NewXor = new ICode("xori",Rd,Rd,"1",InstrLine);
                            NewBlockCode.CodeList.add(NewXor);
                        }
                        else if(Objects.equals(NewIROp.Mode, "ule")){
                            Op = "slt";
                            String Tmp;
                            Tmp  = Rs1;
                            Rs1 = Rs2;
                            Rs2 = Tmp;
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            ICode NewXor = new ICode("xori",Rd,Rd,"1",InstrLine);
                            NewBlockCode.CodeList.add(NewXor);
                        }
                        else if(Objects.equals(NewIROp.Mode, "eq")){
                            Op = "sub";
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            UCode NewSeqz = new UCode("seqz",Rd,Rd,InstrLine);
                            NewBlockCode.CodeList.add(NewSeqz);
                        }
                        else{
                            Op = "sub";
                            RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                            NewBlockCode.CodeList.add(NewOp);
                            UCode NewSnez = new UCode("snez",Rd,Rd,InstrLine);
                            NewBlockCode.CodeList.add(NewSnez);

                        }
                    }
                    else {
                        Op = NewIROp.Op;
                        RCode NewOp = new RCode(Op, Rd, Rs1, Rs2, InstrLine);
                        NewBlockCode.CodeList.add(NewOp);
                    }
                }
            }
            else if(Instr instanceof StoreInstr NewIRStore){
                String Op;
                String Ptr;
                String Rs;
                int Offset;
                Op = "sw";
                if(NewIRStore.IsPtrGlobal){
                    String PtrRd = RegDistribute(NewIRStore.Ptr, FuncVarPos,"",NewBlockCode);
                    UCode NewLui = new UCode("lui",PtrRd ,"%hi("+NewIRStore.Ptr+")",InstrLine);
                    ICode NewAddi = new ICode("addi",PtrRd ,PtrRd ,"%lo("+NewIRStore.Ptr+")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    Ptr = PtrRd ;
                    Offset = 0;
                }
                else{
                    Address Pos = FuncVarPos.get(NewIRStore.Ptr);
                    if(Pos.Offset>0){
                        Ptr =  Pos.AimReg;
                        Offset = 0;
                    }
                    else {
                        Ptr = StackPointerName;
                        Offset = Pos.Offset;
                    }

                }
                    if(FuncVarPos.containsKey(NewIRStore.Rs)) {
                        Address Pos = FuncVarPos.get(NewIRStore.Rs);
                        if (Pos.Offset> 0) Rs = Pos.AimReg;
                        else {
                            Rs = RegDistribute(NewIRStore.Rs, FuncVarPos,"",NewBlockCode);
                            LCode NewRsLoad = new LCode("lw", Rs, Pos.AimReg, Pos.Offset,InstrLine);
                            NewBlockCode.CodeList.add(NewRsLoad);
                        }
                    }
                    else{
                        if(NewIRStore.IsRsGlobal){
                            String PtrRd = RegDistribute(NewIRStore.Rs, FuncVarPos,"",NewBlockCode);
                            UCode NewLui = new UCode("lui",PtrRd ,"%hi("+NewIRStore.Rs+")",InstrLine);
                            ICode NewAddi = new ICode("addi",PtrRd ,PtrRd ,"%lo("+NewIRStore.Rs+")",InstrLine);
                            NewBlockCode.CodeList.add(NewLui);
                            NewBlockCode.CodeList.add(NewAddi);
                            Rs = PtrRd ;
                        }
                        else {
                            Integer Pos = CurFuncVariables.get(NewIRStore.Rs);
                            if (Pos > 0) Rs = RegTable.get(Pos).RegName;
                            else {
                                Rs = RegDistribute(NewIRStore.Rs, FuncVarPos, "", NewBlockCode);
                                int RsOffset = -Pos;
                                LCode NewRsLoad = new LCode("lw", Rs, StackPointerName, RsOffset, InstrLine);
                                NewBlockCode.CodeList.add(NewRsLoad);
                            }
                        }
                    }
                SCode NewStore = new SCode(Op,Rs,Ptr, Offset,InstrLine);
                NewBlockCode.CodeList.add(NewStore);
                if(Offset!=0){
                    Address NewAddr = new Address("sp",Offset);
                    FuncVarPos.replace(NewIRStore.Rs,NewAddr);
                }
                else{
                    Address NewAddr = new Address(Ptr,Offset);
                    FuncVarPos.replace(NewIRStore.Rs,NewAddr);
                }
                RegFree(Rs);
          //      RegFree(Ptr);
            }
            else if(Instr instanceof GetelementInstr NewIRGet) {
                String Rs;
                Integer RdSize = IRTypeToSize(NewIRGet.RdType);
                if (NewIRGet.IsPtrGlobal) {
                    String PtrRd = RegDistribute(NewIRGet.Ptr, FuncVarPos,"",NewBlockCode);
                    UCode NewLui = new UCode("lui", PtrRd, "%hi(" + NewIRGet.Ptr + ")",InstrLine);
                    ICode NewAddi = new ICode("addi", PtrRd, PtrRd, "%lo(" + NewIRGet.Ptr + ")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    if(!Objects.equals(NewIRGet.RdType, "i8**")) {
                        ICode NewFiAddi = new ICode("addi", PtrRd, PtrRd, "4", InstrLine);
                        NewBlockCode.CodeList.add(NewFiAddi);
                    }
                    Rs = PtrRd;
                } else {
                    Address Pos = FuncVarPos.get(NewIRGet.Ptr);
                    if (Pos.Offset > 0) Rs =  Pos.AimReg;
                    else {
                        Rs = RegDistribute(NewIRGet.Ptr, FuncVarPos,"",NewBlockCode);
                        LCode NewRsLoad = new LCode("lw", Rs, Pos.AimReg, Pos.Offset,InstrLine);
                        NewBlockCode.CodeList.add(NewRsLoad);
                    }
                }
                String ImmRd = RegDistribute("",FuncVarPos,"",NewBlockCode);
                if(Objects.equals(NewIRGet.Mode, "index")) {
                    String IndexRd;
                    Address Pos = FuncVarPos.get(NewIRGet.Index);
                    if (Pos.Offset > 0) IndexRd = Pos.AimReg;
                    else {
                        IndexRd = RegDistribute("", FuncVarPos, "", NewBlockCode);
                        LCode NewIndexLoad = new LCode("lw", IndexRd, Pos.AimReg, Pos.Offset, InstrLine);
                        NewBlockCode.CodeList.add(NewIndexLoad);
                    }
                    String TmpRd = RegDistribute("",FuncVarPos,"",NewBlockCode);
                    ICode NewLi = new ICode("addi",TmpRd,"zero", RdSize.toString(),InstrLine);
                    ICode NewMuli = new ICode("mul", IndexRd, IndexRd, TmpRd, InstrLine);
                    ICode NewAddi = new ICode("addi", IndexRd, IndexRd, "4", InstrLine);
                    RCode NewAdd = new RCode("addi", ImmRd, IndexRd, "0", InstrLine);
                    RegFree(TmpRd);
                    RegFree(IndexRd);
                    NewBlockCode.CodeList.add(NewLi);
                    NewBlockCode.CodeList.add(NewMuli);
                    NewBlockCode.CodeList.add(NewAddi);
                    NewBlockCode.CodeList.add(NewAdd);
                }
                else if(Objects.equals(NewIRGet.Mode, "offset")){
                        ICode NewOffAddi = new ICode("addi", ImmRd, "zero", NewIRGet.Offset.toString(), InstrLine);
                        NewBlockCode.CodeList.add(NewOffAddi);
                }
               // RegFree(Rs);
                RegFree(ImmRd);
                String Rd = RegDistribute(NewIRGet.Rd, FuncVarPos,"",NewBlockCode);
                RCode NewPtrAdd = new RCode("add", Rd,Rs,ImmRd,InstrLine);
                NewBlockCode.CodeList.add(NewPtrAdd);
            }
            else if(Instr instanceof BranchInstr NewIRBr){
                if(Objects.equals(NewIRBr.Condition, "")){
                    JCode NewJump = new JCode("j", NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewJump);
                }
                else {

                    String CondiRd;
                    Address Pos = FuncVarPos.get(NewIRBr.Condition);
                    if (Pos.Offset > 0) CondiRd =  Pos.AimReg;
                    else {
                        CondiRd = RegDistribute("", FuncVarPos, "",NewBlockCode);
                        LCode NewIndexLoad = new LCode("lw", CondiRd, Pos.AimReg, Pos.Offset,InstrLine);
                        NewBlockCode.CodeList.add(NewIndexLoad);
                    }
                    BCode NewBrT = new BCode("beq", CondiRd, ZeroRegister, NewIRBr.Label2,InstrLine);
                    JCode NewBrF = new JCode("j", NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewBrT);
                    NewBlockCode.CodeList.add(NewBrF);
                //    RegFree(CondiRd);
                }
            }
            else if(Instr instanceof FuncCallInstr NewIRCall){
                List<String> CallParams = NewIRCall.Param;
                List<IRValue> FuncParams =  FindFunc(NewIRCall.FuncName).getParamList();
                HashMap<String, Integer> FuncParam = AllFuncParams.get(NewIRCall.FuncName);
                for(int i = 0 ; i < CallParams.size();i++) {
                    String CallParamName = CallParams.get(i);
                    Integer AimPos = FuncParam.get(FuncParams.get(i).getName());
                   Address Pos = FuncVarPos.get(CallParamName);
                    String Rd;
                    String Rs;
                    if(!NewIRCall.IsGlobal.get(i)) {
                        if (Pos.Offset > 0) Rs =  Pos.AimReg;
                        else {
                            Rs = RegDistribute("", FuncVarPos, "", NewBlockCode);
                            LCode NewIndexLoad = new LCode("lw", Rs, Pos.AimReg, Pos.Offset, InstrLine);
                            NewBlockCode.CodeList.add(NewIndexLoad);
                        }
                    }
                    else{
                        String PtrRd = RegDistribute(CallParamName, FuncVarPos,"",NewBlockCode);
                        UCode NewLui = new UCode("lui", PtrRd, "%hi(" + CallParamName + ")",InstrLine);
                        ICode NewAddi = new ICode("addi", PtrRd, PtrRd, "%lo(" + CallParamName + ")",InstrLine);
                        NewBlockCode.CodeList.add(NewLui);
                        NewBlockCode.CodeList.add(NewAddi);
                        Rs = PtrRd;
                    }
                    if (AimPos > 0){
                        Rd = RegTable.get(AimPos).RegName;
                        ICode NewAddi = new ICode("addi", Rd, Rs, "0",InstrLine);
                        NewBlockCode.CodeList.add(NewAddi);
                    }
                    else {
                        int Offset = StackBottom-AimPos;
                        SCode NewIndexLoad = new SCode("sw", Rs, StackPointerName, Offset,InstrLine);
                        StackBottom -= 4;
                        NewBlockCode.CodeList.add(NewIndexLoad);
                    }
                    //RegFree(Rs);
                }
                //TempInStack(FuncVarPos,NewBlockCode);
                CCode NewCall = new CCode("call", NewIRCall.FuncName,InstrLine);
                NewBlockCode.CodeList.add(NewCall);
                if(!Objects.equals(NewIRCall.Rd, "")){
                    String Ret = RegDistribute(NewIRCall.Rd,FuncVarPos,"",NewBlockCode);
                    ICode NewAddi = new ICode("addi", Ret, "a0", "0",InstrLine);
                    NewBlockCode.CodeList.add(NewAddi);
                    RegFree("a0");
                }
           //     TempOutStack(InStackTemp,FuncVarPos,NewBlockCode);
            }
            else if(Instr instanceof PhiInstr NewIRPhi){
                if(Objects.equals(NewIRPhi.Mode, "start")) RegDistribute(NewIRPhi.Rd,FuncVarPos,"",NewBlockCode);
                else{
                     //   RegDistable(RegTable.get(FuncVarPos.get(NewIRPhi.Rd)).RegName);
                }
            }
            else if(Instr instanceof ReturnInstr NewIRRet){
                //和call一样，需要交流
                if(Objects.equals(NewIRRet.Type, "void")) break;
                String Rs;
                Address Pos = FuncVarPos.get(NewIRRet.Rs);
                if (Pos.Offset > 0) Rs =  Pos.AimReg;
                else {
                    Rs = RegDistribute(NewIRRet.Rs, FuncVarPos,"",NewBlockCode);
                    LCode NewIndexLoad = new LCode("lw", Rs, Pos.AimReg, Pos.Offset,InstrLine);
                    NewBlockCode.CodeList.add(NewIndexLoad);
                }
                String Rd = RegDistribute(".return",FuncVarPos,"a0",NewBlockCode);
                ICode NewAddi = new ICode("addi",Rd,Rs,"0",InstrLine);
                NewBlockCode.CodeList.add(NewAddi);
            }
        }
        func.BlocksCode.add(NewBlockCode);
        for(IRBlock Son :irBlock.getSubBlocks()) BlockGen(Son,func);
    }
}
