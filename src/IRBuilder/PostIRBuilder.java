package IRBuilder;


import ASTNode.BaseAST;
import org.w3c.dom.Node;

import java.util.*;
import java.util.Map.Entry;

class GraphNode{
    List<GraphNode> Predecessors;
    IRBlock Block;
    GraphNode TrueSuccessor;
    GraphNode FalseSuccessor;
    Boolean IsBranch;
    Boolean IsVisit;
    Boolean IsRenew;
    GraphNode(IRBlock block){
        Block = block;
        Predecessors = new ArrayList<>();
        IsVisit = false;
        IsRenew = false;
    }
}
public class PostIRBuilder {
    List<IRModule> ModuleList;
    HashMap<String,GraphNode> NodeSearching;
    GraphNode AllStart;
    HashMap<String,Integer> IRRegUsed;
    public void setModuleList(List<IRModule> moduleList) {
        ModuleList = moduleList;
    }

    public void BlockMerging(){
        for(IRModule module:ModuleList){
            for(IRFunc func : module.FuncSet){
                if(func.IsLinked) continue;
                NodeSearching = new HashMap<>();
                GraphNodeBuilding(func.Start);
                GraphNodeBuilding(func.End);
                GraphBuilding();
                GraphNode StartNode = NodeSearching.get(func.Start.Label);
                BlockMerging(StartNode,func);
                RenewBlock(StartNode,func);
            }
        }
/*        for(IRModule module:ModuleList){
            for(IRFunc func : module.FuncSet){
                NodeSearching = new HashMap<>();
                GraphNodeBuilding(func.Start);
                GraphNodeBuilding(func.End);
                GraphBuilding();
                GraphNode StartNode = NodeSearching.get(func.Start.Label);
                BlockMerging(StartNode,func);
                RenewBlock(StartNode,func);
            }
        }*/
    }
    public void RemoveRedundant(){
        for(IRModule module:ModuleList){
            for(IRFunc func : module.FuncSet){
                IRRegUsed = new HashMap<>();
                CheckingRegUsage(func.Start);
                CheckingRegUsage(func.End);
                DeleteRedundant(func.End);
                DeleteRedundant(func.Start);
            }
        }
    }

    void AddRegUsage(String IRRegName){
        if(IRRegUsed.containsKey(IRRegName)) IRRegUsed.replace(IRRegName,IRRegUsed.get(IRRegName)+1);
        else IRRegUsed.put(IRRegName,0);
    }
    void SubRegUsage(String IRRegName){
        IRRegUsed.replace(IRRegName,IRRegUsed.get(IRRegName)-1);
    }
    void CheckingRegUsage(IRBlock Root){
        if(Root == null) return;
        for(BaseInstr instr : Root.VarInstrList){
            if(instr instanceof AllocaInstr){
                AllocaInstr Allo = (AllocaInstr) instr;
                AddRegUsage(Allo.Rd);
            }
            else  if(instr instanceof FuncCallInstr){
                FuncCallInstr Call = (FuncCallInstr) instr;
                for(String Param : Call.Param) AddRegUsage(Param);
                AddRegUsage(Call.Rd);
            }
            else if(instr instanceof GetelementInstr ){
                GetelementInstr Get = (GetelementInstr) instr;
                AddRegUsage(Get.Ptr);
                AddRegUsage(Get.Rd);
                if(Objects.equals(Get.Mode, "index")) AddRegUsage(Get.Index);
            }
            else if(instr instanceof LoadInstr){
                LoadInstr Load = (LoadInstr) instr;
                AddRegUsage(Load.RsPtr);
                AddRegUsage(Load.Rd);
            }
            else if(instr instanceof OperationInstr ){
                OperationInstr Op = (OperationInstr) instr;
                if(!Op.IsRsImm1) AddRegUsage(Op.Rs1);
                if(!Op.IsRsImm2) AddRegUsage(Op.Rs2);
                AddRegUsage(Op.Rd);
            }
            else if(instr instanceof StoreInstr){
                StoreInstr Store = (StoreInstr) instr;
                AddRegUsage(Store.Rs);
                AddRegUsage(Store.Ptr);
            }
        }
        if(Root.EndInstr instanceof BranchInstr){
            BranchInstr Br = (BranchInstr) Root.EndInstr;
            if(!Objects.equals(Br.Condition, "")) AddRegUsage(Br.Condition);
        }
        else if(Root.EndInstr instanceof ReturnInstr Ret) AddRegUsage(Ret.Rs);
        for(IRBlock Son : Root.SubBlocks) CheckingRegUsage(Son);
    }
    void DeleteRedundant(IRBlock Root){
        if(Root == null) return;
        for(IRBlock Son : Root.SubBlocks) DeleteRedundant(Son);
        int size = Root.VarInstrList.size();
        for(int i = size - 1 ; i>= 0 ;i--){
            BaseInstr Instr = Root.VarInstrList.get(i);
            if(Instr instanceof LoadInstr Load){
                if(IRRegUsed.get(Load.Rd) == 0) {
                    SubRegUsage(Load.RsPtr);
                    Root.VarInstrList.remove(i);
                }
            }
            else if(Instr instanceof GetelementInstr Get) {
                if (IRRegUsed.get(Get.Rd) == 0) {
                    SubRegUsage(Get.Ptr);
                    if (Objects.equals(Get.Mode, "index")) SubRegUsage(Get.Index);
                    Root.VarInstrList.remove(i);
                }
            }
        }
    }

