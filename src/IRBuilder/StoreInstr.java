package IRBuilder;

public class StoreInstr extends BaseInstr{
    String Op;
    String Rs;
    String RsType;
    String Ptr;
    String PtrType;
    public StoreInstr(String op,String rs, String rsType, String ptr, String ptrType){
        super(InstrType.Store);
        Op = op;
        Rs = rs;
        RsType = rsType;
        Ptr = ptr;
        PtrType = ptrType;
    }
}
