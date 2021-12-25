package IRBuilder;

import ASTNode.*;

import java.util.Objects;
import java.util.Stack;
import java.util.List;
class Scope{
    String ScopeName;
    Scope(String scopeName){
        ScopeName = scopeName;
    }
};

public class IRBuilder{
    GlobalAST Program;
    List<IRModule> ModuleList;
    Integer BlockNum;
    IRFunc CurFunc;
    IRBlock CurBlock;
    void setProgram(GlobalAST program){
        Program = program;
    }
    void ProgramIR(){
        BlockNum = 0;
        IRModule GlobalModule = new IRModule();
        GlobalModule.setName("");
        ModuleList.add(GlobalModule);
        IRFunc Init  = new IRFunc();

        Init.FuncName = "_init";
        Init.RetType = "void";
        Init.Start = new IRBlock(BlockType.Basic);
        IRBlock CurBlock = Init.Start;
        CurBlock.Label = "Start";

        for(DeclAST Decl:Program.DeclList) DeclIR(Decl);

    }

    void DeclIR(DeclAST DeclNode){
        if(DeclNode instanceof VarDeclAST)  VarDeclIR(DeclNode);
        else if(DeclNode instanceof FuncDeclAST) FuncDeclIR(DeclNode);
        else ClassDeclIR(DeclNode);
    }

    //
    void VarDeclIR(DeclAST VarDeclNode){
        //TODO 在这里所有的类型都是IR类型
        VarDeclAST VarNode = (VarDeclAST) VarDeclNode;
        List<VarDeclareAST> VarDeclList = VarNode.getVarDeclareList();
        IRModule TopModule = ModuleList.get(ModuleList.size()-1);
        for(VarDeclareAST VarDecl : VarDeclList){
            IRVarPair irVarPair = new IRVarPair();
            irVarPair.Type = VarNode.getType();
            irVarPair.Name = VarDecl.getId();
            irVarPair.Expr = VarDecl.getExpr();
            TopModule.VarInsert(irVarPair);
        }
    }

    void FuncDeclIR(DeclAST FuncDeclNode){
        FuncDeclAST FuncNode = (FuncDeclAST) FuncDeclNode;
        IRModule TopModule = ModuleList.get(ModuleList.size()-1);

        //TODO 分配 this 指针 (即在参数表和VarTable里塞this)和 参数 表
        //TODO 在这里所有的类型都是IR类型
        IRFunc NewFunc = new IRFunc();
        CurFunc = NewFunc;
        NewFunc.FuncName = FuncNode.getFuncName();

        NewFunc.RetType = FuncNode.getFuncType();
        NewFunc.Start = new IRBlock(BlockType.Basic);

        CurBlock = NewFunc.Start;
        CurBlock.Label = "Root" + BlockNum;
        ++BlockNum;

        IRBlock NewParamBlock = new IRBlock(BlockType.Basic);
        NewParamBlock.Label = "Param" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewParamBlock);

        if(Objects.equals(FuncNode.getFuncName(), "main")){
            IRBlock NewInitBlock = new IRBlock(BlockType.Basic);
            NewInitBlock.Label = "Init" + BlockNum;
            ExprAST Init = new ExprAST(ExprType.FuncCall);
            ExprAST Prim = new ExprAST(ExprType.Primary);
            LabelAST FuncName = new LabelAST("_init");
            Prim.InsertSon(FuncName);
            Init.InsertSon(Prim);
            NewInitBlock.InsertExpr(Init);
            ++BlockNum;
            CurBlock.InsertSubBlock(NewInitBlock);
        }


        CurBlock = NewParamBlock;
        List<VarDeclAST> VarList = FuncNode.getParamList();
        for(VarDeclAST VarDecl:VarList) {
            List<VarDeclareAST> VarDeclareList = VarDecl.getVarDeclareList();
            for(VarDeclareAST VarDeclare :VarDeclareList)
                CurBlock.InsertVarDecl(VarDecl.getType(),VarDeclare.getId(),VarDeclare.getExpr());
        }
        CurBlock = CurBlock.Father;

