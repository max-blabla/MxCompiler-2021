package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class NewTypeAST extends ExprAST{
    String Type;
    List<ExprAST> ExprList;
    public NewTypeAST(String type){
        super(ExprType.New);
        ExprList = new ArrayList<>();
        Type = type;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST){
            ExprAST NewSon = (ExprAST) Son;
            NewSon.Father = this;
            ExprList.add(NewSon);
        }
        else if(Son instanceof IdAST ){
            IdAST SingleType = (IdAST) Son;
            SingleType.Father = this;
        }
        else new BuildError("NewTypeAST","InsertSon","");
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(LeftSonExpr);
        SonList.add(RightSonExpr);
        SonList.addAll(ExprList);
        return SonList;
    }
    public Boolean IsNewArray(){
        return Type.contains("[]");
    }

    public String getNewType() {
        return Type;
    }

    public List<ExprAST> getExprList() {
        return ExprList;
    }
}
