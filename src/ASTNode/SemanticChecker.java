
package  ASTNode;

import ASTNode.BaseAST;
import ASTNode.ClassDeclAST;
import ASTNode.GlobalAST;
import ASTNode.ErrorInfo;
import MxParser.MxBaseListener;
import org.w3c.dom.Node;

import java.util.*;

class BaseScope{
    HashMap<String,IdInfo> IdentitySet;
    ScopeType Type;
    BaseScope(ScopeType type){
        Type = type;
        IdentitySet = new HashMap<>();
    }
    void InsertIdentity(String id,String type,Boolean isNull,Boolean isLeft){
        IdInfo NewInfo = new IdInfo();
        NewInfo.Id = id;
        NewInfo.Type = type;
        NewInfo.IsNull = isNull;
        NewInfo.IsLeft = isLeft;
        IdentitySet.put(id,NewInfo);
    }
    IdInfo FindIdentity(String Name){
        return IdentitySet.getOrDefault(Name,null);
    }
}
class IdInfo{
    String Id;
    String Type;
    Boolean IsNull;
    Boolean IsLeft;
}
class ClassScope extends BaseScope {
    String ClassName;
    HashMap<String,List<IdInfo>> FuncMap;
    ClassScope(String className){
        super(ScopeType.C);
        FuncMap = new HashMap<>();
        ClassName = className;
    }
    void InsertFunc(String FuncName,List<IdInfo> Param){
        if(FuncMap.containsKey(FuncName))FuncMap.replace(FuncName,Param);
        else FuncMap.put(FuncName,Param);
    }
    List<IdInfo> FindFunc(String FuncName){
        return FuncMap.getOrDefault(FuncName,null);
    }



}
class GlobalScope extends BaseScope{
    HashMap<String,List<IdInfo>> FuncMap;
    GlobalScope(){
        super(ScopeType.G);
        FuncMap = new HashMap<>();
    }
    void InsertFunc(String FuncName,List<IdInfo> Param){
        if(FuncMap.containsKey(FuncName)) FuncMap.replace(FuncName,Param);
        else FuncMap.put(FuncName,Param);
    }
    List<IdInfo> FindFunc(String FuncName){
        return FuncMap.getOrDefault(FuncName,null);
    }
}
class LoopScope extends BaseScope{
    LoopScope(){
        super(ScopeType.L);
    }
}
class FuncScope extends BaseScope{
    String FuncName;
    String RetType;
    Boolean IsReturned;
   // HashMap<String,IdInfo> Params;
    FuncScope(String funcName,List<IdInfo> params){
        super(ScopeType.F);
        FuncName =funcName;
        RetType = params.get(0).Type;
        IsReturned = false;
      //  Params = new HashMap<>();
        for(IdInfo Info :params)  IdentitySet.put(Info.Id, Info);

    }
}
enum ScopeType{
    G,
    F,
    L,
    B,
    C
}
public class SemanticChecker extends MxBaseListener{
    //可以拿到Global Table
    Stack <BaseScope> ScopeStack = new Stack<>();
    HashMap<String,ClassScope> ClassMap = new HashMap<>();
    GlobalScope Global;
    GlobalAST Program;
    HashSet<String> KeepinToken;
    //HashMap<String,IdInfo> TempIdentitySet;
    Boolean HasLambda = false;
    public Boolean HasLambda(){
        return HasLambda;
    }
    void ClassGather(BaseAST Root) throws ErrorInfo {
        //收集类变量和this，收集类名，加内置类型
        if(Root instanceof GlobalAST){
            ClassScope StringScope = new ClassScope("string");
            KeepinToken = new HashSet<>();
            KeepinToken.add("int");
            KeepinToken.add("this");
            KeepinToken.add("string");
            KeepinToken.add("bool");
            KeepinToken.add("void");
            KeepinToken.add("null");
            ClassMap.put("int",null);
            ClassMap.put("string",StringScope);
            ClassMap.put("bool",null);
            ClassMap.put("void",null);
            ScopeStack.push(Global);
            for(DeclAST Decl : Program.DeclList) ClassGather(Decl);
            ScopeStack.pop();
        }
        else if(Root instanceof ClassDeclAST){
            ClassDeclAST NewClass = (ClassDeclAST) Root;
            if(!ClassCheck(NewClass.getClassName())) throw new  ErrorInfo("Duplicated Class Name");
            if(!KeepinCheck(NewClass.getClassName()) || (Objects.equals(NewClass.getClassName(), "main"))) throw new  ErrorInfo("Invalid Class Name");
            ClassScope CS = new ClassScope(NewClass.getClassName());
            ScopeStack.push(CS);
            for(DeclAST Decl :NewClass.DeclList) ClassGather(Decl);
            ScopeStack.pop();
            ClassMap.put(CS.ClassName,CS);
        }
    }

