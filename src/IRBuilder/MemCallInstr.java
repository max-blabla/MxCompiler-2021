package IRBuilder;

public class MemCallInstr extends BaseInstr{
    String Op;
    String Struct;
    String Index;
    String IndexType;
    String Offset;
    String OffsetType;
    String Rd;
    MemCallInstr(String op,String rd,String struct,String offset){
        super(InstrType.MemCall);
        Index = "0";
        Op = op;
        Rd = rd;
        Struct = struct;
        Offset = offset;
        IndexType = "i32";
        OffsetType = "i32";
    }
}
