package  SemanticChecker;

import ErrorInfo.ErrorInfo;
import GlobalVisitor.*;
import MxParser.MxBaseListener;
import MxParser.MxParser;
import MxParser.MxParser.VarDeclarationContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;


public class SemanticChecker extends MxBaseListener{
    //可以拿到Global Table
    Stack<String> EnvironmentType;
    Stack<BaseScope> Environment;
    GlobalScope GlobalTable;
    Stack<ExpTree> ExpList;
    MxParser Parser;
    //HashMap<String,L_VarInfo> ParamLabelTable = new HashMap<>();

    int CurType = 0;

    public SemanticChecker(GlobalScope GlobalTable, MxParser Parser){
        this.Parser = Parser;
        this.GlobalTable = GlobalTable;

        this.Environment = new Stack<>();
        this.EnvironmentType = new Stack<>();
        this.ExpList = new Stack<>();
    }
    //需要检查的:lambda, 函数作用域, Suite内的重名, 全局和类不用查
    //Then?
    @Override
    public void enterProgram(MxParser.ProgramContext ctx){
        EnvironmentType.push("global");

        if(!GlobalTable.FindFunc("main")){
            ErrorInfo errorInfo = new ErrorInfo("No Main Func",ctx.getText());
            throw new RuntimeException();
        }
        else if(!GlobalTable.GetFunc("main").get(0).equals(2)){
            ErrorInfo errorInfo = new ErrorInfo("Main Func Only To Be Int",ctx.getText());
            throw new RuntimeException();
        }
        else if(GlobalTable.GetFunc("main").size()>=2){
            ErrorInfo errorInfo = new ErrorInfo("Main Func Should Not Have Params",ctx.getText());
            throw new RuntimeException();
        }
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(3);
        Base.SetGlobalDerivation(GlobalTable);

        Environment.push(Base);
    }
    @Override
    public void enterClassDef(MxParser.ClassDefContext ctx){
        EnvironmentType.push("class");
        String ClassName = ctx.Identifier().getText();
        ClassScope NewClass = GlobalTable.GetClassScope(ClassName);
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(2);
        Base.SetClassDerivation(NewClass);
        Environment.push(Base);

    }
    @Override
    public void exitClassDef(MxParser.ClassDefContext ctx){
        Environment.pop();
        EnvironmentType.pop();
    }

//函数和lambda?
//return 要找到最近的那个函数，如果找不到就G
    @Override
    public void enterParameterList(MxParser.ParameterListContext ctx){
        List<TerminalNode> IdList = ctx.Identifier();
        List<MxParser.TypeContext> TypeList = ctx.type();
        Iterator<TerminalNode> IdIter = IdList.listIterator();
        Iterator<MxParser.TypeContext> TypeIter= TypeList.listIterator();
        while(IdIter.hasNext()){
            TerminalNode Id = IdIter.next();
            MxParser.TypeContext Type = TypeIter.next();
            Environment.peek().InsertVar(Id.getText(),GlobalTable.FindType(Type.getText()),false);
        }
    }
    @Override
    public void enterConFuncDef(MxParser.ConFuncDefContext ctx){
        EnvironmentType.push("function-incomplete");
        FuncScope NewFunc = new FuncScope();
        NewFunc.SetRetType(0);
        NewFunc.SetIsNull(false);
        NewFunc.SetIsLeft(false);

        BaseScope Base = new BaseScope();
        Base.SetDerivationType(1);
        Base.SetFuncDerivation(NewFunc);
        Environment.push(Base);
    }
    @Override
    public void exitConFuncDef(MxParser.ConFuncDefContext ctx){
        Environment.pop();
        EnvironmentType.pop();
    }
    @Override
    public void enterFuncDef(MxParser.FuncDefContext ctx) {

        EnvironmentType.push("function-incomplete");
        FuncScope NewFunc = new FuncScope();
        String RetType = ctx.returnType().getText();
        NewFunc.SetRetType(GlobalTable.FindType(RetType));
        NewFunc.SetIsNull(false);
        NewFunc.SetIsLeft(false);
        NewFunc.SetHasReturn(false);
        NewFunc.SetFuncName(ctx.Identifier().getText());
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(1);
        Base.SetFuncDerivation(NewFunc);
        Environment.push(Base);

    }
    @Override
    public void exitFuncDef(MxParser.FuncDefContext ctx){
        FuncScope FS = Environment.peek().GetFuncDerivation();
        if((FS.GetRetType()!=1&&FS.GetRetType()!=0)&&!FS.GetHasReturn()&&!FS.GetFuncName().equals("main")){
            ErrorInfo errorInfo = new ErrorInfo("Non-Void Function Does Not Have Return",ctx.getText());
            throw new RuntimeException();
        }
        Environment.pop();
        EnvironmentType.pop();
    }
    @Override
    public void enterVarDef(MxParser.VarDefContext ctx){
        String Type = ctx.type().getText();
        /* = 0;
        if (GlobalTable.FindType(Type) == -1) ++ErrorNum;
        else CurType*/
        CurType = GlobalTable.FindType(Type);
    }
    @Override
    public void enterVarDeclaration(MxParser.VarDeclarationContext ctx){
        if(ctx.expression()!=null) {
            ExpList.push(new ExpTree());
            ExpList.peek().NewTree();
            ExpList.peek().SetEnvironment(Environment);
            ExpList.peek().SetEnvironmentType(EnvironmentType);
            ExpList.peek().SetGlobalTable(GlobalTable);
        }
    }
    @Override
    public void exitVarDeclaration(MxParser.VarDeclarationContext ctx) {

        String Label = ctx.Identifier().getText();
        if(GlobalTable.FindClass(Label)){
            ErrorInfo errorInfo = new ErrorInfo("Label Can Not Be The Same With Class",ctx.getText());
            throw new RuntimeException();
        }
        boolean IsNull = false;
        if(ctx.expression()!=null) {
            ExpList.peek().SemanticCheck();
            int ExpTreeType = ExpList.peek().ExpTreeType();
            IsNull = ExpList.peek().ExpTreeIsNull();
            if(ExpTreeType!=CurType&&ExpTreeType!=-3){
                ErrorInfo errorInfo = new ErrorInfo("Assign Type Does Not Match With Declaration",ctx.getText());
                throw new RuntimeException();
            }
            ExpList.pop();
        }
        if ((!EnvironmentType.peek().equals("class"))&&(!Environment.peek().InsertVar(Label,CurType,IsNull))) {
            ErrorInfo errorInfo = new ErrorInfo("Duplicate Value Declaration",ctx.getText());
            throw new RuntimeException();
        }
    }
    @Override
    public void enterSuite(MxParser.SuiteContext ctx){
        if(Objects.equals(EnvironmentType.peek(), "function-incomplete") ){
            EnvironmentType.pop();
            EnvironmentType.push("function");
        }
        else if(Objects.equals(EnvironmentType.peek(), "for-incomplete" )){
            EnvironmentType.pop();
            EnvironmentType.push("for");
        }
        else if(Objects.equals(EnvironmentType.peek(),"while-incomplete")){
            EnvironmentType.pop();
            EnvironmentType.push("while");
        }
        else{
            BaseScope Base = new BaseScope();
            Base.SetDerivationType(0);
            Environment.push(Base);
            EnvironmentType.push("null");
        }
    }
    @Override
    public void exitSuite(MxParser.SuiteContext ctx){
        if(!Objects.equals(EnvironmentType.peek(), "function")&&!Objects.equals(EnvironmentType.peek(), "for")&&!Objects.equals(EnvironmentType.peek(), "while") ){
            EnvironmentType.pop();
            Environment.pop();
        }
    }

