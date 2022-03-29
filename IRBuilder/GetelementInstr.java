package IRBuilder;

import javax.swing.text.Segment;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class GetelementInstr extends BaseInstr{
    public InstrSeg Mode;
    InstrSeg Op;
    public String Rd;
    public String RdType;
    public String Ptr;
    public String Index;
    public Boolean IsIndexImm;
    String IndexType;
    public Integer Offset;
    String OffsetType;
    public Boolean IsPtrGlobal;
    GetelementInstr(InstrSeg op, InstrSeg mode, String rdType,String rd,  String ptr,  String index,Integer offset, Boolean isPtrGlobal){
        super(InstrType.Getelement);
        Op = op;
        Mode = mode;
        Rd = rd;
        RdType = rdType;
        Ptr =ptr;
        Index = index;
        IndexType = "i32";
        Offset = offset;
        OffsetType = "i32";
        IsPtrGlobal = isPtrGlobal;
        IsIndexImm = index.charAt(0) == '-'|| (index.charAt(0)  <= '9' && index.charAt(0) >= '0');
    }
    public void Output(FileWriter Writer) throws IOException{
        Writer.write("%"+Rd +" = "+ Op+" "+ RdType +", ");
        if(IsPtrGlobal) Writer.write("@"+Ptr);
        else Writer.write("%"+Ptr);
        if(Objects.equals(Mode, InstrSeg.index)){
            Writer.write( ", "+IndexType + " " );
            if(IsIndexImm) Writer.write(Index);
            else Writer.write("%"+Index);
        }
        else if(Objects.equals(Mode, InstrSeg.offset)) Writer.write(", "+IndexType + " " + 0 +", " + OffsetType + " " + Offset);
        Writer.write('\n');
    }
}
