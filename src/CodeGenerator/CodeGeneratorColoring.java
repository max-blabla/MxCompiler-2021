package CodeGenerator;

import IRBuilder.*;
import org.antlr.v4.misc.Graph;
import org.w3c.dom.Node;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.*;

//TODO 启发
//TODO 重写
//TODO 重溢出
//TODO 协议替换 ： 全局指针，
class AdjMatrix{
    HashMap<Integer,HashSet<Integer>> Matrix;
    void AddAdj(Integer U,Integer V){
        if(Matrix.containsKey(U)) Matrix.get(U).add(V);
        else Matrix.put(U,new HashSet<>());
        if(Matrix.containsKey(V)) Matrix.get(V).add(U);
        else Matrix.put(V,new HashSet<>());
    }
    Boolean IsAdj(Integer U,Integer V){
        if(Matrix.containsKey(U)) return Matrix.get(U).contains(V);
        else return false;
    }
    void ShowMatrix(){
        for(Entry<Integer,HashSet<Integer>> entry:Matrix.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(':');
            for(Integer Nei : entry.getValue()) System.out.print(Nei.toString() + ' ');
            System.out.println();
        }
    }
    AdjMatrix(){
        Matrix = new HashMap<>();
    }
}

class GraphColoringPass{
    String Sptr = "%sptr";
    String LPar = "(";
    String RPar = ")";
    String MedianTemp = ".mtemp";
    Integer NegOne=  -1;
    String StrZero = "0";
   HashSet<Integer> precolored;
   HashSet<Integer> Initial;
   HashSet<Integer> SimplifyWorklist;
   HashSet<Integer> FreezeWorklist;
   HashSet<Integer> SpillWorklist;
   HashSet<Integer> SpilledNodes;//被溢出的
    HashSet<Integer> CoalescedNodes;
    HashSet<Integer> ColoredNodes;
    Stack<Integer> SelectStack;
    HashSet<BaseCode> CoalescedMoves;
    HashSet<BaseCode> ConstrainedMoves;
    HashSet<BaseCode> FrozenMoves;
    HashSet<BaseCode> WorklistMoves;
    HashSet<BaseCode> ActiveMoves;
    AdjMatrix AdjSet;
    HashMap<Integer,List<Integer>> AdjList;
    HashMap<Integer,Integer> Degree;
    HashMap<Integer,HashSet<BaseCode>> MoveList;
    HashMap<Integer,Integer> Alias;
    HashMap<Integer,RegType> Color;

    ArrayList<BlockSection> Blocks;

    HashMap<String,RegType> PreColorTable;
    List<RegType> StaticOKColors;
    HashMap<String,HashSet<Integer>> BlocksLiveOut;
    Integer K;
    HashMap<String,Integer> VirNameHashTable;
    HashMap<Integer,Integer> StackStorage;
    HashMap<OpType,OpType> ImmTransfrom;
    Integer AllocaNum;
    Integer MedianTempNum;
    FunctionSection FuncSec;
    ControlFlowGraph CFG;
    Integer VirZero;
    Integer ColorUsed;
    GraphColoringPass(FuncCodeInfo FCI,FunctionSection funcSec,ControlFlowGraph cfg){
        MedianTempNum = 0;
        FuncSec = funcSec;
        CFG  = cfg;
        AllocaNum = FCI.AllocaNum;
        VirNameHashTable = FCI.VirNameHashTable;
        StackStorage = FCI.VirStackStorage;
        VirZero = FCI.VirNameHashTable.get("0");
        K = 18;
        StaticOKColors = new ArrayList<>(Arrays.asList(
                RegType.s0,RegType.s1,RegType.s2,RegType.s3,RegType.s4,RegType.s5,RegType.s6, RegType.s7,RegType.s8,
                RegType.s9, RegType.s10,RegType.s11,RegType.t0,RegType.t1,RegType.t2,RegType.t3,RegType.t4,RegType.t5
        ));
        ImmTransfrom = new HashMap<>(){{
            put(OpType.add,OpType.addi);
            put(OpType.sub,OpType.subi);
            put(OpType.and,OpType.andi);
            put(OpType.or,OpType.ori);
            put(OpType.xor,OpType.xori);
            put(OpType.sll,OpType.slli);
            put(OpType.sra,OpType.srai);
            }};
            PreColorTable = new HashMap<>(){{
            put(".sp",RegType.sp);
            put(".temp1",RegType.t1);
            put(".temp0",RegType.t0);
            put(".zero",RegType.zero);
            put(".return",RegType.a0);
            put(".param0",RegType.a0);
            put(".param1",RegType.a1);
            put(".param2",RegType.a2);
            put(".param3",RegType.a3);
            put(".param4",RegType.a4);
            put(".param5",RegType.a5);
            put(".param6",RegType.a6);
            put(".param7",RegType.a7);
        }};
        Blocks = FuncSec.BlocksCode;
        Color = new HashMap<>();
        Alias = new HashMap<>();
        precolored = new HashSet<>(FCI.PreColoredSet);
        Initial= new HashSet<>();
        SimplifyWorklist= new HashSet<>();
        FreezeWorklist= new HashSet<>();
        SpillWorklist= new HashSet<>();
        SpilledNodes= new HashSet<>();//被溢出的
         CoalescedNodes= new HashSet<>();
        ColoredNodes= new HashSet<>();
        SelectStack = new Stack<>();
        CoalescedMoves= new HashSet<>();
        ConstrainedMoves= new HashSet<>();
        FrozenMoves= new HashSet<>();
        WorklistMoves= new HashSet<>();
        ActiveMoves= new HashSet<>();
        AdjSet = new AdjMatrix();
        AdjList = new HashMap<>();
        Degree = new HashMap<>();
        MoveList = new HashMap<>();
    }
    void Init(){
        System.out.println("All VirReg:");
        for(Entry<String,Integer> entry:VirNameHashTable.entrySet()){
            Degree.put(entry.getValue(),0);
            AdjList.put(entry.getValue(),new ArrayList<>());
            Initial.add(entry.getValue());
            MoveList.put(entry.getValue(),new HashSet<>());
            Alias.put(entry.getValue(),entry.getValue());
            System.out.print(entry.getValue().toString()+' ');
        }
        System.out.println();
        System.out.println("All PreReg:");
        for(Entry<String,RegType> entry:PreColorTable.entrySet()){
            Integer VirPreColor = VirNameHashTable.get(entry.getKey());
            if(VirPreColor!=null && precolored.contains(VirPreColor)){Color.put(VirPreColor,entry.getValue());}
        }
        for(Integer pre :precolored) System.out.print(pre.toString() + ' ');
        System.out.println();
        Initial.removeAll(precolored);
    }
    void Run(){
        Init();
        Main();
        ShowColor();
        TrueReg();
        RegularInstr();
        FuncSec.BlocksCode = Blocks;
    }
    void ShowColor(){
        for(Entry<Integer,RegType> entry:Color.entrySet()) System.out.println(entry.getKey()+":"+entry.getValue());
    }

    void RegularInstr(){
        ArrayList<BlockSection> NewBlocks = new ArrayList<>();
        boolean IsPtrChange = false;
        boolean IsImmChange = false;
        String ImmChange = "";
        boolean IsAdd;
        String PtrOffsetChange = "";
        RegType PtrChange = RegType.NULL;
        int AllocaSize = AllocaNum * 4;
        for(BlockSection Block : Blocks){
            BlockSection NewBlock = new BlockSection(Block.BlockLable,Block.IRBlockLabel);
            Iterator<BaseCode> Iter = Block.CodeList.iterator();
            while(Iter.hasNext()){
                BaseCode Code = Iter.next();
                IsAdd = true;
                if(Code instanceof LCode){
                    LCode L = (LCode) Code;
                    if(IsPtrChange){
                        IsPtrChange = false;
                        L.Rs = PtrChange;
                        L.Imm = PtrOffsetChange;
                    }
                }
                else if(Code instanceof SCode){
                    SCode S = (SCode) Code;
                    if(IsPtrChange){
                        IsPtrChange = false;
                        S.Rs2 = PtrChange;
                        S.Imm = PtrOffsetChange;
                    }
                }
                else if(Code instanceof PCode){}
                else if(Code instanceof RCode){
                    RCode R = (RCode) Code;
                    if(IsImmChange){
                        IsImmChange = false;
                        Code = new ICode(ImmTransfrom.get(R.Op),-3,-3,ImmChange,R.Rd,R.Rs1,R.Line);
                    }
                }
                else if(Code instanceof ICode){}
                else if(Code instanceof UCode){
                    UCode U = (UCode) Code;
                    RegType Rd = U.Rd;
                    if(U.Op==OpType.ili){
                        int Imm =Integer.parseInt(U.Imm);
                        if(Imm > -2048 && Imm < 2047){
                            IsImmChange = true;
                            ImmChange = U.Imm;
                            IsAdd = false;
                        }
                        else U.Op = OpType.li;
                    }
                    if(U.Op== OpType.ali){
                        IsAdd = false;
                        int StackPos;
                        if(U.Imm.startsWith("%sptr")) StackPos = AllocaSize - Integer.parseInt(U.Imm.substring(5)) * 4;
                        else StackPos = Integer.parseInt(U.Imm);
                        if(StackPos < 2047){
                            IsPtrChange = true;
                            PtrOffsetChange = Integer.toString(StackPos);
                            PtrChange = RegType.sp;
                        }
                        else {
                            UCode NewLi = new UCode(OpType.li, -3, Rd, Integer.toString(StackPos), U.Line);
                            RCode NewAdd = new RCode(OpType.add, -3, -3, -3, Rd, RegType.sp, Rd, U.Line);
                            NewBlock.CodeList.add(NewLi);
                            NewBlock.CodeList.add(NewAdd);
                        }
                    }
                }
                else if(Code instanceof BPCode){}
                if(IsAdd) NewBlock.CodeList.add(Code);
            }
            NewBlocks.add(NewBlock);
        }
        Blocks = NewBlocks;
    }


