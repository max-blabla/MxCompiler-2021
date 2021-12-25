package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class SuiteAST extends StmtAST{
    List<StmtAST> StmtList = new ArrayList<>();

    public SuiteAST(){}

    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof StmtAST){
            StmtAST NewStmt = (StmtAST) Son;
            NewStmt.Father = this;
            StmtList.add(NewStmt);
        }
        else new BuildError("SuiteAST","InsertSon","");
    }

    public List<StmtAST> getStmtList(){
        return StmtList;
    }
};