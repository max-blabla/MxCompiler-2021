package ASTNode;

import java.util.List;

abstract public class BaseAST {
    public BaseAST Father;
    abstract public void InsertSon(BaseAST Son);
    abstract public List<BaseAST> GetSon();
   // abstract public void Print();
}
