package ASTNode;
import java.util.ArrayList;
import java.util.List;
public class VarStmtAST extends StmtAST{
    VarDeclAST VarDecl;

    @Override
    public void InsertSon(BaseAST Son) {
        VarDecl  = (VarDeclAST) Son;
        VarDecl.Father = this;
    }

    public String getType(){
        return VarDecl.getType();
    }

    public List<VarDeclareAST> getVarDeclList(){
        return VarDecl.getVarDeclareList();
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(VarDecl);
        return SonList;
    }
}