    void TrueReg(){
        ArrayList<BlockSection> NewBlocks = new ArrayList<>();
        int ReserveTop = Math.min(ColorUsed,12)+1;
        int StackSize = (AllocaNum+ReserveTop) * 4;
        for(BlockSection Block : Blocks){
            BlockSection NewBlock = new BlockSection(Block.BlockLable,Block.IRBlockLabel);
            for(BaseCode Code :Block.CodeList){
                if(Code instanceof LCode){
                    LCode L = (LCode) Code;
                    L.Rd = Color.get(L.VirRd);
                    L.Rs = Color.get(L.VirRs);

                }
                else if(Code instanceof SCode){
                    SCode S = (SCode) Code;
                    S.Rs1 = Color.get(S.VirRs1);
                    S.Rs2 = Color.get(S.VirRs2);
                }
                else if(Code instanceof PCode){
                    PCode P = (PCode) Code;
                    P.Rd = Color.get(P.VirRd);
                    P.Rs = Color.get(P.VirRs);
                }
                else if(Code instanceof RCode){
                    RCode R = (RCode) Code;
                    R.Rd = Color.get(R.VirRd);
                    R.Rs1 = Color.get(R.VirRs1);
                    R.Rs2 = Color.get(R.VirRs2);
                }
                else if(Code instanceof ICode){
                    ICode I = (ICode) Code;
                    I.Rd = Color.get(I.VirRd);
                    I.Rs = Color.get(I.VirRs);
                }
                else if(Code instanceof UCode){
                    UCode U = (UCode) Code;
                    //TODO Li 再加改换到U上
                     U.Rd = Color.get(U.VirRd);
                }
                else if(Code instanceof BPCode){
                    BPCode BP = (BPCode) Code;
                    BP.Rs = Color.get(BP.VirRs);
                }
                NewBlock.CodeList.add(Code);
            }
            NewBlocks.add(NewBlock);
        }
        MCode Ret = new MCode(OpType.ret,0);
        UCode DLi = new UCode(OpType.ili,-3,RegType.t0,"-"+StackSize,0);
        RCode SpDown = new RCode(OpType.add,-3,-3,-3,RegType.sp,RegType.sp,RegType.t0,0);
        UCode RaILi = new UCode(OpType.ali,-3,RegType.t0,Integer.toString(StackSize-4),0);
        SCode RaIn = new SCode(OpType.sw,-3,-3,RegType.ra,RegType.t0,StrZero,0);
        UCode RaOLi = new UCode(OpType.ali,-3,RegType.t0,Integer.toString(StackSize-4),0);
        LCode RaOut = new LCode(OpType.sw,-3,-3,RegType.ra,RegType.t0,StrZero,0);
        UCode ULi = new UCode(OpType.ili,-3,RegType.t0, Integer.toString(StackSize),0);
        RCode SpUp = new RCode(OpType.add,-3,-3,-3,RegType.sp,RegType.sp,RegType.t0,0);
        BlockSection End = NewBlocks.get(NewBlocks.size()-1);
        BlockSection Start = NewBlocks.get(0);
        Start.CodeList.add(DLi);
        Start.CodeList.add(SpDown);
        Start.CodeList.add(RaILi);
        Start.CodeList.add(RaIn);
        for(int i = 0 ; i < ReserveTop-1;i++){
            UCode SIILi = new UCode(OpType.ali,-3,RegType.t0,Integer.toString(StackSize-4*(i+2)),0);
            SCode SIIn = new SCode(OpType.sw,-3,-3,StaticOKColors.get(i),RegType.t0,StrZero,0);
            UCode SIOLi = new UCode(OpType.ali,-3,RegType.t0,Integer.toString(StackSize-4*(i+2)),0);
            LCode SIOut = new LCode(OpType.sw,-3,-3,StaticOKColors.get(i),RegType.t0,StrZero,0);
            Start.CodeList.add(SIILi);
            Start.CodeList.add(SIIn);
            End.CodeList.add(SIOLi);
            End.CodeList.add(SIOut);
        }
        End.CodeList.add(RaOLi);
        End.CodeList.add(RaOut);
        End.CodeList.add(ULi);
        End.CodeList.add(SpUp);
        End.CodeList.add(Ret);
        Blocks = NewBlocks;
    }

    void Main(){
        LivenessAnaysisPass NewLiveNess = new LivenessAnaysisPass(CFG.StartNode,Blocks,CFG,FuncSec.FuncName);
        NewLiveNess.Run();
        BlocksLiveOut = NewLiveNess.BlockOut;
        Build();
        MakeWorklist();
        do{
            if(!SimplifyWorklist.isEmpty()) Simplify();
            else if(!WorklistMoves.isEmpty()) Coalesce();
            else if(!FreezeWorklist.isEmpty()) Freeze();
            else if(!SpillWorklist.isEmpty()) SelectSpill();
        }while(!SimplifyWorklist.isEmpty() || !WorklistMoves.isEmpty() || !FreezeWorklist.isEmpty() || !SpillWorklist.isEmpty());
        AdjSet.ShowMatrix();
        AssignColors();
        if(!SpilledNodes.isEmpty()){
            RewriteProgram();
            Main();
        }
    }

    Integer DefDetermine(BaseCode Code){
        if(Code instanceof SCode) return -1;
        else if(Code instanceof BPCode) return -1;
        else if(Code instanceof LCode){
            LCode L = (LCode) Code;
            return L.VirRd;
        }
        else if(Code instanceof PCode){
            PCode P = (PCode) Code;
            return P.VirRd;
        }
        else if(Code instanceof RCode){
            RCode R = (RCode) Code;
            return R.VirRd;
        }
        else if(Code instanceof ICode){
            ICode I = (ICode) Code;
            return I.VirRd;
        }
        else if(Code instanceof UCode){
            UCode U = (UCode) Code;
            return U.VirRd;
        }
        else  return -2;
    }

    List<Integer> UseDetermine(BaseCode Code){
        if(Code instanceof SCode) {
            SCode S = (SCode) Code;
            return List.of(S.VirRs1,S.VirRs2);
        }
        else if(Code instanceof BPCode){
            BPCode BP = (BPCode) Code;
            return  List.of(BP.VirRs);
        }
        else if(Code instanceof LCode){
            LCode L = (LCode) Code;
            return  List.of(L.VirRs);
        }
        else if(Code instanceof PCode){
            PCode P = (PCode) Code;
            return  List.of(P.VirRs);
        }
        else if(Code instanceof RCode){
            RCode R = (RCode) Code;
            return  List.of(R.VirRs1,R.VirRs2);
        }
        else if(Code instanceof ICode){
            ICode I = (ICode) Code;
            return  List.of(I.VirRs);
        }
        else if(Code instanceof UCode) return  List.of();
        else  return List.of();
    }

    void Build(){
        for(BlockSection Block : Blocks){
            HashSet<Integer> live = BlocksLiveOut.get(Block.IRBlockLabel);
            for(int i = Block.CodeList.size()-1;i>=0;i--){
                BaseCode Code = Block.CodeList.get(i);
                List<Integer> Uses = UseDetermine(Code);
                Integer Def = DefDetermine(Code);
                if(IsMoveInstruction(Code)) {
                    Uses.forEach(live::remove);
                    HashSet<Integer> OccurReg = new HashSet<>(Uses);
                    if(Def >= 0) OccurReg.add(Def);
                    for (Integer n : OccurReg){
                        if(MoveList.containsKey(n)) MoveList.get(n).add(Code);
                        else MoveList.put(n,new HashSet<>(Set.of(Code)));
                    }
                    WorklistMoves.add(Code);
                }
                if(Def >= 0) {
                    live.add(Def);
                    for (Integer Live : live) AddEdge(Live, Def);
                    live.remove(Def);
                }
                live.addAll(Uses);
            }
        }
        AdjSet.ShowMatrix();
    }

