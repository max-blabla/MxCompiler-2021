package CodeGenerator;

import java.io.PrintStream;

public abstract class BaseCode {
    CodeType codeType;
    Integer Line;
    BaseCode(CodeType type,Integer line){
        codeType = type;
        Line = line;
    }
    public abstract void Output(PrintStream Stream);
    public abstract void VirOutput(PrintStream Stream);
    void OutputLine(PrintStream Stream){
        if(Line != 0 ) Stream.print("\t\t"+"#"+Line);
    }
}
