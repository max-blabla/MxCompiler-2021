package ASTNode;

import org.antlr.v4.codegen.model.Loop;

import java.util.ArrayList;
import java.util.List;

public class WhileStmtAST extends  StmtAST{
    ConditionAST ConditionExpr;
    StmtAST LoopStmt;
    public WhileStmtAST(){}

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof ConditionAST){
            ConditionExpr = (ConditionAST) Son;
            ConditionExpr.Father = this;
        }
        else if(Son instanceof StmtAST){
            LoopStmt = (StmtAST) Son;
            LoopStmt.Father = this;
        }
        else new BuildError("WhileStmtAST","InsertSon","");
    }

    public ExprAST getConditionExpr(){
        return ConditionExpr.Expr;
    }

    public StmtAST getLoopStmt(){
        return LoopStmt;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(ConditionExpr);
        SonList.add(LoopStmt);

        return SonList;
    }
};