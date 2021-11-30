package ASTNode;
public class MemCallExprAST extends ExprAST {
    MemCallExprAST(ExprAST l, ExprAST r){
        ThisExprType = ExprType.MemCall;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
};