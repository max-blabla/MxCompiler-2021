package IRBuilder;

import ASTNode.*;
import java.util.*;
//TODO  New Builtin
public class IRBuilder {
    GlobalAST Program;
    List<IRModule> ModuleList;
    HashMap<String, IRFunc> LinkingFunc;
    Boolean IsCollect;
    Integer BlockNum;
    IRFunc CurFunc;
    IRBlock CurBlock;
    IRModule CurModule;
    IRModule GlobalModule;
    IRFunc Init;
    IRFunc Main;

    public IRBuilder(GlobalAST program) {
        Program = program;
        ModuleList = new ArrayList<>();
        BlockNum = 0;
        CurBlock = null;
        CurModule = null;
        CurFunc = null;
        GlobalModule = null;
        Init = null;
        Main = null;
        IsCollect = false;
    }

    public List<IRModule> GetModuleList() {
        return ModuleList;
    }

    public void ProgramIR() {
        //TODO 内建函数和内建类 √
        ModuleList = new ArrayList<>();


        GlobalModule = new IRModule();
        LinkingFunc = new HashMap<>();
        GlobalModule.setName("_global");
        ModuleList.add(GlobalModule);
        LinkingSet();

        Init = new IRFunc();
        Init.FuncName = "_global._init";
        Init.RetType = "void";
        Init.IsUsed = true;

        Init.Start = new IRBlock(BlockType.Basic, "Start", BlockNum++);
        Init.End = new IRBlock(BlockType.Basic, "End", BlockNum++);
        ReturnInstr NewReturn = new ReturnInstr("ret", "void", "");
        Init.Start.EndInstr = new BranchInstr("br", Init.End.Label, "", "");
        Init.End.EndInstr = NewReturn;
        Init.ModuleName = "_global";
        CurFunc = null;
        CurModule = GlobalModule;
        for (DeclAST Decl : Program.DeclList) DeclIR(Decl);
        CurModule = GlobalModule;
        IsCollect = true;
        for (DeclAST Decl : Program.DeclList) DeclIR(Decl);

        GlobalModule.FuncInsert(Init);
    }

    void LinkingSet() {
        IRModule StringModule = new IRModule();
        StringModule.Name = "_string";
        StringModule.Size = 32;
        StringModule.IsUsed = true;
        for (int i = 0; i < 15; i++) {
            IRFunc NewLinking = new IRFunc();
            NewLinking.IsLinked = true;
            switch (i) {
                case 0 -> {
                    NewLinking.FuncName = "malloc";
                    NewLinking.RetType = TypeToIRType("int[]");
                    IRValue Size = new IRValue();
                    Size.Name = "size";
                    Size.Type = TypeToIRType("int");
                    NewLinking.PutParam(Size.Name, Size);
                }
                case 1 -> {
                    NewLinking.FuncName = "println";
                    NewLinking.RetType = TypeToIRType("void");
                    IRValue Out = new IRValue();
                    Out.Name = "out";
                    Out.Type = TypeToIRType("string");
                    NewLinking.PutParam(Out.Name, Out);
                }
                case 2 -> {
                    NewLinking.FuncName = "print";
                    NewLinking.RetType = TypeToIRType("void");
                    IRValue Out = new IRValue();
                    Out.Name = "out";
                    Out.Type = TypeToIRType("string");
                    NewLinking.PutParam(Out.Name, Out);
                }
                case 3 -> {
                    NewLinking.FuncName = "toString";
                    NewLinking.RetType = TypeToIRType("string");
                    IRValue Num = new IRValue();
                    Num.Name = "num";
                    Num.Type = TypeToIRType("int");
                    NewLinking.PutParam(Num.Name, Num);
                }
                case 4 -> {
                    NewLinking.FuncName = "printInt";
                    NewLinking.RetType = TypeToIRType("void");
                    IRValue Out = new IRValue();
                    Out.Name = "out";
                    Out.Type = TypeToIRType("int");
                    NewLinking.PutParam(Out.Name, Out);
                }
                case 5 -> {
                    NewLinking.FuncName = "printlnInt";
                    NewLinking.RetType = TypeToIRType("void");
                    IRValue Out = new IRValue();
                    Out.Name = "out";
                    Out.Type = TypeToIRType("int");
                    NewLinking.PutParam(Out.Name, Out);
                }
                case 6 -> {
                    NewLinking.FuncName = "getString";
                    NewLinking.RetType = TypeToIRType("string");
                }
                case 7 -> {
                    NewLinking.FuncName = "getInt";
                    NewLinking.RetType = TypeToIRType("int");
                }
                case 8 -> {
                    NewLinking.FuncName = "strcopy";
                    NewLinking.RetType = TypeToIRType("string");
                    IRValue Dst = new IRValue();
                    Dst.Name = "dst";
                    Dst.Type = TypeToIRType("string");
                    NewLinking.PutParam(Dst.Name, Dst);
                }

                case 9 -> {
                    NewLinking.FuncName = "append";
                    NewLinking.RetType = TypeToIRType("void");
                    IRValue Dst = new IRValue();
                    Dst.Name = "dst";
                    Dst.Type = TypeToIRType("string");
                    NewLinking.PutParam(Dst.Name, Dst);
                    IRValue Src = new IRValue();
                    Src.Name = "src";
                    Src.Type = TypeToIRType("string");
                    NewLinking.PutParam(Src.Name, Src);
                }
                case 10 -> {
                    NewLinking.ModuleName = "_string";
                    NewLinking.FuncName = "_string.length";
                    NewLinking.RetType = TypeToIRType("int");
                    IRValue Str = new IRValue();
                    Str.Name = "this";
                    Str.Type = TypeToIRType("string");
                    NewLinking.PutParam(Str.Name, Str);
                    StringModule.FuncInsert(NewLinking);
                }
                case 11 -> {
                    NewLinking.ModuleName = "_string";
                    NewLinking.FuncName = "_string.substring";
                    NewLinking.RetType = TypeToIRType("string");
                    IRValue Str = new IRValue();
                    Str.Name = "this";
                    Str.Type = TypeToIRType("string");
                    NewLinking.PutParam(Str.Name, Str);
                    IRValue L = new IRValue();
                    L.Name = "l";
                    L.Type = TypeToIRType("int");
                    NewLinking.PutParam(L.Name, L);
                    IRValue R = new IRValue();
                    R.Name = "r";
                    R.Type = TypeToIRType("int");
                    NewLinking.PutParam(R.Name, R);

                    StringModule.FuncInsert(NewLinking);
                }
                case 12 -> {
                    NewLinking.ModuleName = "_string";
                    NewLinking.FuncName = "_string.parseInt";
                    NewLinking.RetType = TypeToIRType("int");
                    IRValue Str = new IRValue();
                    Str.Name = "this";
                    Str.Type = TypeToIRType("string");
                    NewLinking.PutParam(Str.Name, Str);
                    StringModule.FuncInsert(NewLinking);
                }
                case 13 -> {
                    NewLinking.ModuleName = "_string";
                    NewLinking.FuncName = "_string.ord";
                    NewLinking.RetType = TypeToIRType("int");

                    IRValue Str = new IRValue();
                    Str.Name = "this";
                    Str.Type = TypeToIRType("string");
                    NewLinking.PutParam(Str.Name, Str);
                    StringModule.FuncInsert(NewLinking);
                    IRValue Index = new IRValue();
                    Index.Name = "index";
                    Index.Type = TypeToIRType("int");
                    NewLinking.PutParam(Index.Name, Index);
                }
                case 14 -> {
                    NewLinking.FuncName = "strcomp";
                    NewLinking.RetType = TypeToIRType("int");
                    IRValue Dst = new IRValue();
                    Dst.Name = "dst";
                    Dst.Type = TypeToIRType("string");
                    NewLinking.PutParam(Dst.Name, Dst);
                    IRValue Src = new IRValue();
                    Src.Name = "src";
                    Src.Type = TypeToIRType("string");
                    NewLinking.PutParam(Src.Name, Src);
                }
            }
            LinkingFunc.put(NewLinking.FuncName, NewLinking);
            AddLinking(NewLinking.FuncName);
        }
        ModuleList.add(StringModule);

    }

    void AddLinking(String LinkName) {
        IRFunc Func = LinkingFunc.get(LinkName);
        if (Func != null && !Func.IsUsed) {
            GlobalModule.FuncSet.add(Func);
            Func.IsUsed = true;
        }
    }

