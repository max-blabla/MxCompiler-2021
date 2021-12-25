package ASTNode;

public class LabelAST extends ExprAST{
    String Id;

    public LabelAST(String id){
        super(ExprType.Label);
        Id = id;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("LabelAST","InsertSon","");
    }

    public String getId(){
        return Id;
    }
}
