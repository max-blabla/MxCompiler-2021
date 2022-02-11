package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class LabelAST extends ExprAST{
    String Id;
    IdAST idAST;
    public LabelAST(String id){
        super(ExprType.Label);
        Id = id;
        idAST = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        //new BuildError("LabelAST","InsertSon","");
        if(Son instanceof  IdAST){
           Son.Father = this;
            idAST = (IdAST) Son;
        }
        else new BuildError("LabelAST","InsertSon","");
    }

    public String getId(){
        return Id;
    }
}
