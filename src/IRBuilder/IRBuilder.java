package IRBuilder;

import ASTNode.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
//TODO  New Builtin
enum ScopeType{
    Class,
    Global,
    Func,
    Basic
}
class IRScope{
    ScopeType Type;
    String Name;
    HashMap<String,IRValue> Values;
    HashMap<String,String> Ptrs;
    HashMap<String,String> Value2Ptr;
    IRScope(ScopeType type,String name, Integer Serial){
        Type = type;
        Name = name+Serial;
        Values = new HashMap<>();
        Value2Ptr = new HashMap<>();
        Ptrs = new HashMap<>();
    }
    void MatchPtr(String ValueReg,String PtrReg){
        if(Value2Ptr.containsKey(ValueReg)) Value2Ptr.replace(ValueReg,PtrReg);
        else Value2Ptr.put(ValueReg,PtrReg);
    }

    void InsertPtr(String LabelName,String PtrType,String PtrReg){
        IRValue NewPtr = new IRValue(PtrType,PtrReg);
        Ptrs.put(LabelName,PtrReg);
        Values.put(PtrReg,NewPtr);
    }
}

public class IRBuilder {
    GlobalAST Program;
    List<IRModule> ModuleList;
    HashMap<String, IRFunc> LinkingFunc;
    HashMap<ExprType,InstrSeg> ExprSeg;
    HashMap<String,String> ConstStrs;
    Integer BlockNum;
    IRFunc CurFunc;
    IRBlock CurBlock;
    IRModule CurModule;
    IRModule GlobalModule;
    IRFunc Init;
 //   IRFunc Main;
    Integer TmpCnt;
    Stack<IRScope> ScopeStack;
    char PointerChar = '*';
    String This = "this";
    String Ret = "_ret";
    char ProtectChar = '_';
    char PointChar = '.';
    String I32 = "i32";
    String I8 = "i8";
    String StrOne = "1";
    String StrZero = "0";
    String StrNegOne = "-1";
    String IString = "_string";
    String StrCmp = "strcomp";
    String StrApd = "append";
    String StrCpy = "strcopy";
    String GlobalName = "_global";
    //String NullFound = "_nullfound";
    String Str = "str";
    String Malloc = "malloc";
    String Void = "void";
    String False = "false";
    String StrFour  = "4";
    String Tmp = "_tmp";
    String StrInit = "_init";
    String StrMain = "main";
    List<String> BuiltinFuncNames;
    List<String> BuiltinFuncRet;
    //TODO IR ReMake计划


    public HashMap<String, String> getConstStrs() {
        return ConstStrs;
    }

    public void FileRun(String Filename)throws IOException {
            FileWriter LlvmWriter = new FileWriter(Filename);
            for(Entry<String,String> entry:ConstStrs.entrySet()){
                LlvmWriter.write("@" + entry.getKey() + " = global " );
                LlvmWriter.write("\""+entry.getValue()+"\"\n");
            }
            for(IRModule Module : ModuleList) Module.Output(LlvmWriter);
            LlvmWriter.close();
    }

/*    public IRBuilder(GlobalAST program) {
        Program = program;
        ModuleList = new ArrayList<>();
        CurBlock = null;
        CurModule = null;
        CurFunc = null;
        GlobalModule = null;
        Init = null;
        Main = null;
        IsCollect = false;
    }*/

    public List<IRModule> GetModuleList() {
        return ModuleList;
    }


    void AddLinking(String LinkName) {
        IRFunc Func = LinkingFunc.get(LinkName);
        if (Func != null ) {
            GlobalModule.FuncSet.add(Func);
        }
    }

    IRModule FindModule(String ModuleName){
        for(IRModule Module :ModuleList){
            if(Objects.equals(Module.Name, ModuleName)) return Module;
        }
        return null;
    }

   void GlobalInit(){
       ModuleList = new ArrayList<>();
       GlobalModule = new IRModule(GlobalName);
       LinkingFunc = new HashMap<>();
       ConstStrs = new HashMap<>();
       ScopeStack = new Stack<>();
       BlockNum = 0;
       TmpCnt = 0;
       BuiltinFuncNames = new ArrayList<>(Arrays.asList(
               "print","println","strcopy","toString","printInt","printlnInt","getString","getInt","append","strcomp"
               ,"length","parseInt", "substring","ord"
       ));
       BuiltinFuncRet = new ArrayList<>(Arrays.asList(
               Void,Void,IString,IString,Void,Void,IString,I32,IString,Void,I32,I32,IString,I32
       ));
       ExprSeg = new HashMap<>(){
           {
               put(ExprType.Minus,InstrSeg.sub);put(ExprType.LeftSelfMinus,InstrSeg.sub);put(ExprType.RightSelfMinus,InstrSeg.sub);
               put(ExprType.LeftSelfPlus,InstrSeg.add);put(ExprType.RightSelfPlus,InstrSeg.add);put(ExprType.Equal,InstrSeg.eq);
               put(ExprType.NotEqual,InstrSeg.ne);put(ExprType.LessThan,InstrSeg.ult);put(ExprType.LessThanEqual,InstrSeg.ule);
               put(ExprType.GreaterThan,InstrSeg.ugt);put(ExprType.GreaterThanEqual,InstrSeg.uge);put(ExprType.Multiply,InstrSeg.mul);
               put(ExprType.Divide,InstrSeg.div);put(ExprType.Mod,InstrSeg.rem);put(ExprType.LeftShift,InstrSeg.sll);
               put(ExprType.RightShift,InstrSeg.sra);put(ExprType.Or,InstrSeg.or);put(ExprType.And,InstrSeg.and);
               put(ExprType.Xor,InstrSeg.xor);
           }
       };
       GlobalModule.setName(GlobalName);
       ModuleList.add(GlobalModule);
       Init = new IRFunc("_init",GlobalName,Void);
       Init.Start = new IRBlock(BlockType.Start, "Start", BlockNum++);
       Init.End = new IRBlock(BlockType.End, "End", BlockNum++);
       ReturnInstr NewReturn = new ReturnInstr(InstrSeg.ret, Void, "");
       Init.Start.EndInstr = new BranchInstr(InstrSeg.br, Init.End.Label, "", "");
       Init.End.EndInstr = NewReturn;
       Init.ModuleName = GlobalName;
       GlobalModule.Init = Init;
       IRScope GS = new IRScope(ScopeType.Global,"Global",ScopeStack.size());
       ScopeStack.push(GS);
   }

    void BuiltinSet(){
        IRModule StringModule = new IRModule(IString);
        StringModule.Name = IString;
        StringModule.Size = 32;
        for (int i = 0; i < 14; i++) {
            String ModuleName;
            if(i <10) ModuleName  = GlobalName;
            else ModuleName = IString;
            IRFunc NewLinking = new IRFunc(BuiltinFuncNames.get(i),ModuleName,BuiltinFuncRet.get(i));
            NewLinking.IsLinked = true;
            switch (i) {
                case 0,1,2 -> //Print,Println,StrCopy
                        NewLinking.SetParam(List.of(new Param(IString, "str", "")));
                case 3,4,5 -> //toString,printInt,printlnInt
                        NewLinking.SetParam(List.of(new Param(I32, "num", "")));
                case 6,7 -> //getString,getInt
                        NewLinking.SetParam(List.of());
                case 8,9 -> //append,strcomp
                        NewLinking.SetParam(Arrays.asList(new Param(IString,"dst",""),new Param(IString,"src","")));
                case 10,11 -> {
                    //str.length,str.parseInt
                    NewLinking.SetParam(List.of(new Param(IString, This, "")));
                    StringModule.FuncInsert(NewLinking);
                }
                case 12 -> {
                    //str.substring
                    NewLinking.SetParam(Arrays.asList(new Param(IString,This,""),new Param(I32,"l",""),new Param(I32,"r","")));
                    StringModule.FuncInsert(NewLinking);
                }
                case 13 -> {
                    //str.ord
                    NewLinking.SetParam(Arrays.asList(new Param(IString,This,""),new Param(I32,"index","")));
                    StringModule.FuncInsert(NewLinking);
                }
            }
            LinkingFunc.put(NewLinking.FuncName, NewLinking);
            AddLinking(NewLinking.FuncName);
        }
        ModuleList.add(StringModule);
    }

