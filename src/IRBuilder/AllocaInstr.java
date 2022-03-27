package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class AllocaInstr extends BaseInstr{
    InstrSeg Seg;
    public String Rd;
    public String Type;
    public AllocaInstr(InstrSeg seg,String rd, String type){
        super(InstrType.Alloca);
        Rd = rd;
        Type = type;
        Seg = seg;
    }

    //@Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write("%"+Rd +" = alloca " +Type  +'\n');
    }
}
