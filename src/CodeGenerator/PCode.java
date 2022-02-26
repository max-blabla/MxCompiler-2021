package CodeGenerator;

import java.io.PrintStream;

public class PCode extends BaseCode{
    OpType Op;
    RegType Rd;
    RegType Rs;
    Integer VirRs;
    Integer VirRd;
    PCode(OpType op, Integer virRd, Integer virRs,Integer Line){
        super(CodeType.PType,Line);
        Op = op;
        VirRs  = virRs;
        VirRd = virRd;
    }

    void SetTrue(Triple tr){
        Rd = tr.Rd;
        Rs = tr.Rs1;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.println("\t"+Op+" "+Rd+" ,"+Rs);
    }
}
