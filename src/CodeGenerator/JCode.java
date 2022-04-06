package CodeGenerator;

import java.io.PrintStream;

public class JCode extends BaseCode{
    OpType Op;
    String Block;
    JCode(OpType op,String block,Integer Line){
        super(CodeType.JType,Line);
        Op = op;
        Block = block;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Block);
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+ Op + " " + Block);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
