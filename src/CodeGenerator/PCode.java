package CodeGenerator;

import java.io.PrintStream;

public class PCode extends BaseCode{
    OpType Op;
    RegType Rd;
    RegType Rs;
    Integer VirRs;
    Integer VirRd;
    PCode(OpType op, Integer virRd, Integer virRs,RegType rd,RegType rs,Integer Line){
        super(CodeType.PType,Line);
        Op = op;
        VirRs  = virRs;
        VirRd = virRd;
        Rd = rd;
        Rs = rs;
    }

    void SetTrue(Triple tr){
        Rd = tr.Rd;
        Rs = tr.Rs1;
    }

    @Override
    public void Output(PrintStream Stream) {

        Stream.print("\t"+Op+" "+Rd+" ,"+Rs);
        OutputLine(Stream);
        Stream.println();
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op+" "+VirRd+" ,"+VirRs);
        OutputLine(Stream);
        Stream.println();

    }
}
