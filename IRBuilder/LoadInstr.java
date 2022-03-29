package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class LoadInstr extends BaseInstr{
    InstrSeg Op;
    public String Rd;
    public String RdType;
    public String RsPtr;
    public String RsPrtType;
    public Boolean IsPtrGlobal;
    LoadInstr(InstrSeg op, String rd, String rdType, String rsPtr, String rsPrtType, Boolean isPtrGlobal){
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
