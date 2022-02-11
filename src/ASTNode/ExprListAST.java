package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ExprListAST extends ExprAST{
    List<ExprAST> ExprList = new ArrayList<>();
    public ExprListAST(){
        super(ExprType.ExprList);
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST){
            ExprAST NewExpr = (ExprAST) Son;
            NewExpr.Father = this;
            ExprList.add(NewExpr);
        }
        else new BuildError("ExprListAST","InsertSon","");
    }

    public List<ExprAST> getExprList(){
        return ExprList;
    }
    public List<BaseAST> GetSon(){
        return new ArrayList<>(ExprList);
    }
}