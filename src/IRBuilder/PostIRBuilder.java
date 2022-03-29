package IRBuilder;


import ASTNode.BaseAST;
import org.antlr.v4.misc.Graph;
import org.w3c.dom.Node;

import java.util.*;
import java.util.Map.Entry;
import java.util.spi.CurrencyNameProvider;

class CFGNode{
   IRBlock Block;
   CFGNode Suc;
   CFGNode BrSuc;
   List<CFGNode> Pre;
   CFGNode(IRBlock block){
       Suc = null;
       BrSuc = null;
       Pre=  new ArrayList<>();
       Block = block;
   }
   void SetSuc(CFGNode suc){
       suc.Pre.add(this);
       Suc = suc;
   }
   void SetBrSuc(CFGNode brSuc){
       brSuc.Pre.add(this);
       BrSuc = brSuc;
   }
}
class BlockPhiIndex{
    PhiInstr PhiNode;
    Integer Index;
    BlockPhiIndex(PhiInstr phiNode,Integer index){
        PhiNode = phiNode;
        Index = index;
    }
}
class ControlFlowGraph{
    CFGNode StartNode;
    HashMap<String,CFGNode> NodeTable;
}
class ControlFlowGraphBuildPass{
    ControlFlowGraph CFG;
    IRFunc CurFunc;
    Queue<CFGNode> RenewQueue;
    HashMap<String,List<BlockPhiIndex>> BlockPhi;
    HashSet<IRBlock> Check;
    ControlFlowGraphBuildPass(IRFunc Func){
        CurFunc = Func;
        CFG = new ControlFlowGraph();
        RenewQueue = new ArrayDeque<>();
        Check = new HashSet<>();
    }

    void Run(){
        PhiFuncGather();
        RawCFGRun();
        FuncBlockRenew();
        CFGRun();
        CFG.StartNode = CFG.NodeTable.get(CurFunc.Start.Label);
    }

    private void CFGRun(){
        if(CurFunc == null) return;
        HashMap<String,CFGNode> Graph = new HashMap<>();
        CFGNodeBuild(CurFunc.End,Graph);
        CFGNodeBuild(CurFunc.Start,Graph);
        CFGNodeConnect(CurFunc.Start,Graph);
        CFG.NodeTable = Graph;
    }


    private void CFGNodeBuild(IRBlock Block,HashMap<String,CFGNode> Graph){
        if(Block == null) return;
        CFGNode NewNode = new CFGNode(Block);
        Graph.put(Block.Label,NewNode);
        for(IRBlock Sub : Block.SubBlocks) CFGNodeBuild(Sub,Graph);
    }

    private void CFGNodeConnect(IRBlock Block,HashMap<String,CFGNode> Graph){
        if(Block == null) return;
        //for(IRBlock Sub : Block.SubBlocks) CFGNodeConnect(Sub,Graph);
        if (Block.EndInstr instanceof BranchInstr) {
            CFGNode NewNode = Graph.get(Block.Label);
            BranchInstr Br = (BranchInstr) Block.EndInstr;
            CFGNode Suc1 = Graph.get(Br.Label1);
            NewNode.SetSuc(Suc1);
            if (!Objects.equals(Br.Label2, "")) {
                CFGNode Suc2 = Graph.get(Br.Label2);
                NewNode.SetBrSuc(Suc2);
            }
        }
        for(IRBlock Sub : Block.SubBlocks) CFGNodeConnect(Sub,Graph);
    }

    private void PhiGather(IRBlock irBlock,HashMap<String,List<BlockPhiIndex>> PhiIndex){
        if(irBlock == null) return;
        for(BaseInstr Instr: irBlock.VarInstrList){
            if(Instr instanceof PhiInstr){
                PhiInstr Phi = (PhiInstr) Instr;
                for(int i = 0 ;i < Phi.PreBlock.size();i++) {
                    if(PhiIndex.containsKey(Phi.PreBlock.get(i)))  PhiIndex.get(Phi.PreBlock.get(i)).add(new BlockPhiIndex(Phi,i));
                    else PhiIndex.put(Phi.PreBlock.get(i),new ArrayList<>(List.of(new BlockPhiIndex(Phi, i))));
                }
            }
        }
        for(IRBlock Sub : irBlock.getSubBlocks()) PhiGather(Sub,PhiIndex);
    }

