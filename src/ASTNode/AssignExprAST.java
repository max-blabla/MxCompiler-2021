package ASTNode;
public class AssignExprAST extends ExprAST{
    AssignExprAST(ExprAST l, ExprAST r){
        ThisExprType = ExprType.Assign;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
}
