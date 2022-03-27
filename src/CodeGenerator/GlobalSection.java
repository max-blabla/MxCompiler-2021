package CodeGenerator;

import java.io.PrintStream;
import java.util.Objects;

public class GlobalSection {
    String Asciz;
    Integer Size;
    String Name;
    GlobalSection(String name, String asciz,Integer size){
        Asciz = asciz;
        Size = size;
        Name =name;
    }
    public void Output(PrintStream Stream){
        Stream.println("\n\t.data");
        Stream.println(Name +":");
        if(!Objects.equals(Asciz, ""))
            Stream.println("\t"+".asciz"+"\t" + Asciz);
        else {
                Stream.println("\t" + ".word" + "\t" + 0);
        }
    }
}
