package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class VarDeclareAST extends BaseAST{
    IdAST Id;
    ExprAST Expr;//可以为Null

    public VarDeclareAST(){
        Expr = null;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST) {
            Expr = (ExprAST) Son;
            Expr.Father = this;
        }
        else if(Son instanceof  IdAST){
            Id = (IdAST) Son;
            Id.Father = this;
        }
        else new BuildError("VarDeclareAST","InsertSon","");
    }

    public String getId(){
        return Id.Label;
    }

    public ExprAST getAssignExpr(){
        if(Expr != null) {
            ExprAST AssignExpr = new ExprAST(ExprType.Assign);
            AssignExpr.LeftSonExpr = new LabelAST(Id.Label);
            AssignExpr.RightSonExpr = Expr;
            AssignExpr.LeftSonExpr.Father = AssignExpr;
            AssignExpr.RightSonExpr.Father = AssignExpr;
            return  AssignExpr;
        }
        else return null;

    }

    public ExprAST getExpr(){
        return  Expr;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(Id);
        SonList.add(Expr);
        return SonList;
    }
}