    //终于进入stmt 正确性检查-.-
    @Override
    public void enterWhileStatement(MxParser.WhileStatementContext ctx){
        EnvironmentType.push("while-incomplete");
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(0);
        Environment.push(Base);
    }
    @Override
    public void exitWhileStatement(MxParser.WhileStatementContext ctx){
        EnvironmentType.pop();
        Environment.pop();
    }
    @Override
    public void enterForStatement(MxParser.ForStatementContext ctx){
        EnvironmentType.push("for-incomplete");

        BaseScope Base = new BaseScope();
        Base.SetDerivationType(0);
        Environment.push(Base);

        //
    }

    @Override
    public void enterElseStatement(MxParser.ElseStatementContext ctx){
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(0);
        Environment.push(Base);
        EnvironmentType.push("null");
    }

    @Override
    public void exitElseStatement(MxParser.ElseStatementContext ctx){
        Environment.pop();
        EnvironmentType.pop();
    }

    @Override
    public void enterIfStatement(MxParser.IfStatementContext ctx){
        BaseScope Base = new BaseScope();
        Base.SetDerivationType(0);
        Environment.push(Base);
        EnvironmentType.push("null");
    }



    @Override
    public void exitIfStatement(MxParser.IfStatementContext ctx){
        Environment.pop();
        EnvironmentType.pop();
    }

/*  */
    @Override
    public void exitForStatement(MxParser.ForStatementContext ctx){
        EnvironmentType.pop();
        Environment.pop();
    }
    @Override
    public void enterReturnStatement(MxParser.ReturnStatementContext ctx){

        //无返回语句视作 错误
        //找到最近的一个函数，修改它的相关信息
        if(ctx.expression()!=null){
            ExpList.push(new ExpTree());
            ExpList.peek().NewTree();
            ExpList.peek().SetEnvironment(Environment);
            ExpList.peek().SetEnvironmentType(EnvironmentType);
            ExpList.peek().SetGlobalTable(GlobalTable);
        }
    }
    @Override
    public void exitReturnStatement(MxParser.ReturnStatementContext ctx){
        Integer RetType = 1 ;
        Boolean IsNull = false;
        Boolean IsLeft = false;
        if(ctx.expression()!=null){
            ExpList.peek().SemanticCheck();
            RetType = ExpList.peek().ExpTreeType();
            IsNull = ExpList.peek().ExpTreeIsNull();
            ExpList.pop();
        }

        int index = EnvironmentType.search("function");
        if(index == -1) index = Environment.search("function-incomplete");

        FuncScope Func = Environment.get(EnvironmentType.size() - index).GetFuncDerivation();
        Func.SetHasReturn(true);
        Func.SetIsNull(IsNull);
        Func.SetIsLeft(RetType >= 4);
        if(Func.GetRetType() == -2) Func.SetRetType(RetType);
        else if((Func.GetRetType()==1||Func.GetRetType()==0)&&RetType == 1){}
        else if(Func.GetRetType()>=4 && RetType == -3){}
        else if(!Objects.equals(Func.GetRetType(), RetType)){
            ErrorInfo errorInfo = new ErrorInfo("Return Type Does Not Match With Func Declaration",ctx.getText());
            throw new RuntimeException();
        }
    }
    @Override
    public void enterExpStatement(MxParser.ExpStatementContext ctx){
        ExpList.push(new ExpTree());
        ExpList.peek().NewTree();
        ExpList.peek().SetEnvironment(Environment);
        ExpList.peek().SetEnvironmentType(EnvironmentType);
        ExpList.peek().SetGlobalTable(GlobalTable);
    }
    @Override
    public void exitExpStatement(MxParser.ExpStatementContext ctx){

        ExpList.peek().SemanticCheck();
        ExpList.pop();
    }

