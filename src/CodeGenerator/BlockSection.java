package CodeGenerator;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockSection {
    String BlockLable;
    ArrayDeque<BaseCode> CodeList;
    String IRBlockLabel;
    BlockSection(String Lable,String irBlockLabel){
        BlockLable = Lable;
        IRBlockLabel = irBlockLabel;
        CodeList = new ArrayDeque<>();
    }


    public void Output(PrintStream Stream){
        if(!Objects.equals(BlockLable, "")) Stream.println(BlockLable+":" + "\t\t\t #" +IRBlockLabel);
        for(BaseCode Code : CodeList) Code.Output(Stream);
    }
}
