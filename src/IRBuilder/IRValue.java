package IRBuilder;

import ASTNode.ExprAST;

public class IRValue{
    String Type;
    String Name;
    Integer Index;//表示在Module中的相对位置 而Block 中 为临时变量 不需要
    String PtrReg;//对于匿名变量需要
    IRValue(){
        PtrReg  = "";
    }
    public String getIRType(){
        return "i32";
    }
    public boolean isArray(){
        return false;
    }
    public String getName(){
        return Name;
    }
    public void setIndex(Integer index){
        Index = index;
    }
    public int getIndex(){
        return Index;
    }
}
