package IRBuilder;


import ASTNode.BaseAST;
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
    HashMap<String,BlockPhiIndex> BlockPhi;
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
        for(IRBlock Sub : Block.SubBlocks) CFGNodeConnect(Sub,Graph);
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
        for(IRBlock Sub : Block.SubBlocks) CFGNodeBuild(Sub,Graph);
    }

    private void PhiGather(IRBlock irBlock,HashMap<String,BlockPhiIndex> PhiIndex){
        if(irBlock == null) return;
        for(BaseInstr Instr: irBlock.VarInstrList){
            if(Instr instanceof PhiInstr){
                PhiInstr Phi = (PhiInstr) Instr;
                for(int i = 0 ;i < Phi.PreBlock.size();i++) PhiIndex.put(Phi.PreBlock.get(i),new BlockPhiIndex(Phi,i));
            }
        }
        for(IRBlock Sub : irBlock.getSubBlocks()) PhiGather(Sub,PhiIndex);
    }

    ControlFlowGraph GetCFG(){
        return CFG;
    }
    private void PhiFuncGather(){
        if(CurFunc == null) return;
        HashMap<String,BlockPhiIndex> IndexMap = new HashMap<>();
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
                    BlockPhiIndex PhiIndex = BlockPhi.get(SucBlock.getLabel());
                    PhiIndex.PhiNode.PreBlock.set(PhiIndex.Index,CurBlock.Label);
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
        BlockRenew(Start,End);
        CurFunc.Start = Start.Block;
        CurFunc.End = End.Block;
    }

    private void BlockRenew(CFGNode CurNode,CFGNode End){
        RenewQueue  = new ArrayDeque<>();
        RenewQueue.add(CurNode);
        Check.add(CurNode.Block);
        while(!RenewQueue.isEmpty()){
            CFGNode Top = RenewQueue.peek();
            Top.Block.SubBlocks = new ArrayList<>();
            if(Top.Suc != null && !Check.contains(Top.Suc.Block) && Top.Suc != End){
                Check.add(Top.Suc.Block);
                Top.Block.InsertSubBlock(Top.Suc.Block);
                RenewQueue.add(Top.Suc);
            }
            if(Top.BrSuc != null && !Check.contains(Top.BrSuc.Block)&& Top.BrSuc != End){
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

    PromotionAlloca(AllocaInstr instr,Integer Index) {
        Instr = instr;
        StartBlockIndex = Index;
        UseCnt = 0;
        DefCnt = 0;
        //    BlockName = blockName;
        IsPromotable = true;
    }
    void BanPromotion(){
        IsPromotable = false;
    }
}
class RePostOrderDfsInfo{
    Stack<RePostOrderPassNode>  ReDfsPostOrder;
    HashMap<String,RePostOrderPassNode> RePostOrderNodeNameMap;
    ControlFlowGraph CFG;
    RePostOrderDfsInfo(ControlFlowGraph cfg){
        CFG = cfg;
        RePostOrderNodeNameMap = new HashMap<>();
        ReDfsPostOrder = new Stack<>();
    }
    private void BuildPostOrder(CFGNode CurNode){
        if(CurNode == null) return;
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
    AloBuildSimplifyMemPass(ControlFlowGraph cfg){
        CFG = cfg;
        AloInfo = new AllocaInfo();;
        GlobalPropogationMap = new HashMap<>();
    }
    void Run(){
        AllocGather(CFG.StartNode,CFG.StartNode);
        PromotableCheck(CFG.StartNode);
        CountUseDef(CFG.StartNode);
        MemPropogation(CFG.StartNode);
        ConfigureUse(CFG.StartNode);
    }
    private  void AllocGather(CFGNode CurNode, CFGNode AimNode){
        if(CurNode == null) return;
        AllocGather(CurNode.Suc,AimNode);
        AllocGather(CurNode.BrSuc,AimNode);
        List<BaseInstr> InstrList = CurNode.Block.getVarInstrList();
        for(int i = InstrList.size()-1;i>=0;i--){
            BaseInstr Instr = InstrList.get(i);
            if(Instr instanceof AllocaInstr){
                AllocaInstr Alloc = (AllocaInstr) Instr;
                InstrList.remove(i);
                AloInfo.AllocaPacks.put(Alloc.Rd,new PromotionAlloca(Alloc,i));
                AimNode.Block.PushInstr(Instr);
            }
        }
    }

    private  void PromotableCheck(CFGNode CurNode){
        if(CurNode == null) return;
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
        String BlockName = CurNode.Block.Label;
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

    void MemPropogation(CFGNode StartNode){
        //将一def与一块内传播。在传播完消除无用alloca指令
        if(StartNode == null) return;
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
                        OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, LocalPropogationMap.get(Ptr), "", Load.RdType, "i32", InstrSeg.nullseg);
                        InstrList.set(LoadIndex,RsMove);
                        --AllocProPack.UseCnt;
                    }
                    else if(GlobalPropogationMap.containsKey(Ptr)){
                        OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, GlobalPropogationMap.get(Ptr), "", Load.RdType, "i32", InstrSeg.nullseg);
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
        String BlockName = CurNode.Block.Label;
        List<BaseInstr> InstrList = CurNode.Block.getVarInstrList();
        for (BaseInstr Instr : InstrList) {
            if (Instr instanceof StoreInstr) {
                StoreInstr Store = (StoreInstr) Instr;
                String Ptr = Store.Ptr;
                if (AloInfo.AllocaPacks.containsKey(Ptr)) AloInfo.AllocaPacks.get(Ptr).DefBlocks.add(BlockName);
            }
        }
        CountUseDef(CurNode.Suc);
        CountUseDef(CurNode.BrSuc);
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
        for(RePostOrderPassNode Node:Info.ReDfsPostOrder){
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
        RePostOrderPassNode StartNode = Info.ReDfsPostOrder.peek();
        FingerTable.put(StartNode.BlockName,0);
        Doms.replace(StartNode.BlockName,StartNode);
        boolean IsChanged = true;
        while(IsChanged){
            IsChanged  = false;
            for(RePostOrderPassNode Node:Info.ReDfsPostOrder){
                if(Node == StartNode) continue;
                RePostOrderPassNode NewIdom =null;
                for(String Pres:Node.Predecessors) if(IsProcessed.contains(Pres)) NewIdom = Info.RePostOrderNodeNameMap.get(Pres);
                assert NewIdom != null;
                for(String Pres:Node.Predecessors){
                    RePostOrderPassNode OtherNode = Info.RePostOrderNodeNameMap.get(Pres);
                    if(OtherNode == NewIdom) continue;
                    if(Doms.get(OtherNode.BlockName) == NewIdom)  NewIdom = Intersect(OtherNode.BlockName,NewIdom.BlockName);
                }
                if(NewIdom != Doms.get(Node.BlockName)){
                    Doms.replace(Node.BlockName,NewIdom);
                    IsChanged = true;
                }
                FingerTable.put(Node.BlockName,FingerTable.get(NewIdom.BlockName)+1);
            }

        }
    }
    private  RePostOrderPassNode Intersect(String N1,String N2){
        String FingerName1 = N1;
        String FingerName2 = N2;
        int Finger1 = FingerTable.get(N1);
        int Finger2 = FingerTable.get(N2);
        while(Finger1 != Finger2){
            while(Finger1 <Finger2){
                FingerName1 = Doms.get(FingerName1).BlockName;
                Finger1 = FingerTable.get(FingerName1);
            }
            while(Finger2 <Finger1){
                FingerName2 = Doms.get(FingerName2).BlockName;
                Finger2 = FingerTable.get(FingerName2);
            }
        }
        return Info.RePostOrderNodeNameMap.get(FingerName1);
    }
}
class PhiInsertionInfo{
    HashMap<String, PhiInstr> BlockPhi;
    PhiInsertionInfo(){
        BlockPhi = new HashMap<>();
    }
    void NewPhi(String BlockName,String Rd,String Type){
        BlockPhi.put(BlockName,new PhiInstr(InstrSeg.phi,Rd,Type));
    }
}
class PhiNodeInsertionPass{
    AllocaInfo AloInfo;
    DominanceFrontier DomFron;
    HashMap<String,PhiInsertionInfo> PhiInsertion;//Key : Ptr
    HashMap<String,List<String>> CorrespondingMap;//Key : BlockName Value: 在该块的responding
    ControlFlowGraph CFG;
    HashMap<String,String> IncomingVals;
    HashSet<String> IsVistsed;
    IRFunc CurFunc;
    PhiNodeInsertionPass(AllocaInfo Info, DominanceFrontier DF, IRFunc Func,ControlFlowGraph cfg){
        AloInfo = Info;
        DomFron = DF;
        CurFunc = Func;
        CFG = cfg;
        PhiInsertion = new HashMap<>();
        IncomingVals = new HashMap<>();
        IsVistsed = new HashSet<>();
        CorrespondingMap = new HashMap<>();
    }
    void Run(){
       ConfigureInsertion();
       CompletePhiNode();
       PhiInstrInsertion();
    }
    private void ConfigureInsertion(){
        for(Entry<String,CFGNode> entry : CFG.NodeTable.entrySet()) CorrespondingMap.put(entry.getKey(),new ArrayList<>());
        for(Entry<String,PromotionAlloca> entry: AloInfo.AllocaPacks.entrySet()){
            List<String> Defs = new LinkedList<>(entry.getValue().DefBlocks);
            PhiInsertionInfo Insertion = new PhiInsertionInfo();
            while(Defs.size() != 0){
                String BB = Defs.get(0);
                Defs.remove(0);
                for(RePostOrderPassNode Node : DomFron.FrontierSet.get(BB)){
                    if(!Insertion.BlockPhi.containsKey(Node.BlockName)){
                        Insertion.NewPhi(Node.BlockName,CurFunc.NewReg(),entry.getValue().Instr.Type);
                        CorrespondingMap.get(BB).add(entry.getKey());
                        if(! Defs.contains(Node.BlockName )) Defs.add(Node.BlockName);
                    }
                }
            }
            PhiInsertion.put(entry.getKey(),Insertion);
        }
    }
    private void CompletePhiNode(){
        for(Entry<String,PromotionAlloca> entry: AloInfo.AllocaPacks.entrySet()) IncomingVals.put(entry.getKey(),"0");
        Queue<CFGNode> WorkList = new ArrayDeque<>();
        WorkList.add(CFG.StartNode);
        CFGNode Top;
        List<BaseInstr> InstrList;
        while(WorkList.size() != 0){
            Top = WorkList.peek();
           InstrList = Top.Block.getVarInstrList();
           Iterator<BaseInstr> Iter = InstrList.listIterator();
            while (Iter.hasNext()) {
                BaseInstr Instr = Iter.next();
                if (Instr instanceof LoadInstr) {
                    LoadInstr Load = (LoadInstr) Instr;
                    OperationInstr RsMove = new OperationInstr(InstrSeg.add, Load.Rd, IncomingVals.get(Load.RsPtr), "0", Load.RdType, "i32", InstrSeg.nullseg);
                    Top.Block.VarInstrList.set(InstrList.indexOf(Instr), RsMove);
                } else if (Instr instanceof StoreInstr) {
                    StoreInstr Store = (StoreInstr) Instr;
                    IncomingVals.replace(Store.Ptr, Store.Rs);
                    Iter.remove();
                }
            }

            if (Top.Suc != null) {
                for (String Ptrs : CorrespondingMap.get(Top.Suc.Block.Label)) {
                    PhiInstr AimPhi = PhiInsertion.get(Ptrs).BlockPhi.get(Top.Suc.Block.Label);
                    AimPhi.NewPhiArg(Top.Block.Label, IncomingVals.get(Ptrs), false);
                    IncomingVals.replace(Ptrs, AimPhi.Rd);
                }
                if(IsVistsed.contains(Top.Suc.Block.Label)) {
                    IsVistsed.add(Top.Suc.Block.Label);
                    WorkList.add(Top.Suc);
                }
            }
            if (Top.BrSuc != null) {
                for (String Ptrs : CorrespondingMap.get(Top.BrSuc.Block.Label)) {
                    PhiInstr AimPhi = PhiInsertion.get(Ptrs).BlockPhi.get(Top.BrSuc.Block.Label);
                    AimPhi.NewPhiArg(Top.Block.Label, IncomingVals.get(Ptrs), false);
                    IncomingVals.replace(Ptrs, AimPhi.Rd);
                }
                if(IsVistsed.contains(Top.BrSuc.Block.Label)) {
                    IsVistsed.add(Top.BrSuc.Block.Label);
                    WorkList.add(Top.BrSuc);
                }
            }

        }
    }
    private void PhiInstrInsertion(){
        for(Entry<String, PhiInsertionInfo> entry:PhiInsertion.entrySet()){
            for(Entry<String, PhiInstr> PhiEntry : entry.getValue().BlockPhi.entrySet()){
                CFG.NodeTable.get(PhiEntry.getKey()).Block.PushInstr(PhiEntry.getValue());
            }
        }
    }
    ControlFlowGraph GetSSACFG(){
        return CFG;
    }
}




public class PostIRBuilder {
    List<IRModule> ModuleList;
    HashMap<String,ControlFlowGraph> FuncCFGs;

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
