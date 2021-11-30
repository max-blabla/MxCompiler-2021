package GlobalVisitor;
import SemanticChecker.L_VarInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class ClassScope extends BaseScope {
    String ClassName;
    HashMap<String, Vector<Integer>> FuncSet = new HashMap<>();
    public ClassScope(String Name){ ClassName = Name; }
    @Override
    public boolean InsertVar(String Name,int Type, boolean isNull){
        if(VarSet.containsKey(Name)||FuncSet.containsKey(Name)) return false;
        else{
            L_VarInfo NewLVar = new L_VarInfo(Type,isNull);
            VarSet.put(Name,NewLVar);
            return true;
        }
    }
    public Vector<Integer> GetFunc(String Name){
        return FuncSet.get(Name);
    }
    public boolean InsertFunc(String Name,Vector<Integer> TypeVec){
        /*if (Objects.equals(Name, ClassName) && Objects.equals(TypeVec.firstElement(), 0)) return false;
        else*/ if(VarSet.containsKey(Name)||FuncSet.containsKey(Name)) return false;
        else{
            FuncSet.put(Name,TypeVec);
            return true;
        }
    }

    public boolean FindFunc(String Label){
        return FuncSet.containsKey(Label);
    }
    public String GetClassName(){
        return ClassName;
    }

    public void ShowFunc(){
        System.out.println("func:");
        for(Map.Entry<String,Vector<Integer>> entry: FuncSet.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(" ");
            for(Integer RetType : entry.getValue()){
                System.out.print(RetType);
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
    }
}