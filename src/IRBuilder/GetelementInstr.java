package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class GetelementInstr extends BaseInstr{
    public String Mode;
    String Op;
    public String Rd;
    public String RdType;
    public String Ptr;
    String PtrType;
    public String Index;
    public Boolean IsIndexImm;
    String IndexType;
    public Integer Offset;
    String OffsetType;
    public Boolean IsPtrGlobal;
    Boolean IsRemovable;
    GetelementInstr(String op,String mode,String rd,String rdType,String ptr,String ptrType,String index,String indexType,Integer offset,String offsetType,Boolean isPtrGlobal){
        super(InstrType.Getelement);
        Op = op;
        Mode = mode;
        Rd = rd;
        RdType = rdType;
        Ptr =ptr;
        PtrType = ptrType;
        Index = index;
        IndexType = indexType;
        Offset = offset;
        OffsetType = offsetType;
        IsPtrGlobal = isPtrGlobal;
        IsIndexImm = index.charAt(0) == '-'|| (index.charAt(0)  <= '9' && index.charAt(0) >= '0');
    }
    public void Output(FileWriter Writer) throws IOException{
        Writer.write("%"+Rd +" = "+ Op+" "+ RdType +", ");
        if(IsPtrGlobal) Writer.write("@"+Ptr);
        else Writer.write("%"+Ptr);
        if(Objects.equals(Mode, "index")){
            Writer.write( ", "+IndexType + " " );
            if(IsIndexImm) Writer.write(Index);
            else Writer.write("%"+Index);
        }
        else if(Objects.equals(Mode, "offset")) Writer.write(", "+IndexType + " " + 0 +", " + OffsetType + " " + Offset);
        Writer.write('\n');
    }
}
