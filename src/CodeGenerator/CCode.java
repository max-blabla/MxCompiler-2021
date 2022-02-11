package CodeGenerator;

import java.io.PrintStream;

public class CCode extends BaseCode{
    String Op;
    String Rs;
    CCode(String op,String rs,Integer Line){
        super(CodeType.CType,Line);
        Op = op;
        Rs = rs;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op+ " " + Rs);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