    @Override
    public void enterCondition(MxParser.ConditionContext ctx){
        ExpList.push(new ExpTree());
        ExpList.peek().NewTree();
        ExpList.peek().SetEnvironment(Environment);
        ExpList.peek().SetEnvironmentType(EnvironmentType);
        ExpList.peek().SetGlobalTable(GlobalTable);
    }

    public void exitCondition(MxParser.ConditionContext ctx){
        ExpList.peek().SemanticCheck();
        if(ExpList.peek().ExpTreeType() != 3){
            ErrorInfo errorInfo = new ErrorInfo("Condition Is Only Bool",ctx.getText());
            throw new RuntimeException();
        }
        ExpList.pop();
    }

    @Override
    public void enterPrimary(MxParser.PrimaryContext ctx){
        if(ctx.literal()!=null) {
            int RetType = -2;
            boolean IsNull = false;
            int LiteralType = ctx.literal().literalType.getType();
            switch (LiteralType) {
                case MxParser.True, MxParser.False -> RetType = 3;
                case MxParser.StringConst -> RetType = 4;
                case MxParser.Integer -> RetType = 2;
                case MxParser.Null -> RetType=-3;
            }
            ExpList.peek().InsertSon(ExpType.PrimaryRightValue,RetType, false,IsNull,"");
        }
        else if(ctx.newSingle()!=null){
            String Type = ctx.newSingle().singleType().getText();
            Integer RetType = GlobalTable.FindType(Type);
            if(RetType ==-1) {
                ErrorInfo errorInfo = new ErrorInfo("New Type Does Not Exist",ctx.getText());
                throw new RuntimeException();
            };
            ExpList.peek().InsertSon(ExpType.PrimaryNewSingle,RetType, false,false,"");
        }
        else if(ctx.newArray()!=null){
            String SingleType = ctx.newArray().singleType().getText();
            Integer RetType = GlobalTable.FindType(SingleType);
            if(RetType ==-1) {
                ErrorInfo errorInfo = new ErrorInfo("New Array Type Does Not Exist",ctx.getText());
                throw new RuntimeException();
            }
            List<TerminalNode> Bracket = ctx.newArray().newArrayExp().LeftBracket();

            //String NewType = SingleType + Bracket.size();
            String NewType = SingleType + "[]".repeat(Bracket.size());
            GlobalTable.NewArray(NewType);
            Integer NewRetType = GlobalTable.FindType(NewType);
            ExpList.peek().InsertSon(ExpType.PrimaryNewArray,NewRetType, false,false,"");
            ExpList.peek().DownTree();
        }
        else if(ctx.parenthes()!=null){
            ExpList.peek().InsertSon(ExpType.PrimaryExpression,-2,false,false,"");
            ExpList.peek().DownTree();
        }
        else{
            String Id= ctx.label().getText();
            ExpList.peek().InsertSon(ExpType.PrimaryLeftValue,-2,true,false,Id);
        }
    }

