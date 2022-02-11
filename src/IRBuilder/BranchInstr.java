package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class BranchInstr extends BaseInstr{
    String Op;
    public String Label1;
    public String Label2;
    public String Condition;
    BranchInstr(String op, String label1,String label2, String condition){
        super(InstrType.Branch);
        Op = op;
        Label1 = label1;
        Label2 = label2;
        Condition = condition;
    }
    public void Output(FileWriter Writer)throws IOException {
       // Writer.write(Op+" "+Condition + " " + Label1 + " " + Label2);
     //   Writer.write(Op+" %"+ Condition + " " + Label1);
        if(!Objects.equals(Condition, "")){
            Writer.write(Op+" i8 ");
            Writer.write( "%");
            Writer.write(Condition + " " + Label1+", "+Label2);
        }
        else Writer.write(Op + " " + Label1);
        Writer.write('\n');
    }
}