    Boolean KeepinCheck(String Name){
        return !KeepinToken.contains(Name);
    }

    void BuiltinSet()throws ErrorInfo{
        String StringClassName = "string";
        ClassScope StringScope = ClassMap.get(StringClassName);
        for(int i = 0 ; i < 4 ; ++i) {
            List<IdInfo> BuiltinFuncParam = new ArrayList<>();
            String BuiltinName;
            switch(i){
                case 0:{
                    BuiltinName = "parseInt";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo This = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "int";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    This.Id = "this";
                    This.Type  = "string";
                    This.IsLeft = false;
                    This.IsNull = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(This);
                    break;
                }
                case 1:{
                    BuiltinName = "ord";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo This = new IdInfo();
                    IdInfo Index = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "int";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    This.Id = "this";
                    This.Type  = "string";
                    This.IsLeft = false;
                    This.IsNull = false;
                    Index.Type = "int";
                    Index.Id = "index";
                    Index.IsLeft = false;
                    Index.IsNull = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(This);
                    BuiltinFuncParam.add(Index);
                    break;
                }
                case 2:{
                    BuiltinName = "length";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo This = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "int";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    This.Id = "this";
                    This.Type  = "string";
                    This.IsLeft = false;
                    This.IsNull = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(This);
                    break;
                }
                case 3:{
                    BuiltinName = "substring";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo This = new IdInfo();
                    IdInfo L = new IdInfo();
                    IdInfo R = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "string";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    This.Id = "this";
                    This.Type  = "string";
                    This.IsLeft = false;
                    This.IsNull = false;
                    L.Type = "int";
                    L.Id = "left";
                    L.IsLeft = false;
                    L.IsNull = false;
                    R.Type = "int";
                    R.Id = "right";
                    R.IsLeft = false;
                    R.IsNull = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(This);
                    BuiltinFuncParam.add(L);
                    BuiltinFuncParam.add(R);
                    break;
                }
                default:{
                    throw new ErrorInfo("BuiltinSet Error");
                }
            }
            StringScope.InsertFunc(BuiltinName,BuiltinFuncParam);
        }
        for(int i = 0 ; i < 7 ; ++i) {
            List<IdInfo> BuiltinFuncParam = new ArrayList<>();
            String BuiltinName;
            switch(i){
                case 0:{
                    BuiltinName = "print";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo Content = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "void";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    Content.Id ="content";
                    Content.Type = "string";
                    Content.IsNull = false;
                    Content.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(Content);
                    break;
                }
                case 1:{
                    BuiltinName = "println";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo Content = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "void";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    Content.Id ="content";
                    Content.Type = "string";
                    Content.IsNull = false;
                    Content.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(Content);
                    break;
                }
                case 2:{
                    BuiltinName = "printInt";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo Content = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "void";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    Content.Id ="content";
                    Content.Type = "int";
                    Content.IsNull = false;
                    Content.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(Content);
                    break;
                }
                case 3:{
                    BuiltinName = "printlnInt";
                    IdInfo RetInfo = new IdInfo();
                    IdInfo Content = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "void";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    Content.Id ="content";
                    Content.Type = "int";
                    Content.IsNull = false;
                    Content.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    BuiltinFuncParam.add(Content);
                    break;
                }
                case 4:{
                    BuiltinName = "getInt";
                    IdInfo RetInfo = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "int";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    break;
                }
                case 5:{
                    BuiltinName = "getString";
                    IdInfo RetInfo = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "string";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    break;
                }
                case 6:{
                    BuiltinName = "toString";
                    IdInfo RetInfo = new IdInfo();
                    RetInfo.Id = ".ret";
                    RetInfo.Type = "string";
                    RetInfo.IsNull  = false;
                    RetInfo.IsLeft = false;
                    BuiltinFuncParam.add(RetInfo);
                    IdInfo Content = new IdInfo();
                    RetInfo.IsLeft = false;
                    Content.Id ="content";
                    Content.Type = "int";
                    Content.IsNull = false;
                    Content.IsLeft = false;
                    BuiltinFuncParam.add(Content);
                    break;
                }
                default:{
                    throw new ErrorInfo("BuiltinSet Error");
                }
            }
            Global.InsertFunc(BuiltinName,BuiltinFuncParam);
        }
        ClassScope AnyArray = new ClassScope("[]");
        List<IdInfo> Param = new ArrayList<>();
        IdInfo Ret = new IdInfo();
        Ret.Type = "int";
        Ret.Id = ".ret";
        Ret.IsLeft = false;
        Ret.IsNull = false;
        Param.add(Ret);
        AnyArray.InsertFunc("size",Param);
        ClassMap.put("[]",AnyArray);
    }

