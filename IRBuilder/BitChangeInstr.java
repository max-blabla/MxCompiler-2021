package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class BitChangeInstr extends  BaseInstr{
    String Op;
    String RsType;
    String Rs;
    String RdType;
    String Rd;
    BitChangeInstr(String op,String rd, String rdType,String rs,String rsType){
        super(InstrType.BitChange);
        Op= op;
        Rd = rd;
        RdType = rdType;
        Rs = rs;
        RsType = rsType;
    }
    public void Output(FileWriter Writer) throws IOException{
        Writer.write("%"+Rd + " = " + Op + " %" + Rs + " from " + RsType + " to " + RdType);
    }
}
