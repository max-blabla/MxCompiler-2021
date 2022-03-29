package IRBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhiInstr extends  BaseInstr{
    InstrSeg Op;
    public String Rd;
    String RdType;
    public List<String> PreBlock;
    public List<String> PhiValue;
    public List<Boolean> IsImm;
    PhiInstr(InstrSeg op, String rd, String rdType) {
        super(InstrType.Phi);
        Op = op;
        Rd = rd;
        RdType = rdType;
        PreBlock = new ArrayList<>();
        PhiValue = new ArrayList<>();
        IsImm = new ArrayList<>();
    }

    void NewPhiArg(String preBlock, String phiValue, Boolean isImm){
        PreBlock.add(preBlock);
        PhiValue.add(phiValue);
        IsImm.add(isImm);
    }

    void NewPhiNode(String preBlock, String phiValue, String PreChange){
        if(!PreBlock.isEmpty()){
            int EndIndex = PhiValue.size()-1;
            PhiValue.set(EndIndex,PreChange);
            IsImm.set(EndIndex,true);
        }
        PreBlock.add(preBlock);
        PhiValue.add(phiValue);
        IsImm.add(false);
    }

    @Override
    public void Output(FileWriter Writer) throws IOException {
     //   Writer.write(Op+ ' ' + Mode + ' ' + RdType + " %" + Rd + '\n');
        Writer.write("%"+Rd + " =" + Op + ' ' + RdType);
        for(int i = 0 ;i < PhiValue.size();i++) Writer.write('['+"%"+PhiValue.get(i)+", "+PreBlock.get(i)+']');
        Writer.write('\n');
    }
}
