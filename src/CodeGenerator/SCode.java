package CodeGenerator;

import java.io.PrintStream;

public class SCode extends BaseCode{
    OpType Op;
    RegType Rs1;
    Integer VirRs1;
    RegType Rs2;
    Integer VirRs2;
    String Imm;
    SCode(OpType op,Integer  virRs1,Integer  virRs2, RegType rs1,RegType rs2,String imm,Integer Line){
        super(CodeType.SType, Line);
        Op = op;
        VirRs1  = virRs1;
        VirRs2 = virRs2;
        Rs1 = rs1;
        Rs2 = rs2;
        Imm = imm;
    }

    void SetTrue(Triple tr){
        Rs1 = tr.Rs1;
        Rs2 = tr.Rs2;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rs1 + " ," +Imm  + "("+Rs2+")");
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op + " " + VirRs1 + " ," +Imm  + "("+VirRs2+")");
        OutputLine(Stream);
        Stream.print("\n");
    }
}
