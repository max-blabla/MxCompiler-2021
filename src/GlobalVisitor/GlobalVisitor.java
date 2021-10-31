package GlobalVisitor;

import ErrorInfo.ErrorInfo;
import MxParser.MxBaseListener;
import MxParser.MxListener;
import MxParser.MxParser;
import MxParser.MxBaseListener;
import SemanticChecker.L_VarInfo;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import MxParser.MxParser.VarDeclarationContext;
//登记类成员

public class GlobalVisitor extends MxBaseListener{
    MxParser parser;
    GlobalScope GlobalTable;
    Stack<String> SpaceStack;
    ClassScope ClassTable;
    Vector<Integer> TypeVec;

    public GlobalVisitor(TypeGather TypeSet,MxParser parser){
        this.parser = parser;
        GlobalTable = new GlobalScope(TypeSet);

        SpaceStack = new Stack<>();
        Vector<Integer> BuiltinFunc;
        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(1);
        BuiltinFunc.add(4);
        GlobalTable.InsertFunc("print",BuiltinFunc);
        GlobalTable.InsertFunc("println",BuiltinFunc);

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(1);
        BuiltinFunc.add(2);
        GlobalTable.InsertFunc("printInt",BuiltinFunc);
        GlobalTable.InsertFunc("printlnInt",BuiltinFunc);

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(4);
        GlobalTable.InsertFunc("getString",BuiltinFunc);

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(2);
        GlobalTable.InsertFunc("getInt",BuiltinFunc);

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(4);
        BuiltinFunc.add(2);
        GlobalTable.InsertFunc("toString",BuiltinFunc);

        ClassScope StringScope = new ClassScope("string");

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(2);
        StringScope.InsertFunc("length",BuiltinFunc);
        StringScope.InsertFunc("parseInt",BuiltinFunc);
        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(2);
        BuiltinFunc.add(2);
        StringScope.InsertFunc("ord",BuiltinFunc);

        BuiltinFunc = new Vector<>();
        BuiltinFunc.add(4);
        BuiltinFunc.add(2);
        BuiltinFunc.add(2);
        StringScope.InsertFunc("substring",BuiltinFunc);

        GlobalTable.InsertClass("string",StringScope);
    }
    public boolean LabelCheck(){
        return true;
    }
    //这些需要判断类型问题
    //还有一个问题就是函数名与类名同，这个下一步再解决

    public void ShowType(){
        GlobalTable.ShowType();
    }

    public void ShowFunc(){
        GlobalTable.ShowFunc();
    }

    public void ShowVar(){
        GlobalTable.ShowVar();
    }

    public void ShowClass(){
        GlobalTable.ShowClass();
    }

    public void ShowAll(){
        GlobalTable.ShowAll();
    }

    public GlobalScope GetGlobalTable(){
        return GlobalTable;
    }

    @Override
    public void enterFuncDef(MxParser.FuncDefContext ctx){
        if(ctx.returnType()!=null) {
            String SingleType;
            if (ctx.returnType().type() != null) {
                SingleType = ctx.returnType().type().singleType().getText();
            } else SingleType = "void";
            String Label = ctx.Identifier().getText();
            TypeVec = new Vector<>();
            if (GlobalTable.FindType(SingleType) == -1) {
                ErrorInfo errorInfo = new ErrorInfo("Return Type Does Not Exist", ctx.getText());
                throw new RuntimeException();
            } else {



                String Type = ctx.returnType().getText();

                GlobalTable.NewArray(Type);
                TypeVec.add(GlobalTable.FindType(Type));
                if (Objects.equals(SpaceStack.peek(), "global") && GlobalTable.FindFunc(Label)) {
                    ErrorInfo errorInfo = new ErrorInfo("Duplicated Global Function", ctx.getText());
                    throw new RuntimeException();
                } else if (Objects.equals(SpaceStack.peek(), "class") && (ClassTable.FindFunc(Label) || Label.equals(ClassTable.GetClassName()))) {
                    ErrorInfo errorInfo = new ErrorInfo("Duplicated Class Function", ctx.getText());
                    throw new RuntimeException();
                }else if(Objects.equals(SpaceStack.peek(),"global") && GlobalTable.FindClass(Label)){
                    ErrorInfo errorInfo = new ErrorInfo("Func Label Can Not Be The Same With Class",ctx.getText());
                    throw new RuntimeException();
                }

                List<MxParser.TypeContext> LabelList = ctx.parameterList().type();
                for (MxParser.TypeContext typeContext : LabelList) {
                    String ParamSingleType = typeContext.singleType().getText();
                    String ParamType = typeContext.getText();
                    if (GlobalTable.FindType(ParamSingleType) == -1) {
                        ErrorInfo errorInfo = new ErrorInfo("Param Type Does Not Exist",ctx.getText());
                        throw new RuntimeException();
                    }
                    else{
                        GlobalTable.NewArray(ParamType);
                        TypeVec.add(GlobalTable.FindType(ParamType));
                    }
                }
            }
        }
    }
    @Override
    public void exitFuncDef(MxParser.FuncDefContext ctx){
       // System.out.println(SpaceStack.peek());
        String Label = ctx.Identifier().getText();
        if (Objects.equals(SpaceStack.peek(), "global")) GlobalTable.InsertFunc(Label,TypeVec);
        else if (Objects.equals(SpaceStack.peek(), "class")) ClassTable.InsertFunc(Label,TypeVec);
    }
    @Override
    public void enterConFuncDef(MxParser.ConFuncDefContext ctx) {
        String Label = ctx.Identifier().getText();
        if (Objects.equals(SpaceStack.peek(), "class")&&ClassTable.FindFunc(Label)){
            ErrorInfo errorInfo = new ErrorInfo("Duplicated Construction Function",ctx.getText());
            throw new RuntimeException();
        }
        else if(!Objects.equals(Label, ClassTable.GetClassName())){
            ErrorInfo errorInfo = new ErrorInfo("Wrong Construction Function",ctx.getText());
            throw new RuntimeException();
        }
        else {
          //  System.out.println("be");
            TypeVec = new Vector<>();
            TypeVec.add(GlobalTable.FindType(""));
            ClassTable.InsertFunc(Label, TypeVec);
           // System.out.println(ClassTable.InsertFunc(Label, TypeVec));
        }
    }