    void FuncGather(BaseAST Root)  throws ErrorInfo {
        if(Root instanceof GlobalAST){
            ScopeStack.push(Global);
            for(DeclAST Decl : Program.DeclList) FuncGather(Decl);
            ScopeStack.pop();
        }
        else if(Root instanceof ClassDeclAST){
            ClassDeclAST NewClass = (ClassDeclAST) Root;
         //   if(!ClassCheck(NewClass.getClassName())) throw new ErrorInfo("Duplicated Class Name");
            //加初始化函数
            ClassScope CS = ClassMap.get(NewClass.getClassName());
            ScopeStack.push(CS);
            for(DeclAST Decl :NewClass.DeclList) FuncGather(Decl);
            ScopeStack.pop();
            if(CS.FindFunc(CS.ClassName) == null) {
                List<IdInfo> FuncParam = new ArrayList<>();
                IdInfo Ret = new IdInfo();
                Ret.Type = "void";
                Ret.Id = ".ret";
                Ret.IsNull = false;
                Ret.IsLeft = true;
                IdInfo This = new IdInfo();
                This.Type = NewClass.getClassName();
                This.Id = "this";
                This.IsNull = false;
                This.IsLeft = false;
                FuncParam.add( Ret);
                FuncParam.add(This);
                CS.InsertFunc(NewClass.getClassName(), FuncParam);
            }
        }
        else if(Root instanceof FuncDeclAST){
            FuncDeclAST NewFunc = (FuncDeclAST) Root;
            String FuncName = NewFunc.getFuncName();
            if(!ClassCheck(FuncName) && ScopeStack.peek().Type == ScopeType.G) throw new ErrorInfo("Function Cannot Has The Same Name Of Class");
            if(!FuncCheck(FuncName,ScopeStack.peek())) throw new ErrorInfo("Duplicated Function Name");
            List<IdInfo> FuncParam = new ArrayList<>();
            IdInfo Ret = new IdInfo();
            if(!Objects.equals(NewFunc.RetType.Type, ".construction")) Ret.Type = NewFunc.RetType.Type;
            else{
                ClassScope CS = (ClassScope) ScopeStack.peek();
                if(!Objects.equals(FuncName, CS.ClassName)) throw new ErrorInfo("Construction FuncName Must Be Same As Class");
                Ret.Type = "void";
            }
            Ret.Id = ".ret";
            Ret.IsNull = false;
            Ret.IsLeft = false;
            FuncParam.add(Ret);
            if(ScopeStack.peek().Type == ScopeType.C){
                ClassScope CS = (ClassScope) ScopeStack.peek();
                IdInfo This = new IdInfo();
                This.Type =  CS.ClassName;
                This.Id ="this";
                This.IsNull = false;
                This.IsLeft = false;
                FuncParam.add(This);
            }
            for(VarDeclAST Decl : NewFunc.getParamList()){
                String Type = Decl.getType();
                if(!TypeCheck(Type)) throw new ErrorInfo("Type Not Exist");
                for(VarDeclareAST Declare : Decl.getVarDeclareList()){
                    IdInfo NewParam = new IdInfo();
                    for(IdInfo param :FuncParam)  if(Objects.equals(param.Id, Declare.getId())) throw new ErrorInfo("Duplicated Param Name");
                    NewParam.Type = Type;
                    NewParam.Id = Declare.getId();
                    NewParam.IsNull = false;
                    NewParam.IsLeft = true;
                    FuncParam.add(NewParam);
                }
            }
            if(ScopeStack.peek().Type == ScopeType.C){
                ClassScope CS = (ClassScope) ScopeStack.peek();
                if(Objects.equals(FuncName, CS.ClassName) && !Objects.equals(FuncParam.get(0).Type, "void")) throw new ErrorInfo("Construction Must Be Void");
                CS.InsertFunc(FuncName,FuncParam);
            }
            else if(ScopeStack.peek().Type == ScopeType.G){
                GlobalScope GS = (GlobalScope) ScopeStack.peek();
                GS.InsertFunc(FuncName,FuncParam);
            }
        }
        else if(Root instanceof VarDeclAST){
            if(ScopeStack.peek() instanceof ClassScope){
                VarDeclAST NewVarDecl = (VarDeclAST) Root;
                String Type = NewVarDecl.getType();
                if(!TypeCheck(Type)) throw new ErrorInfo("Type In Class Decl Not Exist");
                for(VarDeclareAST Declare : NewVarDecl.getVarDeclareList()) {
                    String Id = Declare.Id.Label;
                    if(!IdentityCheck(Id)) throw new ErrorInfo("Duplicated Var In Class");
                    if(!ClassCheck(Id))throw new ErrorInfo("Duplicated VarName And ClassName");
                    ScopeStack.peek().InsertIdentity(Id,Type,false,true);
                }
            }
        }
    }

