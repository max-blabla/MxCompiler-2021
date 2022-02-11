package PostASTBuilder;

import ASTNode.*;

import java.util.List;
import java.util.Objects;

class Tuple{
    String StrC;
    Integer IntC;
    Boolean BoolC;
    Boolean IsConst;
    Integer Type;
}
public class PostASTBuilder {
    GlobalAST Program;
    public void SetProgram(GlobalAST program){
        Program = program;
    }
    public GlobalAST GetProgram(){
        return Program;
    }
    public void ParRevise(){
        ParRevise(Program);
    }
    public void NewArrayRevise(){

    }
    public void ConstSpread(){ConstSpread(Program);}
    void ParRevise(BaseAST Root){
        if(Root == null) return;
        if(Root instanceof ExprAST){
            ExprAST Expr = (ExprAST) Root;
            ExprAST Left = Expr.getLeftSonExpr();
            ExprAST Right = Expr.getRightSonExpr();
            if(!(Left == null || Right == null)) {
                ExprAST RightPar = Right.getLeftSonExpr();
                ExprAST LeftPar = Left.getLeftSonExpr();
                if (!(LeftPar instanceof ParAST) && RightPar instanceof ParAST) Expr.SwapSon();
            }
        }
        for(BaseAST Son: Root.GetSon()) ParRevise(Son);

    }



