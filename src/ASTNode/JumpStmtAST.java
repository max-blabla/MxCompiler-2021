package ASTNode;

import java.util.ArrayList;
import java.util.List;

public class JumpStmtAST extends StmtAST{
    Boolean IsBreak;
    public JumpStmtAST(Boolean isBreak){
        IsBreak = isBreak;
    }

    @Override
    public void InsertSon(BaseAST Son) {
        new BuildError("JumpStmt","InsertSon","Wrong Build");
    }

    public Boolean isBreak(){
        return IsBreak;
    }
    public List<BaseAST> GetSon(){
        List<BaseAST> SonList = new ArrayList<>();
        SonList.add(null);
        return SonList;
    }
};