    public void SetProgram(GlobalAST program){
        Program = program;
    }

     public void SemanticCheck()  throws ErrorInfo{
        Global = new GlobalScope();
        ClassGather(Program);
        if(ScopeStack.size() != 0) throw new ErrorInfo("Stack Wrong");
         BuiltinSet();
         FuncGather(Program);
         if(ScopeStack.size() != 0) throw new ErrorInfo("Stack Wrong");
         NodeCheck(Program);
         if(ScopeStack.size() != 0) throw new ErrorInfo("Stack Wrong");
     }

    Boolean FuncCheck(String FuncName,BaseScope CurScope) throws ErrorInfo {
        if(CurScope.Type == ScopeType.C){
            ClassScope CS = (ClassScope) CurScope;
            if(CS.FindFunc(FuncName) == null) return true;
            else return false;
        }
        else if(CurScope.Type == ScopeType.G){
            GlobalScope GS = (GlobalScope) CurScope;
            if(GS.FindFunc(FuncName) == null) return true;
            else return false;
        }
        throw new ErrorInfo("FuncCheck : Wrong Scope");
    }

    IdInfo FindVar(String VarName,String ScopeName){
        if(Objects.equals(ScopeName, "")){
            List<BaseScope> ScopeList = ScopeStack.subList(0,ScopeStack.size());
            for(int i = ScopeList.size()-1;i >= 0 ;i -- ) {
                BaseScope Scope = ScopeList.get(i);
                if (Scope.FindIdentity(VarName) != null) return Scope.FindIdentity(VarName);
            }
            return null;
        }
        else{
            ClassScope CS =  ClassMap.get(ScopeName);
            if(CS == null) return null;
            return CS.FindIdentity(VarName);
        }
    }

    Boolean ClassCheck(String ClassName){
        return !ClassMap.containsKey(ClassName);
    }

    BaseScope ScopeFind(ScopeType Type){
        List<BaseScope> ScopeList = ScopeStack.subList(0,ScopeStack.size());
        for(int i = ScopeList.size()-1;i>=0;i--){
            if(ScopeList.get(i).Type == Type) return ScopeList.get(i);
        }
        return null;
    }

    Boolean TypeCheck(String Type){
        if(Type.contains("[]")){
            String MainType = Type.substring(0,Type.indexOf("["));
            return ClassMap.containsKey(MainType);
        }
        else return ClassMap.containsKey(Type);
    }

    Boolean IdentityCheck(String IdentityName){
        //检查重名
        //List<BaseScope> ScopeList = ScopeStack.subList(0,ScopeStack.size()-1);
        if(ScopeStack.peek().FindIdentity(IdentityName) == null) return true;
        return false;
    }