    ControlFlowGraph GetCFG(){
        return CFG;
    }
    private void PhiFuncGather(){
        if(CurFunc == null) return;
        HashMap<String,List<BlockPhiIndex>> IndexMap = new HashMap<>();
        PhiGather(CurFunc.Start,IndexMap);
        PhiGather(CurFunc.End,IndexMap);
        BlockPhi = IndexMap;
    }

    private void RawCFGRun(){
        if(CurFunc == null) return;
        HashMap<String,CFGNode> Graph = new HashMap<>();
        CFGNodeBuild(CurFunc.End,Graph);
        CFGNodeBuild(CurFunc.Start,Graph);
        CFGNodeConnect(CurFunc.Start,Graph);
        CFG.NodeTable = Graph;
        RawCFGOpt(Graph.get(CurFunc.Start.Label));
    }

    private void RawCFGOpt(CFGNode CurNode){
        if(CurNode == null) return;
        if(Check.contains(CurNode.Block)) return;
        Check.add(CurNode.Block);
        while(true) {
            if (CurNode.BrSuc == null && CurNode.Suc != null && CurNode.Suc.Pre.size() == 1) {
                IRBlock SucBlock = CurNode.Suc.Block;
                IRBlock CurBlock = CurNode.Block;
                CFGNode SucNode = CurNode.Suc;
                if(BlockPhi.containsKey(SucBlock.getLabel())){
                    List<BlockPhiIndex> PhiIndexList = BlockPhi.get(SucBlock.getLabel());
                    for(BlockPhiIndex PhiIndex : PhiIndexList) PhiIndex.PhiNode.PreBlock.set(PhiIndex.Index,CurBlock.Label);
                }
                SucNode.Block = null;
                CurBlock.Serial = SucBlock.Serial;
                CurBlock.VarInstrList.addAll(SucBlock.VarInstrList);
                CurBlock.EndInstr = SucBlock.EndInstr;
                CurNode.BrSuc = SucNode.BrSuc;
                if (CurNode.BrSuc != null) {
                    CurNode.BrSuc.Pre.remove(SucNode);
                    CurNode.BrSuc.Pre.add(CurNode);
                }
                CurNode.Suc = SucNode.Suc;
                if (CurNode.Suc != null) {
                    CurNode.Suc.Pre.remove(SucNode);
                    CurNode.Suc.Pre.add(CurNode);
                }
            }
            else break;
        }
        RawCFGOpt(CurNode.Suc);
        RawCFGOpt(CurNode.BrSuc);
    }

    private void FuncBlockRenew(){
        if(CurFunc == null) return;
        CFGNode End = CFG.NodeTable.get(CurFunc.End.Label);
        CFGNode Start =CFG.NodeTable.get(CurFunc.Start.Label);
        BlockRenew(Start);
        CurFunc.Start = Start.Block;
        CurFunc.End = null;
    }

    private void BlockRenew(CFGNode CurNode){
        RenewQueue  = new ArrayDeque<>();
        RenewQueue.add(CurNode);
        Check = new HashSet<>();
        Check.add(CurNode.Block);
        while(!RenewQueue.isEmpty()){
            CFGNode Top = RenewQueue.peek();
            Top.Block.SubBlocks = new ArrayList<>();
            if(Top.Suc != null && !Check.contains(Top.Suc.Block)){
                Check.add(Top.Suc.Block);
                Top.Block.InsertSubBlock(Top.Suc.Block);
                RenewQueue.add(Top.Suc);
            }
            if(Top.BrSuc != null && !Check.contains(Top.BrSuc.Block)){
                Check.add(Top.BrSuc.Block);
                Top.Block.InsertSubBlock(Top.BrSuc.Block);
                RenewQueue.add(Top.BrSuc);
            }
            RenewQueue.remove();
        }
    }
}

