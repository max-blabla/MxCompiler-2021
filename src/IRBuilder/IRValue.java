package IRBuilder;

import ASTNode.ExprAST;

public class IRValue{
    String Type;
    String Name;
    String Asciz;
    IRValue(){
        Asciz = "";
    }
    public boolean isArray(){
        return false;
    }
    public String getName(){
        return Name;
    }
    public String getAsciz(){return Asciz;}


    public String getType() {
        return Type;
    }
}
