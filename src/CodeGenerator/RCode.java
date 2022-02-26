package CodeGenerator;

import java.io.PrintStream;

public class RCode extends BaseCode{
    OpType Op;
    RegType Rd;
    Integer VirRd;
    RegType Rs1;
    Integer VirRs2;
    Integer VirRs1;
    RegType Rs2;
    RCode(OpType op, Integer virRd,Integer virRs1,Integer virRs2,RegType rd,RegType rs1, RegType rs2,Integer Line){
        super(CodeType.RType,Line);
        Op = op;
        VirRs1  = virRs1;
        VirRs2 = virRs2;
        VirRd = virRd;
        Rd = rd;
        Rs1 = rs1;
        Rs2 = rs2;
    }

    void SetTrue(Triple tr){
        Rd = tr.Rd;
        Rs1 = tr.Rs1;
        Rs2 = tr.Rs2;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," + Rs1 + " ," + Rs2 );
        OutputLine(Stream);
        Stream.print("\n");
    }
}
