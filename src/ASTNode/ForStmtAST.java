package ASTNode;

import ASTNode.ExprAST;
import ASTNode.StmtAST;

public class ForStmtAST extends StmtAST {
    ExprAST ConditionExpr;
    StmtAST VarDeclExpr;
    ExprAST IncrExpr;
    StmtAST LoopStmt;
};