package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class ConditionAST extends BaseAST{
    ExprAST Expr;

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof  ExprAST){
            Expr = (ExprAST)Son;
            Son.Father = this;
        }
        else new BuildError("ConditionAST","InsertSon","");
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(Expr);
        return SonList;
    }
}