    void DeclIR(DeclAST DeclNode) {
        if (DeclNode instanceof VarDeclAST) VarDeclIR(DeclNode);
        else if (DeclNode instanceof FuncDeclAST) FuncDeclIR(DeclNode);
        else ClassDeclIR(DeclNode);
    }

    String TypeToIRType(String Type) {

        if (Objects.equals(Type, "int")) return "i32";
        else if (Objects.equals(Type, "void")) return "void";
        else if (Objects.equals(Type, "bool")) return "i8";
        else {
            int Demension = 0;

            String MainType;
            if (Type.indexOf('[') != -1) {
                MainType = Type.substring(0, Type.indexOf('['));
                Demension = (Type.lastIndexOf('[') - Type.indexOf('[')) / 2 + 1;

            } else {
                MainType = Type;
            }
            String RetMain;
            if (MainType.equals("int")) RetMain = "i32";
            else if (MainType.equals("bool")) RetMain = "i8";
            else if (MainType.equals("string")) RetMain = "_string";
            else {
                if (MainType.charAt(0) == '_') RetMain = MainType;
                else RetMain = "_" + MainType;
            }
            return RetMain + "*".repeat(Demension);
        }
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

    void VarDeclIR(DeclAST VarDeclNode) {
        //TODO 在这里所有的类型都是IR类型 √
        VarDeclAST VarNode = (VarDeclAST) VarDeclNode;
        List<VarDeclareAST> VarDeclList = VarNode.getVarDeclareList();
        //TODO 函数或者说放在构造函数里实现初始化 √
        if (Objects.equals(CurModule.Name, "_global")) {

            for (VarDeclareAST VarDecl : VarDeclList) {
                if (!IsCollect) {
                    IRValue NewValue = new IRValue();
                    NewValue.Type = TypeToIRType(VarNode.getType());
                    NewValue.Name = VarDecl.getId();
                    //TODO 构建新的Expr;
                    CurModule.VarInsert(NewValue);
                    CurModule.SetPtr(NewValue.Name,VarDecl.getId());
                } else {
                    IRBlock PreBlock = CurBlock;
                    CurBlock = Init.Start;
                    ExprToInstr(Init, VarDecl.getAssignExpr(), "");
                    CurBlock = PreBlock;
                }
            }
        } else {
            String InitFuncName = CurModule.Name + "._init";
            IRFunc ClassInit = CurModule.FindFunc(InitFuncName);
            ClassInit.ModuleName = CurModule.Name;
            for (VarDeclareAST VarDecl : VarDeclList) {
                if (!IsCollect) {
                    IRValue NewValue = new IRValue();
                    NewValue.Type = TypeToIRType(VarNode.getType());
                    NewValue.Name = VarDecl.getId();
                    String Ptr = "+" + CurModule.Size;
                    CurModule.Size += TypeSize(TypeToIRType(VarNode.getType()));
                    CurModule.VarInsert(NewValue);
                    CurModule.SetPtr(NewValue.Name, Ptr);
                } else {
                    IRBlock PreBlock = CurBlock;
                    CurBlock = ClassInit.Start;
                    ExprToInstr(ClassInit, VarDecl.getAssignExpr(), "");
                    CurBlock = PreBlock;
                }

            }
        }


        // TopModule.VarInsert(irVarPair);
    }

    void FuncDeclIR(DeclAST FuncDeclNode) {
        FuncDeclAST FuncNode = (FuncDeclAST) FuncDeclNode;
        //TODO 分配 this 指针 (即在参数表和VarTable里塞this)和参数表 √
        //TODO 在这里所有的类型都是IR类型 √
        //TODO 设置ModuleName √
        //TODO void 情况下可能没有 return 语句 但一定会塞ret进入 即为返回值alloca √
        //TODO 所有函数函数有类名 √
        //TODO Return 块分配 √
        IRFunc PreFunc = CurFunc;
        if (!IsCollect) {
            IRFunc NewFunc = new IRFunc();
            NewFunc.IsUsed = true;
            NewFunc.RetType = TypeToIRType(FuncNode.getFuncType());
            NewFunc.Start = new IRBlock(BlockType.Basic, "Start", BlockNum++);
            NewFunc.End = new IRBlock(BlockType.Basic, "End", BlockNum++);

            NewFunc.Start.EndInstr = new BranchInstr("br", NewFunc.End.Label, "", "");
            // IRBlock NewParamBlock = new IRBlock(BlockType.Basic,"Param",BlockNum++);
            if (Objects.equals(CurModule.Name, "_global")) {
                NewFunc.ModuleName = "_global";
                if (Objects.equals(FuncNode.getFuncName(), "main")) NewFunc.FuncName = "main";
                else NewFunc.FuncName = CurModule.Name + "." + FuncNode.getFuncName();
                CurFunc = NewFunc;
            } else {
                if (Objects.equals(FuncNode.getFuncName(), CurModule.Name))
                    CurFunc = CurModule.FindFunc(CurModule.Name + "._init");
                else {
                    NewFunc.ModuleName = CurModule.getName();
                    NewFunc.FuncName = CurModule.Name + "." + FuncNode.getFuncName();
                    CurFunc = NewFunc;
                    String ThisReg = CurFunc.NewReg();
                    IRValue ThisPtr = new IRValue();
                    ThisPtr.Type = TypeToIRType(CurModule.Name);
                    ThisPtr.Name = CurFunc.FuncName + "." + "this";

                    CurFunc.PutParam("this", ThisPtr);
                    CurFunc.Start.VarList.put("this", ThisPtr);


                    AllocaInstr NewAlloca = new AllocaInstr(ThisReg, ThisPtr.Type);
                    StoreInstr NewStore = new StoreInstr("store", ThisPtr.Name, ThisPtr.Type, ThisReg, ThisPtr.Type + "*", false, false);

                    CurFunc.Start.InsertInstr(NewAlloca);
                    CurFunc.Start.InsertInstr(NewStore);

                    CurFunc.Start.putPtr("this", ThisReg);
                }

                //TODO 塞入this 指针
            }
            CurBlock = CurFunc.Start;
            if (Objects.equals(FuncNode.getFuncName(), "main") && Objects.equals(CurModule.Name, "_global")) {
                //TODO 直接Call Init √
                Main = CurFunc;
                FuncCallInstr InitCall = new FuncCallInstr("call", CurFunc.NewReg(), "void", CurModule.Name + "." + "_init", new ArrayList<>(), new ArrayList<>());
                CurFunc.Start.InsertInstr(InitCall);
            }
        } else {
            if (Objects.equals(FuncNode.getFuncName(), CurModule.Name)) CurFunc = FindFunc(CurModule, "_init");
            else CurFunc = FindFunc(CurModule, FuncNode.getFuncName());
            CurBlock = CurFunc.Start;
            if (!Objects.equals(CurFunc.RetType, "void")) {
                IRValue RetValue = new IRValue();
                RetValue.Name = ".return";
                String Ptr = CurFunc.NewReg();
                CurBlock.putPtr(RetValue.Name, Ptr);
                RetValue.Type = CurFunc.RetType;
                AllocaInstr NewRetAlloc = new AllocaInstr(Ptr, CurFunc.RetType);
                CurFunc.Start.InsertInstr(NewRetAlloc);
                CurFunc.End.VarList.put(".return", RetValue);
            }
        }

        List<VarDeclAST> VarList = FuncNode.getParamList();
        //TODO 增添参数表项，参数表项为临时的，还有alloca，往Param块里塞Param √
        for (VarDeclAST VarDecl : VarList) {
            List<VarDeclareAST> VarDeclareList = VarDecl.getVarDeclareList();
            if (!IsCollect) {
                for (VarDeclareAST VarDeclare : VarDeclareList) {
                    IRValue Param = new IRValue();
                    Param.Name = CurFunc.NewReg();
                    Param.Type = TypeToIRType(VarDecl.getType());
                    CurFunc.PutParam(VarDeclare.getId(), Param);
                }
            } else {
                for (VarDeclareAST VarDeclare : VarDeclareList) {
                    IRValue StackParam = new IRValue();
                    IRValue Param = CurFunc.Params.get(VarDeclare.getId());
                    StackParam.Name = VarDeclare.getId();
                    String Ptr = CurFunc.NewReg();
                    CurBlock.putPtr(StackParam.Name, Ptr);
                    StackParam.Type = TypeToIRType(VarDecl.getType());

                    AllocaInstr NewAlloca = new AllocaInstr(Ptr, StackParam.Type);
                    StoreInstr NewStore = new StoreInstr("store", Param.Name, Param.Type, Ptr, Param.Type + "*", false, false);
                    CurBlock.VarList.put(VarDeclare.getId(), StackParam);
                    CurBlock.InsertInstr(NewAlloca);
                    CurBlock.InsertInstr(NewStore);

                    ExprToInstr(CurFunc, VarDeclare.getAssignExpr(), "");
                }
            }
        }


        List<StmtAST> StmtList = FuncNode.getStmtList();
        // TODO 先收集 所有的类和函数
        if (!IsCollect) {
            if (Objects.equals(CurModule.Name, "_global") || !Objects.equals(FuncNode.getFuncName(), CurModule.Name))
                CurModule.FuncInsert(CurFunc);
        } else {
            for (StmtAST Stmt : StmtList) StmtIR(Stmt);
            ReturnInstr NewReturn;
            if (!Objects.equals(CurFunc.RetType, "void")) {
                String Ptr = CurFunc.Start.getPtr(".return");
                String LoadReg = CurFunc.NewReg();

                LoadInstr NewLoad = new LoadInstr("load", LoadReg, CurFunc.RetType, Ptr, CurFunc.RetType + "*", false);
                NewReturn = new ReturnInstr("ret", CurFunc.RetType, LoadReg);
                CurFunc.End.InsertInstr(NewLoad);
            } else NewReturn = new ReturnInstr("ret", "void", "");
            if (!Objects.equals(CurFunc.FuncName, CurModule.Name + "._init")) CurFunc.End.EndInstr = NewReturn;
        }
        CurBlock = CurFunc.End;
        CurFunc = PreFunc;
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

    void ClassDeclIR(DeclAST ClassDeclNode) {
        //TODO 塞入 类初始化函数 初始化函数格式：类名+"."+"_init" √
        ClassDeclAST ClassNode = (ClassDeclAST) ClassDeclNode;
        IRModule ClassModule;
        if (!IsCollect) {
            ClassModule = new IRModule();
            ClassModule.Name = "_" + ClassNode.getClassName();
            ModuleList.add(ClassModule);

            IRFunc ClassInit = new IRFunc();
            ClassInit.IsUsed = true;
            ClassInit.ModuleName = ClassModule.Name;
            ClassInit.Start = new IRBlock(BlockType.Basic, "Start", BlockNum++);
            ClassInit.End = new IRBlock(BlockType.Basic, "End", BlockNum++);
            ClassInit.FuncName = ClassModule.getName() + "._init";
            ClassInit.RetType = "void";

            IRValue ThisPtr = new IRValue();
            IRValue StackThisPtr = new IRValue();
            ThisPtr.Type = TypeToIRType(ClassModule.Name);
            String ThisReg = ClassInit.NewReg();

            ThisPtr.Name = ClassInit.FuncName + "." + "this";
            ClassInit.PutParam("this", ThisPtr);


            StackThisPtr.Type = ThisPtr.Type;
            StackThisPtr.Name = "this";
            ClassInit.Start.VarList.put("this", StackThisPtr);
            AllocaInstr NewAlloca = new AllocaInstr(ThisReg, ThisPtr.Type);
            StoreInstr NewStore = new StoreInstr("store", ThisPtr.Name, ThisPtr.Type, ThisReg, ThisPtr.Type + "*", false, false);
            ClassInit.Start.EndInstr = new BranchInstr("br", ClassInit.End.Label, "", "");
            // IRBlock NewParamBlock = new IRBlock(BlockType.Basic, "Param", BlockNum++);

            ReturnInstr NewReturn = new ReturnInstr("ret", "void", "");
            ClassInit.Start.InsertInstr(NewAlloca);
            ClassInit.Start.InsertInstr(NewStore);
            ClassInit.End.EndInstr = NewReturn;

            //  ClassInit.Start.InsertSubBlock(NewParamBlock);
            ClassModule.FuncInsert(ClassInit);
        } else {
            ClassModule = GlobalModule;
            for (IRModule module : ModuleList) {
                if (Objects.equals(module.Name, "_" + ClassNode.getClassName())) {
                    ClassModule = module;
                    break;
                }
            }

        }
        IRModule PreModule = CurModule;
        CurModule = ClassModule;
        for (DeclAST Decl : ClassNode.getDeclList()) DeclIR(Decl);
        CurModule = PreModule;
        //     CurModule = null;
    }

    IRBlock FindLoopBody(IRBlock Block) {
        IRBlock curBlock = Block;
        while (curBlock != null) {
            if (curBlock.blockType == BlockType.Body) return curBlock;
            else curBlock = curBlock.Father;
        }
        return null;
    }

    void JumpStmtIR(StmtAST JumpStmtNode) {
        //或者让后面的无法加入
        //跳到本块块头或者下一块
        //即兄弟
        JumpStmtAST JumpStmt = (JumpStmtAST) JumpStmtNode;
        IRBlock Body = FindLoopBody(CurBlock);
        IRBlock Father = Body.Father;
        IRBlock Condition = new IRBlock(BlockType.Basic, "", BlockNum);
        for (IRBlock block : Father.SubBlocks) {
            if (block.blockType == BlockType.Condition) Condition = block;
        }
        BranchInstr Jump;
        if (JumpStmt.isBreak())
            Jump = new BranchInstr("br", Father.SubBlocks.get(Father.SubBlocks.size() - 1).Label, "", "");
        else Jump = new BranchInstr("br", Condition.Label, "", "");

        CurBlock.EndInstr = Jump;
        CurBlock.ShutBlock();
    }

    void ReturnStmtIR(StmtAST ReturnStmtNode) {
        ReturnStmtAST ReturnStmt = (ReturnStmtAST) ReturnStmtNode;
        if (ReturnStmt.getExpr() != null) {
            String Ptr = CurFunc.Start.getPtr(".return");
            IRValue Ret = ExprToInstr(CurFunc, ReturnStmt.getExpr(), "");
            StoreInstr NewStore = new StoreInstr("store", Ret.Name, Ret.Type, Ptr, Ret.Type + '*', !Ptr.contains("."), !Ret.Name.contains("."));
            CurBlock.InsertInstr(NewStore);
        }
        CurBlock.EndInstr = new BranchInstr("br", CurFunc.End.Label, "", "");
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


        BranchInstr PreceedJump = new BranchInstr("br", NewConditionBlock.Label, "", "");
        BranchInstr BodyJump = new BranchInstr("br", NewConditionBlock.Label, "", "");
        BranchInstr ConditionJump = new BranchInstr("br", NewBodyBlock.Label, SucceedBlock.Label, "");

        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewConditionBlock.EndInstr = ConditionJump;
        NewBodyBlock.EndInstr = BodyJump;

        CurBlock = NewConditionBlock;
        IRValue Condition = ExprToInstr(CurFunc, WhileStmt.getConditionExpr(), "");
        CurBlock.EndInstr = new BranchInstr("br", NewBodyBlock.Label, SucceedBlock.Label, Condition.Name);


        CurBlock = NewBodyBlock;
        StmtIR(WhileStmt.getLoopStmt());
        CurBlock = SucceedBlock;

    }

    void IfStmtIR(StmtAST IfStmtNode) {
        IfStmtAST IfStmt = (IfStmtAST) IfStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition, "If-Condition", BlockNum++);
        CurBlock.InsertSubBlock(NewConditionBlock);

        IRBlock NewTrueBlock = new IRBlock(BlockType.TrueStmt, "If-True-Stmt", BlockNum++);
        CurBlock.InsertSubBlock(NewTrueBlock);

        IRBlock NewFalseBlock = new IRBlock(BlockType.FalseStmt, "If-False-Stmt", BlockNum++);
        CurBlock.InsertSubBlock(NewFalseBlock);

        IRBlock SucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(SucceedBlock);


        BranchInstr FalseJump = new BranchInstr("br", SucceedBlock.Label, "", "");
        BranchInstr PreceedJump = new BranchInstr("br", NewConditionBlock.Label, "", "");
        BranchInstr TrueJump = new BranchInstr("br", SucceedBlock.Label, "", "");

        IRBlock PreBlock = CurBlock;
        CurBlock = NewConditionBlock;
        IRValue Condition = ExprToInstr(CurFunc, IfStmt.getConditionExpr(), "");
        CurBlock.EndInstr = new BranchInstr("br", NewTrueBlock.Label, NewFalseBlock.Label, Condition.Name);
        CurBlock = PreBlock;


        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewTrueBlock.EndInstr = TrueJump;
        NewFalseBlock.EndInstr = FalseJump;
        //   NewConditionBlock.EndInstr = ConditionJump;


        CurBlock = NewTrueBlock;
        StmtIR(IfStmt.getTrueStmt());


        if (IfStmt.getFalseStmt() != null) {
            CurBlock = NewFalseBlock;
            StmtIR(IfStmt.getFalseStmt());
        }
        CurBlock = SucceedBlock;
    }

    void ForStmtIR(StmtAST ForStmtNode) {
        ForStmtAST ForStmt = (ForStmtAST) ForStmtNode;

        IRBlock NewConditionBlock = new IRBlock(BlockType.Condition, "For-Condition", BlockNum++);
        CurBlock.InsertSubBlock(NewConditionBlock);

        IRBlock NewBodyBlock = new IRBlock(BlockType.Body, "For-Body", BlockNum++);
        CurBlock.InsertSubBlock(NewBodyBlock);

        IRBlock IncrBlock = new IRBlock(BlockType.Basic, "For-Incr", BlockNum++);
        CurBlock.InsertSubBlock(IncrBlock);

        IRBlock SucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(SucceedBlock);

        if (ForStmt.getInitStmt() != null) {
            String Type = ForStmt.getInitStmt().getType();
            List<VarDeclareAST> VarDeclList = ForStmt.getInitStmt().getVarDeclareList();
            //Alloca 加 等号
            for (VarDeclareAST VarDeclare : VarDeclList) {
                IRValue NewValue = new IRValue();
                NewValue.Name = VarDeclare.getId();
                NewValue.Type = TypeToIRType(Type);
                AllocaInstr RetAlloc = new AllocaInstr(CurFunc.NewReg(), NewValue.Type);
                CurBlock.InsertInstr(RetAlloc);
                CurBlock.VarList.put(NewValue.Name, NewValue);
                CurBlock.putPtr(NewValue.Name, CurFunc.NewReg());
                ExprToInstr(CurFunc, VarDeclare.getAssignExpr(), "");
            }
        } else ExprToInstr(CurFunc, ForStmt.getInitExpr(), "");

        BranchInstr PreceedJump = new BranchInstr("br", NewConditionBlock.Label, "", "");
        BranchInstr ForBodyJump = new BranchInstr("br", IncrBlock.Label, "", "");
        BranchInstr IncrJump = new BranchInstr("br", NewConditionBlock.Label, "", "");

        SucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewBodyBlock.EndInstr = ForBodyJump;
        IncrBlock.EndInstr = IncrJump;

        if (ForStmt.getConditionExpr() != null) {
            CurBlock = NewConditionBlock;
            IRValue Condition = ExprToInstr(CurFunc, ForStmt.getConditionExpr(), "");
            CurBlock.EndInstr = new BranchInstr("br", NewBodyBlock.Label, SucceedBlock.Label, Condition.Name);
        } else {
            NewConditionBlock.EndInstr = new BranchInstr("br", NewBodyBlock.Label, "", "");
        }


        CurBlock = NewBodyBlock;
        StmtIR(ForStmt.getLoopStmt());
        CurBlock = SucceedBlock;

        IRBlock PreBlock = CurBlock;
        CurBlock = IncrBlock;
        ExprToInstr(CurFunc, ForStmt.getIncrExpr(), "");
        CurBlock = PreBlock;

    }

    void ExprStmtIR(StmtAST ExprStmtNode) {
        ExprStmtAST ExprStmt = (ExprStmtAST) ExprStmtNode;
        //CurBlock.InsertExpr(ExprStmt.getExpr());
        ExprToInstr(CurFunc, ExprStmt.getExpr(), "");
    }

    void SuiteIR(StmtAST SuiteNode) {
        //生成新的block
        SuiteAST Suite = (SuiteAST) SuiteNode;

        IRBlock NewBasicBlock = new IRBlock(BlockType.Basic, "Basic", BlockNum++);
        CurBlock.InsertSubBlock(NewBasicBlock);

        IRBlock NewSucceedBlock = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(NewSucceedBlock);


        BranchInstr PreceedJump = new BranchInstr("br", NewBasicBlock.Label, "", "");
        BranchInstr BasicJump = new BranchInstr("br", NewSucceedBlock.Label, "", "");

        NewSucceedBlock.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = PreceedJump;
        NewBasicBlock.EndInstr = BasicJump;

        List<StmtAST> StmtList = Suite.getStmtList();
        CurBlock = NewBasicBlock;
        for (StmtAST Stmt : StmtList) StmtIR(Stmt);
        CurBlock = NewSucceedBlock;
    }

    IRFunc FindFunc(IRModule Module, String FuncName) {
        if (Module.FindFunc(FuncName) != null) return Module.FindFunc(FuncName);
        else if (Module.FindFunc(Module.Name + "." + FuncName) != null)
            return Module.FindFunc(Module.Name + "." + FuncName);
        else return GlobalModule.FindFunc(FuncName);
    }

    IRValue ModuleFindValue(IRModule Module, String ValueName) {
        return Module.FindValue(ValueName);
    }

    String ModuleFindPtr(IRModule Module, String ValueName) {
        return Module.GetPtr(ValueName);
    }

    IRModule FindModule(String ModuleType) {
        for (IRModule Module : ModuleList)
            if (Objects.equals(Module.Name, ModuleType))
                return Module;
        return null;
    }


    IRValue FindValue(IRBlock Block, String ValueName, IRModule Module) {
        IRBlock curBlock = Block;
        while (curBlock != null) {
            if (curBlock.FindVar(ValueName) != null) return curBlock.FindVar(ValueName);
            else curBlock = curBlock.Father;
        }
        if (Module.FindValue(ValueName) != null) return Module.FindValue(ValueName);
        return GlobalModule.FindValue(ValueName);
    }

    String FindPtr(IRBlock Block, String Name, IRModule Module) {
        IRBlock curBlock = Block;
        while (curBlock != null) {
            if (curBlock.getPtr(Name) != null) return curBlock.getPtr(Name);
            else curBlock = curBlock.Father;
        }
        if (Module.GetPtr(Name) != null) return Module.GetPtr(Name);
        return GlobalModule.GetPtr(Name);
    }

    void VarStmtIR(StmtAST VarStmtNode) {
        //TODO 如果有 = 就拆开 √
        //添加 Alloca
        VarStmtAST VarStmt = (VarStmtAST) VarStmtNode;
        String Type = VarStmt.getType();
        List<VarDeclareAST> VarDeclList = VarStmt.getVarDeclList();
        //Alloca 加 等号
        for (VarDeclareAST VarDeclare : VarDeclList) {
            IRValue NewValue = new IRValue();
            NewValue.Name = VarDeclare.getId();
            NewValue.Type = TypeToIRType(Type);
            String Ptr = CurFunc.NewReg();
            CurBlock.putPtr(VarDeclare.getId(), Ptr);
            AllocaInstr RetAlloc = new AllocaInstr(Ptr, NewValue.Type);
            //TODO 如果不是内建类 加上调用初始化函数
            CurBlock.InsertInstr(RetAlloc);
            CurBlock.VarList.put(VarDeclare.getId(), NewValue);
            ExprToInstr(CurFunc, VarDeclare.getAssignExpr(), "");
        }
    }

    List<IRValue> ExprListToIRValue(IRFunc Func, IRBlock Block, List<ExprAST> ExprLists, String RetReg) {
        List<IRValue> Ret = new ArrayList<>();
        CurBlock = Block;
        for (ExprAST expr : ExprLists) Ret.add(ExprToInstr(Func, expr, RetReg));
        return Ret;
    }

    String NewArray(IRFunc Func, String Ptr, String Type, List<IRValue> AllSize, Integer CurDemension) {
        String Multi = Func.NewReg();
        String NewPtr = Func.NewReg();
        String Addi = Func.NewReg();
        String Rd = Func.NewReg();
      //  String AimType;
        Boolean IsBultin = Objects.equals(Type, "i32") ||Objects.equals(Type, "_string")||Objects.equals(Type, "i8");
        if (CurDemension != AllSize.size()) {
            List<String> Params = new ArrayList<>();
            List<String> ParamTypes = new ArrayList<>();
            Params.add(Multi);
            ParamTypes.add(TypeToIRType("int"));
            OperationInstr NewMulti = new OperationInstr("mul", AllSize.get(CurDemension).Name, "4", TypeToIRType("int"), TypeToIRType("int"), Multi, "");
            OperationInstr NewAddi = new OperationInstr("add", Multi, "4", TypeToIRType("int"), TypeToIRType("int"), Addi, "");
            CurBlock.InsertInstr(NewMulti);
            CurBlock.InsertInstr(NewAddi);

            FuncCallInstr NewMalloc = new FuncCallInstr("link", Rd, Type, "malloc", ParamTypes, Params);
            StoreInstr SizeStore = new StoreInstr("store", AllSize.get(CurDemension).Name, TypeToIRType("int"), Rd, Type, false, false);
            CurBlock.InsertInstr(NewMalloc);
            CurBlock.InsertInstr(SizeStore);
            if(CurDemension != 0){
                StoreInstr PtrStore = new StoreInstr("store", Rd, Type, Ptr, Type + "*", !Ptr.contains("."), false);
                CurBlock.InsertInstr(PtrStore);
            }
        }
        else {
            if (!IsBultin) {
                //  StoreInstr PtrStore = new StoreInstr("store", "a", "a", "a", "a" + "*", false, false);
                String InitReg = Func.NewReg();
                List<String> MParams = new ArrayList<>();
                List<String> MParamTypes = new ArrayList<>();
                MParams.add(Addi);
                MParamTypes.add(TypeToIRType("int"));
                OperationInstr NewAddi = new OperationInstr("add", Integer.toString(TypeSize(Type) / 8), "0", TypeToIRType("int"), TypeToIRType("int"), Addi, "");
                CurBlock.InsertInstr(NewAddi);
                FuncCallInstr NewMalloc = new FuncCallInstr("link", Rd, Type, "malloc", MParamTypes, MParams);
                CurBlock.InsertInstr(NewMalloc);
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Rd);
                ParamTypes.add(Type);
                FuncCallInstr CallInit = new FuncCallInstr("call", InitReg, "void", Type + "._init", ParamTypes, Params);
                CurBlock.InsertInstr(CallInit);
                StoreInstr PtrStore = new StoreInstr("store", Rd, Type, Ptr, Type + "*", !Ptr.contains("."), false);
                CurBlock.InsertInstr(PtrStore);
            }
            return "";
        }
        IRBlock NewCondition = new IRBlock(BlockType.Condition, "NewArrayCondition", BlockNum++);
        IRBlock NewBlock = new IRBlock(BlockType.Basic, "NewArrayBody", BlockNum++);
        IRBlock NewSucceed = new IRBlock(BlockType.Basic, "Succeed", BlockNum++);
        CurBlock.InsertSubBlock(NewCondition);
        CurBlock.InsertSubBlock(NewBlock);
        CurBlock.InsertSubBlock(NewSucceed);
        String Iteration = Func.NewReg();
        String Condition = Func.NewReg();
        PhiInstr PhiStart = new PhiInstr("phi", "start", Iteration, TypeToIRType("int"));
        OperationInstr NewIterInit = new OperationInstr("add", "0", "0", TypeToIRType("int"), TypeToIRType("int"), Iteration, "");
        OperationInstr NewComp = new OperationInstr("icmp", Iteration, AllSize.get(CurDemension).Name, TypeToIRType("int"), TypeToIRType("int"), Condition, "ult");
        BranchInstr NewCondiBr = new BranchInstr("br", NewBlock.Label, NewSucceed.Label, Condition);
        BranchInstr NewInitBr = new BranchInstr("br", NewCondition.Label, "", "");
        CurBlock.InsertInstr(PhiStart);
        CurBlock.InsertInstr(NewIterInit);
        NewSucceed.EndInstr = CurBlock.EndInstr;
        CurBlock.EndInstr = NewInitBr;

        NewCondition.InsertInstr(NewComp);
        NewCondition.EndInstr = NewCondiBr;
        GetelementInstr NewGet = new GetelementInstr("getelement", "index", NewPtr, Type, Rd, Type, Iteration, TypeToIRType("int"), 0, TypeToIRType("int"), false);
        BranchInstr NewLoopBr = new BranchInstr("br", NewCondition.Label, "", "");

        NewBlock.InsertInstr(NewGet);
        NewBlock.EndInstr = NewLoopBr;
        CurBlock = NewBlock;
        NewArray(Func, NewPtr, Type.substring(0, Type.lastIndexOf('*')), AllSize, CurDemension + 1);
        OperationInstr NewIterAdd = new OperationInstr("add", Iteration, "1", TypeToIRType("int"), TypeToIRType("int"), Iteration, "");
        NewBlock.InsertInstr(NewIterAdd);

        PhiInstr PhiEnd = new PhiInstr("phi", "end", Iteration, TypeToIRType("int"));
        NewSucceed.InsertInstr(PhiEnd);
        CurBlock = NewSucceed;
        AddLinking("malloc");
        return Rd;
    }