    Boolean IsMoveInstruction(BaseCode Code) {
        if (Code instanceof PCode) {
            PCode P = (PCode) Code;
            return P.Op == OpType.mv;
        }
        else return false;
    }
    void AddEdge(Integer u, Integer v){
        if(!AdjSet.IsAdj(u,v) && !u.equals(v)){
            AdjSet.AddAdj(u,v);
            if(!precolored.contains(u)){
                AdjList.get(u).add(v);
                Degree.replace(u,Degree.get(u)+1);
            }
            if(!precolored.contains(v)){
                AdjList.get(v).add(u);
                Degree.replace(u,Degree.get(v)+1);
            }
        }
    }

    void MakeWorklist(){
        Iterator<Integer> Iter = Initial.iterator();
        while(Iter.hasNext()){
            Integer N = Iter.next();
            Iter.remove();
            if(Degree.get(N) >= K) SpillWorklist.add(N);
            else if(MoveRelated(N)) FreezeWorklist.add(N);
            else SimplifyWorklist.add(N);
        }
    }

    List<Integer> Adjacent(Integer N){
            List<Integer> Temp = new ArrayList<>(SelectStack);
            Temp.addAll(CoalescedNodes);
            List<Integer> Ret = new ArrayList<>(AdjList.get(N));
            Ret.removeAll(Temp);
            return Ret;
    }

    HashSet<BaseCode> NodeMoves(Integer N){
        HashSet<BaseCode> Ret = new HashSet<>(ActiveMoves );
        Ret.addAll(WorklistMoves);
        Ret.retainAll(MoveList.get(N));
        return Ret;
    }

    Boolean MoveRelated(Integer N){
        return NodeMoves(N).isEmpty();
    }

    void Simplify(){
        Iterator<Integer> Iter = SimplifyWorklist.iterator();
        while(Iter.hasNext()){
            Integer N = Iter.next();
            Iter.remove();
            SelectStack.push(N);
            List<Integer> AdjcantN = Adjacent(N);
            for(Integer M : AdjcantN) DecrementDegree(M);
        }
    }

    void DecrementDegree(Integer M){
        Integer D = Degree.get(M);
        Degree.replace(M,D-1);
        if(D.equals(K)){
            List<Integer> AdjacentM = Adjacent(M);
            AdjacentM.add(M);
            EnableMoves(AdjacentM);
            SpillWorklist.remove(M);
            if(MoveRelated(M)) FreezeWorklist.add(M);
            else SimplifyWorklist.add(M);
        }
    }

    void EnableMoves(List<Integer> Nodes){
        for(Integer N : Nodes){
            HashSet<BaseCode> NodesMoves = NodeMoves(N);
            for(BaseCode M : NodesMoves){
                if(ActiveMoves.contains(M)){
                    ActiveMoves.remove(M);
                    WorklistMoves.add(M);
                }
            }
        }
    }

    void Coalesce() {
        Iterator<BaseCode> Iter = WorklistMoves.iterator();
        PCode Mv = (PCode) Iter.next();
        Integer X = GetAlias(Mv.VirRs);
        Integer Y = GetAlias(Mv.VirRd);
        Integer U;
        Integer V;
        if (precolored.contains(Y)) {
            U = Y;
            V = X;
        } else {
            U = X;
            V = Y;
        }
        WorklistMoves.remove(Mv);

        if (Objects.equals(U, V)) {
            CoalescedMoves.add(Mv);
            AddWorkList(U);
        } else if (precolored.contains(V) || AdjSet.IsAdj(U, V)) {
            ConstrainedMoves.add(Mv);
            AddWorkList(U);
            AddWorkList(V);
        } else {
            List<Integer> Conser = new ArrayList<>(Adjacent(U) );
            Conser.addAll(Adjacent(V));
            if ((precolored.contains(U) && OK(Adjacent(V), U)) || (!precolored.contains(U) && Conservative(Conser))) {
                CoalescedMoves.add(Mv);
                Combine(U, V);
                AddWorkList(U);
            } else ActiveMoves.add(Mv);
        }
    }

    void AddWorkList(Integer u){
        if(!precolored.contains(u) && !MoveRelated(u) && Degree.get(u) < K){
            FreezeWorklist.remove(u);
            SimplifyWorklist.add(u);
        }
    }

    Boolean OK(List<Integer> Adjacent, Integer r){
        boolean Ret = true;
        for(Integer Adj : Adjacent) Ret = Ret && (precolored.contains(Adj) || Degree.get(Adj) < K ||  AdjSet.IsAdj(Adj,r));
        return Ret;
    }

    Boolean Conservative(List<Integer> Nodes) {
        int k = 0;
        for(Integer n : Nodes) if(Degree.get(n) >= K) ++k;
        return k < K;
    }

    Integer GetAlias(Integer N){
        if(CoalescedNodes.contains(N)) return GetAlias(Alias.get(N));
        return N;
    }

    void Combine(Integer U, Integer V){
        if(FreezeWorklist.contains(V)) FreezeWorklist.remove(V);
        else SpillWorklist.remove(V);
        CoalescedNodes.add(V);
        Alias.replace(V,U);
        EnableMoves(List.of(V));
        List<Integer> Adjacent = Adjacent(V);
        for(Integer Adj : Adjacent){
            AddEdge(Adj,U);
            DecrementDegree(Adj);
        }
        if(Degree.get(U)>= K && FreezeWorklist.contains(U)){
            FreezeWorklist.remove(U);
            SpillWorklist.add(U);
        }
    }

    void Freeze(){
        Iterator<Integer> Iter = FreezeWorklist.iterator();
        while(Iter.hasNext()){
            Integer Freeze = Iter.next();
            Iter.remove();
            FreezeWorklist.remove(Freeze);
            SimplifyWorklist.add(Freeze);
            FreezeMoves(Freeze);
        }
    }

    void FreezeMoves(Integer U){
        HashSet<BaseCode> UMoves = NodeMoves(U);
        for(BaseCode Code : UMoves){
            PCode Mv  = (PCode) Code;
            Integer V;
            if(Objects.equals(GetAlias(Mv.VirRd), GetAlias(U))) V = GetAlias(Mv.VirRs);
            else V = GetAlias(Mv.VirRd);
            ActiveMoves.remove(Code);
            FrozenMoves.add(Code);
            if(NodeMoves(V).isEmpty() && Degree.get(V) < K){
                FreezeWorklist.remove(V);
                SimplifyWorklist.add(V);
            }
        }
    }

    void SelectSpill(){
        Integer Choose = -1;
        for(Integer M : SpillWorklist){
            Choose = M;
            break;
        }
        SpillWorklist.remove(Choose);
        SimplifyWorklist.add(Choose);
        FreezeMoves(Choose);

    }

    void AssignColors(){
        HashSet<Integer> Cope = new HashSet<>(precolored);
        Iterator<RegType> Iter;
        List<RegType> OKColors = new ArrayList<>(StaticOKColors);
        HashSet<RegType> ColorUsedSet = new HashSet<>();
        while(!SelectStack.isEmpty()){
            Integer N = SelectStack.pop();
            for(Integer W : AdjList.get(N)) if(Cope.contains(GetAlias(W))) OKColors.remove(Color.get(GetAlias(W)));
            if(OKColors.isEmpty()) SpilledNodes.add(N);
            else{
                ColoredNodes.add(N);
                Cope.add(N);
                Iter = OKColors.iterator();
                RegType c = Iter.next();
                ColorUsedSet.add(c);
                if(Color.containsKey(N)) Color.replace(N,c);
                else Color.put(N,c);
            }
        }
        for(Integer N : CoalescedNodes){
            if(!Color.containsKey(N)) Color.put(N,Color.get(GetAlias(N)));
            else Color.replace(N,Color.get(GetAlias(N)));
        }
        ColorUsed = ColorUsedSet.size();
    }