        IRBlock NewBasicBlock = new IRBlock(BlockType.Basic);
        NewBasicBlock.Label = "Start" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewBasicBlock);

        CurBlock = NewBasicBlock;
        List<StmtAST> StmtList = FuncNode.getStmtList();
        for(StmtAST Stmt : StmtList) StmtIR(Stmt);
        CurBlock = CurBlock.Father;
        TopModule.FuncInsert(NewFunc);
    }

    void StmtIR(StmtAST StmtNode){
        if(StmtNode instanceof JumpStmtAST)  JumpStmtIR(StmtNode);
        else if(StmtNode instanceof ReturnStmtAST) ReturnStmtIR(StmtNode);
        else if(StmtNode instanceof  WhileStmtAST) WhileStmtIR(StmtNode);
        else if(StmtNode instanceof IfStmtAST) IfStmtIR(StmtNode);
        else if(StmtNode instanceof ExprStmtAST) ExprStmtIR(StmtNode);
        else if(StmtNode instanceof ForStmtAST) ForStmtIR(StmtNode);
        else if(StmtNode instanceof SuiteAST) SuiteIR(StmtNode);
        else VarStmtIR(StmtNode);
    }

    void ClassDeclIR(DeclAST ClassDeclNode){
        ClassDeclAST ClassNode = (ClassDeclAST) ClassDeclNode;

        IRModule ClassModule = new IRModule();
        ClassModule.setName(ClassNode.getClassName());
        ModuleList.add(ClassModule);

        for(DeclAST Decl:Program.DeclList) DeclIR(Decl);
    }


    void JumpStmtIR(StmtAST JumpStmtNode){
        //或者让后面的无法加入
        JumpStmtAST JumpStmt = (JumpStmtAST) JumpStmtNode;
        if(JumpStmt.isBreak()) CurBlock.setShutType(ShutType.Break);
        else CurBlock.setShutType(ShutType.Continue);
        CurBlock.ShutBlock();
    }

    void ReturnStmtIR(StmtAST ReturnStmtNode){
        ReturnStmtAST ReturnStmt = (ReturnStmtAST) ReturnStmtNode;
        if(ReturnStmt.getExpr() != null) CurBlock.setReturnExpr(ReturnStmt.getExpr());
        CurBlock.setShutType(ShutType.Return);
        CurBlock.ShutBlock();
    }

    void WhileStmtIR(StmtAST WhileStmtNode){
        WhileStmtAST WhileStmt = (WhileStmtAST) WhileStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition);
        NewConditionBlock.Label = "While-Condition" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewConditionBlock);
        CurBlock = NewConditionBlock;
        CurBlock.InsertExpr(WhileStmt.getConditionExpr());
        CurBlock = CurBlock.Father;

        IRBlock NewBodyBlock = new IRBlock(BlockType.Condition);
        NewConditionBlock.Label = "While-Body" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewBodyBlock);
        CurBlock = NewBodyBlock;
        StmtIR(WhileStmt.getLoopStmt());
        CurBlock = CurBlock.Father;

    }

    void IfStmtIR(StmtAST IfStmtNode){
        IfStmtAST IfStmt = (IfStmtAST) IfStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition);
        NewConditionBlock.Label = "If-Condition" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewConditionBlock);
        CurBlock = NewConditionBlock;
        CurBlock.InsertExpr(IfStmt.getConditionExpr());
        CurBlock = CurBlock.Father;


        IRBlock NewTrueBlock = new IRBlock(BlockType.TrueStmt);
        NewConditionBlock.Label = "If-True-Stmt"+BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewTrueBlock);
        CurBlock = NewTrueBlock;
        StmtIR(IfStmt.getTrueStmt());
        CurBlock = CurBlock.Father;

        if(IfStmt.getFalseStmt() != null) {
            IRBlock NewFalseBlock = new IRBlock(BlockType.FalseStmt);
            NewConditionBlock.Label = "If-false-Stmt"+BlockNum;
            ++BlockNum;
            CurBlock.InsertSubBlock(NewFalseBlock);
            CurBlock = NewFalseBlock;
            StmtIR(IfStmt.getFalseStmt());
            CurBlock = CurBlock.Father;
        }
    }

    void ForStmtIR(StmtAST ForStmtNode){
        ForStmtAST ForStmt = (ForStmtAST) ForStmtNode;

        IRBlock NewInitBlock = new IRBlock(BlockType.Basic);
        NewInitBlock.Label = "For-Init" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewInitBlock);
        CurBlock = NewInitBlock;
        if(ForStmt.getInitStmt() != null){
            String Type = ForStmt.getInitStmt().getType();
            List<VarDeclareAST> VarDeclList = ForStmt.getInitStmt().getVarDeclareList();
            for(VarDeclareAST VarDeclare : VarDeclList)  CurBlock.InsertVarDecl(Type,VarDeclare.getId(),VarDeclare.getExpr());
        }
        else CurBlock.InsertExpr(ForStmt.getInitExpr());
        CurBlock = CurBlock.Father;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition);
        NewConditionBlock.Label = "For-Condition" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewConditionBlock);
        CurBlock = NewConditionBlock;
        CurBlock.InsertExpr(ForStmt.getConditionExpr());
        CurBlock = CurBlock.Father;



        IRBlock NewBodyBlock = new IRBlock(BlockType.TrueStmt);
        NewBodyBlock.Label = "For-Body" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewBodyBlock);
        CurBlock = NewBodyBlock;
        StmtIR(ForStmt.getLoopStmt());
        CurBlock = CurBlock.Father;
    }

    void ExprStmtIR(StmtAST ExprStmtNode){
        ExprStmtAST ExprStmt = (ExprStmtAST) ExprStmtNode;
        CurBlock.InsertExpr(ExprStmt.getExpr());
    }

    void SuiteIR(StmtAST SuiteNode){
        //生成新的block
        SuiteAST Suite = (SuiteAST) SuiteNode;


        IRBlock NewBasicBlock = new IRBlock(BlockType.Basic);
        NewBasicBlock.Label = "Basic" + BlockNum;
        ++BlockNum;
        CurBlock.InsertSubBlock(NewBasicBlock);
        CurBlock = NewBasicBlock;
        List<StmtAST> StmtList = Suite.getStmtList();
        for(StmtAST Stmt : StmtList){
            if(CurBlock.isShut()) break;
            else StmtIR(Stmt);
        }
        CurBlock = CurBlock.Father;
    }

    void VarStmtIR(StmtAST VarStmtNode){
        //TODO 如果有 = 就拆开
        VarStmtAST VarStmt = (VarStmtAST) VarStmtNode;
        String Type = VarStmt.getType();
        List<VarDeclareAST> VarDeclList = VarStmt.getVarDeclList();
        for(VarDeclareAST VarDeclare : VarDeclList)  CurBlock.InsertVarDecl(Type,VarDeclare.getId(),VarDeclare.getExpr());
    }

}