package IRBuilder;

import ASTNode.ExprAST;

import java.util.List;

public class IRInstrPair {
    ExprAST Expr;
    List<BaseInstr> IRInstrList;
    IRInstrPair(ExprAST expr, List<BaseInstr> irInstrList){
        Expr = expr;
        IRInstrList = irInstrList;
    }
}
