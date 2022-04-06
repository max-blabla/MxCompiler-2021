package CodeGenerator;

import org.antlr.v4.codegen.model.SrcOp;

import java.io.PrintStream;

public class BCode extends BaseCode{
    OpType Op;
    RegType Rs1;
    Integer VirRs1;
    String Block;
    RegType Rs2;
    Integer VirRs2;
    BCode(OpType op, Integer virRs1, Integer virRs2, String block,Integer Line){
        super(CodeType.BType,Line);
        Op = op;
        VirRs1  = virRs1;
        VirRs2 = virRs2;
        Block = block;
    }

    void SetTrue(Triple tr){
        Rs1 = tr.Rs1;
        Rs2 = tr.Rs2;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rs1 + " ," + Rs2 + " ," + Block);
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream){
        Stream.print("\t"+Op + " " + VirRs1 + " ," + VirRs2 + " ," + Block);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
