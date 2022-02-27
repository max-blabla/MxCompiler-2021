package CodeGenerator;

import IRBuilder.*;
import CodeGenerator.OpType;

import javax.lang.model.util.AbstractElementVisitor8;
import java.awt.*;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
class Triple{
    RegType Rd;
    RegType Rs1;
    RegType Rs2;
}

//确定一下规范：分配寄存器的时候应当只考虑指针可能有两种，内置或虚，虚要Load出来
public class CodeGenerator {
    Integer VirT0 = 6;
    Integer VirT1 = 7;
    Integer VirZero = 1;
    Integer VirT2 = 8;
    Integer VirSp = 3;
    Integer VirA0  = 11;
    Integer VirNull = 0;
    Integer BuiltinReg = 33;
    //全局变量
    List<IRModule> ModuleList;
    HashMap<String,GlobalSection> GlobalVariables;
    HashMap<String,List<String>> AllFuncParams;
    List<FunctionSection> FunctionsCode;
    List<RegType> TrueReg;
    List<String> TrueRegName;
    IRModule Global;
    Integer Cnt = 0;
    Integer Flag = 0;
    //Func 变量
    List<String> CurFuncParam;
    HashMap<String,Integer> VirName;
    HashMap<Integer,String> InvVirName;
    HashMap<String,Integer> ImmName;
    HashMap<Integer,String> InvImmName;
    HashMap<String,String> LabelToClang;
    HashMap<Integer,String> VirStack;
    Integer StackTop;
    Integer StackBottom;
    Integer BlockCnt = 0;
    Integer FuncCnt = 8;

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
    public void CodeGenerate(){
        GlobalInit();
        for(IRModule module:ModuleList){
            for(IRFunc func : module.getFuncSet()) FuncGen(func);
        }
    }
    List<String> GetVirFuncParam(IRFunc func){
        List<String> FuncParam = new ArrayList<>();
        for(IRValue value : func.getParamList()) FuncParam.add(value.getName());
        return FuncParam;
    }
    String StackPos(Integer VirIndex){
        if(VirStack.containsKey(VirIndex)) return VirStack.get(VirIndex);
        else {
            StackBottom -= 4;
            String Imm = StackBottom.toString();
            VirStack.put(VirIndex,Imm);
            return Imm;
        }
    }
    void TranSection(BlockSection Start, BlockSection End){
        if(StackBottom < -2048){
            UCode Li = new UCode(OpType.li,VirNull,RegType.t0, Integer.toString(StackBottom),0);
            RCode SpDown = new RCode(OpType.add,VirNull,VirNull,VirNull,RegType.sp,RegType.sp,RegType.t0,0);
            Start.CodeList.add(Li);
            Start.CodeList.add(SpDown);
        }
        else{
            ICode SpDown  = new ICode(OpType.addi, VirNull, VirNull,Integer.toString(StackBottom), 0);
            SpDown.Rd = RegType.sp;
            SpDown.Rs = RegType.sp;
            Start.CodeList.add(SpDown);
        }
        SCode RaIn = new SCode(OpType.sw,VirNull,VirNull,RegType.ra,RegType.sp,Integer.toString(-4),0);
        LCode RaOut = new LCode(OpType.lw,VirNull,VirNull,RegType.ra,RegType.sp,Integer.toString(-4),0);
        SCode S0In = new SCode(OpType.sw,VirNull,VirNull,RegType.s0,RegType.sp,Integer.toString(-8),0);
        LCode S0Out = new LCode(OpType.lw,VirNull,VirNull,RegType.s0,RegType.sp,Integer.toString(-8),0);
        SCode S1In = new SCode(OpType.sw,VirNull,VirNull,RegType.s1,RegType.sp,Integer.toString(-12),0);
        LCode S1Out = new LCode(OpType.lw,VirNull,VirNull,RegType.s1,RegType.sp,Integer.toString(-12),0);
        Start.CodeList.add(RaIn);
        Start.CodeList.add(S0In);
        Start.CodeList.add(S1In);
        End.CodeList.add(S1Out);
        End.CodeList.add(S0Out);
        End.CodeList.add(RaOut);
        if(StackBottom < -2048){
            UCode Li = new UCode(OpType.li,VirNull,RegType.t0,Integer.toString(-StackBottom),0);
            RCode SpUp = new RCode(OpType.add,VirNull,VirNull,VirNull,RegType.sp,RegType.sp,RegType.t0,0);
            Start.CodeList.add(Li);
            Start.CodeList.add(SpUp);
        }
        else{
            ICode SpUp  = new  ICode(OpType.addi, VirNull, VirNull,Integer.toString(-StackBottom), 0);
            SpUp.Rd = RegType.sp;
            SpUp.Rs = RegType.sp;
            End.CodeList.add(SpUp);
        }
    }
    void GlobalInit(){
        //设定函数参数 全局变量 以及 找到全局
        GlobalVariables = new HashMap<>();
        AllFuncParams = new HashMap<>();
        FunctionsCode = new ArrayList<>();
        TrueReg = new ArrayList<>(Arrays.asList(
                RegType.NULL,RegType.zero,RegType.ra,RegType.sp,RegType.gp,RegType.tp,RegType.t0,RegType.t1,
                RegType.t2,RegType.s0,RegType.s1,RegType.a0,RegType.a1,RegType.a2,RegType.a3,RegType.a4,
                RegType.a5,RegType.a6,RegType.a7, RegType.s2,RegType.s3,RegType.s4,RegType.s5,RegType.s6,
                RegType.s7,RegType.s8,RegType.s9,RegType.s10,RegType.s11,RegType.t3,RegType.t4,RegType.t5,
                RegType.t6
        ));
        TrueRegName =  new ArrayList<>(Arrays.asList(
                "NullReg","zero","ra","sp","gp","tp","t0","t1","t2","s0","s1","a0","a1","a2","a3","a4",
                "a5","a6","a7","s2","s3","s4","s5","s6","s7","s8","s9","s10","s11","t3","t4","t5","t6"
        ));
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
    void FuncInit(IRFunc irFunc){
        String NullImm = "NullImm";
        StackTop = 0;
        StackBottom = 0;
        ++FuncCnt;
        BlockCnt = 0;
        CurFuncParam = AllFuncParams.get(irFunc.getFuncName());
        VirName = new HashMap<>();
        InvVirName = new HashMap<>();
        ImmName = new HashMap<>();
        InvImmName = new HashMap<>();
        LabelToClang = new HashMap<>();
        ImmName.put(NullImm,0);
        InvImmName.put(0,NullImm);
        for(int i = 0 ; i < BuiltinReg;i++){
            VirName.put(TrueRegName.get(i),i);
            InvVirName.put(i,TrueRegName.get(i));
        }
        for(int i = 0 ; i< CurFuncParam.size();i++){
            VirName.put(CurFuncParam.get(i),BuiltinReg+i);
            InvVirName.put(BuiltinReg+i,CurFuncParam.get(i));
        }
        VirStack = new HashMap<>();
    }
    void FuncGen(IRFunc irFunc){
        if(irFunc.getInline()) return;
        if(irFunc.getLinked()) return;
        FuncInit(irFunc);
        FunctionSection NewFunc = new FunctionSection(irFunc.getFuncName());
        BlockSection Start = new BlockSection("","");
        StackBottom -= 16;
        BlockGen(irFunc.getStart(),NewFunc);
        BlockGen(irFunc.getEnd(),NewFunc);
        NewFunc.BlocksCode = TransCode(NewFunc);
        NewFunc.BlocksCode = RegDistribute(NewFunc.BlocksCode);
        NewFunc.BlocksCode.add(0,Start);
        BlockSection End = NewFunc.BlocksCode.get(NewFunc.BlocksCode.size()-1);
        TranSection(Start,End);
        NewFunc.BlocksCode = ImmFlush(NewFunc,StackTop-StackBottom);
        MCode Ret = new MCode("ret",0);
        NewFunc.BlocksCode.get(NewFunc.BlocksCode.size()-1).CodeList.add(Ret);
        FunctionsCode.add(NewFunc);
    }
    ArrayList<BlockSection> TransCode(FunctionSection Func){
        ArrayList<BlockSection> NewBlocks = new ArrayList<>();
        for(BlockSection Block : Func.BlocksCode) {
            BlockSection NewBlock = new BlockSection(Block.BlockLable, Block.IRBlockLabel);
            for (BaseCode Code : Block.CodeList) {
                if(Code instanceof  RCode){
                    RCode OpCode = (RCode) Code;
                    RCode SubCode = new RCode(OpType.sub,VirT0,OpCode.VirRs1,OpCode.VirRs2,RegType.NULL,RegType.NULL,RegType.NULL,OpCode.Line);
                    switch (OpCode.Op){
                        case seq:{
                            PCode AndCode = new PCode(OpType.seqz,OpCode.VirRd,VirT0,OpCode.Line);
                            NewBlock.CodeList.add(SubCode);
                            NewBlock.CodeList.add(AndCode);
                            break;
                        }
                        case sne:{
                            SubCode.VirRd = OpCode.VirRd;
                            NewBlock.CodeList.add(SubCode);
                            break;
                        }
                        case sge:{
                            RCode SltCode = new RCode(OpType.slt,VirT0,OpCode.VirRs1,OpCode.VirRs2,RegType.NULL,RegType.NULL,RegType.NULL,OpCode.Line);
                            ICode XorCode = new ICode(OpType.xori,OpCode.VirRd,VirT0,"1",OpCode.Line);
                            NewBlock.CodeList.add(SltCode);
                            NewBlock.CodeList.add(XorCode);
                            break;
                        }
                        case sle:{
                            RCode SltCode = new RCode(OpType.slt,VirT0,OpCode.VirRs2,OpCode.VirRs1,RegType.NULL,RegType.NULL,RegType.NULL,OpCode.Line);
                            ICode XorCode = new ICode(OpType.xori,OpCode.VirRd,VirT0,"1",OpCode.Line);
                            NewBlock.CodeList.add(SltCode);
                            NewBlock.CodeList.add(XorCode);
                            break;
                        }
                        case sgt:{
                            RCode SltCode = new RCode(OpType.slt,OpCode.VirRd,OpCode.VirRs2,OpCode.VirRs1,RegType.NULL,RegType.NULL,RegType.NULL,OpCode.Line);
                            NewBlock.CodeList.add(SltCode);
                            break;
                        }
                        default: NewBlock.CodeList.add(Code);
                    }
                }
                else NewBlock.CodeList.add(Code);
            }
            NewBlocks.add(NewBlock);
        }
        return NewBlocks;
    }
    Integer IRTypeToSize(String Type){
        return 4;
    }
    ArrayList<BlockSection> ImmFlush(FunctionSection Func,Integer StackSize){
        ArrayList<BlockSection> NewBlocks = new ArrayList<>();
        for(BlockSection Block : Func.BlocksCode){
            BlockSection NewBlock = new BlockSection(Block.BlockLable,Block.IRBlockLabel);
            for(BaseCode Code : Block.CodeList){
                if(Code instanceof ICode){
                    ICode ImmCode = (ICode) Code;
                    if(ImmCode.Rs == RegType.sp && ImmCode.Rd != RegType.sp){
                        Integer Offset = Integer.parseInt(ImmCode.Imm)+StackSize;
                        if(Offset > 2047){
                            UCode NewLi = new UCode(OpType.li,VirNull,RegType.t0, Integer.toString(Offset),ImmCode.Line);
                            RCode NewAdd = new RCode(OpType.add,VirNull,VirNull,VirNull,ImmCode.Rd,RegType.t0,RegType.sp,ImmCode.Line);
                            NewBlock.CodeList.add(NewLi);
                            NewBlock.CodeList.add(NewAdd);
                        }
                        else {
                            ImmCode.Imm = Integer.toString(Offset);
                            NewBlock.CodeList.add(ImmCode);
                        }
                    }
                    else   NewBlock.CodeList.add(ImmCode);

                }
                else if(Code instanceof SCode){
                    SCode StCode = (SCode) Code;
                    if(StCode.Rs2 == RegType.sp){
                        int Offset = Integer.parseInt(StCode.Imm)+StackSize;
                        if(Offset > 2047){
                            UCode NewLi = new UCode(OpType.li,VirNull,RegType.t0, Integer.toString(Offset),StCode.Line);
                            RCode NewAdd = new RCode(OpType.add,VirNull,VirNull,VirNull,RegType.t0,RegType.t0,RegType.sp,StCode.Line);
                            NewBlock.CodeList.add(NewLi);
                            NewBlock.CodeList.add(NewAdd);
                            StCode.Rs2 = RegType.t0;
                            StCode.Imm = "0";
                        }
                        else StCode.Imm = Integer.toString(Offset);
                    }
                    NewBlock.CodeList.add(StCode);
                }
                else if(Code instanceof LCode){
                    LCode LdCode = (LCode) Code;
                    if(LdCode.Rs == RegType.sp){
                        int Offset = Integer.parseInt(LdCode.Imm)+StackSize;
                        if(Offset > 2047){
                            UCode NewLi = new UCode(OpType.li,VirNull,RegType.t0, Integer.toString(Offset),LdCode.Line);
                            RCode NewAdd = new RCode(OpType.add,VirNull,VirNull,VirNull,RegType.t0,RegType.t0,RegType.sp,LdCode.Line);
                            NewBlock.CodeList.add(NewLi);
                            NewBlock.CodeList.add(NewAdd);
                            LdCode.Rs = RegType.t0;
                            LdCode.Imm = "0";
                        }
                        else LdCode.Imm = Integer.toString(Offset);
                    }
                    NewBlock.CodeList.add(LdCode);
                }
                else NewBlock.CodeList.add(Code);
            }
            NewBlocks.add(NewBlock);
        }
        return NewBlocks;
    }
    Triple TrueReg(Integer Rd,Integer Rs1, Integer Rs2,BaseCode Self, BlockSection Block){
        Triple NewTriple = new Triple();
        if(Rs1 > 0 && Rs1 < BuiltinReg) NewTriple.Rs1 = TrueReg.get(Rs1);
        else if(Rs1 != 0) {
            if (Rs1 - BuiltinReg < CurFuncParam.size()) {
                int ParamIndex = Rs1 - BuiltinReg;
                if (ParamIndex <= 8) NewTriple.Rs1 = TrueReg.get(11 + ParamIndex);
                else {
                    LCode Load = new LCode(OpType.lw, VirNull, VirNull, RegType.t0, RegType.sp,
                            Integer.toString(4 * (CurFuncParam.size() - ParamIndex)), Self.Line);
                    Block.CodeList.add(Load);
                }
            } else {
                NewTriple.Rs1 = RegType.s0;
                LCode Load = new LCode(OpType.lw, VirNull, VirNull, RegType.s0, RegType.sp,
                        StackPos(Rs1), Self.Line);
                Block.CodeList.add(Load);
            }
        }
        if(Rs2>0 && Rs2 < BuiltinReg) NewTriple.Rs2 = TrueReg.get(Rs2);
        else if(Rs2 !=0 ){
            NewTriple.Rs2 = RegType.s1;
            LCode Load =new LCode(OpType.lw,VirNull,VirNull,RegType.s1,RegType.sp, StackPos(Rs2),Self.Line);
            Block.CodeList.add(Load);
        }
        Block.CodeList.add(Self);
        if(Rd > 0 && Rd < BuiltinReg ) NewTriple.Rd = TrueReg.get(Rd);
        else if(Rd < 0) {
            int ParamIndex = Integer.parseInt(InvVirName.get(Rd).substring(6));
            if (ParamIndex < 8) NewTriple.Rd = TrueReg.get(10 + ParamIndex);
            else {
                SCode Store = new SCode(OpType.sw, VirNull, VirNull, RegType.t0, RegType.sp,
                        StackPos(Rd), Self.Line);
                NewTriple.Rd = RegType.t0;
                Block.CodeList.add(Store);
            }
        }
        else if(Rd != 0){
            NewTriple.Rd = RegType.s0;
            SCode Store = new SCode(OpType.sw,VirNull,VirNull, RegType.s0,RegType.sp,StackPos(Rd),Self.Line);
            Block.CodeList.add(Store);
        }
        return NewTriple;
    }
    ArrayList<BlockSection> RegDistribute(List<BlockSection> BlockList){
        ArrayList<BlockSection> NewBlockList = new ArrayList<>();
        for(BlockSection Block : BlockList){
            BlockSection NewBlock = new BlockSection(Block.BlockLable,Block.IRBlockLabel);
            for(BaseCode Code :Block.CodeList){
                if(Code instanceof ICode) {
                    ICode ImmCode = (ICode) Code;
                    ImmCode.SetTrue(TrueReg(ImmCode.VirRd,ImmCode.VirRs,VirNull,Code,NewBlock));
                }
                else if(Code instanceof SCode){
                    SCode StoreCode = (SCode) Code;
                    StoreCode.SetTrue(TrueReg(VirNull,StoreCode.VirRs1,StoreCode.VirRs2,Code,NewBlock));
                }
                else if(Code instanceof LCode){
                    LCode LoadCode = (LCode) Code;
                    LoadCode.SetTrue(TrueReg(LoadCode.VirRd,LoadCode.VirRs,VirNull,Code,NewBlock));
                }
                else if(Code instanceof RCode){
                   RCode OpCode = (RCode) Code;
                   OpCode.SetTrue(TrueReg(OpCode.VirRd,OpCode.VirRs1,OpCode.VirRs2,Code,NewBlock));
                }
                else if(Code instanceof BCode) {
                    BCode BranchCode = (BCode) Code;
                    BranchCode.SetTrue(TrueReg(VirNull,BranchCode.VirRs1,BranchCode.VirRs2,Code,NewBlock));
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
                    JumpCode.Block = LabelToClang.get(JumpCode.Block);
                    NewBlock.CodeList.add(JumpCode);
                }
                else if(Code instanceof UCode){
                    UCode UpperCode = (UCode) Code;
                    UpperCode.SetTrue(TrueReg(UpperCode.VirRd,VirNull,VirNull,Code,NewBlock));
                }
                else if(Code instanceof BPCode){
                    BPCode BrPeCode = (BPCode) Code;
                    BrPeCode.Label = LabelToClang.get(BrPeCode.Label);
                    BrPeCode.SetTrue(TrueReg(VirNull,BrPeCode.VirRs,VirNull,Code,NewBlock));
                }
                else{
                    PCode PesudoCode = (PCode) Code;
                    PesudoCode.SetTrue(TrueReg(PesudoCode.VirRd,PesudoCode.VirRs,VirNull,Code,NewBlock));
                }
            }
            NewBlockList.add(NewBlock);
        }
        return NewBlockList;
    }
    Integer GetVirtualReg(String User){
        if(VirName.containsKey(User)) return VirName.get(User);
        else if(User.startsWith(".param")){
            int ParamIndex = Integer.parseInt(User.substring(6));
            Integer Ret = -ParamIndex;
            InvVirName.put(Ret,User);
            VirName.put(User,Ret);
            return Ret;
        }
        else {
            Integer Ret = VirName.size();
            InvVirName.put(Ret,User);
            VirName.put(User,Ret);
            return Ret;
        }
    }
    void BlockGen(IRBlock irBlock,FunctionSection func){
        if(irBlock == null) return;
        ++BlockCnt;
        String ClangName = ".LBB"+FuncCnt + "_" +BlockCnt;
        LabelToClang.put(irBlock.getLabel(),ClangName);
        BlockSection NewBlockCode = new BlockSection(ClangName,irBlock.getLabel());
        List<BaseInstr> InstrList = irBlock.getVarInstrList();
        InstrList.add(irBlock.getEndInstr());
        int InstrLine = 0;
        for(BaseInstr Instr : InstrList){
            ++InstrLine;
            if(Instr instanceof AllocaInstr){
                AllocaInstr NewIRAlloc = (AllocaInstr) Instr;
                Integer VirRd = GetVirtualReg(NewIRAlloc.Rd);
                StackBottom -= 4;
                ICode Addi = new ICode(OpType.addi,VirT0,VirSp,StackBottom.toString(),InstrLine);
                StackBottom -= 4;
                VirStack.put(VirRd,StackBottom.toString());
                SCode Store = new SCode(OpType.sw,VirT0,VirSp,RegType.NULL,RegType.NULL,StackBottom.toString(),InstrLine);
                NewBlockCode.CodeList.add(Addi);
                NewBlockCode.CodeList.add(Store);
            }
            else if(Instr instanceof LoadInstr){
                LoadInstr NewIRLoad = (LoadInstr) Instr;
                Integer VirRd = GetVirtualReg(NewIRLoad.Rd);
                if(NewIRLoad.IsPtrGlobal){
                    UCode NewLui = new UCode(OpType.lui, VirT0,RegType.NULL, "%hi("+ NewIRLoad.RsPtr+")",InstrLine);
                    ICode NewAddi = new ICode(OpType.addi,VirT0,VirT0,"%lo("+NewIRLoad.RsPtr+")",InstrLine);
                    LCode NewLoad = new LCode(OpType.lw, VirRd,VirT0, RegType.NULL,RegType.NULL,"0",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    NewBlockCode.CodeList.add(NewLoad);
                }
                else {
                    Integer VirPtr = GetVirtualReg(NewIRLoad.RsPtr);
              //      LCode PtrLoad = new LCode(OpType.lw,VirT0,VirPtr,RegType.NULL,RegType.NULL,"0",InstrLine);
                    LCode NewLoad = new LCode(OpType.lw,VirRd,VirPtr,RegType.NULL,RegType.NULL,"0", InstrLine);
              //      NewBlockCode.CodeList.add(PtrLoad);
                    NewBlockCode.CodeList.add(NewLoad);
                }
            }
            else if(Instr instanceof OperationInstr ){
                OperationInstr NewIROp= ( OperationInstr) Instr;
                OpType Op;
                Integer VirRd = GetVirtualReg(NewIROp.Rd);
                Integer VirRs1;
                Integer VirRs2;
                switch (NewIROp.Op){
                    case "icmp":{
                        switch (NewIROp.Mode){
                            case "ne":{
                                Op = OpType.sne;
                                break;
                            }
                            case "uge":{
                                Op = OpType.sge;
                                break;
                            }
                            case "ule":{
                                Op = OpType.sle;
                                break;
                            }
                            case "ult":{
                                Op = OpType.slt;
                                break;
                            }
                            case "ugt":{
                                Op = OpType.sgt;
                                break;
                            }
                            case "eq":{
                                Op = OpType.seq;
                                break;
                            }
                            default:throw new RuntimeException();
                        }
                        break;
                    }
                    case "add":{
                        Op = OpType.add;
                        break;
                    }
                    case "sub":{
                        Op = OpType.sub;
                        break;
                    }
                    case "div":{
                        Op = OpType.div;
                        break;
                    }
                    case "mul":{
                        Op = OpType.mul;
                        break;
                    }
                    case "rem":{
                        Op = OpType.rem;
                        break;
                    }
                    case "xor":{
                        Op = OpType.xor;
                        break;
                    }
                    case "or":{
                        Op = OpType.or;
                        break;
                    }
                    case "and":{
                        Op = OpType.and;
                        break;
                    }
                    case "sll":{
                        Op = OpType.sll;
                        break;
                    }
                    case "sra":{
                        Op = OpType.sra;
                        break;
                    }
                    default:throw new RuntimeException();
                }
                if(NewIROp.IsRsImm1){
                    int Imm1 = Integer.parseInt(NewIROp.Rs1);
                    if(Imm1 > 2047 || Imm1 < -2048) {
                        UCode NewLi = new UCode(OpType.li,VirT0,RegType.NULL,NewIROp.Rs1,InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    else {
                        ICode NewLi = new ICode(OpType.addi, VirT0, VirZero, NewIROp.Rs1, InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    VirRs1  = VirT0;
                }
                else VirRs1 = GetVirtualReg(NewIROp.Rs1);
                if(NewIROp.IsRsImm2){
                    int Imm2 = Integer.parseInt(NewIROp.Rs2);
                    if(Imm2 > 2047 || Imm2 < -2048) {
                        UCode NewLi = new UCode(OpType.li,VirT1,RegType.NULL,NewIROp.Rs2,InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    else {
                        ICode NewLi = new ICode(OpType.addi, VirT1, VirZero, NewIROp.Rs2, InstrLine);
                        NewBlockCode.CodeList.add(NewLi);
                    }
                    VirRs2  = VirT1;
                }
                else VirRs2 = GetVirtualReg(NewIROp.Rs2);
                RCode NewOp = new RCode(Op,VirRd,VirRs1,VirRs2,RegType.NULL,RegType.NULL,RegType.NULL, InstrLine);
                NewBlockCode.CodeList.add(NewOp);
            }
            else if(Instr instanceof StoreInstr){
                StoreInstr NewIRStore = (StoreInstr)  Instr;
                Integer VirPtr;
                Integer VirRs;
                if(NewIRStore.IsPtrGlobal) {
                    UCode NewLui = new UCode(OpType.lui, VirT0, RegType.NULL,"%hi(" + NewIRStore.Ptr + ")", InstrLine);
                    ICode NewAddi = new ICode(OpType.addi, VirT0, VirT0, "%lo(" + NewIRStore.Ptr + ")", InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    VirPtr = VirT0;
                }
                else VirPtr = GetVirtualReg(NewIRStore.Ptr);
                if(NewIRStore.IsRsGlobal){
                    UCode NewLui = new UCode(OpType.lui,VirT1 ,RegType.NULL,"%hi("+NewIRStore.Rs+")",InstrLine);
                    ICode NewAddi = new ICode(OpType.addi,VirT1 ,VirT1 ,"%lo("+NewIRStore.Rs+")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    VirRs = VirT1;
                }
                else VirRs = GetVirtualReg(NewIRStore.Rs);
                SCode NewStore = new SCode(OpType.sw,VirRs,VirPtr,RegType.NULL,RegType.NULL,"0",InstrLine);
                NewBlockCode.CodeList.add(NewStore);
            }
            else if(Instr instanceof GetelementInstr) {
                GetelementInstr NewIRGet = (GetelementInstr)  Instr;

                Integer RdSize = IRTypeToSize(NewIRGet.RdType);
                Integer VirPtr;
                if (NewIRGet.IsPtrGlobal) {
                    UCode NewLui = new UCode(OpType.lui, VirT0,RegType.NULL,"%hi(" + NewIRGet.Ptr + ")",InstrLine);
                    ICode NewAddi = new ICode(OpType.addi, VirT0,  VirT0, "%lo(" + NewIRGet.Ptr + ")",InstrLine);
                    NewBlockCode.CodeList.add(NewLui);
                    NewBlockCode.CodeList.add(NewAddi);
                    VirPtr = VirT0;
                }
                else VirPtr = GetVirtualReg(NewIRGet.Ptr);
                Integer VirRd = GetVirtualReg(NewIRGet.Rd);
                Integer VirIndex = GetVirtualReg(NewIRGet.Index);
                if(Objects.equals(NewIRGet.Mode, "index")) {
                    ICode NewLi = new ICode(OpType.addi,VirT2,VirZero,RdSize.toString(),InstrLine);
                    RCode NewMuli = new RCode(OpType.mul, VirT1, VirIndex, VirT2,RegType.NULL,RegType.NULL,RegType.NULL, InstrLine);
                    ICode NewAddi = new ICode(OpType.addi, VirT2, VirT1, "4", InstrLine);
                    NewBlockCode.CodeList.add(NewLi);
                    NewBlockCode.CodeList.add(NewMuli);
                    NewBlockCode.CodeList.add(NewAddi);
                }
                else if(Objects.equals(NewIRGet.Mode, "offset")){
                        ICode NewOffAddi = new ICode(OpType.addi, VirT2, VirZero, Integer.toString(NewIRGet.Offset/8), InstrLine);
                        NewBlockCode.CodeList.add(NewOffAddi);
                }
                RCode NewPtrAdd = new RCode(OpType.add,VirRd ,VirPtr,VirT2,RegType.NULL,RegType.NULL,RegType.NULL,InstrLine);
                NewBlockCode.CodeList.add(NewPtrAdd);
            }
            else if(Instr instanceof BranchInstr){
                BranchInstr NewIRBr = (BranchInstr)  Instr;
                if(Objects.equals(NewIRBr.Condition, "")){
                    JCode NewJump = new JCode(OpType.j, NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewJump);
                }
                else {
                    Integer VirCondi = GetVirtualReg(NewIRBr.Condition);
                    BPCode NewBrT = new BPCode(OpType.beqz, VirCondi, NewIRBr.Label2,InstrLine);
                    JCode NewBrF = new JCode(OpType.j, NewIRBr.Label1,InstrLine);
                    NewBlockCode.CodeList.add(NewBrT);
                    NewBlockCode.CodeList.add(NewBrF);
                }
            }
            else if(Instr instanceof FuncCallInstr){
                FuncCallInstr NewIRCall = (FuncCallInstr)  Instr;
                List<String> CallParams = NewIRCall.Param;
                for(int i = 0 ; i < CallParams.size();i++) {
                    //TODO 函数寄存器分配
                    //TODO 根本不分配函数参数？我觉得可以有
                    String CallParamName = CallParams.get(i);
                    Integer VirRd = GetVirtualReg(".param"+(i+1));
                    Integer VirRs = GetVirtualReg(CallParamName);
                    if(NewIRCall.IsGlobal.get(i)) {
                        UCode NewLui = new UCode(OpType.lui,VirT0, RegType.NULL,"%hi(" + CallParamName + ")",InstrLine);
                        ICode NewAddi = new ICode(OpType.addi,VirRs, VirT0, "%lo(" + CallParamName + ")",InstrLine);
                        NewBlockCode.CodeList.add(NewLui);
                        NewBlockCode.CodeList.add(NewAddi);
                    }
                    PCode NewMv = new PCode(OpType.mv, VirRd, VirRs,InstrLine);
                    NewBlockCode.CodeList.add(NewMv);
                }
                CCode NewCall = new CCode(OpType.call, NewIRCall.FuncName,InstrLine);
                NewBlockCode.CodeList.add(NewCall);
                if(!Objects.equals(NewIRCall.Rd, "")){
                    Integer VirRd = GetVirtualReg(NewIRCall.Rd);
                    PCode NewMv = new PCode(OpType.mv,VirRd, VirA0,InstrLine);
                    NewBlockCode.CodeList.add(NewMv);
                }
            }
            else if(Instr instanceof PhiInstr ){
            }
            else if(Instr instanceof ReturnInstr){
                //和call一样，需要交流
                ReturnInstr NewIRRet = ( ReturnInstr)  Instr;
                if(Objects.equals(NewIRRet.Type, "void")) break;
                Integer VirRs = GetVirtualReg(NewIRRet.Rs);
                PCode Move = new PCode(OpType.mv,VirA0,VirRs,InstrLine);
                NewBlockCode.CodeList.add(Move);
            }
        }
        func.BlocksCode.add(NewBlockCode);
        for(IRBlock Son :irBlock.getSubBlocks()) BlockGen(Son,func);
    }
}
