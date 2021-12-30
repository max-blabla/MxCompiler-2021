package IRBuilder;

import ASTNode.*;
import com.sun.jdi.Value;
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
            HashMap<String,IRValue> VarTable = irModule.getVarTable();
            if(Objects.equals(irModule.getName(), "_global")){
                for(Entry<String,IRValue> irVarPairEntry : VarTable.entrySet()){
                    IRValue irVarPair = irVarPairEntry.getValue();
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
                for(Entry<String,IRValue> irVarPairEntry : VarTable.entrySet()){
                    IRValue irVarPair = irVarPairEntry.getValue();
                    LlvmWriter.write(irVarPair.getIRType()+"\n");
                    //TODO 分配相对下标
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
        for(Entry<String,IRValue> entry : irBlock.VarList.entrySet()){
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

    void VarRun(IRValue Pair){
        //还有alloc 并分配 Index 还有下标 Reg

    }

    IRValue FindVar(IRBlock irBlock, String Label,IRModule curModule){
        IRBlock TempBlock = irBlock;
        while(TempBlock.Father != null){
            if(TempBlock.VarList.containsKey(Label)) return TempBlock.VarList.get(Label);
            else TempBlock = TempBlock.Father;
        }
        if(TempBlock.VarList.containsKey(Label)) return TempBlock.VarList.get(Label);
        else{
            //当前类和全局
            if(curModule.VarTable.containsKey(Label)) return curModule.VarTable.get(Label);
            else if(GlobalModule.VarTable.containsKey(Label)) return GlobalModule.VarTable.get(Label);
            else{
                IRValue Bad = new IRValue();
                Bad.Type = "Bad";
                Bad.PtrReg = "*Bad";
                return Bad;
            }
        }
    }

    IRFunc FindFunc(String Label,IRModule curModule){
        //先找当前模块，然后找全局
        for(IRFunc irFunc : curModule.FuncSet)  if(Objects.equals(irFunc.FuncName, Label)) return irFunc;
        for(IRFunc irFunc : GlobalModule.FuncSet)  if(Objects.equals(irFunc.FuncName, Label)) return irFunc;
        return null;
    }

    IRModule FindModule(String ModuleName){
        for(IRModule module : ModuleList)  if(Objects.equals(module.Name, ModuleName)) return module;
        return null;
    }

    //遇到再新建
    List<IRValue> ExprToInstr(ExprAST Expr){
        List<IRValue> RetList = new ArrayList<>();
        if(Expr == null) return RetList;
        List<IRValue> LeftList = ExprToInstr(Expr.getLeftSonExpr());
        List<IRValue> RightList =  ExprToInstr(Expr.getRightSonExpr());
        IRValue Left = LeftList.get(0);
        IRValue Right = RightList.get(0);
        //Label FuncCall 和 MemCall 待调整
        if(Expr.getType() == ExprType.Label){
            if(Expr.Father instanceof ExprAST) if(Expr.getType() == ExprType.MemCall) return RetList;
            LabelAST Label = (LabelAST) Expr;
            IRValue Value = FindVar(CurBlock,Label.getId(),CurModule);
            if(Objects.equals(Value.PtrReg, "-"))  RetList.add(0,new RetPair(Value.Name, Value.Type));
            else if(Objects.equals(Value.PtrReg, "*Bad")) RetList.add(new RetPair("%", Value.Type+"*")) ;
            else{
                String Ret = RegCnt.toString();
                LoadInstr Load = new LoadInstr("Load","%"+Ret, Value.Type, Value.PtrReg, Value.Type+"*");
                CurList.add(Load);
                ++RegCnt;
                //可能找不到
                //会多出奇怪的Load指令，但是后来再优化它吧
                RetList.add(new RetPair("%"+Ret, Value.Type)) ;
            }
            RetList.add(new RetPair("",""));
            return RetList;
        }

        else if(Expr.getType() == ExprType.FuncCall) {
            //TODO 调用时传入this指针
            String Ret;
            IRFunc TargetFunc;
            //左儿子为Mem
            List<String> Param = new ArrayList<>();
            List<String> ParamType = new ArrayList<>();
            String FuncName;
            if (!Objects.equals(LeftList.get(1).Type, "")){
                //左儿子为Mem
                LabelAST FuncNameNode = (LabelAST) Expr.getLeftSonExpr().getRightSonExpr();
                String ClassName = LeftList.get(1).Type;
                IRModule TarModule = FindModule(ClassName);
                TargetFunc = FindFunc(FuncNameNode.getId(),TarModule);
                if (!Objects.equals(TargetFunc.RetType, "void")) {
                    ++RegCnt;
                    Ret = RegCnt.toString();
                }
                else Ret = "";
                Param.add(LeftList.get(0).Name);
                ParamType.add("%" + ClassName + "*");
                FuncName = ClassName + "."+TargetFunc.FuncName;
            }
            else {
                //左儿子为普通标签
                LabelAST FuncNameNode = (LabelAST) Expr.getLeftSonExpr();
                TargetFunc = FindFunc(FuncNameNode.getId(),CurModule);
                if (!Objects.equals(TargetFunc.RetType, "void")) {
                    ++RegCnt;
                    Ret = RegCnt.toString();
                }
                else Ret = "";
                //
                if(!Objects.equals(TargetFunc.ModuleName,"_global")){
                    IRValue This = FindVar(CurBlock,"this",CurModule);
                    Param.add(This.PtrReg);
                    ParamType.add("%" + This.Type + "*");
                    FuncName = TargetFunc.ModuleName + "."+TargetFunc.FuncName;
                }
                else FuncName = TargetFunc.ModuleName;
            }
            for(RetPair Pair : RightList){
                Param.add(Pair.Name);
                ParamType.add(Pair.Type);
            }
            FuncCallInstr FuncCall = new FuncCallInstr("call",Ret,TargetFunc.RetType, FuncName,Param,ParamType);
            CurList.add(FuncCall);
            RetList.add(new RetPair(Ret,TargetFunc.RetType));
            return RetList;
        }
        else if(Expr.getType() == ExprType.ExprList){
            ExprListAST ExprListNode = (ExprListAST) Expr;
            for(ExprAST expr : ExprListNode.getExprList()){
                Value Pair = ExprToInstr(expr).get(0);
                RetList.add(Pair);
            }
            return RetList;
        }

        else if(Expr.getType() == ExprType.MemCall){
            String ClassName = Left.Type;
            IRModule AimModule = FindModule(ClassName);
            LabelAST Label = (LabelAST) Expr.getRightSonExpr();
            String PtrId = Label.getId();
            String PtrReg = RegCnt.toString();
            IRValue Ptr = FindVar(CurBlock,PtrId,AimModule);
            IRValue ClassPtr = FindVar()
            MemCallInstr MemCall = new MemCallInstr("getelementptr","%"+PtrReg,"%"+Ptr.Type,Ptr.Index.toString());
            CurList.add(MemCall);
            ++RegCnt;

            String Ret = RegCnt.toString();
            String RetType = Ptr.Type;
            LoadInstr Load = new LoadInstr("load",Ret,"%"+RetType,"%"+PtrReg,Ptr.Type+"*");
            CurList.add(Load);
            ++RegCnt;
            IRValue ClassValue;
            ClassValue.Name = ClassName
            RetList.add(Ptr) ;
            RetList.add();
        }
        //还有new continue break return index 短路这几个魔王
        else if(Expr.getType() == ExprType.Negative){
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = RegCnt.toString();
            ++RegCnt;
            OperationInstr NewInstr = new OperationInstr("sub","0",Left.Name,"i32", Left.Type, Ret.Name,"");
            CurList.add(NewInstr);
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Positive){
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = Left.Name;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.LeftSelfMinus){
            //包括store在内...
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = RegCnt.toString();
            ++RegCnt;
            OperationInstr NewInstr = new OperationInstr("sub",Left.Name,"1",Left.Type,"i32",Ret.Name,"");
            StoreInstr Store = new StoreInstr("store",Ret.Name,Left.PtrReg,Left.Type,Left.Type+"*");
            CurList.add(NewInstr);
            CurList.add(Store);
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.LeftSelfPlus){
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = RegCnt.toString();
            ++RegCnt;
            OperationInstr NewInstr = new OperationInstr("add",Left.Name,"1",Left.Type,"i32",Ret.Name,"");
            StoreInstr Store = new StoreInstr("store",Ret.Name,Left.PtrReg,Left.Type,Left.Type+"*");
            CurList.add(NewInstr);
            CurList.add(Store);
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Not){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("xor",Left.Name,"1",Left.Type, "i1",Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Tidle){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("xor",Left.Name,"-1",Left.Type, "i32",Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.RightSelfMinus){
            //包括store在内...
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = RegCnt.toString();
            ++RegCnt;
            String TmpReg = RegCnt.toString();
            ++RegCnt;
            OperationInstr NewInstr = new OperationInstr("add",Left.Name,"0",Left.Type,"i32",Ret.Name,"");
            OperationInstr NewInstr2 = new OperationInstr("sub",Left.Name,"1",Left.Type,"i32",TmpReg,"");
            StoreInstr Store = new StoreInstr("store",Left.Name,Left.PtrReg,Left.Type,Left.Type+"*");
            CurList.add(NewInstr);
            CurList.add(NewInstr2);
            CurList.add(Store);
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.RightSelfPlus){
            //包括store在内...
            IRValue Ret = new IRValue();
            Ret.Type = Left.Type;
            Ret.Name = RegCnt.toString();
            ++RegCnt;
            String TmpReg = RegCnt.toString();
            ++RegCnt;
            OperationInstr NewInstr = new OperationInstr("add",Left.Name,"0",Left.Type,"i32",Ret.Name,"");
            OperationInstr NewInstr2 = new OperationInstr("add",Left.Name,"1",Left.Type,"i32",TmpReg,"");
            StoreInstr Store = new StoreInstr("store",Left.Name,Left.PtrReg,Left.Type,Left.Type+"*");
            CurList.add(NewInstr);
            CurList.add(NewInstr2);
            CurList.add(Store);
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Mod){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("srem",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Divide){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("udiv",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Multiply){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("mul",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Minus){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("sub",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
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
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("shl",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.RightShift){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("ashr",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.LessThan){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"slt");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.LessThanEqual){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"sle");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.GreaterThan){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"sgt");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.GreaterThanEqual){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"sge");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Equal){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"eq");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.NotEqual){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("icmp",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"ne");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.And){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("and",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Xor){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("xor",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.Or){
            IRValue Ret = new IRValue();
            Ret.Type = "i32";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("or",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        //TODO 短路求值
        else if(Expr.getType() == ExprType.AndAnd){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("and",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
            return RetList;
        }
        else if(Expr.getType() == ExprType.OrOr){
            IRValue Ret = new IRValue();
            Ret.Type = "i1";
            Ret.Name = RegCnt.toString();
            OperationInstr NewInstr = new OperationInstr("or",Left.Name,Right.Name,Left.Type, Right.Type,Ret.Name,"");
            CurList.add(NewInstr);
            ++RegCnt;
            RetList.add(Ret);
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
