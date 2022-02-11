package CodeGenerator;

import org.antlr.v4.codegen.model.SrcOp;

import java.io.PrintStream;

public class BCode extends BaseCode{
    String Op;
    String Rs1;
    String Imm;
    String Rs2;
    BCode(String op, String rs1, String rs2, String imm,Integer Line){
        super(CodeType.BType,Line);
        Op = op;
        Rs1 = rs1;
        Rs2 = rs2;
        Imm = imm;
    }

    @Override
    public void Output(PrintStream Stream) {
        Stream.print("\t"+Op + " " + Rs1 + " ," + Rs2 + " ," + Imm);
        OutputLine(Stream);
        Stream.print("\n");
    }
}
