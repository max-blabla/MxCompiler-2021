package ASTNode;

import java.util.ArrayList;
import java.util.List;

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
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(ParamList);
        SonList.add(FuncSuite);
        SonList.add(ExprList);

        return SonList;
    }
}
