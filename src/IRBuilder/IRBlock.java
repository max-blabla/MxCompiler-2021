package IRBuilder;

import ASTNode.ExprAST;
import ASTNode.VarDeclAST;
import ASTNode.VarDeclareAST;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;



public class IRBlock{
    String Label;
    List<IRInstrPair> ExprInstrList = new ArrayList<>();
    List<BaseInstr> VarInstrList = new ArrayList<>();
    HashMap<String, IRVarPair> VarList;
    Boolean shutFlag;
    ShutType shutType;
    BaseInstr EndInstr;
    ExprAST returnExpr;
    BlockType blockType;
    IRBlock Father;
    List<IRBlock>  SubBlocks;
    IRBlock(BlockType Type){
        SubBlocks = new ArrayList<>();
        Father = null;
        blockType = Type;
        shutFlag = false;
        shutType = ShutType.Sequent;
        returnExpr = null;
    }
    public void ShutBlock(){
        shutFlag = true;
    }

    public boolean isShut(){
        return shutFlag;
    }
    public void InsertExpr(ExprAST Expr){
        ExprInstrList.add(new IRInstrPair(Expr,new ArrayList<>()));
    }
    public void InsertVarDecl(String Type, String Name , ExprAST Expr){
        IRVarPair NewPair = new IRVarPair();
        NewPair.Type = Type;
        NewPair.Name = Name;
        NewPair.Expr = Expr;
        VarList.put(Name,NewPair);
    }
    public void setShutType(ShutType Type){
        shutType = Type;
    }
    public void setReturnExpr(ExprAST ReturnExpr){
        returnExpr = ReturnExpr;
    }
    public void InsertSubBlock(IRBlock Sub){
        Sub.Father = this;
        SubBlocks.add(Sub);
    }
}