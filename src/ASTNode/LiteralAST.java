package ASTNode;

public class LiteralAST extends ExprAST{
    String Type;
    String Context;

    public LiteralAST(String type, String context){
        super(ExprType.Literal);
        Type = type;
        Context = context;
    }

    public String getContext(){return Context;}
    public String getLiteralType(){return Type;}
    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("LiteralAST","InsertSon","");
    }
}