    Tuple ConstSpread(BaseAST Root) {
        if (Root == null) return null;
        if(! (Root instanceof ExprAST Expr))for (BaseAST Son : Root.GetSon()) ConstSpread(Son);
        else{
            ExprAST Left = Expr.getLeftSonExpr();
            ExprAST Right = Expr.getRightSonExpr();
            Tuple LTuple = ConstSpread(Left);
            Tuple RTuple = ConstSpread(Right);
            Tuple Ret =  new Tuple();
            if(LTuple != null) Ret.Type = LTuple.Type;
            switch (Expr.getType()){
                case Literal -> {
                    Ret.IsConst = true;
                    LiteralAST LiteralNode = (LiteralAST) Root;
                    if(Objects.equals(LiteralNode.getLiteralType(), "string")){
                        Ret.Type = 1;
                        Ret.StrC = LiteralNode.getContext();
                    }
                    else if(Objects.equals(LiteralNode.getLiteralType(), "int")) {
                        Ret.Type = 0;
                        Ret.IntC = Integer.valueOf(LiteralNode.getContext());
                    }
                    else if(Objects.equals(LiteralNode.getLiteralType(), "bool")){
                        Ret.Type = 2;
                        if(Objects.equals(LiteralNode.getContext(), "true")) Ret.BoolC = true;
                        else if(Objects.equals(LiteralNode.getContext(), "false")) Ret.BoolC = false;
                    }
                    else {
                        Ret.IsConst = false;
                    }
                }
                case Label,Index,ExprList,MemCall,LeftSelfMinus,RightSelfMinus,LeftSelfPlus,RightSelfPlus,New,Assign,FuncCall -> Ret.IsConst = false;
                case Primary,Par -> Ret = LTuple;
                case Positive -> {
                    if(LTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC = LTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Negative -> {
                    if(LTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = -LTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Not -> {
                    if(LTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.BoolC  = !LTuple.BoolC;
                    }
                    else Ret.IsConst = false;
                }
                case Tidle -> {
                    if(LTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = ~LTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Multiply -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC * RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Divide -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC / RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Mod -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC % RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Plus -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        if(LTuple.Type == 0) Ret.IntC  = LTuple.IntC + RTuple.IntC;
                        else Ret.StrC = LTuple.StrC.substring(0,LTuple.StrC.length()-1) + RTuple.StrC.substring(1);
                    }
                    else Ret.IsConst = false;
                }
                case  Minus -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC - RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case  LeftShift -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC << RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case  RightShift -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC >> RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case LessThan -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = LTuple.IntC < RTuple.IntC;
                        else if(LTuple.Type == 1) Ret.BoolC = LTuple.StrC.compareTo( RTuple.StrC) < 0;
                    }
                    else Ret.IsConst = false;
                }
                case LessThanEqual -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = LTuple.IntC <= RTuple.IntC;
                        else if(LTuple.Type == 1) Ret.BoolC = LTuple.StrC.compareTo( RTuple.StrC) <= 0;
                    }
                    else Ret.IsConst = false;
                }
                case GreaterThan -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = LTuple.IntC > RTuple.IntC;
                        else if(LTuple.Type == 1) Ret.BoolC = LTuple.StrC.compareTo( RTuple.StrC) > 0;
                    }
                    else Ret.IsConst = false;
                }
                case GreaterThanEqual -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = LTuple.IntC >= RTuple.IntC;
                        else if(LTuple.Type == 1) Ret.BoolC = LTuple.StrC.compareTo( RTuple.StrC) >= 0;
                    }
                    else Ret.IsConst = false;
                }
                case Equal -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = Objects.equals(LTuple.IntC, RTuple.IntC);
                        else if(LTuple.Type == 1) Ret.BoolC = Objects.equals(LTuple.StrC, RTuple.StrC);
                        else if(LTuple.Type == 2) Ret.BoolC = Objects.equals(LTuple.BoolC, RTuple.BoolC);
                    }
                    else Ret.IsConst = false;
                }
                case NotEqual -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.Type = 2;
                        if(LTuple.Type == 0) Ret.BoolC = !Objects.equals(LTuple.IntC, RTuple.IntC);
                        else if(LTuple.Type == 1) Ret.BoolC = !Objects.equals(LTuple.StrC, RTuple.StrC);
                        else if(LTuple.Type == 2) Ret.BoolC = !Objects.equals(LTuple.BoolC, RTuple.BoolC);
                    }
                    else Ret.IsConst = false;
                }
                case And -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC & RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Xor -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC ^ RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case Or -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.IntC  = LTuple.IntC | RTuple.IntC;
                    }
                    else Ret.IsConst = false;
                }
                case OrOr -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.BoolC  = LTuple.BoolC || RTuple.BoolC;
                    }
                    else Ret.IsConst = false;
                }
                case AndAnd -> {
                    if(LTuple.IsConst && RTuple.IsConst) {
                        Ret.IsConst = true;
                        Ret.BoolC  = LTuple.BoolC && RTuple.BoolC;
                    }
                    else Ret.IsConst = false;
                }
            }
            if(Ret.IsConst == null){
                return null;
            }
            if(! (Root.Father instanceof ExprAST) && Ret.IsConst){
                String Type;
                String Context;
                if(Ret.Type == 0){
                    Type = "int";
                    Context = Ret.IntC.toString();
                }
                else if(Ret.Type == 1){
                    Type = "string";
                    Context = Ret.StrC;
                }
                else{
                    Type = "bool";
                    Context = Ret.BoolC.toString();
                }
                LiteralAST NewConst = new LiteralAST(Type,Context);
                List<BaseAST> Sibling = Root.Father.GetSon();
                for(int i = 0 ; i< Sibling.size();i++) if(Sibling.get(i) == Root) Sibling.set(i,NewConst);
            }
            else if(LTuple != null && RTuple != null && LTuple.IsConst ^ RTuple.IsConst){
                String Type;
                String Context;
                if(LTuple.IsConst){
                    if(LTuple.Type == 0){
                        Type = "int";
                        Context = LTuple.IntC.toString();
                    }
                    else if(LTuple.Type == 1){
                        Type = "string";
                        Context = LTuple.StrC;
                    }
                    else{
                        Type = "bool";
                        Context = LTuple.BoolC.toString();
                    }
                    LiteralAST NewConst = new LiteralAST(Type,Context);
                    Expr.setLeftSonExpr(NewConst);
                }
                else {
                    if(RTuple.Type == 0){
                        Type = "int";
                        Context = RTuple.IntC.toString();
                    }
                    else if(RTuple.Type == 1){
                        Type = "string";
                        Context = RTuple.StrC;
                    }
                    else{
                        Type = "bool";
                        Context = RTuple.BoolC.toString();
                    }
                    LiteralAST NewConst = new LiteralAST(Type,Context);
                    Expr.setRightSonExpr(NewConst);
                }
            }
            return Ret;
        }
        return null;
    }
}