    void RewriteProgram(){
        HashSet<Integer> NewTemps = new HashSet<>();
        HashMap<Integer,Integer> LocalTempMap =new HashMap<>();
        for(Integer v :SpilledNodes){
            Integer StackPos = ++AllocaNum;
            StackStorage.put(v,StackPos);
            LocalTempMap.put(v,VirNameHashTable.size());
            NewTemps.add(VirNameHashTable.size());
            VirNameHashTable.put(MedianTemp+v,VirNameHashTable.size());
        }
        ArrayList<BlockSection> NewBlocks = new ArrayList<>();
        for(BlockSection BlockSec :Blocks){
            for(BaseCode Code: BlockSec.CodeList){
                BlockSection NewBlock = new BlockSection(BlockSec.BlockLable,BlockSec.IRBlockLabel);
                if(Code instanceof LCode){
                    LCode L = (LCode) Code;
                    if(SpilledNodes.contains(L.VirRs)){
                        Integer NewVirRs = LocalTempMap.get(L.VirRs);
                        LCode NewLoad = new LCode(OpType.lw, NewVirRs,NewVirRs,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs,RegType.NULL,Sptr+StackStorage.get(L.VirRs),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        L.VirRs  = NewVirRs;
                    }
                    NewBlock.CodeList.add(L);
                    if(SpilledNodes.contains(L.VirRd)) {
                        Integer NewVirRd = LocalTempMap.get(L.VirRd);
                        SCode NewStore = new SCode(OpType.sw,NewVirRd, NewVirRd, RegType.NULL, RegType.NULL, StrZero, -2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRd,RegType.NULL,Sptr +StackStorage.get(L.VirRd),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewStore);
                        L.VirRd  = NewVirRd;
                    }
                }
                else if(Code instanceof SCode){
                    SCode S = (SCode) Code;
                    if(SpilledNodes.contains(S.VirRs1)) {
                        Integer NewVirRs1 = LocalTempMap.get(S.VirRs1);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs1,NewVirRs1,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs1,RegType.NULL,Sptr +StackStorage.get(S.VirRs1),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        S.VirRs1 = NewVirRs1;
                    }
                    if(SpilledNodes.contains(S.VirRs2)){
                        Integer NewVirRs2 = LocalTempMap.get(S.VirRs2);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs2,NewVirRs2,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs2,RegType.NULL,Sptr +StackStorage.get(S.VirRs2),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        S.VirRs2 = NewVirRs2;
                    }
                    NewBlock.CodeList.add(S);
                }
                else if(Code instanceof PCode){
                    PCode P = (PCode) Code;
                    if(SpilledNodes.contains(P.VirRs)){
                        Integer NewVirRs = LocalTempMap.get(P.VirRs);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs,NewVirRs,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs,RegType.NULL,Sptr +StackStorage.get(P.VirRs),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        P.VirRs = NewVirRs;
                    }
                    NewBlock.CodeList.add(P);
                    if(SpilledNodes.contains(P.VirRd)){
                        Integer NewVirRd = LocalTempMap.get(P.VirRd);
                        SCode NewStore = new SCode(OpType.sw, NewVirRd, NewVirRd, RegType.NULL, RegType.NULL, StrZero, -2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRd,RegType.NULL,Sptr +StackStorage.get(P.VirRd),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewStore);
                        P.VirRd = NewVirRd;
                    }
                }
                else if(Code instanceof RCode){
                    RCode R = (RCode) Code;

                    if(SpilledNodes.contains(R.VirRs1)){
                        Integer NewVirRs1 = LocalTempMap.get(R.VirRs1);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs1,NewVirRs1,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs1,RegType.NULL,Sptr +StackStorage.get(R.VirRs1),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        R.VirRs1 = NewVirRs1;
                    }

                    if(SpilledNodes.contains(R.VirRs2)){
                        Integer NewVirRs2 = LocalTempMap.get(R.VirRs2);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs2,NewVirRs2,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs2,RegType.NULL,Sptr +StackStorage.get(R.VirRs2),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        R.VirRs2 = NewVirRs2;
                    }
                    NewBlock.CodeList.add(R);
                    if(SpilledNodes.contains(R.VirRd)){
                        Integer NewVirRd = LocalTempMap.get(R.VirRd);
                        SCode NewStore = new SCode(OpType.sw,NewVirRd, NewVirRd, RegType.NULL, RegType.NULL, StrZero, -2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRd,RegType.NULL,Sptr +StackStorage.get(R.VirRd),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewStore);
                        R.VirRd  = NewVirRd;
                    }
                }
                else if(Code instanceof ICode){
                    ICode I = (ICode) Code;
                    if(SpilledNodes.contains(I.VirRs)){
                        Integer NewVirRs = LocalTempMap.get(I.VirRs);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs,NewVirRs,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs,RegType.NULL,Sptr +StackStorage.get(I.VirRs),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        I.VirRs = NewVirRs;
                    }
                    NewBlock.CodeList.add(I);
                    if(SpilledNodes.contains(I.VirRd)){
                        Integer NewVirRd = LocalTempMap.get(I.VirRd);
                        SCode NewStore = new SCode(OpType.sw,NewVirRd, NewVirRd, RegType.NULL, RegType.NULL, StrZero, -2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRd,RegType.NULL,Sptr +StackStorage.get(I.VirRd),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewStore);
                        I.VirRd  = NewVirRd;
                    }
                }
                else if(Code instanceof UCode){
                    UCode U = (UCode) Code;
                    NewBlock.CodeList.add(U);
                    if(SpilledNodes.contains(U.VirRd)){
                        Integer NewVirRd = LocalTempMap.get(U.VirRd);
                        SCode NewStore = new SCode(OpType.sw,NewVirRd, NewVirRd, RegType.NULL, RegType.NULL, StrZero, -2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRd,RegType.NULL,Sptr +StackStorage.get(U.VirRd),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewStore);
                        U.VirRd  = NewVirRd;
                    }
                }
                else if(Code instanceof BPCode){
                    BPCode BP = (BPCode) Code;
                    if(SpilledNodes.contains(BP.VirRs)){
                        Integer NewVirRs = LocalTempMap.get(BP.VirRs);
                        LCode NewLoad = new LCode(OpType.lw,NewVirRs,NewVirRs,RegType.NULL,RegType.NULL,StrZero,-2);
                        UCode NewLi = new UCode(OpType.ali,NewVirRs,RegType.NULL,Sptr +StackStorage.get(BP.VirRs),-2);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewLoad);
                        BP.VirRs = NewVirRs;
                    }
                    NewBlock.CodeList.add(BP);
                }
                else NewBlock.CodeList.add(Code);
                NewBlocks.add(NewBlock);
            }
        }
        Blocks = NewBlocks;
        SpilledNodes = new HashSet<>();
        Initial = new HashSet<>(ColoredNodes);
        Initial.addAll(CoalescedNodes);
        Initial.addAll(NewTemps);
        ColoredNodes = new HashSet<>();
        CoalescedNodes = new HashSet<>();
//直接开栈空间了。

    }



}

class CFGNode{
    BlockSection BlockCodes;
    String Suc;
    String BrSuc;
    CFGNode SucNode;
    CFGNode BrSucNode;
    List<CFGNode> PreNodes;
    CFGNode(BlockSection blockCodes){
        BlockCodes = blockCodes;
        PreNodes = new ArrayList<>();
        Suc = null;
        BrSuc = null;
    }
}

class ControlFlowGraph{
    HashMap<String,CFGNode> NodeMap;
    CFGNode StartNode;
    ControlFlowGraph(HashMap<String,CFGNode> nodeMap,CFGNode startNode){
        NodeMap = nodeMap;
        StartNode = startNode;
    }
}

class RePostOrder{
    String FuncName;
    Stack<String> RePostOrder;
    RePostOrder(String funcName){
        FuncName = funcName;
        RePostOrder = new Stack<>();
    }
}

