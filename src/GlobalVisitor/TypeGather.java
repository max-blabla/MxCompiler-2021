package GlobalVisitor;
import java.util.HashMap;
import java.util.Map.Entry;

public class TypeGather{
    HashMap<String,Integer> TypeMap;
    HashMap<Integer,String> InvMap;
    TypeGather(){
        TypeMap = new HashMap<>();
        InvMap = new HashMap<>();
        TypeMap.put("",0);
        TypeMap.put("void",1);
        TypeMap.put("int",2);
        TypeMap.put("string",4);
        TypeMap.put("bool",3);
        InvMap.put(0,"");
        InvMap.put(1,"void");
        InvMap.put(2,"int");
        InvMap.put(3,"bool");
        InvMap.put(4,"string");
    }
    public void ShowType(){
        System.out.println("type:");
        for(Entry<String,Integer> entry: TypeMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(" ");
            System.out.print(entry.getValue());
            System.out.print("|");
        }
        System.out.println();
    }
    public boolean Insert(String Type){
        if(TypeMap.containsKey(Type)) return false;
        else{
            int Value = TypeMap.size();
            TypeMap.put(Type,Value);
            InvMap.put(Value,Type);
            return true;
        }
    }
    public String InvFind(Integer Value){
        return InvMap.getOrDefault(Value,"");
    }
    //If Not Found, Return -1.
    public int Find(String Type){
        return TypeMap.getOrDefault(Type, -1);
    }
}