    IRValue ExprToInstr(IRFunc Func, ExprAST Root,String RetReg){
        if(Root == null) return null;
        IRValue Left;
        IRValue Right;
        if(Root.getType() == ExprType.Assign){
            Right = ExprToInstr(Func, Root.getRightSonExpr(),"");
            Left = ExprToInstr(Func, Root.getLeftSonExpr(),"");
        }
        else if(!(Root.getType() == ExprType.AndAnd || Root.getType() == ExprType.OrOr || Root.getType() == ExprType.ExprList||Root.getType() == ExprType.New))
        {
            Left = ExprToInstr(Func,  Root.getLeftSonExpr(),"");
           Right = ExprToInstr(Func,  Root.getRightSonExpr(),"");
        }
        else {
            Left = null;
            Right = null;
        }
        String Rd ;
        if(!Objects.equals(RetReg, "") ) Rd = RetReg;
        else  Rd = Func.NewReg();
        IRValue Ret;
        Ret = new IRValue();
        Ret.Name = Rd;
        //还有 new string的= 和 string的+
        if(Root.getType() == ExprType.New){
            NewTypeAST NewTypeNode =  (NewTypeAST) Root;
            if(NewTypeNode.IsNewArray()){
                Ret.Type = TypeToIRType(NewTypeNode.getNewType());
                List<IRValue> Values = ExprListToIRValue(Func,CurBlock,NewTypeNode.getExprList(),"");
                Ret.Name = NewArray(Func,"",Ret.Type,Values,0);
                AddLinking("malloc");
            }
            else{
                Ret.Type = TypeToIRType(NewTypeNode.getNewType());
                Boolean IsBultin = Objects.equals( Ret.Type , "i32") ||Objects.equals( Ret.Type , "_string")||Objects.equals( Ret.Type , "i8");
                if(!IsBultin) {
                    String InitReg = Func.NewReg();
                    String Addi = Func.NewReg();
                    List<String> MParams = new ArrayList<>();
                    List<String> MParamTypes = new ArrayList<>();
                    MParams.add(Addi);
                    MParamTypes.add(TypeToIRType("int"));
                    OperationInstr NewAddi = new OperationInstr("add", Integer.toString(TypeSize(Ret.Type) / 8), "0", TypeToIRType("int"), TypeToIRType("int"), Addi, "");
                    CurBlock.InsertInstr(NewAddi);
                    FuncCallInstr NewMalloc = new FuncCallInstr("link", Rd, Ret.Type, "malloc", MParamTypes, MParams);
                    CurBlock.InsertInstr(NewMalloc);
                    List<String> Params = new ArrayList<>();
                    List<String> ParamTypes = new ArrayList<>();
                    Params.add(Rd);
                    ParamTypes.add(Ret.Type);
                    FuncCallInstr CallInit = new FuncCallInstr("call", InitReg, "void", Ret.Type + "._init", ParamTypes, Params);
                    CurBlock.InsertInstr(CallInit);
                }
            }
        }
        else if(Root.getType() == ExprType.Par) {
            Ret = Left;
            //       --Func.RegCnt;
        }
        else if(Root.getType() == ExprType.Primary){
            Ret = Left;
            //       --Func.RegCnt;
        }
        else if(Root.getType() == ExprType.Literal){
            //name 照常返回
            LiteralAST LiteralNode = (LiteralAST) Root;
            if(Objects.equals(LiteralNode.getLiteralType(), "int")) {
                OperationInstr NewOp = new OperationInstr("add",LiteralNode.getContext(),"0",TypeToIRType("int"),TypeToIRType("int"),Rd,"");
                CurBlock.InsertInstr(NewOp);
                Ret.Type = TypeToIRType("int");
            }
            else if(Objects.equals(LiteralNode.getLiteralType(), "bool")){
                int Tmp;
                if(Objects.equals(LiteralNode.getContext(), "false")) Tmp = 0;
                else Tmp = 1;
                OperationInstr NewOp = new OperationInstr("or", Integer.toString(Tmp),"0",TypeToIRType("bool"),TypeToIRType("bool"),Rd,"");
                CurBlock.InsertInstr(NewOp);
                Ret.Type = TypeToIRType("bool");
            }
            else if(Objects.equals(LiteralNode.getLiteralType(),"string")){
                IRValue NewConstStr = new IRValue();
                NewConstStr.Name = "_"+"str"+GlobalModule.VarTable.size();
                NewConstStr.Type = TypeToIRType("string");
                NewConstStr.Asciz = LiteralNode.getContext().substring(1,LiteralNode.getContext().length()-1);
                GlobalModule.VarTable.put(NewConstStr.Name,NewConstStr);
                Ret.Name =  NewConstStr.Name;
                Ret.Type = TypeToIRType("string");
         //       --Func.RegCnt;
            }
            else {
                Ret.Type = TypeToIRType("int");
                Ret.Name = Rd;
                OperationInstr NewAddi = new OperationInstr("add","0","0",TypeToIRType("int"),TypeToIRType("int"),Rd,"");
                CurBlock.InsertInstr(NewAddi);
                //      --Func.RegCnt;
            }
        }
        else if(Root.getType() == ExprType.Label) {

            LabelAST LabelNode = (LabelAST) Root;
            Boolean IsSkip;
            ExprAST GrandFatherNode;
            if (Root.Father.Father instanceof ExprAST) {
                GrandFatherNode = (ExprAST) Root.Father.Father;
                if (GrandFatherNode.getType() != ExprType.FuncCall && GrandFatherNode.getType() != ExprType.MemCall )
                    IsSkip = false;
                else IsSkip = true;
            } else IsSkip = false;
            //判断是否为 堆上 还是 栈上
            if (!IsSkip) {
                IRValue Value;
                String PrePtr;
                String Ptr;
                Value = FindValue(CurBlock, LabelNode.getId(),CurModule);
                PrePtr = FindPtr(CurBlock,LabelNode.getId(),CurModule);
                if(PrePtr != null) {
                    if (PrePtr.charAt(0) == '+') {
                        String ThisPtr = FindPtr(CurBlock, "this", CurModule);
                        String ThisRd = Func.NewReg();
                        Ptr = Func.NewReg();
                        LoadInstr NewThisLoad = new LoadInstr("load", ThisRd, CurModule.Name, ThisPtr, CurModule.Name + "*", false);
                        GetelementInstr NewGet = new GetelementInstr("getelementptr", "offset", Ptr, Value.Type + "*", ThisRd, CurModule.Name, "0", "i32", Integer.parseInt(PrePtr.substring(1)), "i32", false);
                        CurBlock.InsertInstr(NewThisLoad);
                        CurBlock.InsertInstr(NewGet);
                    } else Ptr = PrePtr;
                    LoadInstr NewLoad = new LoadInstr("load", Rd, Value.Type, Ptr, Value.Type + "*", false);
                    CurBlock.InsertInstr(NewLoad);
                }
                else{
                    Ptr = LabelNode.getId();
                    LoadInstr NewLoad = new LoadInstr("load", Rd, Value.Type,Ptr, Value.Type + "*", true);
                    CurBlock.InsertInstr(NewLoad);
                }
                Ret.Type = Value.Type;
                Ret.Name = Rd;
                CurBlock.putPtr(Ret.Name,Ptr);
            } else {
                Ret.Name = LabelNode.getId();
            }
        }
        else if(Root.getType() == ExprType.MemCall) {
            //判断父亲是否为函数调用
            ExprAST FatherNode;
            if(Root.Father instanceof ExprAST) FatherNode = (ExprAST) Root.Father ;
            else FatherNode = null;
            if (FatherNode != null && FatherNode.getType() == ExprType.FuncCall) {
                Ret.Name = Left.Name + "." + Right.Name;
                Ret.Type = Left.Type;
            }
            else {
                //Load 出来
                IRValue LeftClass;
                String ClassPtr;
                String PrePtr;
                LeftClass = FindValue(CurBlock,Left.Name,CurModule);
                if(LeftClass == null) LeftClass = Left;
                IRModule TargetModule = FindModule(LeftClass.Type);
                IRValue Value = ModuleFindValue(TargetModule, Right.Name);
                String ValueOffset = ModuleFindPtr(TargetModule, Right.Name);
                PrePtr = FindPtr(CurBlock,Left.Name,CurModule);
                String Ptr = Func.NewReg();
                if(PrePtr != null) {
                    if (PrePtr.charAt(0) == '+') {
                        String ThisPtr = FindPtr(CurBlock, "this", CurModule);
                        String ThisRd = Func.NewReg();
                        ClassPtr = Func.NewReg();
                        LoadInstr NewThisLoad = new LoadInstr("load", ThisRd, CurModule.Name, ThisPtr, CurModule.Name + "*", false);
                        GetelementInstr NewGet = new GetelementInstr("getelementptr", "offset", ClassPtr, LeftClass.Type + "*", ThisRd, CurModule.Name, "0", "i32", Integer.parseInt(PrePtr.substring(1)), "i32", false);
                        CurBlock.InsertInstr(NewThisLoad);
                        CurBlock.InsertInstr(NewGet);
                    } else ClassPtr = PrePtr;
                    String ClassReg = Func.NewReg();
                    LoadInstr NewLoadPtr = new LoadInstr("load", ClassReg, LeftClass.Type, ClassPtr, LeftClass.Type + "*", !ClassPtr.contains("."));
                    GetelementInstr NewGet = new GetelementInstr("getelementptr", "offset", Ptr, Value.Type + "*", ClassReg, LeftClass.Type, "0", "i32", Integer.parseInt(ValueOffset.substring(1)), "i32", false);
                    LoadInstr NewLoad = new LoadInstr("load", Rd, Value.Type, Ptr, Value.Type + "*", false);
                    CurBlock.InsertInstr(NewLoadPtr);
                    CurBlock.InsertInstr(NewGet);
                    CurBlock.InsertInstr(NewLoad);
                    CurBlock.putPtr(Rd, Ptr);
                }
                else{
                    GetelementInstr NewGet = new GetelementInstr("getelementptr", "offset", Ptr, Value.Type + "*", LeftClass.Name, LeftClass.Type, "0", "i32", Integer.parseInt(ValueOffset.substring(1)), "i32", false);
                    LoadInstr NewLoad = new LoadInstr("load", Rd, Value.Type, Ptr, Value.Type + "*", false);
                    CurBlock.InsertInstr(NewGet);
                    CurBlock.InsertInstr(NewLoad);
                }
                Ret.Name = Rd;
                Ret.Type = Value.Type;
            }
        }
        else if(Root.getType() == ExprType.FuncCall){

            ExprListAST ParamNode = (ExprListAST) Root.getRightSonExpr();
            List<IRValue> Params = ExprListToIRValue(Func,CurBlock,ParamNode.getExprList(),"");

            List<String> ParamTypes = new ArrayList<>();
            List<String> ParamNames = new ArrayList<>();

            IRModule TargetModule;
            int Index = Left.Name.lastIndexOf('.');
            String FuncName = Left.Name.substring(Index+1);
            boolean IsSize = false;
                if (Index == -1) {
                    if (FindFunc(CurModule, FuncName) != null) {
                        TargetModule = CurModule;
                        ParamTypes.add(TargetModule.Name);
                        String Ptr = FindPtr(CurBlock,"this",CurModule);
                        String PtrRd = Func.NewReg();
                        LoadInstr GetClass = new LoadInstr("load",PtrRd,TargetModule.Name,Ptr,TargetModule.Name+"*",false);
                        ParamNames.add(PtrRd);
                        CurBlock.InsertInstr(GetClass);
                    }
                    else TargetModule = GlobalModule;
                } else {
                    IRValue LeftClass;
                    String ClassPtr;
                    String ClassValueName = Left.Name.substring(0, Index);
                    LeftClass = FindValue(CurBlock,ClassValueName,CurModule);
                    if(LeftClass == null){
                        LeftClass = new IRValue();
                        LeftClass.Name  = ClassValueName;
                        LeftClass.Type = Left.Type;
                    }
                    ClassPtr = FindPtr(CurBlock,ClassValueName,CurModule);
                    TargetModule = FindModule(LeftClass.Type);
                    if(FuncName.equals("size") && LeftClass.Type.contains("*")){
                        Ret.Type = TypeToIRType("int");
                        Ret.Name = Rd;
                        String PtrReg = Func.NewReg();
                        LoadInstr GetPtr = new LoadInstr("load",PtrReg,LeftClass.Type,ClassPtr,LeftClass.Type+"*",!ClassPtr.contains("."));
                        LoadInstr GetSize = new LoadInstr("load",Ret.Name,TypeToIRType("int"),PtrReg,LeftClass.Type,false);
                        CurBlock.InsertInstr(GetPtr);
                        CurBlock.InsertInstr(GetSize);
                        IsSize = true;
                    }
                    else {
                        ParamTypes.add(LeftClass.Type);
                        String Ptr;
                        String PrePtr  = FindPtr(CurBlock,LeftClass.Name,CurModule);
                        String PtrRd = Func.NewReg();
                        if(PrePtr != null) {
                            if (PrePtr.charAt(0) == '+') {
                                String ThisPtr = FindPtr(CurBlock, "this", CurModule);
                                String ThisRd = Func.NewReg();
                                Ptr = Func.NewReg();
                                LoadInstr NewThisLoad = new LoadInstr("load", ThisRd, CurModule.Name, ThisPtr, CurModule.Name + "*", false);
                                GetelementInstr NewGet = new GetelementInstr("getelementptr", "offset", ClassPtr, LeftClass.Type + "*", ThisRd, CurModule.Name, "0", "i32", Integer.parseInt(PrePtr.substring(1)), "i32", false);
                                CurBlock.InsertInstr(NewThisLoad);
                                CurBlock.InsertInstr(NewGet);
                            }
                            else Ptr = PrePtr;
                            LoadInstr GetClass = new LoadInstr("load", PtrRd, LeftClass.Type, Ptr, LeftClass.Type + "*", !Ptr.contains("."));
                            ParamNames.add(PtrRd);
                            CurBlock.InsertInstr(GetClass);
                        }
                        else ParamNames.add(LeftClass.Name);
                    }
                }
                for (IRValue value : Params) {
                    ParamTypes.add(value.Type);
                    ParamNames.add(value.Name);
                }
                if(!IsSize) {
                    IRFunc CallFunc;
                    if (("_" + FuncName).equals(TargetModule.Name)) CallFunc = FindFunc(TargetModule, "_init");
                    else CallFunc = FindFunc(TargetModule, FuncName);
                    //CallFunc 中已带模块名
                    if (!CallFunc.IsLinked) {
                        FuncCallInstr FuncCall = new FuncCallInstr("call", Ret.Name, CallFunc.RetType, CallFunc.FuncName, ParamTypes, ParamNames);
                        CurBlock.InsertInstr(FuncCall);
                    } else {
                        FuncCallInstr FuncCall = new FuncCallInstr("link", Ret.Name, CallFunc.RetType, CallFunc.FuncName, ParamTypes, ParamNames);
                        CurBlock.InsertInstr(FuncCall);
                    }
                    Ret.Type = CallFunc.RetType;
                }
        }
        else if(Root.getType() == ExprType.Index){
            //TODO 补个Load，然后返回的Ptr修改 √
            Ret.Type = Left.Type.substring(0,Left.Type.lastIndexOf("*"));
            Ret.Name = Rd;
            String Ptr = Func.NewReg();
            GetelementInstr NewPtr = new GetelementInstr("getelementptr","index",Ptr,Left.Type,Left.Name,Left.Type,Right.Name,Right.Type,0,"",!Left.Name.contains("."));
            LoadInstr NewLoad = new LoadInstr("load",Ret.Name,Ret.Type,Ptr,Left.Type,false);
            CurBlock.InsertInstr(NewPtr);
            CurBlock.InsertInstr(NewLoad);
            IRValue NewTemp = new IRValue();
            CurBlock.putPtr(Ret.Name,Ptr);
        }
        else if(Root.getType() == ExprType.Negative){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("sub","0",Left.Name,TypeToIRType("int"),Left.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType() == ExprType.LeftSelfPlus){
            Boolean isGlobal = !Left.Name.contains(".");
            Ret.Type = Left.Type;
            String LoadReg =  Rd;
            Rd = Func.NewReg();
            Ret.Name = Rd;
            LoadInstr NewLoad = new LoadInstr("load",LoadReg,Ret.Type,Left.Name,Left.Type+"*",isGlobal);
            OperationInstr NewOp = new OperationInstr("add",LoadReg,"1",Left.Type,TypeToIRType("int"),Rd,"");
            StoreInstr NewStore = new StoreInstr("store",Rd,Left.Type,Left.Name,Left.Type+"*",isGlobal,false);
            CurBlock.InsertInstr(NewLoad);
            CurBlock.InsertInstr(NewOp);
            CurBlock.InsertInstr(NewStore);
        }
        else if(Root.getType() == ExprType.LeftSelfMinus){
            Boolean isGlobal = !Left.Name.contains(".");
            Ret.Type = Left.Type;
            String LoadReg =  Rd;
            Rd =Func.NewReg();
            Ret.Name = Rd;;
            LoadInstr NewLoad = new LoadInstr("load",LoadReg,Left.Name,Left.Name,Left.Type+"*",isGlobal);
            OperationInstr NewOp = new OperationInstr("sub",LoadReg,"1",Left.Type,TypeToIRType("int"),Rd,"");
            StoreInstr NewStore = new StoreInstr("store",Rd,Left.Type,Left.Name,Left.Type+"*",isGlobal,false);
            CurBlock.InsertInstr(NewLoad);
            CurBlock.InsertInstr(NewOp);
            CurBlock.InsertInstr(NewStore);
        }
        else if(Root.getType() == ExprType.Tidle){
            Ret.Type = Left.Type;
            OperationInstr  XOrOp;
            if(Objects.equals(Left.Type, "i8"))
                XOrOp = new OperationInstr("xor", Left.Name, "255", Left.Type, Left.Type, Ret.Name, "");
            else
                XOrOp = new OperationInstr("xor", Left.Name, "4294967295", Left.Type, Left.Type, Ret.Name, "");
            CurBlock.InsertInstr(XOrOp);
        }
        else if(Root.getType() == ExprType.Not){
                String AndReg = Rd;
                Rd = Func.NewReg();
                Ret.Name = Rd;
                Ret.Type = Left.Type;
                OperationInstr AndOp = new OperationInstr("and",Left.Name,"1",Left.Type,Left.Type,AndReg,"");
                OperationInstr XOrOp = new OperationInstr("xor",Left.Name,"1",Left.Type,Left.Type,Ret.Name,"");
            CurBlock.InsertInstr(AndOp);
            CurBlock.InsertInstr(XOrOp);

        }
        else if(Root.getType() == ExprType.RightSelfPlus){
            Boolean isGlobal = !Left.Name.contains(".");
            Ret.Type = Left.Type;
            String AddReg =  Func.NewReg();
            LoadInstr NewLoad = new LoadInstr("load",Ret.Name,Ret.Type,Left.Name,Left.Type+"*",isGlobal);
            OperationInstr NewOp = new OperationInstr("add",Ret.Name,"1",Left.Type,TypeToIRType("int"),AddReg,"");
            StoreInstr NewStore = new StoreInstr("store",AddReg,Left.Type,Left.Name,Left.Type+"*",isGlobal,false);
            CurBlock.InsertInstr(NewLoad);
            CurBlock.InsertInstr(NewOp);
            CurBlock.InsertInstr(NewStore);
        }
        else if(Root.getType() == ExprType.RightSelfMinus){
            Boolean isGlobal = !Left.Name.contains(".");
            Ret.Type = Left.Type;
            String AddReg = Func.NewReg();
            LoadInstr NewLoad = new LoadInstr("load",Ret.Name,Ret.Type,Left.Name,Left.Type+"*",isGlobal);
            OperationInstr NewOp = new OperationInstr("sub",Ret.Name,"1",Left.Type,TypeToIRType("int"),AddReg,"");
            StoreInstr NewStore = new StoreInstr("store",AddReg,Left.Type,Left.Name,Left.Type+"*",isGlobal,false);
            CurBlock.InsertInstr(NewLoad);
            CurBlock.InsertInstr(NewOp);
            CurBlock.InsertInstr(NewStore);
        }
        else if(Root.getType() == ExprType.Multiply){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("mul",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType() == ExprType.Divide){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("div",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType() == ExprType.Mod){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("rem",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType() == ExprType.Plus){
            if(Objects.equals(Left.Type, "i32")) {
                Ret.Type = Left.Type;
                OperationInstr NewOp = new OperationInstr("add", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name,"");
                CurBlock.InsertInstr(NewOp);
            }else{
                Ret.Type = Left.Type;
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",Ret.Name,Ret.Type,"append",ParamTypes,Params);
                CurBlock.InsertInstr(NewLink);
                AddLinking("append");
                //+ append = memcpy ParseInt strspk Length strlen SubString memcpy Ord getelement
            }
        }
        else if(Root.getType() == ExprType.Minus){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("sub",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.LeftShift){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("sll",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.RightShift){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("sra",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.LessThan){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "ult");
                CurBlock.InsertInstr(NewOp);
            }
            else{
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "ult");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
        }
        else if(Root.getType()==ExprType.LessThanEqual){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "ule");
                CurBlock.InsertInstr(NewOp);
            }
            else{
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "ule");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
        }
        else if(Root.getType()==ExprType.GreaterThan){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "ugt");
                CurBlock.InsertInstr(NewOp);
            }
            else{
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "ugt");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
        }
        else if(Root.getType()==ExprType.GreaterThanEqual){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "uge");
                CurBlock.InsertInstr(NewOp);
            }
            else{
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "uge");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
        }
        else if(Root.getType()==ExprType.Equal){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "eq");
                CurBlock.InsertInstr(NewOp);
            }
            else if(Objects.equals(Left.Type, "_string*")){
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "eq");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
            else{
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "eq");
                CurBlock.InsertInstr(NewOp);
            }
        }
        else if(Root.getType()==ExprType.NotEqual){
            Ret.Type = TypeToIRType("int");
            if(Objects.equals(Left.Type, "i32")) {
                OperationInstr NewOp = new OperationInstr("icmp", Left.Name, Right.Name, Left.Type, Right.Type, Ret.Name, "ne");
                CurBlock.InsertInstr(NewOp);
            }
            else{
                String FuncRet = Func.NewReg();
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Left.Name);
                Params.add(Right.Name);
                ParamTypes.add(Left.Type);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",FuncRet,TypeToIRType("int"),"strcomp",ParamTypes,Params);
                OperationInstr NewOp = new OperationInstr("icmp", FuncRet, "0",TypeToIRType("int"), TypeToIRType("int"), Ret.Name, "ne");
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewOp);
                AddLinking("strcomp");
            }
        }
        else if(Root.getType()==ExprType.And){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("and",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.Xor){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("xor",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.Or){
            Ret.Type = Left.Type;
            OperationInstr NewOp = new OperationInstr("or",Left.Name,Right.Name,Left.Type,Right.Type,Ret.Name,"");
            CurBlock.InsertInstr(NewOp);
        }
        else if(Root.getType()==ExprType.AndAnd){
            //添加新块
         //
            IRValue PhiLeft = ExprToInstr(Func,Root.getLeftSonExpr(),"");

            IRBlock Phi = new IRBlock(BlockType.Basic,"Phi",BlockNum++);
            CurBlock.InsertSubBlock(Phi);

            IRBlock SucceedBlock = new IRBlock(BlockType.Basic,"Succeed",BlockNum++);
            CurBlock.InsertSubBlock(SucceedBlock);
            BranchInstr PreceedJump = new BranchInstr("br",Phi.Label,SucceedBlock.Label, PhiLeft.Name);
            BranchInstr PhiJump = new BranchInstr("br",SucceedBlock.Label,"", "");

            SucceedBlock.EndInstr = CurBlock.EndInstr;
            CurBlock.EndInstr = PreceedJump;
        //    CurBlock.VarInstrList.add(NewPhiStart);
            Phi.EndInstr = PhiJump;
        //    SucceedBlock.VarInstrList.add(NewPhiEnd);

            IRBlock PreBlock = CurBlock;
            CurBlock = Phi;
            IRValue PhiRight = ExprToInstr(Func,Root.getRightSonExpr(),"");
            CurBlock = PreBlock;

            OperationInstr NewOp = new OperationInstr("and",PhiLeft.Name,PhiRight.Name,PhiLeft.Type,PhiRight.Type,PhiLeft.Name,"");
            Phi.VarInstrList.add(NewOp);

            Ret.Name = PhiLeft.Name;
            Ret.Type = PhiLeft.Type;
            CurBlock = SucceedBlock;
        }
        else if(Root.getType() == ExprType.OrOr){
            IRValue PhiLeft = ExprToInstr(Func,Root.getLeftSonExpr(),"");

            IRBlock Phi = new IRBlock(BlockType.Basic,"Phi",BlockNum++);
            CurBlock.InsertSubBlock(Phi);

            IRBlock SucceedBlock = new IRBlock(BlockType.Basic,"Succeed",BlockNum++);
            CurBlock.InsertSubBlock(SucceedBlock);

            BranchInstr PreceedJump = new BranchInstr("br",SucceedBlock.Label,Phi.Label,PhiLeft.Name);
            BranchInstr PhiJump = new BranchInstr("br",SucceedBlock.Label,"", "");

            SucceedBlock.EndInstr = CurBlock.EndInstr;
            CurBlock.EndInstr = PreceedJump;

            Phi.EndInstr = PhiJump;

            IRBlock PreBlock = CurBlock;
            CurBlock = Phi;
            IRValue PhiRight = ExprToInstr(Func,Root.getRightSonExpr(),"");
            CurBlock = PreBlock;

            OperationInstr NewOp = new OperationInstr("or",PhiLeft.Name,PhiRight.Name,PhiLeft.Type,PhiRight.Type,PhiLeft.Name,"");

            Phi.VarInstrList.add(NewOp);

            Ret.Name = PhiLeft.Name;
            Ret.Type = PhiLeft.Type;
            CurBlock = SucceedBlock;
        }
        else if(Root.getType() == ExprType.Assign){
            //TODO String 的赋值操作 有两种
            if(!Objects.equals(Left.Type, "_string")) {
                String Ptr = FindPtr(CurBlock,Left.Name,CurModule);
                Ret = Left;
                if(Ptr ==null) Ptr = Left.Name;
                StoreInstr NewStore = new StoreInstr("store", Right.Name, Right.Type, Ptr, Left.Type + "*", !Ptr.contains("."),!Right.Name.contains("."));
                CurBlock.InsertInstr(NewStore);
            }
            else{
                String Ptr = FindPtr(CurBlock,Left.Name,CurModule);
                Ret.Type = Left.Type;
                List<String> Params = new ArrayList<>();
                List<String> ParamTypes = new ArrayList<>();
                Params.add(Right.Name);
                ParamTypes.add(Right.Type);
                FuncCallInstr NewLink = new FuncCallInstr("link",Ret.Name,Ret.Type,"strcopy",ParamTypes,Params);
                StoreInstr NewStore = new StoreInstr("store", Ret.Name, Left.Type, Ptr , Left.Type + "*", !Ptr.contains("."),false);
                CurBlock.InsertInstr(NewLink);
                CurBlock.InsertInstr(NewStore);
            }
        }
        //TODO 为他们新开一个函数
        return Ret;
    }

}