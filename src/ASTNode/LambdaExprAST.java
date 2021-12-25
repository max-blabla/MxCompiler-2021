package ASTNode;

public class LambdaExprAST extends BaseAST{
    ParamListAST ParamList;
    SuiteAST FuncSuite;
    ExprListAST ExprList;

    public LambdaExprAST(){
        ParamList = null;
        ExprList = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ParamListAST){
            ParamList = (ParamListAST) Son;
            ParamList.Father = this;
        }
        else if(Son instanceof SuiteAST){
            FuncSuite = (SuiteAST) Son;
            FuncSuite.Father = this;
        }
        else if(Son instanceof ExprListAST){
            ExprList = (ExprListAST) Son;
            ExprList.Father = this;
        }
        else new BuildError("LambdaExpr","InsertSon","");
    }
}
