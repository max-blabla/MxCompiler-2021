
package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ClassDeclAST extends DeclAST{
    List<DeclAST> DeclList = new ArrayList<>();
    IdAST ClassName;
    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof IdAST){
            ClassName = (IdAST) Son;
            ClassName.Father = this;
        }
        else if(Son instanceof DeclAST){
            DeclAST NewDecl = (DeclAST) Son;
            NewDecl.Father = this;
            DeclList.add(NewDecl);
        }
        else {
            new BuildError("ClassDeclAST","InsertSon","");
        }
    }

    public String getClassName(){
        return ClassName.Label;
    }
}