// For SSA:
class RePostOrderPassNode{
    String BlockName;
    List<String> Predecessors;
    RePostOrderPassNode(String blockName){
        BlockName = blockName;
        Predecessors = new ArrayList<>();
    }
    void AddPredecessor(String Pre){
        Predecessors.add(Pre);
    }
}
class PromotionAlloca{
    AllocaInstr Instr;
    int StartBlockIndex;
    int UseCnt;
    int DefCnt;
    Boolean IsPromotable;
    //  String BlockName;
    String DebugInfo;
    HashSet<String> DefBlocks;

    PromotionAlloca(AllocaInstr instr) {
        Instr = instr;
        UseCnt = 0;
        DefCnt = 0;
        //    BlockName = blockName;
        IsPromotable = true;
        DefBlocks = new HashSet<>();
    }
    void BanPromotion(){
        IsPromotable = false;
    }
}
class RePostOrderDfsInfo{
    Stack<RePostOrderPassNode>  ReDfsPostOrder;
    HashSet<String> IsReached;
    HashMap<String,RePostOrderPassNode> RePostOrderNodeNameMap;
    ControlFlowGraph CFG;
    RePostOrderDfsInfo(ControlFlowGraph cfg){
        CFG = cfg;
        IsReached  = new HashSet<>();
        RePostOrderNodeNameMap = new HashMap<>();
        ReDfsPostOrder = new Stack<>();
    }
    private void BuildPostOrder(CFGNode CurNode){
        if(CurNode == null) return;
        if(IsReached.contains(CurNode.Block.Label)) return;
        IsReached.add(CurNode.Block.Label);
        String BlockName = CurNode.Block.Label;
        RePostOrderPassNode NewNode = new RePostOrderPassNode(BlockName);
        RePostOrderNodeNameMap.put(BlockName,NewNode);
        for(CFGNode Pres:CurNode.Pre) NewNode.AddPredecessor(Pres.Block.Label);
        BuildPostOrder(CurNode.Suc);
        BuildPostOrder(CurNode.BrSuc);
        ReDfsPostOrder.push(NewNode);
    }
    void Build(){
        BuildPostOrder(CFG.StartNode);
    }
}
class AllocaInfo{
    HashMap<String, PromotionAlloca> AllocaPacks;
    AllocaInfo(){
        AllocaPacks = new HashMap<>();
    }
}
//输入后序栈，输出支配表
class AloBuildSimplifyMemPass{
    ControlFlowGraph CFG;
    AllocaInfo AloInfo;
    HashMap<String,String> GlobalPropogationMap;
    HashSet<String> IsReached;
    AloBuildSimplifyMemPass(ControlFlowGraph cfg){
        CFG = cfg;
        AloInfo = new AllocaInfo();;
        GlobalPropogationMap = new HashMap<>();
        IsReached = new HashSet<>();
    }
    void Run(){
        IsReached = new HashSet<>();
        AllocGather(CFG.StartNode,CFG.StartNode);
        IsReached = new HashSet<>();
        PromotableCheck(CFG.StartNode);
        IsReached = new HashSet<>();
        CountUseDef(CFG.StartNode);
        IsReached = new HashSet<>();
        MemPropogation(CFG.StartNode);
        IsReached = new HashSet<>();
        ConfigureUse(CFG.StartNode);
    }
    private  void AllocGather(CFGNode CurNode, CFGNode AimNode){
        if(CurNode == null) return;
        if(IsReached.contains(CurNode.Block.Label)) return;
        IsReached.add(CurNode.Block.Label);
        AllocGather(CurNode.Suc,AimNode);
        AllocGather(CurNode.BrSuc,AimNode);
        List<BaseInstr> InstrList = CurNode.Block.getVarInstrList();
        Iterator<BaseInstr> Iter = InstrList.listIterator();
        List<AllocaInstr> TmpAllocaPack = new ArrayList<>();
        while(Iter.hasNext()){
            BaseInstr Instr = Iter.next();
            if(Instr instanceof AllocaInstr){
                AllocaInstr Alloc = (AllocaInstr) Instr;
                Iter.remove();
                TmpAllocaPack.add(Alloc);

            }
        }
        for(AllocaInstr Alloc : TmpAllocaPack) {
            AloInfo.AllocaPacks.put(Alloc.Rd, new PromotionAlloca(Alloc));
            AimNode.Block.PushInstr(Alloc);
        }
    }

