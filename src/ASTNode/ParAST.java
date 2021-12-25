package ASTNode;

public class ParAST extends ExprAST{
    ExprAST Expr;

    public ParAST(){
        super(ExprType.Par);
        Expr = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof  ExprAST){
            if(Expr == null) Expr = (ExprAST) Son;
            else new BuildError("ParAST","InsertSon","Expr Only Needed Once");
        }
        else new BuildError("ParAST","InsertSon","");
    }
}
