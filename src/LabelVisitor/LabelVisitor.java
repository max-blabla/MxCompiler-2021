package LabelVisitor;

import MxParser.MxBaseVisitor;
import MxParser.MxVisitor;
import MxParser.MxParser;

import java.util.HashMap;
import java.util.Map;
public class LabelVisitor extends MxBaseVisitor<String>{

}
class TypeVisitor extends MxBaseVisitor<String> {
    HashMap<String,Integer> TypeMap;
    TypeVisitor(){
        TypeMap = new HashMap<String, Integer>();
        TypeMap.put("void",0);
        TypeMap.put("int",1);
        TypeMap.put("String",2);
        TypeMap.put("bool",3);
    }
    public boolean Insert(String Type){
        if(TypeMap.containsKey(Type)) return false;
        else{
            TypeMap.put(Type,TypeMap.size());
            return true;
        }
    }

    //If Not Found, Return -1.
    public int Find(String Type){
        return TypeMap.getOrDefault(Type, -1);
    }
}
