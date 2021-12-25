package IRBuilder;


import java.util.List;

public class FuncCallInstr extends BaseInstr{
    String Rd;
    String Type;
    List<String> ParamType;
    List<String> Param;
    FuncCallInstr(String rd, String type, List<String> paramType,List<String> param){
        super(InstrType.FuncCall);
        Rd = rd;
        Type = type;
        Param = param;
        ParamType = paramType;
    }
}
