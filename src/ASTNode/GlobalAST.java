package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class GlobalAST extends DeclAST{
    public List<DeclAST> DeclList = new ArrayList<>();
    public GlobalAST(){};

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof DeclAST){
            DeclAST NewDecl = (DeclAST) Son;
            NewDecl.Father = this;
            DeclList.add(NewDecl);
        }
        else new BuildError("GlobalAST","InsertSon","");
    }
}