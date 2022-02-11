
package ASTNode;

import javax.swing.*;
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
    public List<DeclAST> getDeclList(){return DeclList;}
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(ClassName);
        SonList.addAll(DeclList);
        return SonList;
    }
}