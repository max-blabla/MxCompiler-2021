package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ForInitAST extends BaseAST{
    VarDeclAST Init;
    ExprAST Exp;
    public ForInitAST(){
        Init = null;
        Exp  = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof VarDeclAST){
            if(Exp == null){
                Init = (VarDeclAST) Son;
                Son.Father = this;
            }
            else new BuildError("ForInitAST","InsertSon","Wrong Initial");
        }
        else if(Son instanceof ExprAST){
            if(Exp == null){
                Exp = (ExprAST) Son;
                Son.Father = this;
            }
            else new BuildError("ForInitAST","InsertSon","Wrong Initial");
        }
        else new BuildError("ForInitAST","InsertSon","");
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(Init);
        SonList.add(Exp);
        return SonList;
    }
}
