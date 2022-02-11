package CodeGenerator;

import java.io.PrintStream;

public class SCode extends BaseCode{
    String Op;
    String Rs1;
    String Rs2;
    Integer Imm;
    SCode(String op,String rs1,String rs2,Integer imm,Integer Line){
        super(CodeType.SType, Line);
        Op = op;
        Rs1  = rs1;
        Rs2 = rs2;
        Imm = imm;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rs1 + " ," +Imm  + "("+Rs2+")");
        OutputLine(Stream);
        Stream.print("\n");
    }
}
