package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ParamListAST extends BaseAST{
    List<VarDeclAST> ParamTypeList = new ArrayList<>();
    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof VarDeclAST){
            VarDeclAST NewSon = (VarDeclAST) Son;
            ParamTypeList.add(NewSon);
            Son.Father = this;
        }
        else  new BuildError("ParamListAST","InsertSon","");
    }
    public List<BaseAST> GetSon(){
        return new ArrayList<>(ParamTypeList);
    }
}
