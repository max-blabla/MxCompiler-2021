package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


public class IRModule{
    String Name;
    List<String> PtrType;
    HashMap<String, Integer> ClassPtrIndex;
    List<IRFunc> FuncSet;
    IRFunc Init;
    Integer Size;

    public IRFunc getInit() {
        return Init;
    }

    public HashMap<String, Integer> getClassPtrIndex() {
        return ClassPtrIndex;
    }

    public IRModule(String name){
        PtrType = new ArrayList<>();
        ClassPtrIndex = new HashMap<>();
        FuncSet = new ArrayList<>();
        Name = name;
        Size = 0;
    }
    public String FindPtrType(String Label){
        if(ClassPtrIndex.containsKey(Label)) return PtrType.get(ClassPtrIndex.get(Label));
        else return null;
    }
    public Integer FindOffset(String Name){
        return ClassPtrIndex.get(Name) *32;
    }
    public void InsertPtr(String Type,String Label){
        ClassPtrIndex.put(Label,PtrType.size());
        PtrType.add(Type);
        Size += 32;
    }

    public void FuncInsert(IRFunc irFunc){
        FuncSet.add(irFunc);
    }
    public void setName(String name){
        Name = name;
    }
    public String getName(){return Name;}
    public List<IRFunc> getFuncSet(){return FuncSet;}
    public IRFunc FindFunc(String FuncName){
        for(IRFunc Func:FuncSet) if(Objects.equals(Func.FuncName,Name +"."+ FuncName)) return Func;
        return null;
    }
/*    public IRValue FindValue(String Name){
        return VarTable.getOrDefault(Name, null);
    }*/
    public void Output(FileWriter Writer) throws IOException {
        if(Objects.equals(this.Name, "_global")){
            for(Entry<String,Integer> entry: ClassPtrIndex.entrySet()) {
                Writer.write("@" + entry.getKey() + " = global ");
                Writer.write("zeroinitializer\n");
            }
            Init.Output(Writer);
            for(IRFunc func : FuncSet) func.Output(Writer);
        }
        else{
            if(!Objects.equals(this.Name, "_string")) {
                Writer.write("%" + "struct." + this.Name + " = type { ");
                boolean IsStart = false;
                for (String Type: PtrType) {
                    if (!IsStart) IsStart = true;
                    else Writer.write(", ");
                    Writer.write(Type);
                }
                Writer.write(" }\n");
            }
            if(Init != null) Init.Output(Writer);
            for(IRFunc func : FuncSet) func.Output(Writer);
        }
    }

    public Integer getSize() {
        return Size;
    }
}