package IRBuilder;

public abstract class BaseInstr {
    InstrType Type;
    BaseInstr(InstrType instrType){
        Type = instrType;
    }
}
