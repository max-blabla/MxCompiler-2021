package IRBuilder;


import java.util.List;

public class FuncCallInstr extends BaseInstr{
    String Op;
    String Rd;
    String Type;
    String FuncName;
    List<String> ParamType;
    List<String> Param;
    FuncCallInstr(String op, String rd, String type, String funcName, List<String> paramType,List<String> param){
        super(InstrType.FuncCall);
        Op = op;
        Rd = rd;
        Type = type;
        Param = param;
        FuncName = funcName;
        ParamType = paramType;
    }
}
