package ASTNode;
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
};