    void GraphNodeBuilding(IRBlock Root){
        if(Root == null) return;
        GraphNode Start = new GraphNode(Root);
        NodeSearching.put(Root.Label,Start);
        for(IRBlock Son : Root.SubBlocks) GraphNodeBuilding(Son);
    }
    void GraphBuilding(){
        //GraphNode StartNode = NodeSearching.get(Start.Label);
        for(Entry<String,GraphNode> entry : NodeSearching.entrySet()){
            GraphNode graphNode = entry.getValue();
            if(graphNode.Block.EndInstr instanceof ReturnInstr){
                graphNode.FalseSuccessor = null;
                graphNode.TrueSuccessor = null;
                graphNode.IsBranch = false;
            }
            else if(graphNode.Block.EndInstr instanceof BranchInstr){
                BranchInstr Branch = (BranchInstr) graphNode.Block.EndInstr;
                if(Objects.equals(Branch.Label2, "")){
                    graphNode.TrueSuccessor = NodeSearching.get(Branch.Label1);
                    NodeSearching.get(Branch.Label1).Predecessors.add(graphNode);
                    graphNode.IsBranch = false;
                }
                else{
                    graphNode.TrueSuccessor = NodeSearching.get(Branch.Label1);
                    NodeSearching.get(Branch.Label1).Predecessors.add(graphNode);
                    graphNode.FalseSuccessor = NodeSearching.get(Branch.Label2);
                    NodeSearching.get(Branch.Label2).Predecessors.add(graphNode);
                    graphNode.IsBranch = true;
                }
            }
        }
    }
    void BlockMerging( GraphNode CurNode,IRFunc irFunc){
      //  GraphNode CurNode = NodeSearching.get(StartLabel);
        if(CurNode == null) return;
        if (!CurNode.IsVisit) {
            CurNode.IsVisit = true;
     //       System.out.println(CurNode.Block.Label);
            if((CurNode.Predecessors.size()==1)&& CurNode.Predecessors.get(0).FalseSuccessor == null){
                if(CurNode.Block == irFunc.End) irFunc.End = null;
                GraphNode PrevNode = CurNode.Predecessors.get(0);
                PrevNode.TrueSuccessor = CurNode.TrueSuccessor;
                PrevNode.FalseSuccessor = CurNode.FalseSuccessor;
                PrevNode.Block.EndInstr = CurNode.Block.EndInstr;
                PrevNode.IsBranch = CurNode.IsBranch;
                PrevNode.Block.VarList.putAll(CurNode.Block.VarList);
                PrevNode.Block.VarInstrList.addAll(CurNode.Block.VarInstrList);
                if(CurNode.TrueSuccessor != null) {
                    for(int i = 0 ; i<  CurNode.TrueSuccessor.Predecessors.size() ;++i) {
                        if(CurNode.TrueSuccessor.Predecessors.get(i).Block == CurNode.Block){
                            CurNode.TrueSuccessor.Predecessors.remove(i);
                            break;
                        }
                    }
                    CurNode.TrueSuccessor.Predecessors.add(PrevNode);
                }
                if(CurNode.FalseSuccessor != null){
                    for(int i = 0 ; i<  CurNode.FalseSuccessor.Predecessors.size() ;++i) {
                        if(CurNode.FalseSuccessor.Predecessors.get(i).Block == CurNode.Block){
                            CurNode.FalseSuccessor.Predecessors.remove(i);
                            break;
                        }
                    }
                    CurNode.FalseSuccessor.Predecessors.add(PrevNode);
                }
                NodeSearching.remove(CurNode.Block.Label);
            }
            BlockMerging(CurNode.TrueSuccessor,irFunc);
            BlockMerging(CurNode.FalseSuccessor,irFunc);
        }
        else if(CurNode.TrueSuccessor == null) CurNode.IsVisit = true;

        //return;
    }
    void RenewBlock(GraphNode CurNode,IRFunc irFunc){
        if(CurNode == null) return;
        if(!CurNode.IsRenew){
            CurNode.IsRenew = true;
            CurNode.Block.SubBlocks = new ArrayList<>();
            if(CurNode.TrueSuccessor != null && !CurNode.TrueSuccessor.IsRenew){
                if(CurNode.TrueSuccessor.Block != irFunc.End) CurNode.Block.SubBlocks.add(CurNode.TrueSuccessor.Block);
                RenewBlock(CurNode.TrueSuccessor,irFunc);
            }
            if(CurNode.FalseSuccessor!=null  && !CurNode.FalseSuccessor.IsRenew){
                if(CurNode.FalseSuccessor.Block != irFunc.End) CurNode.Block.SubBlocks.add(CurNode.FalseSuccessor.Block);
                RenewBlock(CurNode.FalseSuccessor,irFunc);
            }
        }
    }
}
