package ASTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeAST extends BaseAST{
    String Type;
    public TypeAST(String type){
        Type = type;
    }

    @Override
    public void InsertSon(BaseAST Son){
        if(Objects.equals(Type, "void"))  new BuildError("TypeAST","InsertSon","Wrong Build");
        else Son.Father = this;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(null);
        return SonList;
    }
}