class LivenessAnaysisPass{
    HashMap<String,HashSet<Integer>> BlockIn;
    HashMap<String,HashSet<Integer>> BlockOut;
    HashMap<String,HashSet<Integer>> BlockKill;
    HashMap<String,HashSet<Integer>> BlockGen;
    List<BlockSection> Blocks;
    HashSet<String> IsChecked;
    RePostOrder Topo;
    CFGNode StartNode;
    ControlFlowGraph CFG;
    LivenessAnaysisPass(CFGNode startNode,List<BlockSection> blocks,ControlFlowGraph cfg,String FuncName){
        StartNode = startNode;
        Blocks = blocks;
        CFG = cfg;
        Topo = new RePostOrder(FuncName);
        IsChecked = new HashSet<>();
        BlockIn = new HashMap<>();
        BlockOut = new HashMap<>();
        BlockGen = new HashMap<>();
        BlockKill = new HashMap<>();
    }
    void Run(){
        BlockCombine();
        TopologicalSort(StartNode);
        LiveNessAnalysis();
    }
    void BlockCombine(){
        for(BlockSection BlockSec :Blocks){
            HashSet<Integer> Gen = new HashSet<>();
            HashSet<Integer> Kill = new HashSet<>();
            for(int i = 0 ; i < BlockSec.CodeList.size();i++){
                BaseCode Code = BlockSec.CodeList.get(i);
                if(Code instanceof LCode){
                    LCode L = (LCode) Code;
                    Gen.remove(L.VirRd);
                    Gen.add(L.VirRs);
                    Kill.add(L.VirRd);
                }
                else if(Code instanceof SCode){
                    SCode S = (SCode) Code;
                    Gen.add(S.VirRs1);
                }
                else if(Code instanceof PCode){
                    PCode P = (PCode) Code;
                    Gen.remove(P.VirRd);
                    Gen.add(P.VirRs);
                    Kill.add(P.VirRd);
                }
                else if(Code instanceof RCode){
                    RCode R = (RCode) Code;
                    Gen.remove(R.VirRd);
                    Gen.add(R.VirRs1);
                    Gen.add(R.VirRs2);
                    Kill.add(R.VirRd);
                }
                else if(Code instanceof ICode){
                    ICode I = (ICode) Code;
                    Gen.remove(I.VirRd);
                    Gen.add(I.VirRs);
                    Kill.add(I.VirRd);
                }
                else if(Code instanceof UCode){
                    UCode U = (UCode) Code;
                    Gen.remove(U.VirRd);
                    Kill.add(U.VirRd);
                }
                else if(Code instanceof BPCode){
                    BPCode BP = (BPCode) Code;
                    Gen.add(BP.VirRs);
                }
            }
            BlockGen.put(BlockSec.IRBlockLabel,Gen);
            BlockKill.put(BlockSec.IRBlockLabel,Kill);
        }
    }
    void TopologicalSort(CFGNode CurNode){
        if(CurNode == null) return;
        if(IsChecked.contains(CurNode.BlockCodes.IRBlockLabel)) return;
        System.out.println(CurNode.BlockCodes.IRBlockLabel);
        IsChecked.add(CurNode.BlockCodes.IRBlockLabel);
        TopologicalSort(CurNode.SucNode);
        TopologicalSort(CurNode.BrSucNode);
        Topo.RePostOrder.push(CurNode.BlockCodes.IRBlockLabel);
    }
    void LiveNessAnalysis() {
        boolean IsChanged = false;
        for (int i = Topo.RePostOrder.size() - 1; i >= 0; i--) {
            String NodeName = Topo.RePostOrder.get(i);
            HashSet<Integer> Out = new HashSet<>();
            BlockOut.put(NodeName,Out);
        }
        do {
            IsChanged = false;
            for (int i = Topo.RePostOrder.size() - 1; i >= 0; i--) {
                String NodeName = Topo.RePostOrder.get(i);
                CFGNode Node = CFG.NodeMap.get(NodeName);
                HashSet<Integer> In = new HashSet<>();
                for (CFGNode Pre : Node.PreNodes) In.addAll(BlockOut.get(Pre.BlockCodes.IRBlockLabel));
                HashSet<Integer> Out;
                    HashSet<Integer> NewOut = new HashSet<>(BlockGen.get(NodeName));
                    Out = BlockOut.get(NodeName);
                    HashSet<Integer> Temp = new HashSet<>(In);
                    Temp.removeAll(BlockKill.get(NodeName));
                    NewOut.addAll(Temp);
                    if (!Out.containsAll(NewOut)) {
                        IsChanged = true;
                        BlockOut.replace(NodeName, NewOut);
                    }
            }
        } while (IsChanged);

        for (int i = Topo.RePostOrder.size() - 1; i >= 0; i--) {
            String TopNodeName = Topo.RePostOrder.get(i);
            HashSet<Integer> Out = BlockOut.get(TopNodeName);
            System.out.println(TopNodeName + ":");
            for (Integer LiveOut : Out) System.out.print(LiveOut + " ");
            System.out.println();
        }
    }
}

class ControlFlowGraphBuildPass{
    HashMap<String,CFGNode> NodeMap;
    FunctionSection FuncSec;
    IRFunc Func;
    HashSet<String> IsReached;
    ControlFlowGraphBuildPass(FunctionSection funcSec,IRFunc func){
        FuncSec = funcSec;
        Func = func;
        IsReached = new HashSet<>();
        NodeMap = new HashMap<>();
    }
    void BuildNode(IRBlock irBlock){
        CFGNode NewNode = new CFGNode(FuncSec.BlocksMap.get(irBlock.getLabel()));
        if(irBlock.getEndInstr() instanceof BranchInstr){
            BranchInstr Branch = (BranchInstr) irBlock.getEndInstr();
            NewNode.Suc  =Branch.Label1;
            if(!Objects.equals(Branch.Condition, "")) NewNode.BrSuc = Branch.Label2;
            else NewNode.BrSuc = null;
        }
        else{
            NewNode.Suc = null;
            NewNode.BrSuc = null;
        }
        NodeMap.put(irBlock.getLabel(),NewNode);
        for(IRBlock Sub : irBlock.getSubBlocks()) BuildNode(Sub);
    }

    void BuildGraph(String CurNodeLabel){
        if(CurNodeLabel == null) return;
        if(IsReached.contains(CurNodeLabel)) return;
        IsReached.add(CurNodeLabel);
        CFGNode CurNode = NodeMap.get(CurNodeLabel);
        if(CurNode.Suc == null) return;
        CFGNode SucNode = NodeMap.get(CurNode.Suc);
        CurNode.SucNode = SucNode;
        SucNode.PreNodes.add(CurNode);
       // System.out.println(CurNodeLabel);
        BuildGraph(CurNode.Suc);
        if(CurNode.BrSuc != null){
            CFGNode BrSucNode = NodeMap.get(CurNode.BrSuc);
            CurNode.BrSucNode = BrSucNode;
            BrSucNode.PreNodes.add(BrSucNode);
            BuildGraph(CurNode.BrSuc);
        }
    }
    ControlFlowGraph CFGBuild(){
        BuildNode(Func.getStart());
        BuildGraph(Func.getStart().getLabel());
        return new ControlFlowGraph(NodeMap,NodeMap.get(Func.getStart().getLabel()));
    }
}

class ProgramOutputPass{
    String OutputFileName;
    HashMap<String,GlobalSection> GlobalVariables;
    List<FunctionSection> FunctionsCode;
    Integer OutputType;
    ProgramOutputPass(String outputFileName, Integer outputType,HashMap<String,GlobalSection> globalVariables,List<FunctionSection> functionsCode){
        GlobalVariables = globalVariables;
        FunctionsCode = functionsCode;
        OutputType = outputType;
        OutputFileName = outputFileName;
    }
    void Output() throws IOException {
        PrintStream Os = new PrintStream(OutputFileName);
        Os.println("\t.text");
        for(FunctionSection Func : FunctionsCode)
            Func.Output(Os,OutputType);
        for(Entry<String,GlobalSection> entry : GlobalVariables.entrySet())
            entry.getValue().Output(Os);
    }
}

public class CodeGeneratorColoring{
    List<IRModule> ModuleList;
    HashMap<String,String> ConstStr;
//    HashMap<String,GlobalSection> GlobalVariables;
    GlobalCodeInfo GlobalInfo;
    List<FunctionSection> FunctionsCode;
    HashMap<FunctionSection,IRFunc> FuncEntry;
    public CodeGeneratorColoring(List<IRModule> moduleList,HashMap<String,String> constStr){
        ModuleList = moduleList;
        ConstStr = constStr;
        FuncEntry = new HashMap<>();
    }
    public void Run(){
        IrTranslationPass IrT = new IrTranslationPass(ModuleList,ConstStr,FuncEntry);
        IrT.Run();
        GlobalInfo = IrT.GlobalCode;
        FunctionsCode = IrT.GlobalCode.FunctionsCode;
        FuncEntry = IrT.GlobalCode.FuncEntry;
        for(Entry<FunctionSection,IRFunc> fE : FuncEntry.entrySet()){
            ControlFlowGraphBuildPass CFGBP = new ControlFlowGraphBuildPass(fE.getKey(),fE.getValue());
            ControlFlowGraph CFG = CFGBP.CFGBuild();
            GraphColoringPass GCP = new GraphColoringPass(GlobalInfo.FuncCodeInfos.get(fE.getKey().FuncName),fE.getKey(),CFG);
            GCP.Run();
        }
    }
    public void Output(String FileName, Integer Mode) throws IOException{
        ProgramOutputPass POP = new ProgramOutputPass(FileName,Mode,GlobalInfo.GlobalVariables,FunctionsCode);
        POP.Output();
    }

}

class GlobalCodeInfo{
    HashMap<String,String> ConstStr;
    HashMap<String,GlobalSection> GlobalVariables;
    List<FunctionSection> FunctionsCode;
    HashMap<FunctionSection,IRFunc> FuncEntry;
    HashMap<String,FuncCodeInfo> FuncCodeInfos;
    GlobalCodeInfo(HashMap<String,String> constStr,
                   HashMap<FunctionSection,IRFunc> funcEntry){
        FuncCodeInfos = new HashMap<>();
        ConstStr = constStr;
        FuncEntry = funcEntry;
        FunctionsCode = new ArrayList<>();
        GlobalVariables = new HashMap<>();
    }
}

