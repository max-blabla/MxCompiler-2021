package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class VarDeclAST extends DeclAST{
    TypeAST VarType;
    List<VarDeclareAST> DeclareList = new ArrayList<>();

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof TypeAST){
            VarType = (TypeAST) Son;
            VarType.Father= this;
        }
        else if(Son instanceof VarDeclareAST){
            VarDeclareAST NewDeclare = (VarDeclareAST) Son;
            NewDeclare.Father= this;
            DeclareList.add(NewDeclare);
        }
        else {
            new BuildError("VarDeclAST","InsertSon","");
        }
    }


    public List<VarDeclareAST> getVarDeclareList(){
        return DeclareList;
    }

    public String getType(){
        return VarType.Type;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(VarType);
        SonList.addAll(DeclareList);
        return SonList;
    }
}