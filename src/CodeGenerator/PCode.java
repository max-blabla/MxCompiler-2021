package CodeGenerator;

import java.io.PrintStream;

public class PCode extends BaseCode{
    String Op;
    String Rd;
    String Rs;
    PCode(String op, String rd, String rs,Integer Line){
        super(CodeType.PType,Line);
        Op = op;
        Rd = rd;
        Rs = rs;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.println("\t"+Op+" "+Rd+" ,"+Rs);
    }
}
