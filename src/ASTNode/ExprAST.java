package ASTNode;
public class ExprAST extends BaseAST{
    ExprAST LeftSonExpr;
    ExprAST RightSonExpr;
    ExprType ThisExprType;

    public ExprAST(ExprType exprType){
        LeftSonExpr = null;
        RightSonExpr = null;
        ThisExprType = exprType;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ExprAST){
            if(LeftSonExpr == null){
                LeftSonExpr = (ExprAST) Son;
                LeftSonExpr.Father = this;
            }
            else if(RightSonExpr == null){
                RightSonExpr = (ExprAST) Son;
                RightSonExpr.Father = this;
            }
            else new BuildError("ExprAST","InsetSon","Only Two Expr Needed");
        }
        else new BuildError("ExprAST","InsertSon","");
    }

    public ExprType getType(){return ThisExprType;}
    public ExprAST getLeftSonExpr(){return LeftSonExpr;}
    public ExprAST getRightSonExpr(){return RightSonExpr;}
};