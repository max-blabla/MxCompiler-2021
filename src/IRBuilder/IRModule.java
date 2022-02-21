package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


public class IRModule{
    String Name;
    HashMap<String, IRValue> VarTable;
    HashMap<String,String> PtrTable;
    List<IRFunc> FuncSet;
    Boolean IsUsed;
    Integer Size;
    public IRModule(){
        VarTable = new HashMap<>();
        PtrTable = new HashMap<>();
        FuncSet = new ArrayList<>();
        Size = 0 ;
    }
    public void VarInsert(IRValue varPair){
        VarTable.put(varPair.Name, varPair);
    }
    public void FuncInsert(IRFunc irFunc){
        FuncSet.add(irFunc);
    }
    public void setName(String name){
        Name = name;
    }
    public String getName(){return Name;}
    public List<IRFunc> getFuncSet(){return FuncSet;}
    public HashMap<String,IRValue> getVarTable(){return VarTable;}
    public IRFunc FindFunc(String FuncName){
        for(IRFunc Func:FuncSet) if(Objects.equals(Func.FuncName, FuncName)) return Func;
        return null;
    }
    public IRValue FindValue(String Name){
        return VarTable.getOrDefault(Name, null);
    }
    public String GetPtr(String Name){
        return PtrTable.getOrDefault(Name, null);
    }
    public void SetPtr(String Name,String Ptr){
        PtrTable.put(Name,Ptr);
    }
    public void Output(FileWriter Writer) throws IOException {
        if(Objects.equals(this.Name, "_global")){
            for(Entry<String,IRValue> entry: VarTable.entrySet()) {
                Writer.write("@" + entry.getKey() + " = global ");
                if(Objects.equals(entry.getValue().Asciz, "")) Writer.write("zeroinitializer\n");
                else Writer.write("["+entry.getValue().Asciz.length()+",\""+entry.getValue().Asciz+"\"]\n");
            }
            for(IRFunc func : FuncSet) func.Output(Writer);
        }
        else{
            if(!Objects.equals(this.Name, "_string")) {
                Writer.write("%" + "struct." + this.Name + " = type { ");
                boolean IsStart = false;
                for (Entry<String, IRValue> entry : VarTable.entrySet()) {
                    if (!IsStart) IsStart = true;
                    else Writer.write(", ");
                    Writer.write(entry.getValue().Type);
                }
                Writer.write(" }\n");
            }
            for(IRFunc func : FuncSet) func.Output(Writer);
        }
    }

    public Integer getSize() {
        return Size;
    }
}