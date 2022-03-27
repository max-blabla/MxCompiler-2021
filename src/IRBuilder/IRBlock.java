package IRBuilder;

import ASTNode.ExprAST;
import ASTNode.VarDeclAST;
import ASTNode.VarDeclareAST;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;



public class IRBlock{
    String Label;
    Integer Serial;
    String Note ;
    List<BaseInstr> VarInstrList = new ArrayList<>();
    BaseInstr EndInstr;
    //变量名到
    HashMap<String, IRValue> VarList;
    BlockType blockType;
    Boolean IsShut;
    IRBlock Father;
    List<IRBlock>  SubBlocks;
    HashMap<String,String> PtrTable;
    IRBlock(BlockType Type,String note, Integer serial){
        SubBlocks = new ArrayList<>();
        VarList = new HashMap<>();
        Father = null;
        blockType = Type;
        IsShut = false;
        PtrTable = new HashMap<>();
        Serial = serial;
        Note = note;
        Label = Note+Serial;
    }
    public void ShutBlock(){
        IsShut = true;
    }
    public boolean isShut(){
        return IsShut;
    }
   // public void InsertExpr(ExprAST Expr){
    public void InsertSubBlock(IRBlock Sub){
        Sub.Father = this;
        SubBlocks.add(Sub);
    }
    public void PushInstr(BaseInstr Inst){
        VarInstrList.add(0,Inst);
    }
    public void InsertInstr(BaseInstr Inst){
        VarInstrList.add(Inst);
    }
   // public IRBlock getLastSubBlock(){
    //    if(SubBlocks.size()==0) return this;
    //    else return SubBlocks.get(SubBlocks.size()-1).getLastSubBlock();
    //}

    public void Output(FileWriter Writer) throws IOException {
        Writer.write(  Label + ":\n");
        if(this.VarInstrList.size()!=0) {
            for (BaseInstr Instr : VarInstrList) {
                Writer.write("\t");
                Instr.Output(Writer);
            }
        }
        Writer.write("\t");
        EndInstr.Output(Writer);
    }

    public List<BaseInstr> getVarInstrList() {
        return VarInstrList;
    }

    public String getLabel() {
        return Label;
    }

    public List<IRBlock> getSubBlocks() {
        return SubBlocks;
    }

    public BaseInstr getEndInstr() {
        return EndInstr;
    }
}