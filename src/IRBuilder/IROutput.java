package IRBuilder;

import ASTNode.*;
import org.antlr.v4.runtime.misc.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class IROutput {
    List<IRModule> ModuleList;
    IRModule GlobalModule;
    IRModule CurModule;
    FileWriter LlvmWriter;
    IRFunc InitFunc;
    IRBlock CurBlock;
    List<BaseInstr> CurList;
    //TODO 进入新函数清零
    Integer RegCnt;
    //先分配再打印
    void FileRun(String Filename){
        //类中函数会有一个隐藏的指针
        //函数中全为临时变量
        //在这个函数中从头做到底？
        //解决作用域问题：生成新的block

        try {
            //将 全局 变量 的 指针 置为 "-";
            LlvmWriter = new FileWriter(Filename);
            IRModule Global = ModuleList.get(0);
            VarTableOutput(Global);
            List<IRFunc> FuncSet = Global.getFuncSet();
            for(IRFunc irFunc : FuncSet) {
                //塞内建类和内建函数
                if (Objects.equals(irFunc.getFuncName(), "main")) {
                    FunctionOutput(Global.getName(), irFunc);
                    break;
                }
            }
        }catch(Exception E){
            System.out.println(".");
        }
    }

    void VarTableRun(IRModule irModule) throws IOException {
        //分配和标记
            HashMap<String,IRVarPair> VarTable = irModule.getVarTable();
            if(Objects.equals(irModule.getName(), "_global")){
                for(Entry<String,IRVarPair> irVarPairEntry : VarTable.entrySet()){
                    IRVarPair irVarPair = irVarPairEntry.getValue();
                    String GlobalVar = "@" + irVarPair.getName() + " = " + irVarPair.getIRType() + " 0\n";
                    LlvmWriter.write(GlobalVar);
                    //init 函数
                    InitFunc.Start.InsertExpr(irVarPair.Expr);
                }
            }
            else{
                String ClassName = irModule.getName();
                LlvmWriter.write("%"+ClassName+" = " + "type");
                LlvmWriter.write(" {\n ");
                for(Entry<String,IRVarPair> irVarPairEntry : VarTable.entrySet()){
                    IRVarPair irVarPair = irVarPairEntry.getValue();
                    LlvmWriter.write(irVarPair.getIRType()+"\n");
                    //相对下标
                }
                LlvmWriter.write(" }\n ");
            }

    }
    void FunctionOutput(String ModuleName, IRFunc irFunc) throws IOException {
        LlvmWriter.write("define " + ModuleName +"." + irFunc.getIrRetType() + " @" + irFunc.getFuncName()+"{\n");
        List<IRBlock> SubBlocks = irFunc.Start.SubBlocks;

        for(IRBlock irBlock : SubBlocks) BlockRun(irBlock);
        LlvmWriter.write("}\n");
    }

    void BlockRun(IRBlock irBlock) throws IOException {
        LlvmWriter.write(irBlock.Label + ":\n");
        //TODO 先alloc 处理
        for(Entry<String,IRVarPair> entry : irBlock.VarList.entrySet()){
            CurList = irBlock.VarInstrList;
            VarRun(entry.getValue());
        }
        for(IRInstrPair Pair : irBlock.ExprInstrList){
            CurList = Pair.IRInstrList;
            ExprToInstr(Pair.Expr);
        }
        //TODO 还有Break Continue Return 等条件判断呢
        //即处理终末instr
    }

    void VarRun(IRVarPair Pair){
        //还有alloc 并分配 Index 还有下标 Reg
        String Ptr = RegCnt.toString();
        Pair.PtrReg = Ptr;
        AllocaInstr Alloca = new AllocaInstr(Ptr,Pair.Type);
        CurList.add(Alloca);
        ++RegCnt;
    }

    IRVarPair FindVar(IRBlock irBlock, String Label,IRModule curModule){
        IRBlock TempBlock = irBlock;
        while(TempBlock.Father != null){
            if(TempBlock.VarList.containsKey(Label)) return TempBlock.VarList.get(Label);
            else TempBlock = TempBlock.Father;
        }
        if(TempBlock.VarList.containsKey(Label)) return TempBlock.VarList.get(Label);
        else{
            //当前类和全局
            if(curModule.VarTable.containsKey(Label)) return curModule.VarTable.get(Label);
            else return GlobalModule.VarTable.get(Label);
        }
    }

    FuncPair FindFunc(String Label,IRModule curModule){
        //先找当前模块，然后找全局

    }

    //遇到再新建
    List<RetPair> ExprToInstr(ExprAST Expr){
        RetPair BinaryRetPair = new RetPair("","");
        List<RetPair> RetList = new ArrayList<>();
        RetList.add(BinaryRetPair);
        if(Expr == null) return RetList;
        List<RetPair> LeftList = ExprToInstr(Expr.getLeftSonExpr());
        List<RetPair> RightList =  ExprToInstr(Expr.getRightSonExpr());
        RetPair Left = LeftList.get(0);
        RetPair Right = RightList.get(0);
        if(Expr.getType() == ExprType.Label){
            LabelAST Label = (LabelAST) Expr;
            IRVarPair Pair = FindVar(CurBlock,Label.getId(),CurModule);
            if(Objects.equals(Pair.PtrReg, "-"))  RetList.set(0,new RetPair("@"+Pair.Name, Pair.Type));
            else{
                //从内存取
                String Ret = RegCnt.toString();
                LoadInstr Load = new LoadInstr(Ret, Pair.Type, Pair.PtrReg, Pair.Type+"*");
                CurList.add(Load);
                ++RegCnt;
                RetList.set(0,new RetPair("%"+Ret, Pair.Type)) ;
            }
            return RetList;
        }
        else if(Expr.getType() == ExprType.FuncCall){
            //TODO 调用时传入this指针
            String Ret;
            FuncPair TargetFunc =  FindFunc(Left.Name,CurModule);
            List<String> Param = new ArrayList<>();
            List<String> ParamType = new ArrayList<>();
            if(!Objects.equals(TargetFunc.Func.RetType, "void")){
                ++RegCnt;
                Ret = RegCnt.toString();
            }
            else Ret = "";
            if(!Objects.equals(TargetFunc.ModuleName,"_global")){
                IRVarPair This = CurModule.VarTable.get("this");
                Param.add("%"+This.PtrReg);
                ParamType.add("%" + CurModule.Name + "*");
            }
            for(RetPair Pair : RightList){
                Param.add(Pair.Name);
                ParamType.add(Pair.Type);
            }
            FuncCallInstr FuncCall = new FuncCallInstr(Ret,TargetFunc.Func.RetType,Param,ParamType);
            CurList.add(FuncCall);
            RetList.set(0,new RetPair(Ret,TargetFunc.Func.RetType));
            return RetList;
        }
        else if(Expr.getType() == ExprType.ExprList){
            ExprListAST ExprListNode = (ExprListAST) Expr;
            for(ExprAST expr : ExprListNode.getExprList()){
                RetPair Pair = ExprToInstr(expr).get(0);
                RetList.add(Pair);
            }
            return RetList;
        }
        else if(Expr.getType() == ExprType.MemCall){

        }
        else if(Expr.getType() == ExprType.Mod){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("srem",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Divide){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("udiv",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Multiply){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("mul",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Minus){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("sub",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Plus){
            //还有字符串加法...
            //翻倍空间吧
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("add",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            return Rd;
        }
        else if(Expr.getType() == ExprType.LeftShift){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("shl",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.RightShift){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("ashr",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.LessThan){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"slt");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.LessThanEqual){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"sle");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.GreaterThan){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"sgt");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.GreaterThanEqual){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"sge");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Equal){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"eq");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.NotEqual){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"ne");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.And){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("and",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Xor){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("xor",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.Or){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("or",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i32"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.AndAnd){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("and",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
        else if(Expr.getType() == ExprType.OrOr){
            String Rd = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("or",Left.Name,Right.Name,Left.Type, Right.Type,Rd,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.set(0,new RetPair(Rd,"i1"));
            return RetList;
        }
    }
}

class RetPair{
    String Name;
    String Type;

    public RetPair(String name, String type) {
        Name = name;
        Type = type;
    }
}

class FuncPair{
    String ModuleName;
    IRFunc Func;
    public FuncPair(String moduleName,IRFunc func){
        ModuleName = moduleName;
        Func = func;
    }
}
