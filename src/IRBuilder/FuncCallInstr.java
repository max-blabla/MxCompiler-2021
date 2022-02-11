package IRBuilder;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FuncCallInstr extends BaseInstr{
    String Op;
    public String Rd;
    String Type;
    public String FuncName;
    public List<String> ParamType;
    public List<String> Param;
    public List<Boolean> IsGlobal;
    FuncCallInstr(String op, String rd, String type, String funcName, List<String> paramTypes,List<String> params){
        super(InstrType.FuncCall);
        Op = op;
        Rd = rd;
        Type = type;
        Param = params;
        FuncName = funcName;
        ParamType = paramTypes;
        IsGlobal = new ArrayList<>();
        for(String paramName :params)
            IsGlobal.add(!paramName.contains("."));
    }

    //@Override
    public void Output(FileWriter Writer) throws IOException {
        if(!Objects.equals(Type, "void")&&!Objects.equals(Rd, "")) Writer.write("%"+Rd+ " = ");
        Writer.write(Op+" "+Type+" @" +FuncName );
        Writer.write("(");
        for(int i = 0 ; i< ParamType.size()-1;++i){
            Writer.write(ParamType.get(i)+" ");
            if(IsGlobal.get(i)) Writer.write("@");
            else  Writer.write("%");
            Writer.write( Param.get(i)+", ");
        }
        if(ParamType.size() !=0 ){
            Writer.write(ParamType.get(ParamType.size()-1)+" ");
            if(IsGlobal.get(Param.size()-1)) Writer.write("@");
            else  Writer.write("%");
            Writer.write( Param.get(ParamType.size()-1));
        }
        Writer.write(")\n");

    }
}
