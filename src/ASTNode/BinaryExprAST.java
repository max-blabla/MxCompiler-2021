
package ASTNode;
public class BinaryExprAST extends ExprAST {
    BinaryExprAST(ExprAST l, ExprAST r, ExprType et){
        ThisExprType = et;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
};