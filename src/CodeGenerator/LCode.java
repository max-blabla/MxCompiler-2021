package CodeGenerator;

import java.io.PrintStream;

public class LCode extends BaseCode{
    OpType Op;
    RegType Rd;
    Integer VirRd;
    RegType Rs;
    Integer VirRs;
    String Imm;
    LCode(OpType op,Integer virRd, Integer virRs,RegType rd , RegType rs,String imm,Integer Line){
        super(CodeType.LType,Line);
        Op = op;
        VirRs  = virRs;
        VirRd = virRd;
        Rd = rd;
        Rs = rs;
        Imm = imm;
    }

    void SetTrue(Triple tr){
        Rd  = tr.Rd;
        Rs = tr.Rs1;
    }

    void SetTrueImm(String trImm){
        Imm = trImm;
    }


    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," +Imm  + "("+Rs+")");
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op + " " + VirRd + " ," +Imm  + "("+VirRs+")");
        OutputLine(Stream);
        Stream.print("\n");
    }
}