    private  void PromotableCheck(CFGNode CurNode){
        if(CurNode == null) return;
        if(IsReached.contains(CurNode.Block.Label)) return;
        IsReached.add(CurNode.Block.Label);
        for(BaseInstr Instr :CurNode.Block.VarInstrList){
            if(Instr instanceof GetelementInstr){
                GetelementInstr Gep = (GetelementInstr) Instr;
                AloInfo.AllocaPacks.remove(Gep.Ptr);
            }
        }
        PromotableCheck(CurNode.Suc);
        PromotableCheck(CurNode.BrSuc);
    }

    private  void CountUseDef(CFGNode CurNode){
        if(CurNode == null) return;
        if(IsReached.contains(CurNode.Block.Label)) return;
        IsReached.add(CurNode.Block.Label);
        List<BaseInstr> InstrList = CurNode.Block.getVarInstrList();
        for (BaseInstr Instr : InstrList) {
            if (Instr instanceof LoadInstr) {
                LoadInstr Load = (LoadInstr) Instr;
                String Ptr = Load.RsPtr;
                if (AloInfo.AllocaPacks.containsKey(Ptr)) AloInfo.AllocaPacks.get(Ptr).UseCnt++;
            } else if (Instr instanceof StoreInstr) {
                StoreInstr Store = (StoreInstr) Instr;
                String Ptr = Store.Ptr;
                if (AloInfo.AllocaPacks.containsKey(Ptr)) AloInfo.AllocaPacks.get(Ptr).DefCnt++;
            }
        }
        CountUseDef(CurNode.Suc);
        CountUseDef(CurNode.BrSuc);
    }

    Boolean IsAllocaEmpty(){
        return AloInfo.AllocaPacks.isEmpty();
    }

