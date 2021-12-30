package IRBuilder;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;


public class IRModule{
    String Name;
    HashMap<String, IRValue> VarTable;
    List<IRFunc> FuncSet;
    Boolean IsUsed;
    public void VarInsert(IRValue varPair){
        VarTable.put(varPair.Name, varPair);
    }
    public void FuncInsert(IRFunc irFunc){
        FuncSet.add(irFunc);
    }
    public void setName(String name){
        Name = name;
    }
    public IRFunc getTopFunc(){ return FuncSet.get(FuncSet.size() - 1);}
    public String getName(){return Name;}
    public List<IRFunc> getFuncSet(){return FuncSet;}
    public HashMap<String,IRValue> getVarTable(){return VarTable;}

}