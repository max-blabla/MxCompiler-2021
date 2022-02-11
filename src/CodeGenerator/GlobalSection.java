package CodeGenerator;

import java.io.PrintStream;
import java.util.Objects;

public class GlobalSection {
    String Asciz;
    Integer Size;
    String Name;
    Integer Word;
    GlobalSection(String name, String asciz,Integer size,Integer word){
        Asciz = asciz;
        Size = size;
        Name =name;
        Word = word;
    }
    public void Output(PrintStream Stream){
        Stream.println("\n\t.data");
        Stream.println(Name +":");
        if(!Objects.equals(Asciz, ""))
            Stream.println("\t"+".asciz"+"\t" +"\""+ Asciz+"\"");
        else {
            Stream.println("\t" + ".word" + "\t" + Word);
            if(Word != 0){
                Stream.println("\t" + ".word" + "\t" + 0);
            }
        }
        Stream.println("\t"+".size"+"\t" + Size.toString());
    }
}
