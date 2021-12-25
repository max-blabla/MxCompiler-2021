package IRBuilder;

import java.util.List;

class IRFunc{
    String FuncName;
    String RetType;
    IRBlock Start;
    Boolean IsUsed;
    public String getIrRetType(){return "i32";}

    public String getFuncName() {
        return FuncName;
    }
}