    void MemPropogation(CFGNode StartNode){
        //将一def与一块内传播。在传播完消除无用alloca指令
        if(StartNode == null) return;
        if(IsReached.contains(StartNode.Block.Label)) return;
        IsReached.add(StartNode.Block.Label);
        List<BaseInstr> InstrList = StartNode.Block.VarInstrList;
        Iterator<BaseInstr> Iter = InstrList.listIterator();
        HashMap<String,String> LocalPropogationMap = new HashMap<>();
        HashSet<String> NewPropogationMapKey = new HashSet<>();
        HashSet<BaseInstr> UnUsedDef = new HashSet<>();
        HashMap<String,BaseInstr> PreStore = new HashMap<>();
        while(Iter.hasNext()){
            BaseInstr Instr= Iter.next();
            if(Instr instanceof StoreInstr){
                StoreInstr Store = (StoreInstr) Instr;
                String Ptr = Store.Ptr;
                String Rs = Store.Rs;
                if(AloInfo.AllocaPacks.containsKey(Ptr)){
                    PromotionAlloca AllocProPack = AloInfo.AllocaPacks.get(Ptr);
                    if(AllocProPack.DefCnt == 1){
                        GlobalPropogationMap.put(Ptr,Rs);
                        NewPropogationMapKey.add(Ptr);
                        --AllocProPack.DefCnt;
                        Iter.remove();
                    }
                    else{
                        if(!PreStore.containsKey(Ptr)) PreStore.put(Ptr,Instr);
                        else{
                            UnUsedDef.add(PreStore.get(Ptr));
                            PreStore.replace(Ptr,Instr);
                        }
                        LocalPropogationMap.put(Ptr,Rs);
                    }

                }
            }
            else if(Instr instanceof LoadInstr){
                LoadInstr Load = (LoadInstr) Instr;
                String Ptr = Load.RsPtr;
                if(AloInfo.AllocaPacks.containsKey(Ptr)){
                    PromotionAlloca AllocProPack = AloInfo.AllocaPacks.get(Ptr);
                    int LoadIndex = InstrList.indexOf(Load);
                    if(LocalPropogationMap.containsKey(Ptr)) {
                        OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, LocalPropogationMap.get(Ptr), "0", Load.RdType, "i32", InstrSeg.nullseg);
                        InstrList.set(LoadIndex,RsMove);
                        --AllocProPack.UseCnt;
                    }
                    else if(GlobalPropogationMap.containsKey(Ptr)){
                        OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, GlobalPropogationMap.get(Ptr), "0", Load.RdType, "i32", InstrSeg.nullseg);
                        InstrList.set(LoadIndex,RsMove);
                        --AllocProPack.UseCnt;
                    }
                }
            }
        }
        Iter = InstrList.listIterator();
        while(Iter.hasNext()){
            BaseInstr Instr = Iter.next();
            if(UnUsedDef.contains(Instr)) Iter.remove();
        }
        MemPropogation(StartNode.Suc);
        MemPropogation(StartNode.BrSuc);
        for(String Key : NewPropogationMapKey) GlobalPropogationMap.remove(Key);
    }

    void ConfigureUse(CFGNode CurNode){
        if(CurNode == null) return;
        if(IsReached.contains(CurNode.Block.Label)) return;
        IsReached.add(CurNode.Block.Label);
        String BlockName = CurNode.Block.Label;
        List<BaseInstr> InstrList = CurNode.Block.getVarInstrList();
        for (BaseInstr Instr : InstrList) {
            if (Instr instanceof StoreInstr) {
                StoreInstr Store = (StoreInstr) Instr;
                String Ptr = Store.Ptr;
                if (AloInfo.AllocaPacks.containsKey(Ptr)) AloInfo.AllocaPacks.get(Ptr).DefBlocks.add(BlockName);
            }
        }
        ConfigureUse(CurNode.Suc);
        ConfigureUse(CurNode.BrSuc);
    }

     AllocaInfo GetAllocaInfo(){
        return AloInfo;
    }
}
class DominanceFrontier{
    HashMap<String,List<RePostOrderPassNode>> FrontierSet;
    DominanceFrontier(){
        FrontierSet = new HashMap<>();
    }
}
class DominancePass{
    RePostOrderDfsInfo Info;
    HashMap<String,RePostOrderPassNode> Doms;
    HashSet<String> IsProcessed;
    HashMap<String,Integer> FingerTable;
    DominanceFrontier DomFron;
    private  void ConfigureDominanceFrontier(){
        for(RePostOrderPassNode Node:Info.ReDfsPostOrder) {
            DomFron.FrontierSet.put(Node.BlockName,new ArrayList<>());
        }
        RePostOrderPassNode Runner;
        for(int i = Info.ReDfsPostOrder.size()-1;i>=0;i--){
            RePostOrderPassNode Node = Info.ReDfsPostOrder.get(i);
            if(Node.Predecessors.size() >= 2){
                for(String Pres:Node.Predecessors){
                    Runner = Info.RePostOrderNodeNameMap.get(Pres);
                    while(Runner != Doms.get(Node.BlockName)){
                        DomFron.FrontierSet.get(Runner.BlockName).add(Node);
                        Runner = Doms.get(Runner.BlockName);
                    }
                }
            }
        }
    }
    DominancePass(RePostOrderDfsInfo info){
        Info = info;
        Doms = new HashMap<>();
        IsProcessed = new HashSet<>();
        FingerTable = new HashMap<>();
        DomFron = new DominanceFrontier();
    }
    DominanceFrontier GetDominanceFrontier(){
        return DomFron;
    }

