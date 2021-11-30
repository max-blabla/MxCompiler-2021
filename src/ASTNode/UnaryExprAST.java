package ASTNode;
public class UnaryExprAST extends ExprAST {
    UnaryExprAST(ExprAST l, ExprAST r, ExprType et){
        ThisExprType = et;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
};