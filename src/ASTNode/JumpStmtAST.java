package ASTNode;
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
};