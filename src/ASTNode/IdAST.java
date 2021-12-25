package ASTNode;

public class IdAST extends BaseAST{
    String Label;

    public IdAST(String label){
        Label = label;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("IdAST","InsertSon","Wrong Build");
    }
}
