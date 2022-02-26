package CodeGenerator;

import java.io.PrintStream;

public class UCode extends BaseCode{
    RegType Rd;
    Integer VirRd;
    OpType Op;
    String Imm;
    UCode(OpType op, Integer virRd,RegType rd, String imm,Integer Line){
        super(CodeType.UType,Line);
        VirRd = virRd;
        Imm = imm;
        Op = op;
        Rd = rd;
    }

    void SetTrue(Triple tr){
        Rd = tr.Rd;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
