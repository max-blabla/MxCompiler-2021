package IRBuilder;

import ASTNode.ExprAST;

public class IRVarPair{
    String Type;
    String Name;
    ExprAST Expr;
    Integer Index;
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
