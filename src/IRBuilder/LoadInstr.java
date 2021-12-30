package IRBuilder;

public class LoadInstr extends BaseInstr{
    String Op;
    String Rd;
    String RdType;
    String RsPtr;
    String RsPrtType;
    LoadInstr(String op, String rd, String rdType, String rsPtr, String rsPrtType){
        super(InstrType.Load);
        Op = op;
        Rd = rd;
        RdType = rdType;
        RsPtr  = rsPtr;
        RsPrtType = rsPrtType;
    }
}
