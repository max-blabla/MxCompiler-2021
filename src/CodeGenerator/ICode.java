package CodeGenerator;

import java.io.PrintStream;

public class ICode extends BaseCode{
    String Op;
    String Rd;
    String Rs;
    String Imm;
    ICode(String  op, String rd,String rs,String imm,Integer Line){
        super(CodeType.IType,Line);
        Op = op;
        Rd = rd;
        Rs = rs;
        Imm = imm;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " "+ Rd + " ," + Rs + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
