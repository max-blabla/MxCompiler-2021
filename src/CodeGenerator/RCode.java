package CodeGenerator;

import java.io.PrintStream;

public class RCode extends BaseCode{
    String Op;
    String Rd;
    String Rs1;
    String Rs2;
    RCode(String op,String rd,String rs1,String rs2,Integer Line){
        super(CodeType.RType,Line);
        Op = op;
        Rd = rd;
        Rs1 = rs1;
        Rs2 = rs2;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," + Rs1 + " ," + Rs2 );
        OutputLine(Stream);
        Stream.print("\n");
    }
}
