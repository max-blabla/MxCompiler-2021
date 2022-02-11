package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;

public class IRFunc{
    String FuncName;
    String RetType;
    IRBlock Start;
    IRBlock End;
    List<BaseInstr> InlineInstr;
    Boolean IsUsed;
    Boolean IsLinked;
    Boolean IsInline;
    Integer RegCnt;
    String ModuleName;
    HashMap<String,IRValue> Params;
    List<IRValue> ParamList;
    public IRFunc(){
        Params = new HashMap<>();
        ParamList = new ArrayList<>();
        IsUsed = false;
        RegCnt = 0;
        IsLinked = false;
        IsInline = false;
    }
   // public String getIrRetType(){return "i32";}
    public String getFuncName() {
        return FuncName;
    }
   // public IRBlock getLastBlock(){
    //    return Start.getLastSubBlock();
   // }
    public String NewReg(){
        String Ret =  FuncName + "." + RegCnt;
        ++RegCnt;
        return Ret;
    }
    public void PutParam(String Key,IRValue Value){
        Params.put(Key,Value);
        ParamList.add(Value);
    }

    public void Output(FileWriter Writer) throws IOException {
        if(!IsUsed) return;
        if(IsInline) return;
        if(IsLinked) Writer.write(("\nlink " + this.RetType + " @" + this.FuncName));
        else Writer.write(("\ndefine " + this.RetType + " @" + this.FuncName));
        Writer.write("(");
        boolean IsStart = false;
        for(IRValue Param : ParamList){
            if(!IsStart) IsStart = true;
            else  Writer.write(", ");
             Writer.write(Param.Type+ " %"+ Param.Name);
        }
        Writer.write(")");
        if(IsLinked) {
            Writer.write(";\n");
            return;
        }
        Writer.write("{\n");
        Start.Output(Writer);
        if(End != null) End.Output(Writer);
        Writer.write("}\n");
    }

    public IRBlock getStart() {
        return Start;
    }

    public IRBlock getEnd() {
        return End;
    }

    public List<IRValue> getParamList() {
        return ParamList;
    }

    public Boolean getLinked() {
        return IsLinked;
    }
    public List<BaseInstr> InlineReplace(List<String> Param,List<String> ParamTypes,String Rd){
        FuncCallInstr ImcompLink = (FuncCallInstr) InlineInstr.get(InlineInstr.size()-1);
        ImcompLink.ParamType.addAll(ParamTypes);
        ImcompLink.Param.addAll(Param);
        FuncCallInstr NewLink = new FuncCallInstr(ImcompLink.Op,Rd,ImcompLink.Type, ImcompLink.FuncName, ImcompLink.ParamType,ImcompLink.Param);
        InlineInstr.set(InlineInstr.size()-1,NewLink);
        return InlineInstr;
    }

    public Boolean getInline() {
        return IsInline;
    }
}