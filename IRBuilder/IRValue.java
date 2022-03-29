package IRBuilder;

import ASTNode.ExprAST;

public class IRValue{
    String Type;
    String Name;
    IRValue(String type,String name){
        Type = type;
        Name = name;
    }
    public boolean isArray(){
        return false;
    }
    public String getName() {
        return Name;
    }

}
