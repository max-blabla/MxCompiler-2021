package CodeGenerator;

import java.io.PrintStream;

public class LCode extends BaseCode{
    String Op;
    String Rd;
    String Rs;
    Integer Offset;
    LCode(String op,String rd, String rs,Integer offset,Integer Line){
        super(CodeType.LType,Line);
        Op = op;
        Rd = rd;
        Rs = rs;
        Offset = offset;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rd + " ," +Offset  + "("+Rs+")");
        OutputLine(Stream);
        Stream.print("\n");
    }
}
