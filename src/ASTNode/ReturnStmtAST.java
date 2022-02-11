package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ReturnStmtAST extends StmtAST{
    ExprAST ReturnExpr;
    public ReturnStmtAST(){
        ReturnExpr = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST){
            ReturnExpr = (ExprAST) Son;
            ReturnExpr.Father = this;
        }
        else new BuildError("ReturnStmtAST","InsertSon","");
    }

    public ExprAST getExpr(){
        return ReturnExpr;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(ReturnExpr);
        return SonList;
    }
};