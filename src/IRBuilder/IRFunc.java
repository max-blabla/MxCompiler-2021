package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class IRFunc{
    String FuncName;
    String RetType;
    IRBlock Start;
    IRBlock End;
    Boolean IsLinked;
    Integer RegCnt;
    String ModuleName;
    List<Param> ParamList;
    Queue<IRBlock> OutQueue;
    public IRFunc(String funcName,String moduleName,String retType){
        FuncName = moduleName+"."+funcName;
        ModuleName = moduleName;
        ParamList = new ArrayList<>();
        RegCnt = 0;
        IsLinked = false;
        RetType = retType;
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
    public void SetParam(List<Param> Params){
        ParamList.addAll(Params);
    }
    void SetFuncName (String Name){
        FuncName = Name;
    }
    void AddInstr(BaseInstr Instr){
        End.InsertInstr(Instr);
    }

    public void Output(FileWriter Writer) throws IOException {
        if(IsLinked) Writer.write(("\nlink " + this.RetType + " @" + this.FuncName));
        else Writer.write(("\ndefine " + this.RetType + " @" + this.FuncName));
        Writer.write("(");
        boolean IsStart = false;
        for(Param param : ParamList){
            if(!IsStart) IsStart = true;
            else  Writer.write(", ");
             Writer.write(param.Type+ " %"+ param.Name);
        }
        Writer.write(")");
        if(IsLinked) {
            Writer.write(";\n");
            return;
        }
        Writer.write("{\n");
        OutQueue = new ArrayDeque<>();
        OutQueue.add(Start);
        while(!OutQueue.isEmpty()){
            IRBlock Top = OutQueue.peek();
            Top.Output(Writer);
            for(IRBlock Sub : Top.SubBlocks) if(Sub != null) OutQueue.add(Sub);
            OutQueue.remove();
        }
        if(End != null) End.Output(Writer);
        Writer.write("}\n");
    }

    public IRBlock getStart() {
        return Start;
    }

    public IRBlock getEnd() {
        return End;
    }

    public List<Param> getParamList() {
        return ParamList;
    }

    public Boolean getLinked() {
        return IsLinked;
    }


}