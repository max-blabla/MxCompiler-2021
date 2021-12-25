package ASTNode;

public class LiteralAST extends ExprAST{
    String Type;
    String Context;

    public LiteralAST(String type, String context){
        super(ExprType.Literal);
        Type = type;
        Context = context;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("LiteralAST","InsertSon","");
    }
}
