package CodeGenerator;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockSection {
    String BlockLable;
    ArrayDeque<BaseCode> CodeList;
    BlockSection(String Lable){
        BlockLable = Lable;
        CodeList = new ArrayDeque<>();
    }
    public void Output(PrintStream Stream){
        if(!Objects.equals(BlockLable, "")) Stream.println(BlockLable+":");
        for(BaseCode Code : CodeList) Code.Output(Stream);
    }
}