    @Override
    public void enterVarDef(MxParser.VarDefContext ctx){
     //   System.out.println(ctx.type().singleType().getText());
        String SingleType = ctx.type().singleType().getText();

        String Type = ctx.type().getText();
        int CurType  = 0;
        if (GlobalTable.FindType(SingleType) == -1) {
            ErrorInfo errorInfo = new ErrorInfo("VarDef Type Does Not Exist",ctx.getText());
            throw new RuntimeException();
        }
        else {
            GlobalTable.NewArray(Type);
            CurType = GlobalTable.FindType(Type);
        }
        List<VarDeclarationContext> varDecList= ctx.varDeclaration();
       /* if(Objects.equals(SpaceStack.peek(),"global")) {
            for (VarDeclarationContext Var : varDecList) {
                String Label = Var.Identifier().getText();
                if (!GlobalTable.InsertVar(Label, CurType, false)) {
                    ErrorInfo errorInfo = new ErrorInfo("Duplicated Global Value",ctx.getText());
                    throw new RuntimeException();
                }
            }
        }
        else*/
        if(Objects.equals(SpaceStack.peek(),"class")){
            for (VarDeclarationContext Var : varDecList) {
                String Label = Var.Identifier().getText();
                if (!ClassTable.InsertVar(Label, CurType, false)) {
                    ErrorInfo errorInfo = new ErrorInfo("Duplicated Class Value",ctx.getText());
                    throw new RuntimeException();
                }
            }
        }
    }

    @Override
    public void enterClassDef(MxParser.ClassDefContext ctx){
        String ClassName = ctx.Identifier().getText();
        if(GlobalTable.FindClass(ClassName) && GlobalTable.FindVar(ClassName) && GlobalTable.FindFunc(ClassName)){
            ErrorInfo errorInfo = new ErrorInfo("Classname Can Not Be Duplicated With Existed Label",ctx.getText());
            throw new RuntimeException();
        }
        ClassTable = new ClassScope(ClassName);
        SpaceStack.push("class");
    }


    @Override
    public void exitClassDef(MxParser.ClassDefContext ctx){
        //塞一个this 进去
        String Label = ClassTable.GetClassName();
        ClassTable.InsertVar("this",GlobalTable.FindType(Label),false);

        if(!ClassTable.FindFunc(Label)){
            TypeVec = new Vector<>();
            TypeVec.add(GlobalTable.FindType(""));
            ClassTable.InsertFunc(Label,TypeVec);
        }
        GlobalTable.InsertClass(Label,ClassTable);
        SpaceStack.pop();
    }

    @Override
    public void enterSuite(MxParser.SuiteContext ctx){
        SpaceStack.push("suite");
    }
    @Override
    public void exitSuite(MxParser.SuiteContext ctx){
        SpaceStack.pop();
    }

    @Override
    public void enterProgram(MxParser.ProgramContext ctx){
        SpaceStack.push("global");
    }
    @Override
    public void exitProgram(MxParser.ProgramContext ctx){
        SpaceStack.pop();
    }
}
