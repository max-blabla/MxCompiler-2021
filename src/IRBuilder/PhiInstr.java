package IRBuilder;

import org.stringtemplate.v4.ST;

import java.io.FileWriter;
import java.io.IOException;
public class PhiInstr extends  BaseInstr{
    String Op;
    public String Mode;
    public String Rd;
    String RdType;
    PhiInstr(String op, String mode, String rd, String rdType) {
        super(InstrType.Phi);
        Op = op;
        Mode = mode;
        Rd = rd;
        RdType = rdType;
    }

    @Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write(Op+ ' ' + Mode + ' ' + RdType + " %" + Rd + '\n');
    }
}
