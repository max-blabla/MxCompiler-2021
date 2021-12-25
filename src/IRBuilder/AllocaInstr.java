package IRBuilder;

public class AllocaInstr extends BaseInstr{
    String Rd;
    String Type;
    AllocaInstr(String rd, String type){
        super(InstrType.Alloca);
        Rd = rd;
        Type = type;
    }
}
