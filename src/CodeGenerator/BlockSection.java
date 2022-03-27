package CodeGenerator;

import java.io.PrintStream;
import java.util.*;

public class BlockSection {
    String BlockLable;
    ArrayList<BaseCode> CodeList;
    String IRBlockLabel;
    BlockSection(String Lable,String irBlockLabel){
        BlockLable = Lable;
        IRBlockLabel = irBlockLabel;
        CodeList = new ArrayList<>();
    }

    public void PhiInsert(BaseCode Code){
      for(int i = CodeList.size()-1;i>=0;i--){
          CodeType Type = CodeList.get(i).codeType;
          if(!(Type == CodeType.BPType || Type == CodeType.JType || Type==CodeType.BType)){
              CodeList.add(i+1,Code);
              break;
          }
      }
    }


    public void Output(PrintStream Stream){
        if(!Objects.equals(BlockLable, "")) Stream.println(BlockLable+":" + "\t\t\t #" +IRBlockLabel);
        for(BaseCode Code : CodeList) Code.Output(Stream);
    }
}