    Boolean IsGlobal(String Ptr){
        if(ConstStrs.containsKey(Ptr)) return true;
        else return GlobalModule.ClassPtrIndex.containsKey(Ptr);
    }

    public void RunIR(GlobalAST program){
        Program = program;
        GlobalInit();
        BuiltinSet();
        CurModule = GlobalModule;
        for(DeclAST Decl :Program.DeclList) PreGather(Decl);
        CurModule = GlobalModule;
        for(DeclAST Decl :Program.DeclList) DeclIR(Decl);
    }

    IRFunc FindFunc(IRModule Module,String FuncName){
        return Module.FindFunc(FuncName);
    }

    String TransType(String Type) {
        if(Type == null) return "void";
        int Demension;
        String RetMain;
        if(Type.contains("[]")){
            Demension = (Type.lastIndexOf('[')-Type.indexOf('['))/2+1;
            RetMain = Type.substring(0,Type.indexOf('['));
        }
        else{
            RetMain = Type;
            Demension = 0;
        }

        if (Objects.equals(RetMain, "int")) RetMain =  I32;
        else if (Objects.equals(RetMain, "void"))  RetMain = Void;
        else if (Objects.equals(RetMain, "bool")) RetMain  = I32;
        else if(Objects.equals(RetMain,".construction")) RetMain = Void;
        else RetMain = ProtectChar+ RetMain;
        return RetMain + "*".repeat(Demension);
    }

    void PreGather(DeclAST DeclNode){
        if (DeclNode instanceof VarDeclAST){
            VarDeclAST VarDecl = (VarDeclAST) DeclNode;
            String Type = TransType(VarDecl.getType())+PointerChar;
            for(VarDeclareAST Declare : VarDecl.getVarDeclareList()){
                CurModule.InsertPtr(Type, Declare.getId());
                //ExprToInstr(CurFunc, Declare.getAssignExpr());
            }
        }
        else if (DeclNode instanceof FuncDeclAST){
            //分配形式参数名字,this指针
            //开栈 存参数
            //所有函数分配初始块
            FuncDeclAST FuncDecl = (FuncDeclAST) DeclNode;
            String RetType = TransType(FuncDecl.getFuncType());
            IRFunc FuncForm;
            FuncForm = new IRFunc(FuncDecl.getFuncName(),CurModule.Name,RetType);
            if((ProtectChar + FuncDecl.getFuncName()).equals(CurModule.Name)){
                FuncForm.FuncName = CurModule.Name+PointChar+CurModule.Name;
                String ThisRd = CurModule.Init.NewReg();
                String Ptr = FindLabelPtr(This);
                LoadInstr ThisLoad = new LoadInstr(InstrSeg.load,ThisRd,CurModule.Name,Ptr,CurModule.Name+PointerChar,false);
                FuncCallInstr InitCall = new FuncCallInstr(InstrSeg.call,"",Void,FuncForm.FuncName,new ArrayList<>(List.of(CurModule.Name)),new ArrayList<>(List.of(ThisRd)),new ArrayList<>(List.of(false)));
                CurModule.Init.Start.InsertInstr(ThisLoad);
                CurModule.Init.Start.InsertInstr(InitCall);
            }
            List<Param> FuncParam = new ArrayList<>();
            IRBlock StartBlock = new IRBlock(BlockType.Start,"Start",++BlockNum);
            IRBlock EndBlock = new IRBlock(BlockType.End,"End",++BlockNum);
            FuncForm.Start = StartBlock;
            FuncForm.End = EndBlock;
            StartBlock.EndInstr = new BranchInstr(InstrSeg.br,EndBlock.Label,"","");
            CurFunc = FuncForm;
            CurBlock = CurFunc.Start;
            if(CurModule != GlobalModule) FuncParam.add(new Param(CurModule.Name,CurFunc.NewReg(),This));
            for(VarDeclAST Decl:FuncDecl.getParamList()){
                String Type = TransType(Decl.getType());
                for(VarDeclareAST Declare :Decl.getVarDeclareList()){
                    String ParamReg = CurFunc.NewReg();
                    FuncParam.add(new Param(Type,ParamReg,Declare.getId()));
                }
            }
            FuncForm.SetParam(FuncParam);
            CurModule.FuncInsert(FuncForm);
        }
        else {
            //初始化函数
            ClassDeclAST ClassDecl = (ClassDeclAST) DeclNode;
            IRModule NewModule = new IRModule(ProtectChar + ClassDecl.getClassName());
            NewModule.Init = new IRFunc(StrInit,NewModule.Name, Void);
            List<Param> FuncParam = new ArrayList<>();
            IRBlock StartBlock = new IRBlock(BlockType.Start, "Start", ++BlockNum);
            IRBlock EndBlock = new IRBlock(BlockType.End, "End", ++BlockNum);
            NewModule.Init.Start = StartBlock;
            NewModule.Init.End = EndBlock;
            StartBlock.EndInstr = new BranchInstr(InstrSeg.br, EndBlock.Label, "", "");
            EndBlock.EndInstr = new ReturnInstr(InstrSeg.ret, Void, "");
            CurFunc = NewModule.Init;
            CurBlock = CurFunc.Start;

            IRScope NewScope = new IRScope(ScopeType.Func, CurFunc.FuncName, ScopeStack.size());
            CurBlock = CurFunc.Start;
            String ThisType = NewModule.Name;
            String ThisReg = CurFunc.NewReg();
            String StackPtr = CurFunc.NewReg();
            NewScope.InsertPtr(This, ThisType + PointerChar, StackPtr);
            AllocaInstr NewAlloc = new AllocaInstr(InstrSeg.alloca, StackPtr, ThisType);
            StoreInstr NewStore = new StoreInstr(InstrSeg.store, ThisReg, ThisType, StackPtr, ThisType + PointerChar
                    , false, false);
            CurBlock.InsertInstr(NewAlloc);
            CurBlock.InsertInstr(NewStore);
            FuncParam.add(new Param(NewModule.Name, ThisReg, This));
            NewModule.Init.SetParam(FuncParam);
            CurFunc.End.EndInstr = new ReturnInstr(InstrSeg.ret, Void, "");

            ModuleList.add(NewModule);
            CurModule = NewModule;
            ScopeStack.push(NewScope);
            for (DeclAST Decl : ClassDecl.getDeclList()) PreGather(Decl);
            ScopeStack.pop();
            CurModule = GlobalModule;
        }
    }

    void DeclIR(DeclAST DeclNode) {
        if (DeclNode instanceof VarDeclAST) VarDeclIR(DeclNode);
        else if (DeclNode instanceof FuncDeclAST) FuncDeclIR(DeclNode);
        else ClassDeclIR(DeclNode);
    }

    void VarDeclIR(DeclAST DeclNode){
        VarDeclAST VarDecl = (VarDeclAST) DeclNode;
        IRScope TopScope = ScopeStack.peek();
        if(TopScope.Type == ScopeType.Global){
            CurFunc = Init;
            CurBlock = CurFunc.Start;
        }
        else if(TopScope.Type == ScopeType.Class) return;
            String Type = TransType(VarDecl.getType());
            String PtrReg = CurFunc.NewReg();
            for (VarDeclareAST Declare : VarDecl.getVarDeclareList()) {
                if(!(TopScope.Type == ScopeType.Global || TopScope.Type == ScopeType.Class))ScopeStack.peek().InsertPtr(Declare.getId(), Type+PointerChar, PtrReg);
                AllocaInstr NewAlloca = new AllocaInstr(InstrSeg.alloca, PtrReg, Type);
                CurBlock.InsertInstr(NewAlloca);
                ExprToInstr(CurFunc, Declare.getAssignExpr());
            }
    }

