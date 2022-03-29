package CodeGenerator;

import java.io.PrintStream;

public class MCode extends BaseCode{
    String Op;
    MCode(String op,Integer Line){
        super(CodeType.MType,Line);
        Op = op;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
