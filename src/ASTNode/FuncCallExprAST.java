package ASTNode;
public class FuncCallExprAST extends ExprAST {
    FuncCallExprAST(ExprAST l, ExprAST r){
        ThisExprType = ExprType.FuncCall;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
};