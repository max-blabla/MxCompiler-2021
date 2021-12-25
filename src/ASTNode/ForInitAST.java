package ASTNode;

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
}
