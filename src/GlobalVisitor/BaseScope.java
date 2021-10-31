package GlobalVisitor;

import SemanticChecker.L_VarInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BaseScope{
    HashMap<String, L_VarInfo> VarSet = new HashMap<>();
    ClassScope ClassDerivation =null;
    FuncScope FuncDerivation = null;
    GlobalScope GlobalDerivation = null;
    Integer DerivationType = -1;//0 为空 1 为 Func 2为 Class 3 为Global;
    Vector<Integer> LambdaParam = null;
    public void ShowDerivation(){
        switch (DerivationType) {
            case 0 -> System.out.println("Null");
            case 1 -> System.out.println("Func");
            case 2 -> System.out.println("Class");
            case 3 -> System.out.println("Global");
            default -> System.out.println("Error");
        }
    }
    public void SetNewLambda(Vector<Integer> lambdaParam){
        LambdaParam = lambdaParam;
    }
    public Vector<Integer> GetLambda(){
        return LambdaParam;
    }
    public boolean SetDerivationType(Integer derivationType){
        if(DerivationType == -1)
        {DerivationType = derivationType;return true;}
        else return false;
    }

    public void SetClassDerivation(ClassScope classDerivation){
        ClassDerivation = classDerivation;
        VarSet = ClassDerivation.GetVarSet();
    }

    public void SetGlobalDerivation(GlobalScope globalDerivation){
        GlobalDerivation = globalDerivation;
        VarSet = GlobalDerivation.GetVarSet();
    }

    public FuncScope GetFuncDerivation(){
        return FuncDerivation;
    }
    public ClassScope GetClassDerivation(){
        return ClassDerivation;
    }
    public GlobalScope GetGlobalDerivation(){
        return GlobalDerivation;
    }

    public void SetFuncDerivation(FuncScope funcDerivation){

        FuncDerivation = funcDerivation;
        VarSet = FuncDerivation.GetVarSet();
    }

    public boolean InsertVar(String Name,int Type, boolean isNull){
        if(VarSet.containsKey(Name)) return false;
        else{
            L_VarInfo NewLVar = new L_VarInfo(Type,isNull);
            VarSet.put(Name,NewLVar);
            return true;
        }
    }
    public HashMap<String,L_VarInfo> GetVarSet(){
        return VarSet;
    }
    public boolean FindVar(String Label){
        return VarSet.containsKey(Label);
    }
    public int GetType(String Label){
        return VarSet.get(Label).Type();
    }
    public void ShowVar(){
        System.out.println("var:");
        for(Map.Entry<String,L_VarInfo> entry: VarSet.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(" ");
            System.out.print(entry.getValue().Type());
            System.out.print("|");
        }
        System.out.println();
    }
}