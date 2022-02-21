package ASTBuilder;
import ASTNode.*;
import MxParser.MxBaseListener;
import MxParser.MxParser;

import java.util.List;
import java.util.Objects;
import ASTNode.ErrorInfo;

//首先要有一个类型表
public class ASTBuilder extends MxBaseListener{
    GlobalAST AST;
    BaseAST CurASTNode;

    public GlobalAST getGlobalAST(){
        return AST;
    }

    @Override
    public void enterProgram(MxParser.ProgramContext ctx){
        AST = new GlobalAST();
        CurASTNode = AST;
    }

    @Override
    public void enterFuncDef(MxParser.FuncDefContext ctx){
        FuncDeclAST NewDef = new FuncDeclAST();
        CurASTNode.InsertSon(NewDef);
        CurASTNode = NewDef;
        if(ctx.returnType() == null){
            TypeAST NewType = new TypeAST(".construction");
            CurASTNode.InsertSon(NewType);
        }
    }

    @Override
    public void exitFuncDef(MxParser.FuncDefContext ctx){
        CurASTNode = CurASTNode.Father;
    }

    public void enterParameterList(MxParser.ParameterListContext ctx) {
        ParamListAST NewParams = new ParamListAST();
        CurASTNode.InsertSon(NewParams);
        CurASTNode = NewParams;
    }
    public void exitParameterList(MxParser.ParameterListContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterClassDef(MxParser.ClassDefContext ctx){
        ClassDeclAST NewDef = new ClassDeclAST();
        CurASTNode.InsertSon(NewDef);
        CurASTNode = NewDef;
    }

    @Override
    public void exitClassDef(MxParser.ClassDefContext ctx){
        CurASTNode = CurASTNode.Father;
    }

    @Override
    public void enterVarDef(MxParser.VarDefContext ctx) {
        VarDeclAST NewDef = new VarDeclAST();
        CurASTNode.InsertSon(NewDef);
        CurASTNode = NewDef;
    }

    @Override
    public void exitVarDef(MxParser.VarDefContext ctx){
        CurASTNode = CurASTNode.Father;
    }

    @Override
    public void enterVarDeclaration(MxParser.VarDeclarationContext ctx) {
        VarDeclareAST NewDecl = new VarDeclareAST();
        CurASTNode.InsertSon(NewDecl);
        CurASTNode = NewDecl;
    }
    @Override
    public void exitVarDeclaration(MxParser.VarDeclarationContext ctx){
        CurASTNode = CurASTNode.Father;
    }

    @Override
    public void enterSuite(MxParser.SuiteContext ctx){
        SuiteAST NewSuite = new SuiteAST();
        CurASTNode.InsertSon(NewSuite);
        CurASTNode = NewSuite;
    }

    @Override
    public void exitSuite(MxParser.SuiteContext ctx){
        CurASTNode = CurASTNode.Father;
    }

    @Override
    public void enterIfStatement(MxParser.IfStatementContext ctx) {
        IfStmtAST NewStmt = new IfStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitIfStatement(MxParser.IfStatementContext ctx) { CurASTNode = CurASTNode.Father;}


    @Override public void enterCondition(MxParser.ConditionContext ctx) {
        ConditionAST Condition = new ConditionAST();
        CurASTNode.InsertSon(Condition);
        CurASTNode = Condition;
    }

    @Override
    public void enterNewErrorArray(MxParser.NewErrorArrayContext ctx){
        new ErrorInfo("Wrong Array Declare");
        throw new RuntimeException();
    }

    @Override
    public void exitCondition(MxParser.ConditionContext ctx) {CurASTNode = CurASTNode.Father;}

    @Override
    public void enterForInit(MxParser.ForInitContext ctx) {
        ForInitAST NewStmt = new ForInitAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitForInit(MxParser.ForInitContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterForStatement(MxParser.ForStatementContext ctx) {
        ForStmtAST NewStmt = new ForStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitForStatement(MxParser.ForStatementContext ctx) {  CurASTNode = CurASTNode.Father;}

    @Override
    public void enterWhileStatement(MxParser.WhileStatementContext ctx) {
        WhileStmtAST NewStmt = new WhileStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitWhileStatement(MxParser.WhileStatementContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterReturnStatement(MxParser.ReturnStatementContext ctx) {
        ReturnStmtAST NewStmt = new ReturnStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitReturnStatement(MxParser.ReturnStatementContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterJumpStatement(MxParser.JumpStatementContext ctx) {
        JumpStmtAST NewStmt;
        if(ctx.Break() == null) NewStmt = new JumpStmtAST(Boolean.FALSE);
        else NewStmt = new JumpStmtAST(Boolean.TRUE);
        CurASTNode.InsertSon(NewStmt);
        CurASTNode = NewStmt;
    }

    @Override
    public void exitJumpStatement(MxParser.JumpStatementContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterExprStatement(MxParser.ExprStatementContext ctx){
        ExprStmtAST NewStmt= new ExprStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode  = NewStmt;
    }

    @Override
    public void exitExprStatement(MxParser.ExprStatementContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterVarStatement(MxParser.VarStatementContext ctx){
        VarStmtAST NewStmt= new VarStmtAST();
        CurASTNode.InsertSon(NewStmt);
        CurASTNode  = NewStmt;
    }

    @Override
    public void exitVarStatement(MxParser.VarStatementContext ctx) { CurASTNode = CurASTNode.Father; }


    @Override
    public void enterExpression(MxParser.ExpressionContext ctx) {
        //在这里的默认构造要传进去表达式类型
        //TODO ExprListAST Waiting
        ExprType Type ;
        if(ctx.primary() != null) Type = ExprType.Primary;
        else if(ctx.lambdaExpression() != null) Type = ExprType.Lambda;
        else if(ctx.LeftParenthes() != null) Type = ExprType.FuncCall;
        else if(ctx.indexExpr() != null) Type = ExprType.Index;
        else if(ctx.Dot() != null) Type = ExprType.MemCall;
        else if(ctx.Plus() != null){
            if(ctx.expression().size() == 1) Type = ExprType.Positive;
            else Type = ExprType.Plus;
        }
        else if(ctx.Minus()!=null){
            if(ctx.expression().size() == 1) Type = ExprType.Negative;
            else Type = ExprType.Minus;
        }
        else if(ctx.Not() != null) Type = ExprType.Not;
        else if(ctx.Tidle() != null) Type = ExprType.Tidle;
        else if(ctx.SelfMinus() != null){
            if(ctx.rop == null) Type = ExprType.LeftSelfMinus;
            else Type = ExprType.RightSelfMinus;
        }
        else if(ctx.SelfPlus() != null){
            if(ctx.rop == null) Type = ExprType.LeftSelfPlus;
            else Type = ExprType.RightSelfPlus;
        }
        else if(ctx.Times() != null) Type = ExprType.Multiply;
        else if(ctx.Divide() != null) Type = ExprType.Divide;
        else if(ctx.Mod() != null) Type = ExprType.Mod;
        else if(ctx.LeftShift() != null) Type = ExprType.LeftShift;
        else if(ctx.RightShift() != null) Type = ExprType.RightShift;
        else if(ctx.GreaterThan() != null) Type = ExprType.GreaterThan;
        else if(ctx.LessThan() != null) Type = ExprType.LessThan;
        else if(ctx.LessEqual() != null) Type = ExprType.LessThanEqual;
        else if(ctx.GreaterEqual() != null) Type = ExprType.GreaterThanEqual;
        else if(ctx.Equal() != null) Type = ExprType.Equal;
        else if(ctx.NotEqual()!= null) Type = ExprType.NotEqual;
        else if(ctx.And() != null ) Type = ExprType.And;
        else if(ctx.Or() != null) Type = ExprType.Or;
        else if(ctx.Xor() != null) Type = ExprType.Xor;
        else if(ctx.AndAnd() != null) Type = ExprType.AndAnd;
        else if(ctx.OrOr() != null) Type = ExprType.OrOr;
        else if(ctx.Assign() != null) Type = ExprType.Assign;
        else Type = ExprType.Null;
        ExprAST NewExpr = new ExprAST(Type);
        CurASTNode.InsertSon(NewExpr);
        CurASTNode = NewExpr;
    }

    @Override
    public void exitExpression(MxParser.ExpressionContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterExpressionList(MxParser.ExpressionListContext ctx) {
        ExprListAST NewExprList = new ExprListAST();
        CurASTNode.InsertSon(NewExprList);
        CurASTNode = NewExprList;
    }
    @Override
    public void exitExpressionList(MxParser.ExpressionListContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        LambdaExprAST NewLambda = new LambdaExprAST();
        CurASTNode.InsertSon(NewLambda);
        CurASTNode = NewLambda;
    }

    @Override
    public void exitLambdaExpression(MxParser.LambdaExpressionContext ctx) { CurASTNode = CurASTNode.Father; }


    @Override
    public void enterReturnType(MxParser.ReturnTypeContext ctx) {
        TypeAST NewType = new TypeAST(ctx.getText());
        CurASTNode.InsertSon(NewType);
        CurASTNode = NewType;
    }

    @Override
    public void exitReturnType(MxParser.ReturnTypeContext ctx) {CurASTNode = CurASTNode.Father;}

    @Override
    public void enterType(MxParser.TypeContext ctx) {
        TypeAST NewType = new TypeAST(ctx.getText());
        CurASTNode.InsertSon(NewType);
        CurASTNode = NewType;
    }
    @Override
    public void exitType(MxParser.TypeContext ctx) { CurASTNode = CurASTNode.Father;  }
    @Override
    public void enterLiteral(MxParser.LiteralContext ctx) {
        String Context  = ctx.getText();
        String LiteralType;
        if(Objects.equals(Context, "true") || Objects.equals(Context, "false")) LiteralType = "bool";
        else if(Objects.equals(Context, "null")) LiteralType = "null";
        else if(Context.charAt(0)<='9' && Context.charAt(0) >= '0') LiteralType = "int";
        else LiteralType = "string";
        LiteralAST NewLiteral = new LiteralAST(LiteralType,Context);
        CurASTNode.InsertSon(NewLiteral);
        CurASTNode = NewLiteral;
    }
    @Override
    public void exitLiteral(MxParser.LiteralContext ctx) { CurASTNode = CurASTNode.Father; }
    @Override
    public void enterLabel(MxParser.LabelContext ctx) {
        String LabelName = ctx.getText();
        LabelAST NewLabel = new LabelAST(LabelName);
        CurASTNode.InsertSon(NewLabel);
        CurASTNode = NewLabel;
    }
    @Override
    public void exitLabel(MxParser.LabelContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterParenthes(MxParser.ParenthesContext ctx){
        ParAST NewPar = new ParAST();
        CurASTNode.InsertSon(NewPar);
        CurASTNode = NewPar;
    }

    @Override
    public void exitParenthes(MxParser.ParenthesContext ctx) { CurASTNode = CurASTNode.Father; }

    @Override
    public void enterNewType(MxParser.NewTypeContext ctx){
        StringBuilder Type = new StringBuilder(ctx.singleType().getText());
        int Num = ctx.LeftBracket().size();
        Type.append("[]".repeat(Num));
        NewTypeAST NewNewType = new NewTypeAST(Type.toString());
        CurASTNode.InsertSon(NewNewType);
        CurASTNode = NewNewType;
    }

    @Override
    public void exitNewType(MxParser.NewTypeContext ctx){CurASTNode = CurASTNode.Father;}

    @Override
    public void enterId(MxParser.IdContext ctx){
        String IdName = ctx.getText();
        IdAST NewLabel = new IdAST(IdName);
        CurASTNode.InsertSon(NewLabel);
        CurASTNode = NewLabel;
    }
    @Override
    public void exitId(MxParser.IdContext ctx) { CurASTNode = CurASTNode.Father; }
}