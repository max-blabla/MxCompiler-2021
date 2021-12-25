package ASTNode;

public class VarDeclareAST extends BaseAST{
    IdAST Id;
    ExprAST Expr;//可以为Null

    public VarDeclareAST(){
        Expr = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST) {
            Expr = (ExprAST) Son;
            Expr.Father = this;
        }
        else if(Son instanceof  IdAST){
            Id = (IdAST) Son;
            Id.Father = this;
        }
        else new BuildError("VarDeclareAST","InsertSon","");
    }

    public String getId(){
        return Id.Label;
    }

    public ExprAST getExpr(){
        return  Expr;
    }
}