    void Run(){
        ConfigureDominance();
        ConfigureDominanceFrontier();
    }
    private  void ConfigureDominance(){
        for(RePostOrderPassNode Node:Info.ReDfsPostOrder) Doms.put(Node.BlockName,null);
        for(RePostOrderPassNode Node:Info.ReDfsPostOrder) FingerTable.put(Node.BlockName,Integer.MAX_VALUE);
        RePostOrderPassNode StartNode = Info.ReDfsPostOrder.peek();
        FingerTable.replace(StartNode.BlockName,0);
        IsProcessed.add(StartNode.BlockName);
        Doms.replace(StartNode.BlockName,StartNode);
        boolean IsChanged = true;
        while(IsChanged){
            IsChanged  = false;
            for(int i = Info.ReDfsPostOrder.size()-1;i>=0;i--){
                RePostOrderPassNode Node = Info.ReDfsPostOrder.get(i);
                if(Node == StartNode) continue;
                RePostOrderPassNode NewIdom;
                RePostOrderPassNode FirstProcessPre =null;
                for(String Pres:Node.Predecessors) if(IsProcessed.contains(Pres)) FirstProcessPre = Info.RePostOrderNodeNameMap.get(Pres);
                assert FirstProcessPre != null;
                NewIdom = FirstProcessPre;
                for(String Pres:Node.Predecessors){
                    RePostOrderPassNode OtherNode = Info.RePostOrderNodeNameMap.get(Pres);
                    if(OtherNode == FirstProcessPre) continue;
                    if(Doms.get(OtherNode.BlockName) != null)  NewIdom = Intersect(OtherNode.BlockName,NewIdom.BlockName);
                }
                if(NewIdom != Doms.get(Node.BlockName)){
                    Doms.replace(Node.BlockName,NewIdom);
                    FingerTable.replace(Node.BlockName,FingerTable.get(NewIdom.BlockName)+1);
                    IsChanged = true;
                }
                IsProcessed.add(Node.BlockName);
            }
        }
    }
    private  RePostOrderPassNode Intersect(String N1,String N2){
        String FingerName1 = N1;
        String FingerName2 = N2;
        int Finger1 = FingerTable.get(N1);
        int Finger2 = FingerTable.get(N2);
            //小的是父亲
            while(Finger1>Finger2){
                FingerName1 = Doms.get(FingerName1).BlockName;
                Finger1 = FingerTable.get(FingerName1);
            }
            while(Finger2 > Finger1){
                FingerName2 = Doms.get(FingerName2).BlockName;
                Finger2 = FingerTable.get(FingerName2);
            }
            while(!Objects.equals(FingerName1, FingerName2)){
                FingerName1 = Doms.get(FingerName1).BlockName;
                FingerName2 = Doms.get(FingerName2).BlockName;
            }
        return Info.RePostOrderNodeNameMap.get(FingerName1);
    }
}
class PhiInsertionInfo{
    //Block To HashMap
    HashMap<String, PhiInstr> BlockPhi;//Ptr To PhiInstr;
    PhiInsertionInfo(){
        BlockPhi = new HashMap<>();
    }
    void NewPhi(String Ptr,String Rd,String Type){
        BlockPhi.put(Ptr,new PhiInstr(InstrSeg.phi,Rd,Type));
    }
}
class PhiNodeInsertionPass{
    AllocaInfo AloInfo;
    DominanceFrontier DomFron;
    HashMap<String,PhiInsertionInfo> PhiInsertion;//Key : Block
//    HashMap<String,List<String>> CorrespondingMap;//Key : BlockName Value: 在该块的responding
    ControlFlowGraph CFG;
    HashSet<String> IsVistsed;
    HashMap<String,Stack<String>> IncomingVals;
    IRFunc CurFunc;
    PhiNodeInsertionPass(AllocaInfo Info, DominanceFrontier DF, IRFunc Func,ControlFlowGraph cfg){
        AloInfo = Info;
        DomFron = DF;
        CurFunc = Func;
        CFG = cfg;
        PhiInsertion = new HashMap<>();
        IncomingVals = new HashMap<>();
        IsVistsed = new HashSet<>();
    }
    void Run(){
       ConfigureInsertion();
       CompletePhiNode();
       PhiInstrInsertion();
       RemoveAlloca();
    }
    private void ConfigureInsertion(){
     //   for(Entry<String,CFGNode> entry : CFG.NodeTable.entrySet()) CorrespondingMap.put(entry.getKey(),new ArrayList<>());
        for(Entry<String,PromotionAlloca> entry: AloInfo.AllocaPacks.entrySet()){
            List<String> Defs = new LinkedList<>(entry.getValue().DefBlocks);
            while(Defs.size() != 0){
                String BB = Defs.get(0);
                Defs.remove(0);
                for(RePostOrderPassNode Node : DomFron.FrontierSet.get(BB)){
                    if(!PhiInsertion.containsKey(Node.BlockName)) PhiInsertion.put(Node.BlockName,new PhiInsertionInfo());
                    PhiInsertionInfo NewPhiInsertion = PhiInsertion.get(Node.BlockName);
                    if(!NewPhiInsertion.BlockPhi.containsKey(entry.getKey())){
                        NewPhiInsertion.NewPhi(entry.getKey(),CurFunc.NewReg(),entry.getValue().Instr.Type);
                        PhiInsertion.put(Node.BlockName,NewPhiInsertion);
                        if(! Defs.contains(Node.BlockName )) Defs.add(Node.BlockName);
                    }
                }
            }
        }
    }
    private void CompletePhiNode(){
        for (Entry<String, PromotionAlloca> entry : AloInfo.AllocaPacks.entrySet()) {
            Stack<String> ValStack = new Stack<>();
            ValStack.push("0");
            IncomingVals.put(entry.getKey(), ValStack);
        }
        CompletePhiNodeDfs(CFG.StartNode);
    }
    private void CompletePhiNodeDfs(CFGNode CurNode) {
        if(IsVistsed.contains(CurNode.Block.Label)) return;
        IsVistsed.add(CurNode.Block.Label);
        Iterator<BaseInstr> Iter = CurNode.Block.VarInstrList.listIterator();
        List<String> Defs = new ArrayList<>();
        while (Iter.hasNext()) {
            BaseInstr Instr = Iter.next();
            if (Instr instanceof LoadInstr) {
                LoadInstr Load = (LoadInstr) Instr;
                if (IncomingVals.containsKey(Load.RsPtr)) {
                    OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, IncomingVals.get(Load.RsPtr).peek(), "0", Load.RdType, "i32", InstrSeg.nullseg);
                    CurNode.Block.VarInstrList.set(CurNode.Block.VarInstrList.indexOf(Instr), RsMove);
                }
            } else if (Instr instanceof StoreInstr) {
                StoreInstr Store = (StoreInstr) Instr;
                if (IncomingVals.containsKey(Store.Ptr)) {
                    Defs.add(Store.Ptr);
                    IncomingVals.get(Store.Ptr).push(Store.Rs);
                    Iter.remove();
                }
            }
        }
            System.out.println(CurNode.Block.getLabel());
            if (CurNode.Suc != null) {
                PhiInsertionInfo SucPhis = PhiInsertion.get(CurNode.Suc.Block.Label);
                if (SucPhis != null) for (Entry<String, PhiInstr> e : SucPhis.BlockPhi.entrySet()) {
                    e.getValue().NewPhiArg(CurNode.Block.Label, IncomingVals.get(e.getKey()).peek(), false);
                    IncomingVals.get(e.getKey()).push(e.getValue().Rd);
                    Defs.add(e.getKey());
                }
                CompletePhiNodeDfs(CurNode.Suc);
            }
            if (CurNode.BrSuc != null) {
                PhiInsertionInfo SucPhis = PhiInsertion.get(CurNode.BrSuc.Block.Label);
                if (SucPhis != null) for (Entry<String, PhiInstr> e : SucPhis.BlockPhi.entrySet()) {
                    e.getValue().NewPhiArg(CurNode.Block.Label, IncomingVals.get(e.getKey()).peek(), false);
                    IncomingVals.get(e.getKey()).push(e.getValue().Rd);
                    Defs.add(e.getKey());

                }
                CompletePhiNodeDfs(CurNode.BrSuc);
            }
            for (String Str : Defs) IncomingVals.get(Str).pop();

    }
    private void PhiInstrInsertion(){
        for(Entry<String, PhiInsertionInfo> entry:PhiInsertion.entrySet()){
            for(Entry<String, PhiInstr> PhiEntry : entry.getValue().BlockPhi.entrySet()){
                CFG.NodeTable.get(entry.getKey()).Block.PushInstr(PhiEntry.getValue());
            }
        }
    }
    private void RemoveAlloca(){
        for(Entry<String,PromotionAlloca> entry:AloInfo.AllocaPacks.entrySet()){
            CFG.StartNode.Block.VarInstrList.remove(entry.getValue().Instr);
        }
    }
    ControlFlowGraph GetSSACFG(){
        return CFG;
    }
}




