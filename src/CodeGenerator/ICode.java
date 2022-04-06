package CodeGenerator;

import java.io.PrintStream;

public class ICode extends BaseCode{
    OpType Op;
    RegType Rd;
    Integer VirRd;
    RegType Rs;
    Integer VirRs;
    String Imm;
    ICode(OpType  op,  Integer virRd, Integer virRs,String imm,RegType rd,RegType rs,Integer Line){
        super(CodeType.IType,Line);
        Op = op;
        VirRs  = virRs;
        VirRd = virRd;
        Imm = imm;
        Rd = rd;
        Rs = rs;
    }

    void SetTrue(Triple tr){
        Rd = tr.Rd;
        Rs = tr.Rs1;
    }


    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " "+ Rd + " ," + Rs + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op + " "+ VirRd + " ," + VirRs + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