class FuncCodeInfo{
    HashMap<String,BlockSection> BlockSectionMap;
    HashMap<String,Integer> VirNameHashTable;
    HashMap<Integer,String> InvVirName;
    HashMap<Integer,Integer> VirStackStorage;
    HashSet<Integer> PreColoredSet;
    Integer AllocaNum;
    FuncCodeInfo(){
        BlockSectionMap = new HashMap<>();
        VirNameHashTable = new HashMap<>();
        VirStackStorage = new HashMap<>();
        InvVirName = new HashMap<>();
        PreColoredSet = new HashSet<>();
    }
}

class IrTranslationPass{
    //全局
    List<IRModule> ModuleList;
    int FuncCnt;
    HashMap<String,String> IrToClang;
    HashMap<InstrSeg,OpType> OpTranslation;
    GlobalCodeInfo GlobalCode;

    //函数
    FuncCodeInfo CurFuncInfo;
    HashMap<String,PhiInstr> PhiSet;
    BlockSection EndBlock;
    int CalleeParam;
    int AllocaNum;
    int Zero = 0;
    int BlockCnt;
    String StrOne = "1";
    String StrPhi = "phi";
    String None = "";
    Integer NegOne = -1;
    String StrFour = "4";
    String VirT0 = ".temp0";
    String VirT1 = ".temp1";
    String VirSP = ".sp";
    String VirZero = ".zero";
    String Gptr = "%gptr";
    String Sptr = "%sptr";
    String Hi = "%hi";
    String Lo = "lo";
    String VirParam = ".param";
    String VirRet = ".return";
    String Void = "void";
    String VirSp = ".sp";
    String VirRa = ".ra";
    String VirS0 = ".s0";
    String StrZero = "0";
    String VirS1 = ".s1";
    String GlobalName = "_global";
    char LeftPar = '(';
    char RightPar = ')';
    IrTranslationPass(List<IRModule> moduleList,HashMap<String,String> constStr,HashMap<FunctionSection,IRFunc> funcEntry){
        ModuleList = moduleList;
        GlobalCode = new GlobalCodeInfo(constStr,funcEntry);
    }
    void Run(){
        GlobalInit();
        for(IRModule Module : ModuleList){
            if(Module.getInit() != null){
                FuncCodeInfo NewFuncInfo = new FuncCodeInfo();
                FuncRun(Module.getInit(),NewFuncInfo);
                GlobalCode.FuncCodeInfos.put(Module.getInit().getFuncName(),NewFuncInfo);
            }
            for(IRFunc Func : Module.getFuncSet()){
                FuncCodeInfo NewFuncInfo = new FuncCodeInfo();
                FuncRun(Func,NewFuncInfo);
                GlobalCode.FuncCodeInfos.put(Func.getFuncName(),NewFuncInfo);
            }
        }
    }



    void GlobalInit(){
        //用 ConstrStr 和 全局变量 构造全局
        //GlobalVariables = new HashMap<>();
        FuncCnt = 0;
        IrToClang = new HashMap<>();
        //FunctionsCode = new ArrayList<>();
        for(IRModule Module : ModuleList){
            if(Objects.equals(Module.getName(), GlobalName)){
                for(Entry<String,Integer> entry : Module.getClassPtrIndex().entrySet()){
                    Integer Size = 4;
                    GlobalSection NewSec = new GlobalSection(entry.getKey(),"",Size);
                    GlobalCode.GlobalVariables.put(entry.getKey(),NewSec);
                }
                for(Entry<String,String> entry:GlobalCode.ConstStr.entrySet()){
                    Integer Size = 4;
                    GlobalSection NewSec = new GlobalSection(entry.getKey(),entry.getValue(),Size);
                    GlobalCode.GlobalVariables.put(entry.getKey(),NewSec);
                }
                break;
            }
        }
        OpTranslation = new HashMap<>(){{
            put(InstrSeg.add,OpType.add);
            put(InstrSeg.sub,OpType.sub);
            put(InstrSeg.mul,OpType.mul);
            put(InstrSeg.div,OpType.div);
            put(InstrSeg.rem,OpType.rem);
            put(InstrSeg.and,OpType.and);
            put(InstrSeg.or,OpType.or);
            put(InstrSeg.xor,OpType.xor);
            put(InstrSeg.ule,OpType.sle);
            put(InstrSeg.ult,OpType.slt);
            put(InstrSeg.uge,OpType.sge);
            put(InstrSeg.ugt,OpType.sgt);
            put(InstrSeg.ne,OpType.sne);
            put(InstrSeg.eq,OpType.seq);
        }};
    }

    void FuncRun(IRFunc Func,FuncCodeInfo FuncInfo){
        if(Func.getLinked()) return;
        CurFuncInfo = FuncInfo;
        FuncInit();
        FunctionSection NewFuncSec = new FunctionSection(Func.getFuncName());
        BlockGen(Func.getStart(),NewFuncSec);
        PhiInsert();
        TranSection(NewFuncSec,Func);
        LabelFlush(NewFuncSec);
        CurFuncInfo.AllocaNum = AllocaNum;
        NewFuncSec.BlocksMap = CurFuncInfo.BlockSectionMap;
        GlobalCode.FunctionsCode.add(NewFuncSec);
        GlobalCode.FuncEntry.put(NewFuncSec,Func);
    }

    HashMap<String,GlobalSection> GetGlobalVariables(){
        return GlobalCode.GlobalVariables;
    }

    List<FunctionSection> GetFunctionCodes(){
        return GlobalCode.FunctionsCode;
    }



    Integer VirNameHash(String VirName){
        if(CurFuncInfo.VirNameHashTable.containsKey(VirName)) return CurFuncInfo.VirNameHashTable.get(VirName);
        Integer Vir = CurFuncInfo.VirNameHashTable.size();
        CurFuncInfo.VirNameHashTable.put(VirName,Vir);
        CurFuncInfo.InvVirName.put(Vir,VirName);
        return Vir;
    }


    void TranSection(FunctionSection Func,IRFunc irFunc){
        List<Param> ParamList = irFunc.getParamList();
        int i = 0 ;
        CurFuncInfo.PreColoredSet.add(VirNameHash(VirT0));
        CurFuncInfo.PreColoredSet.add(VirNameHash(VirT1));
        CurFuncInfo.PreColoredSet.add(VirNameHash(VirZero));
        CurFuncInfo.PreColoredSet.add(VirNameHash(VirRet));
        BlockSection Blocks =  new BlockSection("","");
        for(Param param : ParamList){
            Integer VirParamHash = VirNameHash(VirParam+i);
            if(i<8){
                PCode ParamMv = new PCode(OpType.mv,VirNameHash(param.getName()),VirParamHash,RegType.NULL,RegType.NULL,-2);
                Blocks.CodeList.add(0,ParamMv);
            }
            else{
                SCode ParamS = new SCode(OpType.sw,VirNameHash(param.getName()),VirNameHash(VirSp),RegType.NULL,RegType.NULL,Integer.toString((ParamList.size()-i)*4),-2);
                Blocks.CodeList.add(0,ParamS);
            }
            ++i;
            CurFuncInfo.PreColoredSet.add(VirParamHash);
        }
        Func.BlocksCode.add(0,Blocks);
        if(EndBlock != null){
            Func.BlocksCode.remove(EndBlock);
            Func.BlocksCode.add(EndBlock);
        }
        AllocaNum += CalleeParam;
       /* ++AllocaNum;
        Integer Sp = ++AllocaNum;
        PCode SpMv = new PCode(OpType.mv,VirNameHash(Sptr+LeftPar+Sp+RightPar),VirNameHash(VirSp),RegType.NULL,RegType.NULL,-1);
        ++AllocaNum;
        Integer Ra= ++AllocaNum;
        PCode RaMv = new PCode(OpType.mv,VirNameHash(Sptr+LeftPar+Ra+RightPar),VirNameHash(VirRa),RegType.NULL,RegType.NULL,-1);
        ++AllocaNum;
        Integer S0 = ++AllocaNum;
        PCode S0Mv = new PCode(OpType.mv,VirNameHash(Sptr+LeftPar+S0+RightPar),VirNameHash(VirS0),RegType.NULL,RegType.NULL,-1);
        ++AllocaNum;
        Integer S1 = ++AllocaNum;
        PCode S1Mv = new PCode(OpType.mv,VirNameHash(Sptr+LeftPar+S1+RightPar),VirNameHash(VirS1),RegType.NULL,RegType.NULL,-1);
        BlockSection Start = Func.BlocksCode.get(0);
        Start.CodeList.add(SpMv);
        Start.CodeList.add(RaMv);
        Start.CodeList.add(S0Mv);
        Start.CodeList.add(S1Mv);
        PCode SpMvO = new PCode(OpType.mv,VirNameHash(VirSp),VirNameHash(Sptr+LeftPar+Sp+RightPar),RegType.NULL,RegType.NULL,-1);
        PCode RaMvO = new PCode(OpType.mv,VirNameHash(VirRa),VirNameHash(Sptr+LeftPar+Ra+RightPar),RegType.NULL,RegType.NULL,-1);
        PCode S1MvO = new PCode(OpType.mv,VirNameHash(VirS1),VirNameHash(Sptr+LeftPar+S1+RightPar),RegType.NULL,RegType.NULL,-1);
        PCode S0MvO = new PCode(OpType.mv,VirNameHash(VirS0),VirNameHash(Sptr+LeftPar+S0+RightPar),RegType.NULL,RegType.NULL,-1);
        Func.BlocksCode.remove(EndBlock);
        EndBlock.CodeList.add(SpMvO);
        EndBlock.CodeList.add(RaMvO);
        EndBlock.CodeList.add(S1MvO);
        EndBlock.CodeList.add(S0MvO);
        Func.BlocksCode.add(EndBlock);*/
    }

