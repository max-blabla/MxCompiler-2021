package IRBuilder;

public class OperationInstr extends BaseInstr{
    String Mode;
    String Op;
    String Rs1;
    String Rs2;
    String Rd;
    String Type1;
    String Type2;
    OperationInstr(String op, String rs1, String rs2, String type1 , String type2, String rd, String mode){
        super(InstrType.Operation);
        Op = op;
        Rs1 = rs1;
        Rs2 = rs2;
        Rd = rd;
        Type1 = type1;
        Type2 = type2;
        Mode = mode;
    }
}