   void NodeCheck(BaseAST Root) throws ErrorInfo {
        if(Root == null) return;
        if(Root instanceof GlobalAST){
            List<IdInfo> Main =  FuncFind("main","");
            if( Main== null) throw new ErrorInfo("Main Function Does Not Exist");
            else if(!Objects.equals(Main.get(0).Type, "int"))throw new ErrorInfo("Main Function Must Return Integer");
            else if(Main.size() > 1) throw new ErrorInfo("Main Function Has No Parameter");
            ScopeStack.push(Global);
            for(DeclAST Decl : Program.DeclList) NodeCheck(Decl);
            ScopeStack.pop();
        }
        else if(Root instanceof ClassDeclAST){
            ClassDeclAST NewClass = (ClassDeclAST) Root;
            ClassScope CS = ClassMap.get(NewClass.getClassName());
            ScopeStack.push(CS);
            for(DeclAST Decl : NewClass.DeclList)  NodeCheck(Decl);
            ScopeStack.pop();
        }
        else if(Root instanceof FuncDeclAST){
            FuncDeclAST NewFunc = (FuncDeclAST) Root;
       //     HashMap<String,IdInfo> FuncParams = FuncMap.get(NewFunc.FuncName.Label);
            BaseScope CurScope = ScopeFind(ScopeType.C);
            List<IdInfo> FuncParams;
            if(CurScope != null){
                ClassScope CS = (ClassScope) CurScope;
                FuncParams = CS.FindFunc(NewFunc.getFuncName());
            }
            else FuncParams = Global.FindFunc(NewFunc.getFuncName());

            FuncScope NewFuncScope = new FuncScope(NewFunc.getFuncName(),FuncParams);
            ScopeStack.push(NewFuncScope);
            NodeCheck(NewFunc.FuncSuite);
            if(!NewFuncScope.IsReturned && (!Objects.equals(NewFuncScope.RetType, "void") && !Objects.equals(NewFuncScope.FuncName, "main"))) throw new ErrorInfo("Non-void Func Must Have Return");
            ScopeStack.pop();
        }
        else if(Root instanceof VarDeclAST){
            VarDeclAST NewVar = (VarDeclAST) Root;
            String Type = NewVar.getType();
            if(!TypeCheck(Type)) throw new ErrorInfo("Type Not Exist");
            boolean IsBuiltin = Objects.equals(Type, "int") || Objects.equals(Type, "string") || Objects.equals(Type, "bool");
            for(VarDeclareAST Declare : NewVar.getVarDeclareList()) {
                String NewVarName = Declare.getId();
                if(Declare.Expr != null) {
                    IdInfo Right = ExprCheck(Declare.Expr);
                    if (!Objects.equals(Right.Type, Type) && !(!IsBuiltin && Objects.equals(Right.Type, "null")))
                        throw new ErrorInfo("Wrong Assign Type");
                }
                if (!( ScopeStack.peek() instanceof ClassScope) ){
                    if(!IdentityCheck(NewVarName) ) throw new ErrorInfo("Duplicated Variable Name");
                    if(!ClassCheck(NewVarName))throw new ErrorInfo("Duplicated VarName And ClassName");
                    ScopeStack.peek().InsertIdentity(NewVarName,Type,false,true);
                }
            }
        }
        else if(Root instanceof SuiteAST){
            SuiteAST Suite = (SuiteAST) Root;
            BaseScope BS = new BaseScope(ScopeType.B);
            ScopeStack.push(BS);
            for(StmtAST Stmt : Suite.getStmtList()) NodeCheck(Stmt);
            ScopeStack.pop();
        }
        else if(Root instanceof WhileStmtAST){
            WhileStmtAST WhileStmt = (WhileStmtAST) Root;
            IdInfo Info = ExprCheck(WhileStmt.getConditionExpr());
            if (!Objects.equals(Info.Type, "bool")) throw new ErrorInfo("Condition Must Be Bool");
            LoopScope LS = new LoopScope();
            ScopeStack.push(LS);
            NodeCheck(WhileStmt.getLoopStmt());
            ScopeStack.pop();

        }
        else if(Root instanceof ForStmtAST){
            ForStmtAST ForStmt = (ForStmtAST) Root;
            LoopScope LS = new LoopScope();
            ScopeStack.push(LS);
            if(ForStmt.getInitStmt() != null) NodeCheck(ForStmt.getInitStmt() );
            if(ForStmt.ConditionExpr  != null) {
                IdInfo Info = ExprCheck(ForStmt.getConditionExpr());
                if (!Objects.equals(Info.Type, "bool")) throw new ErrorInfo("Condition Must Be Bool");
            }
            else ExprCheck(ForStmt.getInitExpr());
            NodeCheck(ForStmt.getLoopStmt());
            ScopeStack.pop();

        }
        else if(Root instanceof IfStmtAST){
            IfStmtAST IfStmt = (IfStmtAST) Root;
            IdInfo Info = ExprCheck(IfStmt.getConditionExpr());
            if(!Objects.equals(Info.Type, "bool")) throw new ErrorInfo("Condition Must Be Bool");
            BaseScope TBS = new BaseScope(ScopeType.B);
            ScopeStack.push(TBS);
            NodeCheck(IfStmt.getTrueStmt());
            ScopeStack.pop();
            BaseScope FBS = new BaseScope(ScopeType.B);
            ScopeStack.push(FBS);
            NodeCheck(IfStmt.getFalseStmt());
            ScopeStack.pop();

        }
        else if(Root instanceof ReturnStmtAST){
            ReturnStmtAST ReturnStmt =(ReturnStmtAST) Root;
            FuncScope FS = (FuncScope) ScopeFind(ScopeType.F);
            if(FS == null) throw new ErrorInfo("Return Must In A Function");
            else{
                IdInfo Ret;
                FS.IsReturned  = true;
                if(ReturnStmt.getExpr() != null) Ret = ExprCheck(ReturnStmt.getExpr());
                else{
                    Ret = new IdInfo();
                    Ret.Type = "void";
                    Ret.Id = ".ret";
                    Ret.IsNull = false;
                    Ret.IsLeft = false;
                }
                boolean IsBuiltin = Objects.equals(FS.RetType, "int") || Objects.equals(FS.RetType, "string") || Objects.equals(FS.RetType, "bool");
                if(Objects.equals(FS.FuncName, "_lambda")){
                    if(Objects.equals(FS.RetType, ".lambda")) FS.RetType = Ret.Type;
                }
                else if(!Objects.equals(Ret.Type, FS.RetType) && !(Objects.equals(Ret.Type, "null") &&!IsBuiltin) ){
                    throw new ErrorInfo("Return Type Not Match");
                }
            }
        }
        else if(Root instanceof JumpStmtAST){
            if(ScopeFind(ScopeType.L) == null) throw new ErrorInfo("Jump Must In A Loop");
        }
        else if(Root instanceof VarStmtAST){
            VarStmtAST NewVar = (VarStmtAST) Root;
            String Type = NewVar.getType();
            if(!TypeCheck(Type)) throw new ErrorInfo("Type Not Exist");
            boolean IsBuiltin = Objects.equals(Type, "int") || Objects.equals(Type, "string") || Objects.equals(Type, "bool");
            for(VarDeclareAST Declare : NewVar.getVarDeclList()) {
                if(Declare.Expr != null) {
                    IdInfo Right = ExprCheck(Declare.Expr);
                    if (!Objects.equals(Right.Type, Type) && !(!IsBuiltin && Objects.equals(Right.Type, "null")))
                        throw new ErrorInfo("Wrong Assign Type");
                }
                String NewVarName = Declare.getId();
                if(!IdentityCheck(NewVarName)) throw new ErrorInfo("Duplicated Variable Name In Func");
                if(!ClassCheck(NewVarName))throw new ErrorInfo("Duplicated VarName And ClassName");
                ScopeStack.peek().InsertIdentity(NewVarName,Type,false,true);
            }
        }
        else{
            ExprStmtAST ExprStmt = (ExprStmtAST) Root;
            ExprCheck(ExprStmt.getExpr());
        }
    }

