package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class StoreInstr extends BaseInstr{
    String Op;
    public String Rs;
    public String RsType;
    public String Ptr;
    String PtrType;
    public Boolean IsPtrGlobal;
    public Boolean IsRsGlobal;
    public StoreInstr(String op,String rs, String rsType, String ptr, String ptrType,Boolean isPtrGlobal,Boolean isRsGlobal){
        super(InstrType.Store);
        Op = op;
        Rs = rs;
        RsType = rsType;
        Ptr = ptr;
        PtrType = ptrType;
        IsPtrGlobal = isPtrGlobal;
        IsRsGlobal = isRsGlobal;
    }

   // @Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write(Op+ ' ' + RsType + ' ' );
        if(!IsRsGlobal)  Writer.write("%"+Rs );
        else Writer.write("@"+Rs);
        Writer.write( ", " + PtrType+ " ");
        if(!IsPtrGlobal)    Writer.write("%"+Ptr) ;
        else  Writer.write("@"+Ptr) ;
        Writer.write( '\n');
    }
}
