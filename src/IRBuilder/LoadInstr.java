package IRBuilder;

public class LoadInstr extends BaseInstr{
    String Rd;
    String RdType;
    String RsPtr;
    String RsPrtType;
    LoadInstr(String rd, String rdType, String rsPtr, String rsPrtType){
        super(InstrType.Load);
        Rd = rd;
        RdType = rdType;
        RsPtr  = rsPtr;
        RsPrtType = rsPrtType;
    }
}
