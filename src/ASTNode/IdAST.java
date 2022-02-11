package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class IdAST extends BaseAST{
    String Label;

    public IdAST(String label){
        Label = label;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("IdAST","InsertSon","Wrong Build");
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(null);
        return SonList;
    }
}