    void FuncDeclIR(DeclAST DeclNode){
        FuncDeclAST FuncDecl = (FuncDeclAST) DeclNode;
        if((ProtectChar + FuncDecl.getFuncName()).equals(CurModule.Name)) CurFunc = CurModule.FindFunc(ProtectChar+FuncDecl.getFuncName());
        else CurFunc = CurModule.FindFunc(FuncDecl.getFuncName());

        List<Param> FuncFormParam = CurFunc.ParamList;
        IRScope NewScope = new IRScope(ScopeType.Func,FuncDecl.getFuncName(),ScopeStack.size());
        CurBlock = CurFunc.Start;
        String RetPtr = CurFunc.NewReg();
        String RetType = TransType(FuncDecl.getFuncType());
        if(Objects.equals(CurFunc.FuncName, GlobalName + PointChar + StrMain)) {
            FuncCallInstr NewInit = new FuncCallInstr(InstrSeg.call,"",Void,GlobalName+PointChar+StrInit,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
            CurBlock.InsertInstr(NewInit);
        }
        if(!Objects.equals(RetType, Void)) {
            NewScope.InsertPtr(Ret, RetType + PointerChar, RetPtr);
            AllocaInstr NewRet = new AllocaInstr(InstrSeg.alloca, RetPtr, RetType);
            CurBlock.PushInstr(NewRet);
        }
        for(Param param:FuncFormParam){
            String StackPtr = CurFunc.NewReg();
            NewScope.InsertPtr(param.Label,param.Type+PointerChar,StackPtr);
            AllocaInstr NewAlloc = new AllocaInstr(InstrSeg.alloca,StackPtr,param.Type);
            StoreInstr NewStore =  new StoreInstr(InstrSeg.store,param.Name,param.Type,StackPtr,param.Type+PointerChar
                    ,false,false);
            CurBlock.InsertInstr(NewAlloc);
            CurBlock.InsertInstr(NewStore);
        }
        ScopeStack.push(NewScope);
        for(StmtAST Stmt:FuncDecl.getStmtList()) StmtIR(Stmt);
        // 结尾块分配返回值
        CurBlock = CurFunc.End;
        String RetReg;
        if(!Objects.equals(RetType, Void)) {
            RetReg = CurFunc.NewReg();
            LoadInstr NewLoad = new LoadInstr(InstrSeg.load,RetReg,RetType,RetPtr,RetType+PointerChar,false);
            ScopeStack.peek().MatchPtr(RetReg,RetPtr);
            CurFunc.End.InsertInstr(NewLoad);
        }
        else RetReg = "";
        CurFunc.End.EndInstr = new ReturnInstr(InstrSeg.ret,RetType,RetReg);
        ScopeStack.pop();
    }

    void ClassDeclIR(DeclAST DeclNode){
        ClassDeclAST ClassDecl = (ClassDeclAST) DeclNode;
        CurModule = FindModule(ProtectChar+ClassDecl.getClassName());
        IRScope NewScope = new IRScope(ScopeType.Class,ClassDecl.getClassName(),ScopeStack.size());
        ScopeStack.push(NewScope);
        for(DeclAST Decl:ClassDecl.getDeclList()) DeclIR(Decl);
        ScopeStack.pop();
        CurModule = GlobalModule;
    }

    void StmtIR(StmtAST StmtNode) {
        if (StmtNode == null) return;
        if (CurBlock.isShut()) return;
        else {
            if (StmtNode instanceof JumpStmtAST) JumpStmtIR(StmtNode);
            else if (StmtNode instanceof ReturnStmtAST) ReturnStmtIR(StmtNode);
            else if (StmtNode instanceof WhileStmtAST) WhileStmtIR(StmtNode);
            else if (StmtNode instanceof IfStmtAST) IfStmtIR(StmtNode);
            else if (StmtNode instanceof ExprStmtAST) ExprStmtIR(StmtNode);
            else if (StmtNode instanceof ForStmtAST) ForStmtIR(StmtNode);
            else if (StmtNode instanceof SuiteAST) SuiteIR(StmtNode);
            else VarStmtIR(StmtNode);
        }
    }

    IRValue FindValue(String Reg){
        List<IRScope> ScopeList = ScopeStack.subList(0, ScopeStack.size());
        IRScope Scope;

        for(int i = ScopeList.size()-1 ; i>=0 ;i--){
            Scope = ScopeList.get(i);
            if(Scope.Values.containsKey(Reg)) return Scope.Values.get(Reg);
        }
        return null;
    }

    String FindLabelPtr(String Label){
        List<IRScope> ScopeList = ScopeStack.subList(0, ScopeStack.size());
        IRScope Scope;
        for(int i = ScopeList.size()-1 ; i>=0 ;i--){
            Scope = ScopeList.get(i);
            if(Scope.Ptrs.containsKey(Label)) return Scope.Ptrs.get(Label);
        }
        return null;
    }

    String FindPtr(String Reg){
        List<IRScope> ScopeList = ScopeStack.subList(0, ScopeStack.size());
        IRScope Scope;
        for(int i = ScopeList.size()-1 ; i>=0 ;i--){
            Scope = ScopeList.get(i);
            if(Scope.Value2Ptr.containsKey(Reg)) return Scope.Value2Ptr.get(Reg);
        }
        return  null;
    }

    Integer TypeSize(String Type) {
        if (Objects.equals(Type, "i32")) return 32;
        else if (Objects.equals(Type, "i8")) return 8;
        else if (Type.contains("*")) return 32;
        else {
            for (IRModule module : ModuleList) if (Objects.equals(module.Name, Type)) return module.Size;
            return -1;
        }
    }

    IRBlock FindLoopBody(IRBlock Block) {
        IRBlock curBlock = Block;
        while (curBlock != null) {
            if (curBlock.blockType == BlockType.Body) return curBlock;
            else curBlock = curBlock.Father;
        }
        return null;
    }

    void JumpStmtIR(StmtAST JumpStmtNode){
        //或者让后面的无法加入
        //跳到本块块头或者下一块
        //即兄弟
        JumpStmtAST JumpStmt = (JumpStmtAST) JumpStmtNode;
        IRBlock Body = FindLoopBody(CurBlock);
        IRBlock Father = Body.Father;
        IRBlock Condition = null;
        Boolean IsFor;
        if(Body.Label.startsWith("For")) IsFor = true;
        else IsFor = false;
        for (IRBlock block : Father.SubBlocks){
            if (!IsFor && block.blockType == BlockType.Condition) Condition = block;
            if (IsFor && block.blockType == BlockType.Incr) Condition = block;
        }
        BranchInstr Jump;
        if (JumpStmt.isBreak())
            Jump = new BranchInstr(InstrSeg.br, Father.SubBlocks.get(Father.SubBlocks.size() - 1).Label, "", "");
        else {
            assert Condition != null;
            Jump = new BranchInstr(InstrSeg.br, Condition.Label, "", "");
        }
        CurBlock.EndInstr = Jump;
        CurBlock.ShutBlock();
    }

    void ReturnStmtIR(StmtAST ReturnStmtNode) {
        ReturnStmtAST ReturnStmt = (ReturnStmtAST) ReturnStmtNode;
        if (ReturnStmt.getExpr() != null) {
            String Ptr = FindLabelPtr(Ret);
            IRValue Ret = ExprToInstr(CurFunc, ReturnStmt.getExpr());
            StoreInstr NewStore = new StoreInstr(InstrSeg.store, Ret.Name, Ret.Type, Ptr, Ret.Type + '*', false, IsGlobal(Ret.Name));
            ScopeStack.peek().MatchPtr(Ret.Name,Ptr);
            CurBlock.InsertInstr(NewStore);
        }
        CurBlock.EndInstr = new BranchInstr(InstrSeg.br, CurFunc.End.Label, "", "");
        CurBlock.ShutBlock();
    }

    //先在当前block 塞 跳转
    void WhileStmtIR(StmtAST WhileStmtNode) {
        WhileStmtAST WhileStmt = (WhileStmtAST) WhileStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition, "While-Condition", BlockNum++);
        CurBlock.InsertSubBlock(NewConditionBlock);

        IRBlock NewBodyBlock = new IRBlock(BlockType.Body, "While-Body", BlockNum++);
        CurBlock.InsertSubBlock(NewBodyBlock);

        IRBlock SucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(SucceedBlock);

        BranchInstr PreceedJump = new BranchInstr(InstrSeg.br, NewConditionBlock.Label, "", "");

        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewConditionBlock.EndInstr = PreceedJump;
        CurBlock = NewConditionBlock;

        IRValue Condition = ExprToInstr(CurFunc, WhileStmt.getConditionExpr());
        CurBlock.EndInstr = new BranchInstr(InstrSeg.br, NewBodyBlock.Label, SucceedBlock.Label, Condition.Name);
        NewBodyBlock.EndInstr = new BranchInstr(InstrSeg.br, NewConditionBlock.Label, "", "");

        IRScope NewScope = new IRScope(ScopeType.Basic,"While",ScopeStack.size());
        ScopeStack.push(NewScope);
        CurBlock = NewBodyBlock;
        StmtIR(WhileStmt.getLoopStmt());
        CurBlock = SucceedBlock;
        ScopeStack.pop();

    }

