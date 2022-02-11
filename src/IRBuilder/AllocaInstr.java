package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class AllocaInstr extends BaseInstr{
    public String Rd;
    public String Type;
    public AllocaInstr(String rd, String type){
        super(InstrType.Alloca);
        Rd = rd;
        Type = type;
    }

    //@Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write("%"+Rd +" = alloca " +Type  +'\n');
    }
}
