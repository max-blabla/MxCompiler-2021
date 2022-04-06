package CodeGenerator;

import java.io.PrintStream;

public class BPCode extends BaseCode{
    OpType Op;
    RegType Rs;
    String Label;
    Integer VirRs;
    BPCode(OpType op,Integer virRs,String label,Integer line){
        super(CodeType.BPType,line);
        Op = op;
        VirRs = virRs;
        Label = label;
    }

    void SetTrue(Triple tr){
        Rs = tr.Rs1;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " "+ Rs + " ," + Label);
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op + " "+ VirRs + " ," + Label);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