    void FuncInit(){
        PhiSet = new HashMap<>();
        CurFuncInfo.BlockSectionMap = new HashMap<>();
        EndBlock = null;
        CurFuncInfo.VirNameHashTable = new HashMap<>();
        CurFuncInfo.InvVirName = new HashMap<>();
        CalleeParam = 0;
        AllocaNum = 0;
    }

    void PhiInsert(){
        int i = 0;
        for(Entry<String,PhiInstr> entry : PhiSet.entrySet()){
            ++i;
            PhiInstr Phi = entry.getValue();
            Integer Temp = VirNameHash(StrPhi+i);
            PCode Mv = new PCode(OpType.mv,VirNameHash(Phi.Rd),Temp,RegType.NULL,RegType.NULL,NegOne);
            CurFuncInfo.BlockSectionMap.get(entry.getKey()).CodeList.add(0,Mv);
            for(int j = 0 ; j  < Phi.PreBlock.size();j++){
                if(Phi.IsImm.get(j)){
                    UCode Li = new UCode(OpType.li,Temp,RegType.NULL,Phi.PhiValue.get(j),NegOne);
                    CurFuncInfo.BlockSectionMap.get(Phi.PreBlock.get(j)).PhiInsert(Li);
                }
                else{
                    PCode AMv = new PCode(OpType.mv,Temp,VirNameHash(Phi.PhiValue.get(j)),RegType.NULL,RegType.NULL,NegOne);
                    CurFuncInfo.BlockSectionMap.get(Phi.PreBlock.get(j)).PhiInsert(AMv);
                }
            }
        }
    }


    String LabelToClang(String IrLabel){
        ++BlockCnt;
        String ClangLabel = ".LBB"+FuncCnt + "_" +BlockCnt;
        IrToClang.put(IrLabel,ClangLabel);
        return ClangLabel;
    }

    void LabelFlush(FunctionSection FuncSec){
        for(BlockSection Block : FuncSec.BlocksCode){
            for(BaseCode Code : Block.CodeList){
                if(Code instanceof BCode){
                    BCode B = (BCode) Code;
                    B.Block = IrToClang.get(B.Block);
                }
                else if(Code instanceof BPCode){
                    BPCode BP = (BPCode) Code;
                    BP.Label = IrToClang.get(BP.Label);
                }
                else if(Code instanceof JCode){
                    JCode J = (JCode) Code;
                    J.Block = IrToClang.get(J.Block);
                }
            }
        }
    }

