package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class IfStmtAST extends StmtAST{
    ConditionAST ConditionExpr;
    StmtAST FalseStmt;
    StmtAST TrueStmt;

    public IfStmtAST(){
        FalseStmt = null;
        TrueStmt = null;
    }
    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof StmtAST){
            if(TrueStmt == null) {
                TrueStmt = (StmtAST) Son;
                TrueStmt.Father = this;
            }
            else if(FalseStmt == null) {
                FalseStmt = (StmtAST) Son;
                FalseStmt.Father = this;
            }
            else new BuildError("IfStmtAST","InsertSon","Only Two Stmt Needed");
        }
        else if(Son instanceof  ConditionAST) {
            ConditionExpr = (ConditionAST) Son;
            ConditionExpr.Father = this;
        }
        else new BuildError("IfStmtAST","InsertSon","");
    }

    public ExprAST getConditionExpr(){
        return ConditionExpr.Expr;
    }

    public StmtAST getTrueStmt(){
        return TrueStmt;
    }

    public StmtAST getFalseStmt(){
        return FalseStmt;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(ConditionExpr);
        SonList.add(FalseStmt);
        SonList.add(TrueStmt);

        return SonList;
    }
};