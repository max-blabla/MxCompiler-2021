package ASTNode;

public class ConditionAST extends BaseAST{
    ExprAST Expr;

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof  ExprAST){
            Expr = (ExprAST)Son;
            Son.Father = this;
        }
        else new BuildError("ConditionAST","InsertSon","");
    }
}
