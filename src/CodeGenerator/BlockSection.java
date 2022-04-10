package CodeGenerator;

import java.io.PrintStream;
import java.util.*;

public class BlockSection {
    String BlockLable;
    ArrayList<BaseCode> CodeList;
    String IRBlockLabel;
    int LoopStatus;
    BlockSection(String Lable,String irBlockLabel,int loopStatus){
        BlockLable = Lable;
        IRBlockLabel = irBlockLabel;
        CodeList = new ArrayList<>();
        LoopStatus = loopStatus;
    }

    public void PhiInsert(BaseCode Code){
      for(int i = CodeList.size()-1;i>=0;i--){
          CodeType Type = CodeList.get(i).codeType;
          if(!(Type == CodeType.BPType || Type == CodeType.JType || Type==CodeType.BType)){
              CodeList.add(i+1,Code);
              return;
          }
      }
      CodeList.add(0,Code);
    }


    public void Output(PrintStream Stream,Integer Mode){
        if(!Objects.equals(BlockLable, "")) Stream.println(BlockLable+":" + "\t\t\t #" +IRBlockLabel);
        if(Mode == 1)  for(BaseCode Code : CodeList) Code.VirOutput(Stream);
        else if(Mode == 2) for(BaseCode Code : CodeList) Code.Output(Stream);
        else if(Mode == 3){
            for(BaseCode Code : CodeList){
                Stream.print("#");
                Code.VirOutput(Stream);
                Code.Output(Stream);
            }
        }
    }
}
