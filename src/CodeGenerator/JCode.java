package CodeGenerator;

import java.io.PrintStream;

public class JCode extends BaseCode{
    String Op;
    String Block;
    JCode(String op,String block,Integer Line){
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
}
