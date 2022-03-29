package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class ReturnInstr extends BaseInstr{
    InstrSeg Op;
    public String Type;
    public String Rs;
    ReturnInstr(InstrSeg op, String type, String rd){
        super(InstrType.Return);
        Op = op;
        Type = type;
        Rs = rd;
  }

    //@Override
    public void Output(FileWriter Writer) throws IOException {
        Writer.write(Op.toString()+' '+Type);
        if(!Objects.equals(Type, "void")) {
            Writer.write(" %" + Rs);
        }
        Writer.write('\n');
    }
}
