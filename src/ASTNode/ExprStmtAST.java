package ASTNode;

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
}