public class PostIRBuilder {
    List<IRModule> ModuleList;
    HashMap<String,ControlFlowGraph> FuncCFGs = new HashMap<>();

    public void setModuleList(List<IRModule> moduleList) {
        ModuleList = moduleList;
    }

    public void StartOpt(Integer Status){
        if(Status == 1) {
            //进行块间修正
            for (IRModule module : ModuleList) {
                CFGBuild(module.Init);
                for (IRFunc func : module.FuncSet) {
                    if (!func.IsLinked) CFGBuild(func);
                }
            }
        }
        else if(Status == 2){
            for (IRModule module : ModuleList) {
                CFGBuild(module.Init);
                SSABuild(module.Init);
                for (IRFunc func : module.FuncSet) {
                    if (!func.IsLinked){
                        CFGBuild(func);
                        SSABuild(func);
                    }
                }
            }
        }
    }

    void CFGBuild(IRFunc Func){
        if(Func != null) {
            ControlFlowGraphBuildPass CFGBuild = new ControlFlowGraphBuildPass(Func);
            CFGBuild.Run();
            FuncCFGs.put(Func.FuncName,CFGBuild.GetCFG());
        }
    }

    void SSABuild(IRFunc Func){
        if(Func != null){
            ControlFlowGraph CFG = FuncCFGs.get(Func.FuncName);
            AloBuildSimplifyMemPass AllocaPass = new AloBuildSimplifyMemPass(CFG);
            AllocaPass.Run();
            if(AllocaPass.IsAllocaEmpty()) return;
            RePostOrderDfsInfo RPO = new RePostOrderDfsInfo(CFG );
            RPO.Build();
            DominancePass DominanceBuildPass = new DominancePass(RPO);
            DominanceBuildPass.Run();
            PhiNodeInsertionPass PhiInsertion = new PhiNodeInsertionPass(AllocaPass.AloInfo,DominanceBuildPass.GetDominanceFrontier(),Func,CFG );
            PhiInsertion.Run();
            FuncCFGs.replace(Func.getFuncName(),PhiInsertion.GetSSACFG());
        }
    }


    //ProPack:指针名->alloca 包 Memdistribution: 块名->MemInstr包



}