    @Override
    public void exitPrimary(MxParser.PrimaryContext ctx){
        if(ctx.parenthes()!=null){
            ExpList.peek().UpTree();
        }
        else if(ctx.newArray()!=null){
            ExpList.peek().UpTree();
        }
    }


    @Override
    public void enterExpressionList(MxParser.ExpressionListContext ctx){
        ExpList.peek().InsertSon(ExpType.ParamList,-2,false,false,"");
        ExpList.peek().DownTree();
    }
    @Override
    public void exitExpressionList(MxParser.ExpressionListContext ctx){
        ExpList.peek().UpTree();
    }
    @Override
    public void enterLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        Vector<Integer> Lambda = new Vector<>();
        Lambda.add(-2);

        List<MxParser.TypeContext> ParamType = ctx.parameterList().type();

        for(MxParser.TypeContext Type:ParamType){
            GlobalTable.NewArray(Type.getText());
            Lambda.add(GlobalTable.FindType(Type.getText()));
        }

        Environment.peek().SetNewLambda(Lambda);


        EnvironmentType.push("function-incomplete");
        FuncScope NewFunc = new FuncScope();

        NewFunc.SetRetType(-2);
        NewFunc.SetIsNull(false);
        NewFunc.SetIsLeft(false);

        BaseScope Base = new BaseScope();
        Base.SetDerivationType(1);
        Base.SetFuncDerivation(NewFunc);
        Environment.push(Base);
    }
    @Override
    public void exitLambdaExpression(MxParser.LambdaExpressionContext ctx){
        FuncScope Func = Environment.peek().GetFuncDerivation();
        //boolean IsNull = Func.GetIsNull();
        //boolean IsLeft = Func.GetIsLeft();
        int RetType = Func.GetRetType();
      //  ExpList.peek().InsertSon(ExpType.Primary,RetType,IsLeft,IsNull,"");
        Environment.pop();
        EnvironmentType.pop();
        Environment.peek().GetLambda().set(0,RetType);
    }
    @Override
    public void enterExpression(MxParser.ExpressionContext ctx){

        if(ctx.primary()!=null){
            ExpList.peek().InsertSon(ExpType.Primary,-2,false,false,"");
        }
        else if(ctx.lambdaExpression()!=null){
            ExpList.peek().InsertSon(ExpType.LambdaExpr,-2,false,false,"_lambda");
        }
        else if(ctx.funcExpr()!=null){
            ExpList.peek().InsertSon(ExpType.FuncExpr,-2,false,false,"");
        }
        else if(ctx.indexExpr()!=null){
            ExpList.peek().InsertSon(ExpType.IndexExpr,-2,false,false,"");
        }
        else{
            if(ctx.leftSelfExprssion()!=null){
                int opType = ctx.leftSelfExprssion().op.getType();
                ExpType expType = switch (opType) {
                    case MxParser.SelfMinus, MxParser.SelfPlus -> ExpType.SelfLeft;
                    case MxParser.Plus, MxParser.Minus -> ExpType.SignChange;
                    case MxParser.Not -> ExpType.Not;
                    case MxParser.Tidle -> ExpType.BitNot;
                    default -> ExpType.Null;
                };
                ExpList.peek().InsertSon(expType,-2,false,false,"");
            }
            else{
                int opType = ctx.op.getType();
                ExpType expType = switch (opType) {
                    case MxParser.Dot -> ExpType.Mem;
                    case MxParser.SelfPlus, MxParser.SelfMinus -> ExpType.SelfRight;
                    case MxParser.Mod, MxParser.Times, MxParser.Divide -> ExpType.Multiply;
                    case MxParser.Plus -> ExpType.Plus;
                    case MxParser.Minus -> ExpType.Minus;
                    case MxParser.LeftShift, MxParser.RightShift -> ExpType.Shift;
                    case MxParser.GreaterEqual, MxParser.LessEqual, MxParser.GreaterThan, MxParser.LessThan -> ExpType.Comparison;
                    case MxParser.Equal, MxParser.NotEqual -> ExpType.Equal;
                    case MxParser.And -> ExpType.BitAnd;
                    case MxParser.Xor -> ExpType.BitXor;
                    case MxParser.Or -> ExpType.BitOr;
                    case MxParser.AndAnd -> ExpType.And;
                    case MxParser.OrOr -> ExpType.Or;
                    case MxParser.Assign -> ExpType.Assign;
                    default -> ExpType.Null;
                };
                ExpList.peek().InsertSon(expType,-2,false,false,"");
            }
        }
        ExpList.peek().DownTree();
    }
    @Override
    public void exitExpression(MxParser.ExpressionContext ctx) {

        ExpList.peek().UpTree();
    }
    @Override
    public void enterJumpStatement(MxParser.JumpStatementContext ctx){
        int index1 = EnvironmentType.search("while");
        int index2 = EnvironmentType.search("for");
        if(index1 == -1 && index2 == -1){
            index1 =   EnvironmentType.search("while-incomplete");
            index2 =   EnvironmentType.search("for-incomplete");
            if(index1 == -1 && index2 == -1) {
                ErrorInfo errorInfo = new ErrorInfo("This Statement Is Not In Loop", ctx.getText());
                throw new RuntimeException();
            }
        };
    }

}

//作用域检查
//即标签是否正确
//this 也是一种标签