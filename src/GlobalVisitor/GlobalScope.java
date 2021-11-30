package GlobalVisitor;

import java.util.*;
import SemanticChecker.L_VarInfo;
//我要在 scope里 记下名字和分配的地址和类型
//此时可以判断的错误：重名 声明不存在的type
// 变量名字 -> 值 和 类型
// 函数名字 -> 返回类型 和
// 地址 -> 编译后的函数占的字节大小。。。
// 类 -> 地址 和 类标签
// 地址 -> 变量的地址 和 函数的地址

public class GlobalScope extends ClassScope{
    TypeGather TypeSet;
    HashMap<String,ClassScope> ClassSet = new HashMap<>();
    public GlobalScope(TypeGather typeSet){
        super("");
        TypeSet =  typeSet;
    }
    public void InsertClass(String Name, ClassScope Class){
        //类重名交给TypeVisitor解决
        ClassSet.put(Name,Class);
    }
    public TypeGather GetTypeSet(){
        return TypeSet;
    }
    public void SetFuncSet(HashMap<String,Vector<Integer>> FuncMap){
        FuncSet = FuncMap;
    }
    public HashMap<String,Vector<Integer>> SetFuncSet(){
        return FuncSet;
    }
    public HashMap<String,ClassScope> GetClassSet(){
        return ClassSet;
    }
    public HashMap<String,L_VarInfo> GetClassVarSet(String ClassName){
        return ClassSet.get(ClassName).GetVarSet();
    }
    public Integer FindType(String Type){
        return TypeSet.Find(Type);
    }
    public Boolean FindClass(String Type){return ClassSet.containsKey(Type);}
    public void InsertType(String Type){
        TypeSet.Insert(Type);
    }
    public void NewArray(String Type){
        if(TypeSet.Find(Type)==-1) {
            ClassScope Array= new ClassScope(Type);
            Vector<Integer> SizeFunc = new Vector<>();
            SizeFunc.add(2);
            Array.InsertFunc("size",SizeFunc);
            ClassSet.put(Type,Array);
            TypeSet.Insert(Type);
        }
    }
    public ClassScope GetClassScope(String Name){
        return ClassSet.get(Name);
    }
    public String InvType(Integer Type){
        return TypeSet.InvFind(Type);
    }
    public void ShowClass(){
        System.out.println("class:");
        for(Map.Entry<String,ClassScope> entry: ClassSet.entrySet()){
            System.out.println(entry.getValue().ClassName);
            entry.getValue().ShowVar();
            entry.getValue().ShowFunc();
        }
        System.out.println();
    }

    public void ShowType(){
        TypeSet.ShowType();
    }

    public void ShowAll(){
        System.out.println("all:");
        ShowVar();
        ShowFunc();
        ShowClass();
    }
}
