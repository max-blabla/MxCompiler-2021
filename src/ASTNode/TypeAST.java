package ASTNode;

public class TypeAST extends BaseAST{
    String Type;

    public TypeAST(String type){
        Type = type;
    }

    @Override
    public void InsertSon(BaseAST Son){
        new BuildError("TypeAST","InsertSon","Wrong Build");
    }
}
