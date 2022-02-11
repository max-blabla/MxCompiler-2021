package CodeGenerator;

import java.io.PrintStream;

public class UCode extends BaseCode{
    String Rd;
    String Op;
    String Imm;
    UCode(String op, String rd, String imm,Integer Line){
        super(CodeType.UType,Line);
        Rd = rd;
        Imm = imm;
        Op = op;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
