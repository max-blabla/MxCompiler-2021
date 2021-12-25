package ASTNode;

import java.util.List;

public class NewTypeAST extends ExprAST{
    String Type;
    List<ExprAST> ExprList;
    public NewTypeAST(String type){
        super(ExprType.Null);
        Type = type;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST){
            ExprAST NewSon = (ExprAST) Son;
            NewSon.Father = Son;
            ExprList.add(NewSon);
        }
        else new BuildError("NewTypeAST","InsertSon","");
    }
}
