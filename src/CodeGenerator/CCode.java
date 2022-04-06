package CodeGenerator;

import java.io.PrintStream;

public class CCode extends BaseCode{
    OpType Op;
    String FuncName;
    CCode(OpType op,String funcName,Integer Line){
        super(CodeType.CType,Line);
        Op = op;
        FuncName = funcName;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op+ " " + FuncName);
        OutputLine(Stream);
        Stream.print("\n");
    }

    @Override
    public void VirOutput(PrintStream Stream) {
        Stream.print("\t"+Op+ " " + FuncName);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
