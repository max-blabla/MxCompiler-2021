package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static IRBuilder.InstrSeg.nullseg;

public class OperationInstr extends BaseInstr{
    public InstrSeg Mode;
    public InstrSeg Op;
    public String Rs1;
    public String Rs2;
    public String Rd;
    String Type1;
    String Type2;
    public Boolean IsRsImm1;
    public Boolean IsRsImm2;
    OperationInstr(InstrSeg op, String rd,  String rs1, String rs2, String type1 , String type2,InstrSeg mode){
        super(InstrType.Operation);
        Op = op;
        Rs1 = rs1;
        Rs2 = rs2;
        Rd = rd;
        Type1 = type1;
        Type2 = type2;
        Mode = mode;
        IsRsImm1 ='0' <= rs1.charAt(0) && rs1.charAt(0) <= '9' || rs1.equals("true") ||rs1.equals("false") || rs1.charAt(0) == '-';
        IsRsImm2 ='0' <= rs2.charAt(0) && rs2.charAt(0) <= '9'|| rs2.equals("true") || rs2.equals("false") || rs2.charAt(0) == '-';
    }

    @Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write("%"+Rd + " = "+ Op );
        if(!Objects.equals(Mode, nullseg))  Writer.write( " " + Mode );
        Writer.write(", " + Type1 +" ");
        if(!IsRsImm1) Writer.write("%");
        Writer.write(Rs1 + ", " + Type2 + " ");
        if(!IsRsImm2) Writer.write("%");
        Writer.write(Rs2 + '\n');
    }
}
