package ASTNode;

public class ParAST extends ExprAST{
    //ExprAST Expr;

    public ParAST(){
        super(ExprType.Par);

    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof  ExprAST){
            if(LeftSonExpr == null){
                LeftSonExpr = (ExprAST) Son;
                LeftSonExpr.Father = this;
            }
            else new BuildError("ParAST","InsertSon","Expr Only Needed Once");
        }
        else new BuildError("ParAST","InsertSon","");
    }
}
