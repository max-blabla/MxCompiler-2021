package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class LoadInstr extends BaseInstr{
    String Op;
    public String Rd;
    public String RdType;
    public String RsPtr;
    String RsPrtType;
    public Boolean IsPtrGlobal;
    LoadInstr(String op, String rd, String rdType, String rsPtr, String rsPrtType, Boolean isPtrGlobal){
        super(InstrType.Load);
        Op = op;
        Rd = rd;
        RdType = rdType;
        RsPtr  = rsPtr;
        RsPrtType = rsPrtType;
        IsPtrGlobal = isPtrGlobal;
    }

    //@Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write("%"+Rd + " = " + Op + " "  + RdType + ", "+RsPrtType + " ");
        if(IsPtrGlobal) Writer.write(  "@"+RsPtr);
        else Writer.write(  "%"+RsPtr);
        Writer.write('\n');
    }
}
