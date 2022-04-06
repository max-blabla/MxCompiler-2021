package CodeGenerator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FunctionSection {
    ArrayList<BlockSection> BlocksCode;
    String FuncName;
    HashMap<String,BlockSection> BlocksMap;
    FunctionSection(String funcName){
        BlocksCode = new ArrayList<>();
        FuncName = funcName;
    }
    public void Output(PrintStream Stream,Integer Mode){
        Stream.println("\n\t.globl\t"+FuncName);
        Stream.println(FuncName+":");
        for(BlockSection Block : BlocksCode){
            Block.Output(Stream,Mode);
        }
    }
}
