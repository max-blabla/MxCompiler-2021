package CodeGenerator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FunctionSection {
    BlockSection Start;
  //  BlockSection End;
    List<BlockSection> BlocksCode;
    String FuncName;
    FunctionSection(String funcName){
        BlocksCode = new ArrayList<>();
        FuncName = funcName;
    }
    public void Output(PrintStream Stream){
        Stream.println("\n\t.globl\t"+FuncName);
        Stream.println(FuncName+":");
        Start.Output(Stream);
        for(BlockSection Block : BlocksCode){
            Block.Output(Stream);
        }
    }
}