    void IfStmtIR(StmtAST IfStmtNode) {
        IfStmtAST IfStmt = (IfStmtAST) IfStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition, "If-Condition", BlockNum++);
        CurBlock.InsertSubBlock(NewConditionBlock);
        BranchInstr PreceedJump = new BranchInstr(InstrSeg.br, NewConditionBlock.Label, "", "");

        IRBlock NewTrueBlock = new IRBlock(BlockType.TrueStmt, "If-True-Stmt", BlockNum++);
        CurBlock.InsertSubBlock(NewTrueBlock);

        IRBlock NewFalseBlock = new IRBlock(BlockType.FalseStmt, "If-False-Stmt", BlockNum++);
        CurBlock.InsertSubBlock(NewFalseBlock);

        IRBlock SucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(SucceedBlock);

        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewConditionBlock.EndInstr = PreceedJump;
        CurBlock = NewConditionBlock;
        IRValue Condition = ExprToInstr(CurFunc, IfStmt.getConditionExpr());
        CurBlock.EndInstr = new BranchInstr(InstrSeg.br, NewTrueBlock.Label, NewFalseBlock.Label, Condition.Name);

        NewTrueBlock.EndInstr = new BranchInstr(InstrSeg.br, SucceedBlock.Label, "", "");
        NewFalseBlock.EndInstr = new BranchInstr(InstrSeg.br, SucceedBlock.Label, "", "");


        IRScope NewScope = new IRScope(ScopeType.Basic,"IfTrue",ScopeStack.size());
        ScopeStack.push(NewScope);
        CurBlock = NewTrueBlock;
        StmtIR(IfStmt.getTrueStmt());
        ScopeStack.pop();

