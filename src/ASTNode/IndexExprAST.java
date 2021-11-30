package ASTNode;
public class IndexExprAST extends ExprAST{
    IndexExprAST(ExprAST l, ExprAST r){
        ThisExprType = ExprType.MemCall;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
}