    List<IdInfo> FuncFind(String FuncName,String CurScopeName){
        ClassScope CS = ClassMap.get(CurScopeName);
        List<IdInfo> Ret;
        if(CS != null){
            Ret = CS.FindFunc(FuncName);
            if(Ret == null)  Ret = Global.FindFunc(FuncName);
        }
        else Ret = Global.FindFunc(FuncName);
        return Ret;

    }

    IdInfo ExprCheck(ExprAST Root)throws ErrorInfo {
        if(Root == null) return null;
        switch (Root.getType()){
            case Primary, Par -> {
                return ExprCheck(Root.getLeftSonExpr());
            }
            case FuncCall -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                ExprListAST ExprList = (ExprListAST) Root.getRightSonExpr();
                List<IdInfo> CallParam = new ArrayList<>();
                String ClassName;
                if (Objects.equals(Left.Type, "")) {
                    if (ScopeFind(ScopeType.C) != null) {
                        ClassScope CS = (ClassScope) ScopeFind(ScopeType.C);
                        if(CS.FindFunc(Left.Id)!= null) {
                            IdInfo This = new IdInfo();
                            This.Type = CS.ClassName;
                            This.Id = "this";
                            This.IsNull = false;
                            This.IsLeft = false;
                            CallParam.add(This);
                        }
                        ClassName = CS.ClassName;
                    }
                    else ClassName = "";
                } else if (Left.Type.contains("[]")) {
                    if (Objects.equals(Left.Id, "size")) ClassName = "[]";
                    else throw new ErrorInfo("Invalid Func Call");
                } else {
                    IdInfo This = new IdInfo();
                    This.Type = Left.Type;
                    This.Id = Left.Id;
                    This.IsNull = false;
                    This.IsLeft = false;
                    ClassName = Left.Type;
                    CallParam.add(This);
                }
                CallParam.addAll(ExprListCheck(ExprList.getExprList()));
                List<IdInfo> FuncParam;
                if (!Objects.equals(Left.Id, "")) FuncParam = FuncFind(Left.Id, ClassName);
                else FuncParam = FuncFind(ClassName, ClassName);
                if(FuncParam == null) throw new ErrorInfo("Func Not Exist");
                if (CallParam.size() != FuncParam.size() - 1) throw new ErrorInfo("Wrong Param Number");
                for (int i = 1; i <= CallParam.size(); i++) {
                    IdInfo Info = FuncParam.get(i);
                    IdInfo CallInfo = CallParam.get(i - 1);
                    boolean IsBuiltin = Objects.equals(Info.Type, "int") || Objects.equals(Info.Type, "string") || Objects.equals(Info.Type, "bool");
                    if (!Objects.equals(Info.Type, CallInfo.Type) && !(!IsBuiltin && Objects.equals(CallInfo.Type, "null"))) throw new ErrorInfo("Wrong Param Type");
                }
                return FuncParam.get(0);
            }
            case Lambda -> {
                HasLambda = true;
                LambdaExprAST LambdaNode = (LambdaExprAST) Root.LeftSonExpr;
                List<IdInfo> FuncParam = new ArrayList<>();
                IdInfo Ret = new IdInfo();
                FuncParam.add(Ret);
                Ret.Id = ".ret";
                Ret.IsLeft = false;
                Ret.IsNull = false;

                    Ret.Type = ".lambda";
                    if(LambdaNode.ParamList != null) {
                        for (VarDeclAST Decl : LambdaNode.ParamList.ParamTypeList) {
                            String Type = Decl.getType();
                            if (!TypeCheck(Type)) throw new ErrorInfo("Type Not Exist");
                            for (VarDeclareAST Declare : Decl.getVarDeclareList()) {
                                IdInfo NewParam = new IdInfo();
                                for (IdInfo param : FuncParam)
                                    if (Objects.equals(param.Id, Declare.getId()))
                                        throw new ErrorInfo("Duplicated Param Name");
                                NewParam.Type = Type;
                                NewParam.Id = Declare.getId();
                                NewParam.IsNull = false;
                                NewParam.IsLeft = true;
                                FuncParam.add(NewParam);
                            }
                        }
                    }
                FuncScope FS = new FuncScope("_lambda",FuncParam);
                FS.RetType = Ret.Type;
                ScopeStack.push(FS);
                NodeCheck(LambdaNode.FuncSuite);
                ScopeStack.pop();
                List<IdInfo> CallParam = ExprListCheck(LambdaNode.ExprList.getExprList());
                if (CallParam.size() != FuncParam.size() - 1) throw new ErrorInfo("Wrong Lambda Param Number");
                for (int i = 1; i <= CallParam.size(); i++) {
                    IdInfo Info = FuncParam.get(i);
                    IdInfo CallInfo = CallParam.get(i - 1);
                    if (!Objects.equals(Info.Type, CallInfo.Type)) throw new ErrorInfo("Wrong Lambda Param Type");
                }
                Ret.Type = FS.RetType;
                return Ret;
            }
            case MemCall -> {
                if(Root.Father instanceof  ExprAST) {
                    ExprAST Father = (ExprAST) Root.Father;
                    if ( Father.getType() == ExprType.FuncCall) {
                        IdInfo Ret = new IdInfo();
                        IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                        Ret.Id = ExprCheck(Root.getRightSonExpr()).Id;
                        Ret.IsNull = false;
                        Ret.IsLeft = true;
                        String ClassId = Left.Id;
                        if(!Objects.equals(ClassId, "") &&!Objects.equals(ClassId, ".ret") ) {
                            IdInfo ClassInfo = FindVar(ClassId, "");
                            if (ClassInfo == null) throw new ErrorInfo("Var Not Exist In MemFuncCall");
                            Ret.Type = ClassInfo.Type;
                        }
                        else Ret.Type = Left.Type;
                        return Ret;
                    }
                }
                    IdInfo Ret = new IdInfo();
                    String Name =  ExprCheck(Root.getRightSonExpr()).Id;
                    IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                    String ClassId =  Left.Id;
                    if(!Objects.equals(ClassId, "") &&!Objects.equals(ClassId, ".ret") ) {
                        IdInfo ClassInfo = FindVar(ClassId, "");
                        if (ClassInfo == null) throw new ErrorInfo("Var Not Exist In MemCall");
                        IdInfo Info = FindVar(Name, ClassInfo.Type);
                        if (Info == null) throw new ErrorInfo("Var Not Exist In Class");
                        Ret.Type = Info.Type;
                    }
                    else{
                        IdInfo Info = FindVar(Name,Left.Type);
                        if (Info == null) throw new ErrorInfo("Var Not Exist In Class");
                        Ret.Type = Info.Type;
                    }
                    Ret.Id = "";
                    Ret.IsNull = false;
                    Ret.IsLeft = true;
                    return Ret;
            }
            case Label -> {
                LabelAST Label = (LabelAST) Root;
                if(Root.Father.Father instanceof  ExprAST) {
                    ExprAST GrandFather = (ExprAST) Root.Father.Father;
                    if (GrandFather.getType() == ExprType.MemCall || GrandFather.getType() == ExprType.FuncCall) {
                        IdInfo Ret = new IdInfo();
                        Ret.Id = Label.Id;
                        Ret.Type = "";
                        Ret.IsNull = false;
                        Ret.IsLeft = true;
                        return Ret;
                    }
                }
                    if(FindVar(Label.Id, "") != null) return FindVar(Label.Id, "");
                    else throw new ErrorInfo("Var Not Exist");
            }
            case Index -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                if(!Objects.equals(Right.Type, "int")) throw new ErrorInfo("Index Must Be Integer");
                if(Left.IsNull ) throw new ErrorInfo("Array Is Null");
                if(!Left.Type.contains("[]")) throw new ErrorInfo("This Is Not An Array");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.IsNull = false;
                Ret.Type = Left.Type.substring(0,Left.Type.lastIndexOf('['));
                Ret.IsLeft = true;
                return Ret;
            }
            case Positive -> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("Positive Operator Must Adapted To Integer");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Negative -> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("Negative Operator Must Be Adapted To Integer");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case LeftSelfPlus-> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Integer");
                if(!Left.IsLeft) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Left Type");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = true;
                return Ret;
            }
                case RightSelfPlus -> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Integer");
                if(!Left.IsLeft) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Left Type");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case LeftSelfMinus-> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("SelfMinus Operator Must Be Adapted To Integer");
                if(!Left.IsLeft) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Left Type");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = true;
                return Ret;
            }
            case RightSelfMinus -> {
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("SelfMinus Operator Must Be Adapted To Integer");
                if(!Left.IsLeft) throw new ErrorInfo("SelfPlus Operator Must Be Adapted To Left Type");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Not ->{
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "bool") && !Objects.equals(Left.Type, "int") ) throw new ErrorInfo("Logic Not Operator Must Be Adapted To Bool Or Int");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Tidle ->{
                IdInfo Left =ExprCheck(Root.getLeftSonExpr());
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo("Bit Not Operator Must Be Adapted To Int");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Plus -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                if(!Objects.equals(Left.Type, Right.Type))throw new ErrorInfo("Plus Operator Must Be Adapted To Same Type");
                if(!Objects.equals(Left.Type, "int") && !Objects.equals(Left.Type, "string")) throw new ErrorInfo("Plus Operator Must Be Adapted To String Or Integer");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Multiply,Divide,Mod,Minus,LeftShift,RightShift,Or,And,Xor -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                if(!Objects.equals(Left.Type, Right.Type))throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To Same Type");
                if(!Objects.equals(Left.Type, "int")) throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To  Integer");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case LessThan,GreaterThan ,LessThanEqual ,GreaterThanEqual -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                if(!Objects.equals(Left.Type, Right.Type))throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To Same Type");
                if(!Objects.equals(Left.Type, "int") &&!Objects.equals(Left.Type, "string") && !Objects.equals(Left,"bool")) throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To  Integer Or String Or Bool");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = "bool";
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Equal,NotEqual ->{
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                boolean IsLeftBuiltin = Objects.equals(Left.Type, "int") || Objects.equals(Left.Type, "string") || Objects.equals(Left.Type, "bool");
                boolean IsRightBuiltin = Objects.equals(Right.Type, "int") || Objects.equals(Right.Type, "string") || Objects.equals(Right.Type, "bool");
                if(!Objects.equals(Left.Type, Right.Type) && (! (( !IsLeftBuiltin && Right.IsNull) || ( !IsRightBuiltin && Left.IsNull) ) )) throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To Correct Type");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = "bool";
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case AndAnd,OrOr ->{
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                if(!Objects.equals(Left.Type, Right.Type))throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To Same Type");
                if(!Objects.equals(Left.Type, "bool")) throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To  Bool");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Assign -> {
                IdInfo Left = ExprCheck(Root.getLeftSonExpr());
                IdInfo Right = ExprCheck(Root.getRightSonExpr());
                boolean IsLeftBuiltin = Objects.equals(Left.Type, "int") || Objects.equals(Left.Type, "string") || Objects.equals(Left.Type, "bool");
                if(!Left.IsLeft) throw new ErrorInfo("Assign Must Be Adaped To Lvalue");
                if(Left.IsNull){
                    throw new ErrorInfo("Null Cannot Be Assigned");
                }
                if(!Objects.equals(Left.Type, Right.Type) && (! (( !IsLeftBuiltin && Right.IsNull)  ))) throw new ErrorInfo(Root.getType()+"Operator Must Be Adapted To Correct Type");

                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Left.Type;
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case New -> {
                NewTypeAST NewType = (NewTypeAST) Root;
                List<IdInfo> InfoList  = ExprListCheck(NewType.ExprList);
                for(IdInfo Info : InfoList) if(!Objects.equals(Info.Type, "int")) throw new ErrorInfo("The Size Of Array Must Be Integer");
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = NewType.getNewType();
                Ret.IsNull = false;
                Ret.IsLeft = false;
                return Ret;
            }
            case Literal -> {
                LiteralAST Literal = (LiteralAST) Root;
                IdInfo Ret = new IdInfo();
                Ret.Id = "";
                Ret.Type = Literal.Type;
                Ret.IsLeft = false;
                Ret.IsNull = Objects.equals(Ret.Type, "null");
                return Ret;
            }
            default -> {
                return null;
            }
        }
    }

    List<IdInfo> ExprListCheck(List<ExprAST> List)throws ErrorInfo {
        List<IdInfo> ParamList = new ArrayList<>();
        for(ExprAST Expr : List) ParamList.add(ExprCheck(Expr));
        return ParamList;
    }
}

//作用域检查
//即标签是否正确
//this 也是一种标签