        if (IfStmt.getFalseStmt() != null) {
            IRScope NewFalseScope = new IRScope(ScopeType.Basic,"IfFalse",ScopeStack.size());
            ScopeStack.push(NewFalseScope);
            CurBlock = NewFalseBlock;
            StmtIR(IfStmt.getFalseStmt());
            ScopeStack.pop();
        }
        CurBlock = SucceedBlock;
    }

    void ForStmtIR(StmtAST ForStmtNode) {
        ForStmtAST ForStmt = (ForStmtAST) ForStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition, "For-Condition", BlockNum++);
        CurBlock.InsertSubBlock(NewConditionBlock);

        IRBlock NewBodyBlock = new IRBlock(BlockType.Body, "For-Body", BlockNum++);
        CurBlock.InsertSubBlock(NewBodyBlock);

        IRBlock IncrBlock = new IRBlock(BlockType.Incr, "For-Incr", BlockNum++);
        CurBlock.InsertSubBlock(IncrBlock);

        IRBlock SucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(SucceedBlock);

        if (ForStmt.getInitStmt() != null) {
            String Type = TransType(ForStmt.getInitStmt().getType());
            List<VarDeclareAST> VarDeclList = ForStmt.getInitStmt().getVarDeclareList();
            for (VarDeclareAST VarDeclare : VarDeclList) {
                String PtrReg = CurFunc.NewReg();
                ScopeStack.peek().InsertPtr(VarDeclare.getId(),Type+PointerChar,PtrReg);
                AllocaInstr Alloc = new AllocaInstr(InstrSeg.alloca, PtrReg,Type);
                CurBlock.InsertInstr(Alloc);
                ExprToInstr(CurFunc,VarDeclare.getAssignExpr());

            }
        } else ExprToInstr(CurFunc, ForStmt.getInitExpr());

        BranchInstr PreceedJump = new BranchInstr(InstrSeg.br, NewConditionBlock.Label, "", "");
        BranchInstr ForBodyJump = new BranchInstr(InstrSeg.br, IncrBlock.Label, "", "");
        BranchInstr IncrJump = new BranchInstr(InstrSeg.br, NewConditionBlock.Label, "", "");

        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewConditionBlock.EndInstr = PreceedJump;
        CurBlock = NewConditionBlock;
        NewBodyBlock.EndInstr = ForBodyJump;
        IncrBlock.EndInstr = IncrJump;

        if (ForStmt.getConditionExpr() != null) {
            IRValue Condition = ExprToInstr(CurFunc, ForStmt.getConditionExpr());
            CurBlock.EndInstr = new BranchInstr(InstrSeg.br, NewBodyBlock.Label, SucceedBlock.Label, Condition.Name);
        } else CurBlock.EndInstr = new BranchInstr(InstrSeg.br, NewBodyBlock.Label, "", "");


        IRScope NewScope = new IRScope(ScopeType.Basic,"ForBody",ScopeStack.size());
        ScopeStack.push(NewScope);
        CurBlock = NewBodyBlock;
        StmtIR(ForStmt.getLoopStmt());


        CurBlock = IncrBlock;
        ExprToInstr(CurFunc, ForStmt.getIncrExpr());
        CurBlock = SucceedBlock;
        ScopeStack.pop();
    }

    void ExprStmtIR(StmtAST ExprStmtNode) {
        ExprStmtAST ExprStmt = (ExprStmtAST) ExprStmtNode;
        //CurBlock.InsertExpr(ExprStmt.getExpr());
        ExprToInstr(CurFunc, ExprStmt.getExpr());
    }

    void SuiteIR(StmtAST SuiteNode) {
        //生成新的block
        SuiteAST Suite = (SuiteAST) SuiteNode;

        IRBlock NewBasicBlock = new IRBlock(BlockType.Basic, "Basic", BlockNum++);
        CurBlock.InsertSubBlock(NewBasicBlock);

        IRBlock NewSucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(NewSucceedBlock);

        BranchInstr PreceedJump = new BranchInstr(InstrSeg.br, NewBasicBlock.Label, "", "");
        BranchInstr BasicJump = new BranchInstr(InstrSeg.br, NewSucceedBlock.Label, "", "");

        NewSucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewBasicBlock.EndInstr = BasicJump;

        List<StmtAST> StmtList = Suite.getStmtList();
        IRScope NewScope = new IRScope(ScopeType.Basic,"Suite",ScopeStack.size());
        ScopeStack.push(NewScope);
        CurBlock = NewBasicBlock;
        for (StmtAST Stmt : StmtList) StmtIR(Stmt);
        CurBlock = NewSucceedBlock;
        ScopeStack.pop();
    }

    /*IRValue ModuleFindValue(IRModule Module, String ValueName) {
        return Module.FindPtrType(ValueName);
    }*/


    void VarStmtIR(StmtAST VarStmtNode) {
        //添加 Alloca
        VarStmtAST VarStmt = (VarStmtAST) VarStmtNode;
        String Type = TransType(VarStmt.getType());
        List<VarDeclareAST> VarDeclList = VarStmt.getVarDeclList();
        for (VarDeclareAST VarDeclare : VarDeclList) {
            String PtrReg = CurFunc.NewReg();
            ScopeStack.peek().InsertPtr(VarDeclare.getId(),Type+PointerChar,PtrReg);
            AllocaInstr Alloc = new AllocaInstr(InstrSeg.alloca, PtrReg,Type);
            CurBlock.InsertInstr(Alloc);
            ExprToInstr(CurFunc,VarDeclare.getAssignExpr());
        }
    }

    List<IRValue> ExprListToIRValue(IRFunc Func, IRBlock Block, List<ExprAST> ExprLists) {
        List<IRValue> Ret = new ArrayList<>();
        CurBlock = Block;
        for (ExprAST expr : ExprLists) Ret.add(ExprToInstr(Func, expr));
        return Ret;
    }

    String NewArray(IRFunc Func, String Ptr, String PtrType, List<IRValue> AllSize,List<String> IterPtr, Integer CurDemension) {
        if (CurDemension == AllSize.size()) return "";
        String Multi = Func.NewReg();
        String NewPtr = Func.NewReg();
        String Addi = Func.NewReg();
        String Rd = Func.NewReg();
        String ArrSize = AllSize.get(CurDemension).Name;
        List<String> Params = new ArrayList<>(List.of(Addi));
        List<String> ParamTypes = new ArrayList<>(List.of(I32));
        List<Boolean> IsParamsGlobal = new ArrayList<>(List.of(false));
        OperationInstr NewMulti = new OperationInstr(InstrSeg.mul,Multi,ArrSize, StrFour, I32, I32, InstrSeg.nullseg);
        OperationInstr NewAddi = new OperationInstr(InstrSeg.add, Addi,Multi, StrFour, I32, I32, InstrSeg.nullseg);
        FuncCallInstr NewMalloc = new FuncCallInstr(InstrSeg.link, Rd, PtrType, Malloc, ParamTypes, Params, IsParamsGlobal);
        StoreInstr SizeStore = new StoreInstr(InstrSeg.store, ArrSize, I32, Rd, PtrType, false, false);
        CurBlock.InsertInstr(NewMulti);
        CurBlock.InsertInstr(NewAddi);
        CurBlock.InsertInstr(NewMalloc);
        CurBlock.InsertInstr(SizeStore);
        if(CurDemension != 0) {
            StoreInstr PtrStore = new StoreInstr(InstrSeg.store, Rd, PtrType, Ptr, PtrType + PointerChar, false, false);
            ScopeStack.peek().MatchPtr(Rd, Ptr);
            CurBlock.InsertInstr(PtrStore);
        }
        if (CurDemension != AllSize.size()-1) {
            IRBlock NewCondition = new IRBlock(BlockType.Condition, "NewArrayCondition", BlockNum++);
            IRBlock NewBlock = new IRBlock(BlockType.Basic, "NewArrayBody", BlockNum++);
            IRBlock NewIncr = new IRBlock(BlockType.Basic, "NewArrayIncr", BlockNum++);
            IRBlock NewSucceed = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
            CurBlock.InsertSubBlock(NewCondition);
            CurBlock.InsertSubBlock(NewBlock);
            CurBlock.InsertSubBlock(NewIncr);
            CurBlock.InsertSubBlock(NewSucceed);
            String NextType = PtrType.substring(0, PtrType.lastIndexOf(PointerChar));
            String IterationPtr = IterPtr.get(CurDemension);
            String Condition = Func.NewReg();
            String ZeroReg = Func.NewReg();
            String Iteration = Func.NewReg();
            String IterRenew = Func.NewReg();
            String IterRenewValue = Func.NewReg();
            OperationInstr NewIterInit = new OperationInstr(InstrSeg.add,ZeroReg ,StrZero, StrZero, I32, I32, InstrSeg.nullseg);
            StoreInstr InitStore = new StoreInstr(InstrSeg.store, ZeroReg, I32, IterationPtr, I32 + PointerChar, false, false);
            LoadInstr IterLoad = new LoadInstr(InstrSeg.load, Iteration, I32, IterationPtr, I32 + PointerChar, false);
            ScopeStack.peek().MatchPtr(Iteration, IterationPtr);
            OperationInstr NewComp = new OperationInstr(InstrSeg.icmp,Condition, Iteration, AllSize.get(CurDemension).Name, I32, I32, InstrSeg.ult);
            BranchInstr NewCondiBr = new BranchInstr(InstrSeg.br, NewBlock.Label, NewSucceed.Label, Condition);
            BranchInstr NewInitBr = new BranchInstr(InstrSeg.br, NewCondition.Label, "", "");
            BranchInstr NewIncrBr = new BranchInstr(InstrSeg.br, NewCondition.Label, "", "");
            BranchInstr NewLoopBr = new BranchInstr(InstrSeg.br, NewIncr.Label, "", "");

            CurBlock.InsertInstr(NewIterInit);
            CurBlock.InsertInstr(InitStore);
            NewCondition.InsertInstr(IterLoad);
            NewCondition.InsertInstr(NewComp);

            NewSucceed.EndInstr = CurBlock.EndInstr;
            CurBlock.EndInstr = NewInitBr;
            NewCondition.EndInstr = NewCondiBr;
            NewIncr.EndInstr = NewIncrBr;
            NewBlock.EndInstr = NewLoopBr;

            GetelementInstr NewGet = new GetelementInstr(InstrSeg.getelemenptr, InstrSeg.index, NextType,NewPtr, Rd, Iteration, 0, false);
            NewBlock.InsertInstr(NewGet);
            CurBlock = NewBlock;
            NewArray(Func, NewPtr, NextType, AllSize, IterPtr, CurDemension + 1);

            LoadInstr NewIterLoad = new LoadInstr(InstrSeg.load,IterRenew,I32,IterationPtr,I32+PointerChar,false);
            OperationInstr NewIterAdd = new OperationInstr(InstrSeg.add,IterRenewValue ,IterRenew, StrOne, I32, I32, InstrSeg.nullseg);
            StoreInstr NewIterStore = new StoreInstr(InstrSeg.store, IterRenewValue, I32, IterationPtr, I32 + PointerChar, false, false);
            ScopeStack.peek().MatchPtr(IterRenewValue, IterationPtr);
            ScopeStack.peek().MatchPtr(IterRenew, IterationPtr);
            NewIncr.InsertInstr(NewIterLoad);
            NewIncr.InsertInstr(NewIterAdd);
            NewIncr.InsertInstr(NewIterStore);
            CurBlock = NewSucceed;
            AddLinking(Malloc);
        }
        return Rd;
    }

    IRValue NewPhi(PhiInstr AimPhi,IRBlock SucBlock,IRFunc Func,ExprAST Root){
        if(Root ==null) return null;
        if(Root.getType() == ExprType.AndAnd || Root.getType() == ExprType.OrOr){
            IRValue PhiLeft = NewPhi(AimPhi,SucBlock,Func,Root.getLeftSonExpr());
            IRBlock Phi = new IRBlock(BlockType.Basic, "Phi", ++BlockNum);
            CurBlock.InsertSubBlock(Phi);
            BranchInstr PreceedJump;
            if (Root.getType() == ExprType.AndAnd)
                PreceedJump = new BranchInstr(InstrSeg.br, Phi.Label, SucBlock.Label, PhiLeft.Name);
            else PreceedJump = new BranchInstr(InstrSeg.br, SucBlock.Label, Phi.Label, PhiLeft.Name);
            BranchInstr PhiJump = new BranchInstr(InstrSeg.br, SucBlock.Label, "", "");
            CurBlock.EndInstr = PreceedJump;
            Phi.EndInstr = PhiJump;
            CurBlock = Phi;
            IRValue PhiRight =  NewPhi(AimPhi,SucBlock,Func,Root.getRightSonExpr());
            String PreChange;
            if (Root.getType() == ExprType.AndAnd) PreChange = StrZero;
            else PreChange = StrOne;
            AimPhi.NewPhiNode(CurBlock.Label, PhiRight.Name, PreChange);
            return PhiRight;
        }
        else  return ExprToInstr(Func, Root);
    }

    IRValue ExprToInstr(IRFunc Func,ExprAST Root){
        if(Root == null) return null;
        IRValue Left;
        IRValue Right;
        if(Root.getType() == ExprType.Assign){
            Right = ExprToInstr(Func, Root.getRightSonExpr());
            Left = ExprToInstr(Func, Root.getLeftSonExpr());
        }
        else if(!(Root.getType() == ExprType.AndAnd || Root.getType() == ExprType.OrOr || Root.getType() == ExprType.ExprList||Root.getType() == ExprType.New))
        {
            Left = ExprToInstr(Func,  Root.getLeftSonExpr());
            Right = ExprToInstr(Func,  Root.getRightSonExpr());
        }
        else{
            Left = null;
            Right = null;
        }
        assert Left != null;
        assert Right != null;
        IRValue Ret;
        ExprType NodeType = Root.getType();
        //Ptr 约定：当进行ptr操作都会更新ptr，而label的FindPtr结果是自己，reg的FindPtr结果是自身最近绑定的ptr
        //对label的操作必须要进行一次Load和Store
        //全局直接返回自己
        //局部先load出来
        switch (NodeType){
            case New ->{
                NewTypeAST NewTypeNode =  (NewTypeAST) Root;
                String Type;
                String Rd;
                if(NewTypeNode.IsNewArray()){
                    Type = TransType(NewTypeNode.getNewType());
                    List<IRValue> Values = ExprListToIRValue(Func,CurBlock,NewTypeNode.getExprList());
                    //alloca一定量出来
                    List<String> IterPtr = new ArrayList<>();
                    for(int i =0 ; i < Values.size();i++){
                        String NewIterPtr = Func.NewReg();
                        ScopeStack.peek().InsertPtr(Tmp+TmpCnt++,I32+PointerChar,NewIterPtr);
                        AllocaInstr NewAlloc = new AllocaInstr(InstrSeg.alloca,NewIterPtr,I32);
                        IterPtr.add(NewIterPtr);
                        CurBlock.InsertInstr(NewAlloc);
                    }
                    Rd = NewArray(Func,"",Type,Values,IterPtr,0);
                    AddLinking(Malloc);
                }
                else{
                    Type = TransType(NewTypeNode.getNewType());
                    boolean IsBultin = Objects.equals( Type , I32) ||Objects.equals( Type ,IString)||Objects.equals(Type , I8);
                    if(!IsBultin) {
                        String MRd = Func.NewReg();
                        String Addi = Func.NewReg();
                        Rd = Func.NewReg();
                        List<String> MParams = new ArrayList<>(List.of(Addi));
                        List<String> MParamTypes = new ArrayList<>(List.of(I32));
                        List<Boolean> MIsGlobal = new ArrayList<>(List.of(false));
                        OperationInstr NewAddi = new OperationInstr(InstrSeg.add,Addi, Integer.toString(TypeSize(Type) / 8), StrZero, I32, I32, InstrSeg.nullseg);
                        FuncCallInstr NewMalloc = new FuncCallInstr(InstrSeg.link, MRd, Type, Malloc, MParamTypes, MParams,MIsGlobal);
                        CurBlock.InsertInstr(NewAddi);
                        CurBlock.InsertInstr(NewMalloc);
                        List<String> Params = new ArrayList<>(List.of(MRd));
                        List<String> ParamTypes = new ArrayList<>(List.of(Type));
                        List<Boolean> IsGlobal = new ArrayList<>(List.of(false));
                        FuncCallInstr CallInit = new FuncCallInstr(InstrSeg.call, Rd, Void, Type+PointChar+StrInit, ParamTypes, Params,IsGlobal);
                        CurBlock.InsertInstr(CallInit);
                    }
                    else Rd =  null;
                }
                Ret = new IRValue(Type,Rd);
            }
            case Primary,Par -> Ret = Left;
            //TODO 绑定指针
            case Label -> {
                ExprAST GrFatherNode;
                String Rd;
                String Type;
                LabelAST LabelNode = (LabelAST) Root;
                String Id = LabelNode.getId();
                if(Root.Father.Father instanceof ExprAST) GrFatherNode = (ExprAST) Root.Father.Father ;
                else GrFatherNode = null;
                if(GrFatherNode == null || (!( GrFatherNode.getType() == ExprType.MemCall && Root == GrFatherNode.getRightSonExpr().getLeftSonExpr())&& !( GrFatherNode.getType() == ExprType.FuncCall && Root == GrFatherNode.getLeftSonExpr().getLeftSonExpr()))) {
                    //判断是否为 堆上 还是 栈上
                    //好像函数和变量不会重名，因此只管找就好
                    if (FindLabelPtr(Id) != null) {
                        String PtrReg = FindLabelPtr(Id);
                        IRValue PtrValue = FindValue(PtrReg);
                        Rd = Func.NewReg();
                        Type = PtrValue.Type.substring(0, PtrValue.Type.lastIndexOf(PointerChar));
                        LoadInstr Load = new LoadInstr(InstrSeg.load, Rd, Type, PtrReg, PtrValue.Type, false);
                        ScopeStack.peek().MatchPtr(Rd, PtrReg);
                        CurBlock.InsertInstr(Load);
                    } else if (CurModule.FindPtrType(Id) != null) {
                        if (CurModule != GlobalModule) {
                            String ThisPtr = FindLabelPtr(This);
                            String ThisReg = Func.NewReg();
                            String Ptr = Func.NewReg();
                            String RightType = CurModule.FindPtrType(Id);
                            Rd = Func.NewReg();
                            Type = RightType.substring(0, RightType.lastIndexOf(PointerChar));
                            Integer ValueOffset = CurModule.FindOffset(Id);
                            LoadInstr ThisLoad = new LoadInstr(InstrSeg.load, ThisReg, CurModule.Name, ThisPtr, CurModule.Name + PointerChar, false);
                            ScopeStack.peek().MatchPtr(ThisReg, ThisReg);
                            GetelementInstr PtrGet = new GetelementInstr(InstrSeg.getelemenptr, InstrSeg.offset, RightType, Ptr, ThisReg, "0", ValueOffset, false);
                            LoadInstr Load = new LoadInstr(InstrSeg.load, Rd, Type, Ptr, RightType, false);
                            ScopeStack.peek().MatchPtr(Rd, Ptr);
                            CurBlock.InsertInstr(ThisLoad);
                            CurBlock.InsertInstr(PtrGet);
                            CurBlock.InsertInstr(Load);
                        } else {
                            String PtrType = CurModule.FindPtrType(Id);
                            Rd = Func.NewReg();
                            Type = PtrType.substring(0, PtrType.lastIndexOf(PointerChar));
                            LoadInstr Load = new LoadInstr(InstrSeg.load, Rd, Type, Id, PtrType, true);
                            ScopeStack.peek().MatchPtr(Rd, Id);
                            CurBlock.InsertInstr(Load);
                        }
                    } else if (GlobalModule.FindPtrType(Id) != null) {
                        String PtrType = GlobalModule.FindPtrType(Id);
                        Rd = Func.NewReg();
                        Type = PtrType.substring(0, PtrType.lastIndexOf(PointerChar));
                        LoadInstr Load = new LoadInstr(InstrSeg.load, Rd, Type, Id, PtrType, true);
                        ScopeStack.peek().MatchPtr(Rd, Id);
                        CurBlock.InsertInstr(Load);
                    } else {
                        Rd = Id;
                        Type = null;
                    }
                }
                else{
                    Rd = Id;
                    Type = null;
                }
              Ret = new IRValue(Type,Rd);
            }
            case MemCall ->{
                //判断父亲是否为函数调用
                ExprAST FatherNode;
                if(Root.Father instanceof ExprAST) FatherNode = (ExprAST) Root.Father ;
                else FatherNode = null;
                String Rd;
                String Type;
                if (FatherNode != null && FatherNode.getType() == ExprType.FuncCall) {
                    Rd = Left.Name + "." + Right.Name;
                    Type = Left.Type;
                }
                else {
                    //Load 出来
                    IRModule TargetModule =FindModule(Left.Type);
                    String ValueType = TargetModule.FindPtrType(Right.Name);
                    Integer ValueOffset = TargetModule.FindOffset(Right.Name);
                    String Ptr = Func.NewReg();
                    Rd = Func.NewReg();
                    Type = ValueType.substring(0,ValueType.lastIndexOf(PointerChar));
                    GetelementInstr NewGet = new GetelementInstr(InstrSeg.getelemenptr, InstrSeg.offset,ValueType ,Ptr, Left.Name,"0", ValueOffset,IsGlobal(Left.Name));
                    LoadInstr NewLoad = new LoadInstr(InstrSeg.load,Rd,Type, Ptr, ValueType, false);
                    ScopeStack.peek().MatchPtr(Rd,Ptr);
                    CurBlock.InsertInstr(NewGet);
                    CurBlock.InsertInstr(NewLoad);
                }
                Ret = new IRValue(Type,Rd);
            }
            case FuncCall -> {
                ExprListAST ParamNode = (ExprListAST) Root.getRightSonExpr();
                List<IRValue> Params = ExprListToIRValue(Func,CurBlock,ParamNode.getExprList());
                List<String> ParamTypes = new ArrayList<>();
                List<String> ParamNames = new ArrayList<>();
                List<Boolean> IsParamsGlobal = new ArrayList<>();
                String Rd = Func.NewReg();
                int Index = Left.Name.lastIndexOf('.');
                String FuncName = Left.Name.substring(Index+1);
                String ModuleName;
                IRFunc CallFunc;
                boolean IsSize;
                if (Index == -1) {
                    IsSize = false;
                    if (CurModule.FindFunc(FuncName) != null && CurModule != GlobalModule) {
                            String Ptr = FindLabelPtr(This);
                            String PtrRd = Func.NewReg();
                            LoadInstr GetClass = new LoadInstr(InstrSeg.load, PtrRd,CurModule.Name, Ptr, CurModule.Name + PointerChar, false);
                        ScopeStack.peek().MatchPtr(PtrRd,Ptr);
                        ParamNames.add(PtrRd);
                        ParamTypes.add(CurModule.Name);
                        IsParamsGlobal.add(false);
                        CurBlock.InsertInstr(GetClass);
                        ModuleName = CurModule.Name;
                        CallFunc = CurModule.FindFunc(FuncName);
                    }
                    else{
                        ModuleName = GlobalName;
                        CallFunc = GlobalModule.FindFunc(FuncName);
                    }
                } else {
                    String LeftReg = Left.Name.substring(0, Index);
                    if(FuncName.equals("size") && Left.Type.indexOf(PointerChar) != -1){
                        LoadInstr GetSize = new LoadInstr(InstrSeg.load,Rd,I32,LeftReg,Left.Type,IsGlobal(LeftReg));
                        CurBlock.InsertInstr(GetSize);
                        IsSize = true;
                        CallFunc  = null;
                        ModuleName = "";
                    }
                    else {
                        ModuleName = Left.Type;
                        IsSize = false;
                        ParamNames.add(LeftReg);
                        ParamTypes.add(Left.Type);
                        IsParamsGlobal.add(false);
                        CallFunc = FindModule(Left.Type).FindFunc(FuncName);
                    }
                }
                for (IRValue value : Params) {
                    ParamTypes.add(value.Type);
                    ParamNames.add(value.Name);
                    IsParamsGlobal.add(IsGlobal(value.Name));
                }
                if(!IsSize) {
                    if (!CallFunc.IsLinked) {
                        FuncCallInstr FuncCall = new FuncCallInstr(InstrSeg.call,Rd, CallFunc.RetType, CallFunc.FuncName, ParamTypes, ParamNames,IsParamsGlobal);
                        CurBlock.InsertInstr(FuncCall);
                    } else {
                        FuncCallInstr FuncCall = new FuncCallInstr(InstrSeg.link,Rd, CallFunc.RetType, CallFunc.FuncName, ParamTypes, ParamNames,IsParamsGlobal);
                        CurBlock.InsertInstr(FuncCall);
                    }
                    Ret =new IRValue(CallFunc.RetType,Rd);
                }
                else Ret = new IRValue(I32,Rd);
            }
            case Literal -> {
                //name 照常返回
                LiteralAST LiteralNode = (LiteralAST) Root;
                String LiteralType = LiteralNode.getLiteralType();
                String Rd = Func.NewReg();
                if(Objects.equals(LiteralType , "int")) {
                    OperationInstr NewOp = new OperationInstr(InstrSeg.add,Rd,LiteralNode.getContext(),StrZero,I32,I32,InstrSeg.nullseg);
                    CurBlock.InsertInstr(NewOp);
                    Ret = new IRValue(I32,Rd);
                }
                else if(Objects.equals(LiteralType , "bool")){
                    int Tmp;
                    if(Objects.equals(LiteralNode.getContext(), False)) Tmp = 0;
                    else Tmp = 1;
                    OperationInstr NewOp = new OperationInstr(InstrSeg.or,Rd, Integer.toString(Tmp),StrZero,I32,I32,InstrSeg.nullseg);
                    CurBlock.InsertInstr(NewOp);
                    Ret =new IRValue(I32,Rd);
                }
                else if(Objects.equals(LiteralType , "string")){
                    //TODO 更新全局 str名;
                    String Ptr = ProtectChar+Str+ConstStrs.size();
                    ConstStrs.put(Ptr,LiteralNode.getContext());
                    //GlobalModule.InsertPtr(IString,Ptr);
                    Ret = new IRValue(IString,Ptr);
                }
                else {
                    OperationInstr NewAddi = new OperationInstr(InstrSeg.add,Rd,StrZero,StrZero,I32,I32,InstrSeg.nullseg);
                    CurBlock.InsertInstr(NewAddi);
                    Ret = new IRValue(I32,Rd);
                }
            }
            case Index ->{
                String Type = Left.Type.substring(0,Left.Type.lastIndexOf("*"));
                String Rd = Func.NewReg();
                String Ptr = Func.NewReg();
                GetelementInstr NewPtr = new GetelementInstr(InstrSeg.getelemenptr,InstrSeg.index,Left.Type,Ptr,Left.Name,Right.Name,0,IsGlobal(Left.Name));
                LoadInstr NewLoad = new LoadInstr(InstrSeg.load,Rd,Type,Ptr,Left.Type,false);
                ScopeStack.peek().MatchPtr(Rd,Ptr);
                CurBlock.InsertInstr(NewPtr);
                CurBlock.InsertInstr(NewLoad);
                //TODO 更新ptr
                Ret = new IRValue(Type,Rd);
                //CurBlock.putPtr(Ret.Name,Ptr);
            }
            case Negative -> {
                String Rd = Func.NewReg();
                String Type = Left.Type;
                OperationInstr NewOp = new OperationInstr(InstrSeg.sub, Rd, StrZero, Left.Name, I32, I32, InstrSeg.nullseg);
                CurBlock.InsertInstr(NewOp);
                Ret = new IRValue(Type,Rd);
            }
            case LeftSelfPlus,LeftSelfMinus -> {
                String LoadRd = Func.NewReg();
                String Rd = Func.NewReg();
                String Ptr = FindPtr(Left.Name);
                boolean IsPtrGlobal = IsGlobal(Ptr);
                LoadInstr NewLoad = new LoadInstr(InstrSeg.load,LoadRd,I32,Ptr,I32+PointerChar,IsPtrGlobal);
                ScopeStack.peek().MatchPtr(LoadRd,Ptr);
                OperationInstr NewOp = new OperationInstr(ExprSeg.get(NodeType), Rd, LoadRd,StrOne , I32, I32, InstrSeg.nullseg);
                StoreInstr NewStore = new StoreInstr(InstrSeg.store,Rd,I32,Ptr,I32+PointerChar,IsPtrGlobal,false);
                ScopeStack.peek().MatchPtr(Rd,Ptr);
                CurBlock.InsertInstr(NewLoad);
                CurBlock.InsertInstr(NewOp);
                CurBlock.InsertInstr(NewStore);
                Ret = new IRValue(I32,Rd);
            }
            case Not->{
                String Rd = Func.NewReg();
                OperationInstr CmpOp = new OperationInstr(InstrSeg.icmp,Rd,Left.Name,StrZero,Left.Type,Left.Type,InstrSeg.eq);
                CurBlock.InsertInstr(CmpOp);
                Ret = new IRValue(Left.Type,Rd);
            }
            case Tidle->{
                String Rd = Func.NewReg();
                OperationInstr XOrOp = new OperationInstr(InstrSeg.xor,Rd, Left.Name, StrNegOne, Left.Type, Left.Type, InstrSeg.nullseg);
                CurBlock.InsertInstr(XOrOp);
                Ret = new IRValue(Left.Type,Rd);
            }
            case RightSelfMinus,RightSelfPlus ->{
                String Ptr = FindPtr(Left.Name);
                boolean IsPtrGlobal = IsGlobal(Ptr);
                String Rd = Func.NewReg();
                String AddRd = Func.NewReg();
                LoadInstr NewLoad = new LoadInstr(InstrSeg.load,Rd,I32,Ptr,I32+PointerChar,IsPtrGlobal);
                ScopeStack.peek().MatchPtr(Rd,Ptr);
                OperationInstr NewOp = new OperationInstr(ExprSeg.get(NodeType), AddRd, Rd,StrOne , I32, I32, InstrSeg.nullseg);
                StoreInstr NewStore = new StoreInstr(InstrSeg.store,AddRd,I32,Ptr,I32+PointerChar,IsPtrGlobal,false);
                ScopeStack.peek().MatchPtr(AddRd,Ptr);
                CurBlock.InsertInstr(NewLoad);
                CurBlock.InsertInstr(NewOp);
                CurBlock.InsertInstr(NewStore);
                Ret = new IRValue(I32,Rd);
            }
            case Plus -> {
                String Rd = Func.NewReg();
                String Type = Left.Type;
                if(Objects.equals(Type, IString)){
                    List<String> Params = new ArrayList<>(Arrays.asList(Left.Name,Right.Name));
                    List<String> ParamTypes = new ArrayList<>(Arrays.asList(Left.Type,Right.Type));
                    List<Boolean> IsParamsGlobal = new ArrayList<>(Arrays.asList(IsGlobal(Left.Name),IsGlobal(Right.Name)));
                    FuncCallInstr NewLink = new FuncCallInstr(InstrSeg.link,Rd,IString,StrApd,ParamTypes,Params,IsParamsGlobal);
                    CurBlock.InsertInstr(NewLink);
                    AddLinking(StrApd);
                }
                else {
                    OperationInstr NewOp = new OperationInstr(InstrSeg.add,Rd, Left.Name, Right.Name, I32, I32, InstrSeg.nullseg);
                    CurBlock.InsertInstr(NewOp);
                }
                Ret = new IRValue(Type,Rd);
            }
            case Minus,Divide,Mod,Multiply,LeftShift,RightShift,And,Or,Xor -> {
                String Rd = Func.NewReg();
                String Type = Left.Type;
                OperationInstr NewOp = new OperationInstr(ExprSeg.get(NodeType),Rd,Left.Name,Right.Name,I32,I32,InstrSeg.nullseg);
                CurBlock.InsertInstr(NewOp);
                Ret = new IRValue(Type,Rd);
            }
            case LessThanEqual,LessThan,GreaterThan,GreaterThanEqual,Equal,NotEqual ->{
                String Rd = Func.NewReg();
                if(Objects.equals(Left.Type, IString)) {
                    String FuncRet = Func.NewReg();
                    List<String> Params = new ArrayList<>(Arrays.asList(Left.Name,Right.Name));
                    List<String> ParamTypes = new ArrayList<>(Arrays.asList(Left.Type,Right.Type));
                    List<Boolean> IsParamsGlobal = new ArrayList<>(Arrays.asList(IsGlobal(Left.Name),IsGlobal(Right.Name)));
                    FuncCallInstr NewLink = new FuncCallInstr(InstrSeg.link,FuncRet,I32,StrCmp,ParamTypes,Params,IsParamsGlobal);
                    OperationInstr NewOp = new OperationInstr(InstrSeg.icmp, Rd, FuncRet, StrZero,I32, I32, InstrSeg.ult);
                    CurBlock.InsertInstr(NewLink);
                    CurBlock.InsertInstr(NewOp);
                    AddLinking(StrCmp);
                }
                else{
                    OperationInstr NewOp = new OperationInstr(InstrSeg.icmp,Rd, Left.Name, Right.Name,I32,I32, ExprSeg.get(NodeType));
                    CurBlock.InsertInstr(NewOp);
                }
                Ret = new IRValue(I32,Rd);
            }
            case AndAnd,OrOr->{
                String Rd = Func.NewReg();
                PhiInstr NewPhi = new PhiInstr(InstrSeg.phi,Rd,I32);
                IRBlock SucBlock = new IRBlock(BlockType.Basic,"PhiSuc",++BlockNum);
                SucBlock.EndInstr = CurBlock.EndInstr;
                if(NodeType == ExprType.AndAnd)NewPhi.NewPhiNode(CurBlock.Label,"1","");
                else NewPhi.NewPhiNode(CurBlock.Label,"0","");
              //  CurBlock.EndInstr = new BranchInstr(InstrSeg.br,SucBlock.Label,"","");
                NewPhi(NewPhi,SucBlock,Func,Root);
                CurBlock.InsertSubBlock(SucBlock);
                CurBlock = SucBlock;
                CurBlock.InsertInstr(NewPhi);
                Ret = new IRValue(I32,Rd);
            }
            case Assign->{
                String Rd = Func.NewReg();
                String Ptr = FindPtr(Left.Name);
                if(Objects.equals(Left.Type, IString)) {
                    List<String> Params = new ArrayList<>();
                    List<String> ParamTypes = new ArrayList<>();
                    List<Boolean> IsParamsGlobal = new ArrayList<>();
                    Params.add(Right.Name);
                    ParamTypes.add(Right.Type);
                    IsParamsGlobal.add(IsGlobal(Right.Name));
                    FuncCallInstr NewLink = new FuncCallInstr(InstrSeg.link,Rd,IString,StrCpy,ParamTypes,Params,IsParamsGlobal);
                    StoreInstr NewStore = new StoreInstr(InstrSeg.store,Rd, Right.Type, Ptr , Left.Type + PointerChar, IsGlobal(Ptr),false);
                    ScopeStack.peek().MatchPtr(Rd,Ptr);
                    CurBlock.InsertInstr(NewLink);
                    CurBlock.InsertInstr(NewStore);
                }
                else{
                    StoreInstr NewStore = new StoreInstr(InstrSeg.store, Right.Name, Right.Type, Ptr, Left.Type + PointerChar, IsGlobal(Ptr),IsGlobal(Right.Name));
                    LoadInstr NewLoad = new LoadInstr(InstrSeg.load,Rd,Right.Type,Ptr,Left.Type + PointerChar,IsGlobal(Ptr));
                    ScopeStack.peek().MatchPtr(Rd,Ptr);
                    ScopeStack.peek().MatchPtr(Right.Name,Ptr);
                    CurBlock.InsertInstr(NewStore);
                    CurBlock.InsertInstr(NewLoad);
                }
                Ret = new IRValue(Right.Type,Rd);
            }
            default -> Ret = null;
        }
        return Ret;
    }

}