    void BlockGen(IRBlock CurBlock,FunctionSection FuncSec){
        if(CurBlock == null) return;
        String IrLabel = CurBlock.getLabel();
        BlockSection NewBlock = new BlockSection(LabelToClang(IrLabel),IrLabel);
        CurFuncInfo.BlockSectionMap.put(CurBlock.getLabel(),NewBlock);
        int Line = 0;
        List<BaseInstr> InstrList = CurBlock.getVarInstrList();
        InstrList.add(CurBlock.getEndInstr());
        for(BaseInstr Instr : InstrList){
            ++Line;
            if(Instr instanceof StoreInstr){
                StoreInstr Store = (StoreInstr) Instr;
                Integer VirRs;
                if(Store.IsRsGlobal){
                    VirRs = VirNameHash(VirT0);
                    UCode NewLi = new UCode(OpType.li,VirRs,RegType.NULL,Hi+LeftPar+Store.Rs+RightPar,Line);
                    ICode NewAdd = new ICode(OpType.add,VirRs,VirRs,Lo+LeftPar+Store.Rs+RightPar,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewAdd);
                }
                else VirRs = VirNameHash(Store.Rs);
                Integer VirPtr;
                if(Store.IsPtrGlobal){
                    VirPtr = VirNameHash(VirT1);
                    UCode NewLi = new UCode(OpType.li,VirPtr,RegType.NULL,Hi+LeftPar+Store.Ptr+RightPar,Line);
                    ICode NewAdd = new ICode(OpType.add,VirPtr,VirPtr,Lo+LeftPar+Store.Rs+RightPar,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewAdd);
                    SCode NewStore = new SCode(OpType.sw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewStore);
                }
                else if(CurFuncInfo.VirStackStorage.containsKey(VirNameHash(Store.Ptr))){
                    VirPtr = VirNameHash(VirT0);
                    UCode NewLi = new UCode(OpType.ali,VirPtr,RegType.NULL,Sptr+CurFuncInfo.VirStackStorage.get(VirNameHash(Store.Ptr)),Line);
                    SCode NewStore = new SCode(OpType.sw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewStore);
                }
                else {
                    VirPtr = VirNameHash(Store.Ptr);
                    SCode NewStore = new SCode(OpType.sw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewStore);
                }
            }
            else if(Instr instanceof LoadInstr){
                LoadInstr Load = (LoadInstr) Instr;
                Integer VirPtr;
                Integer VirRs = VirNameHash(Load.Rd);
                if(Load.IsPtrGlobal){
                    VirPtr = VirNameHash(VirT1);
                    UCode NewLi = new UCode(OpType.li,VirPtr,RegType.NULL,Hi+LeftPar+Load.RsPtr+RightPar,Line);
                    ICode NewAdd = new ICode(OpType.addi,VirPtr,VirPtr,Lo+LeftPar+Load.RsPtr+RightPar,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewAdd);
                    LCode NewLoad = new LCode(OpType.lw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewLoad);
                }
                else if(CurFuncInfo.VirStackStorage.containsKey(VirNameHash(Load.RsPtr))){
                    VirPtr = VirNameHash(VirT0);
                    UCode NewLi = new UCode(OpType.ali,VirPtr,RegType.NULL,Sptr+CurFuncInfo.VirStackStorage.get(VirNameHash(Load.RsPtr)),Line);
                    LCode NewLoad = new LCode(OpType.lw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewLoad);
                }
                else {
                    VirPtr = VirNameHash(Load.RsPtr);
                    LCode NewLoad = new LCode(OpType.lw, VirRs, VirPtr, RegType.NULL, RegType.NULL, StrZero, Line);
                    NewBlock.CodeList.add(NewLoad);
                }
            }
            else if(Instr instanceof OperationInstr){
                OperationInstr Operation = (OperationInstr) Instr;
                Integer VirRs1;
                OpType Op;
                if(Operation.IsRsImm1){
                    UCode NewLi = new UCode(OpType.li,VirNameHash(VirT0),RegType.NULL,Operation.Rs1,Line);
                    VirRs1 = VirNameHash(VirT0);
                    NewBlock.CodeList.add(NewLi);
                }
                else VirRs1 = VirNameHash(Operation.Rs1);
                Integer VirRs2;
                if(Operation.IsRsImm2){
                    UCode NewLi;
                    if(Operation.Op != InstrSeg.div &&Operation.Op != InstrSeg.rem && Operation.Op != InstrSeg.mul  ) NewLi = new UCode(OpType.ili,VirNameHash(VirT1),RegType.NULL,Operation.Rs2,Line);
                    else NewLi = new UCode(OpType.li,VirNameHash(VirT1),RegType.NULL,Operation.Rs2,Line);
                    VirRs2 = VirNameHash(VirT1);
                    NewBlock.CodeList.add(NewLi);
                }
                else VirRs2 = VirNameHash(Operation.Rs2);
                Integer VirRd = VirNameHash(Operation.Rd);
                if(Operation.Op != InstrSeg.icmp){
                    Op = OpTranslation.get(Operation.Op);
                    RCode NewOp = new RCode(Op,VirRd,VirRs1,VirRs2,RegType.NULL,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewOp);
                }
                else {
                    if (Operation.Mode == InstrSeg.ne) {
                        RCode NewSub = new RCode(OpType.sub, VirRs1, VirRs1, VirRs2, RegType.NULL, RegType.NULL, RegType.NULL, Line);
                        PCode NewSnez = new PCode(OpType.snez, VirRd, VirRs1, RegType.NULL, RegType.NULL, Line);
                        NewBlock.CodeList.add(NewSub);
                        NewBlock.CodeList.add(NewSnez);
                    } else if (Operation.Mode == InstrSeg.eq) {
                        RCode NewSub = new RCode(OpType.sub, VirRs1, VirRs1, VirRs2, RegType.NULL, RegType.NULL, RegType.NULL, Line);
                        PCode NewSeqz = new PCode(OpType.seqz, VirRd, VirRs1, RegType.NULL, RegType.NULL, Line);
                        NewBlock.CodeList.add(NewSub);
                        NewBlock.CodeList.add(NewSeqz);
                    } else if (Operation.Mode == InstrSeg.uge) {
                        RCode NewSlt = new RCode(OpType.slt, VirRs1, VirRs1, VirRs2, RegType.NULL, RegType.NULL, RegType.NULL, Line);
                        ICode NewXori = new ICode(OpType.xori, VirRd, VirRs1, StrOne, RegType.NULL, RegType.NULL, Line);
                        NewBlock.CodeList.add(NewSlt);
                        NewBlock.CodeList.add(NewXori);
                    } else if (Operation.Mode == InstrSeg.ugt) {
                        int Tmp = VirRs1;
                        VirRs1 = VirRs2;
                        VirRs2 = Tmp;
                        RCode NewSlt = new RCode(OpType.slt, VirRs1, VirRs1, VirRs2, RegType.NULL, RegType.NULL, RegType.NULL, Line);
                        NewBlock.CodeList.add(NewSlt);
                    } else if (Operation.Mode == InstrSeg.ule) {
                        int Tmp = VirRs1;
                        VirRs1 = VirRs2;
                        VirRs2 = Tmp;
                        RCode NewSlt = new RCode(OpType.slt, VirRs1, VirRs1, VirRs2, RegType.NULL, RegType.NULL, RegType.NULL, Line);
                        ICode NewXori = new ICode(OpType.xori, VirRd, VirRs1, StrOne, RegType.NULL, RegType.NULL, Line);
                        NewBlock.CodeList.add(NewSlt);
                        NewBlock.CodeList.add(NewXori);
                    } else {
                        RCode NewSlt = new RCode(OpType.slt,VirRd,VirRs1,VirRs2,RegType.NULL,RegType.NULL,RegType.NULL,Line);
                        NewBlock.CodeList.add(NewSlt);
                    }
                }
            }
            else if(Instr instanceof GetelementInstr){
                GetelementInstr Get = (GetelementInstr) Instr;
                if(Get.Mode == InstrSeg.index) {
                    ICode NewMul;
                    if(Get.IsIndexImm) NewMul = new ICode(OpType.mul, VirNameHash(VirT0), VirNameHash(Get.Index), StrFour, RegType.NULL, RegType.NULL, Line);
                    else NewMul = new ICode(OpType.mul, VirNameHash(VirT0), VirNameHash(VirZero), Get.Index, RegType.NULL, RegType.NULL, Line);
                    ICode NewAddi = new ICode(OpType.addi,VirNameHash(VirT0),VirNameHash(VirT0),StrFour,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewMul);
                    NewBlock.CodeList.add(NewAddi);
                }
                else{
                    ICode NewAddi = new ICode(OpType.addi,VirNameHash(VirT0),VirNameHash(VirZero),Get.Offset.toString(),RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewAddi);
                }
                if(Get.IsPtrGlobal){
                    Integer VirPtr = VirNameHash(VirT1);
                    UCode NewLi = new UCode(OpType.li,VirPtr,RegType.NULL,Hi+LeftPar+Get.Ptr+RightPar,Line);
                    ICode NewAdd = new ICode(OpType.addi,VirPtr,VirPtr,Lo+LeftPar+Get.Ptr+RightPar,RegType.NULL,RegType.NULL,Line);
                    ICode  NewPtr  = new ICode(OpType.addi,VirNameHash(Get.Rd),VirPtr,StrZero,RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(NewLi);
                    NewBlock.CodeList.add(NewAdd);
                  NewBlock.CodeList.add(NewPtr);
                }
                else{
                    RCode NewPtr = new RCode(OpType.add, VirNameHash(Get.Rd), VirNameHash(Get.Ptr), VirNameHash(VirT0), RegType.NULL, RegType.NULL, RegType.NULL, Line);
                    NewBlock.CodeList.add(NewPtr);
                }
            }
            else if(Instr instanceof BranchInstr){
                BranchInstr Branch = (BranchInstr) Instr;
                if(!Objects.equals(Branch.Condition, "")){
                    BPCode NewBP = new BPCode(OpType.beqz,VirNameHash(Branch.Condition),Branch.Label2,Line);
                    JCode NewJump = new JCode(OpType.j,Branch.Label1,Line);
                    NewBlock.CodeList.add(NewBP);
                    NewBlock.CodeList.add(NewJump);
                }
                else{
                    JCode NewJump = new JCode(OpType.j,Branch.Label1,Line);
                    NewBlock.CodeList.add(NewJump);
                }
            }
            else if(Instr instanceof FuncCallInstr){
                FuncCallInstr FuncCall = (FuncCallInstr) Instr;
                int ParamNum = FuncCall.Param.size();
                CalleeParam = Math.max(CalleeParam, ParamNum);
                for(int i = 0 ;i < ParamNum;i++){
                    Integer VirParamHash = VirNameHash(VirParam+i);
                    if(FuncCall.IsGlobal.get(i)){
                        String ParamPtr = FuncCall.Param.get(i);
                        Integer VirPtr = VirNameHash(ParamPtr);
                        UCode NewLi = new UCode(OpType.li,VirPtr,RegType.NULL,Hi+LeftPar+ParamPtr+RightPar,Line);
                        ICode NewAdd = new ICode(OpType.addi,VirPtr,VirPtr,Lo+LeftPar+ParamPtr+RightPar,RegType.NULL,RegType.NULL,Line);
                   //     ICode Li = new ICode(OpType.addi,VirParamHash,VirNameHash(VirZero),StrZero,RegType.NULL,RegType.NULL,Line);
                        NewBlock.CodeList.add(NewLi);
                        NewBlock.CodeList.add(NewAdd);
                  //      NewBlock.CodeList.add(Li);
                    }
                    if(i<8){
                        PCode Mv = new PCode(OpType.mv,VirParamHash,VirNameHash(FuncCall.Param.get(i)),RegType.NULL,RegType.NULL,Line);
                        NewBlock.CodeList.add(Mv);
                    }
                    else{
                        SCode S = new SCode(OpType.sw,VirParamHash,VirNameHash(VirSp),RegType.NULL,RegType.NULL,Integer.toString((ParamNum-i)*4),Line);
                        NewBlock.CodeList.add(S);
                    }
                    CurFuncInfo.PreColoredSet.add(VirParamHash);
                }
                CCode FuncCallCode = new CCode(OpType.call,FuncCall.FuncName,Line);
                NewBlock.CodeList.add(FuncCallCode);
                if(!FuncCall.Type.equals(Void)){
                    PCode Mv = new PCode(OpType.mv,VirNameHash(FuncCall.Rd),VirNameHash(VirRet),RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(Mv);
                }
            }
            else if(Instr instanceof ReturnInstr){
                ReturnInstr Return = (ReturnInstr) Instr;
                EndBlock = NewBlock;
                if(!Return.Type.equals(Void)){
                    PCode Mv = new PCode(OpType.mv,VirNameHash(VirRet),VirNameHash(Return.Rs),RegType.NULL,RegType.NULL,Line);
                    NewBlock.CodeList.add(Mv);
                }
            }
            else if(Instr instanceof AllocaInstr){
                AllocaInstr Alloca = (AllocaInstr) Instr;
                ++AllocaNum;
               CurFuncInfo.VirStackStorage.put(VirNameHash(Alloca.Rd),AllocaNum);
            }
            else{
                PhiInstr Phi = (PhiInstr) Instr;
                PhiSet.put(CurBlock.getLabel(),Phi);
            }
        }
        FuncSec.BlocksCode.add(NewBlock);
        for(IRBlock Sub : CurBlock.getSubBlocks()) BlockGen(Sub,FuncSec);
    }
}
