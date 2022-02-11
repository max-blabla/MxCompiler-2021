package IRBuilder;

import ASTNode.ExprAST;

public class IRValue{
    String Type;
    String Name;
    Integer Index;//表示在Module中的相对位置 而Block 中 为临时变量 不需要
    Integer Offset;//
    String PtrReg;//对于匿名变量需要
    String Asciz;
    Integer Word;
    IRValue(){
        PtrReg  = "";
        Asciz = "";
        Word = 0;
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
    public String getAsciz(){return Asciz;}

    public Integer getWord() {
        return Word;
    }

    public String getType() {
        return Type;
    }
}
