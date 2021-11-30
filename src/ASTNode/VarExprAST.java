package ASTNode;
public class VarExprAST extends ExprAST{
    String VarName;
    VarExprAST(ExprAST l, ExprAST r){
        ThisExprType = ExprType.Variable;
        LeftSonExpr = l;
        RightSonExpr = r;
    }
};