package ASTNode;

import ASTNode.ExprAST;
import ASTNode.StmtAST;

public class ForStmtAST extends StmtAST {
    ConditionAST ConditionExpr;
    ForInitAST InitExpr;
    ExprAST IncrExpr;
    StmtAST LoopStmt;

    public ForStmtAST(){
        InitExpr = null;
        IncrExpr = null;
        ConditionExpr = null;
    }
    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ConditionAST){
            ConditionExpr = (ConditionAST) Son;
            ConditionExpr.Father = this;
        }
        else if(Son instanceof ForInitAST) {
            InitExpr = (ForInitAST) Son;
            InitExpr.Father = this;
        }
        else if(Son instanceof ExprAST){
            IncrExpr = (ExprAST) Son;
            IncrExpr.Father = this;
        }
        else if(Son instanceof StmtAST){
            LoopStmt = (StmtAST) Son;
            LoopStmt.Father = this;
        }
        else new BuildError("ForStmtAST","InsertSon","");
    }

    public ExprAST getInitExpr(){
        return InitExpr.Exp;
    }

    public VarDeclAST getInitStmt(){
        return InitExpr.Init;
    }

    public ExprAST getConditionExpr(){
        return ConditionExpr.Expr;
    }

    public ExprAST getIncrExpr(){
        return  IncrExpr;
    }

    public StmtAST getLoopStmt(){
        return LoopStmt;
    }

};