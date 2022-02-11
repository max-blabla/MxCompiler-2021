package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ExprStmtAST extends StmtAST{
    ExprAST Expr;

    @Override
    public void InsertSon(BaseAST Son) {
        Expr = (ExprAST) Son;
        Expr.Father = this;
    }

    public ExprAST getExpr(){
        return Expr;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(Expr);
        return SonList;
    }
}
