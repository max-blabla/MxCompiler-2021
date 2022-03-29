


import ASTNode.ErrorInfo;
import ASTNode.GlobalAST;
import ASTNode.SemanticChecker;
import CodeGenerator.CodeGenerator;
import IRBuilder.IRBuilder;
import MxParser.MxParser;
import PostASTBuilder.PostASTBuilder;
import MxParser.MxLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.Vector;
import ASTBuilder.ASTBuilder;
import IRBuilder.IRBuilder;
import IRBuilder.PostIRBuilder;

public class Main {
    public static void main(String args[]) {
            try {
                String InputFile;
             //   InputFile = "./Compiler-2021-testcases/codegen/e" + 1 + ".mx";
                    String IROutputFile = "./src/IROutput/test.ll";
                String OptIROutputFile = "./src/IROutput/Opttest.ll";

                //                String CGOutputFile = "./src/CGOutput/test.s";
                    //  String CGOutputFile = "D://Coding/ravel-master/build/test.s";
                        String CGOutputFile = "test.s";
                       InputStream is = System.in;
            //        InputStream is = new FileInputStream(InputFile);

                  //  ANTLRInputStream input = new ANTLRInputStream(is);
                    ANTLRInputStream input = new ANTLRInputStream(is);
                    MxLexer lexer = new MxLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    MxParser parser = new MxParser(tokens);
                    ParseTree tree = parser.program();

                    if (parser.getNumberOfSyntaxErrors() != 0) throw new RuntimeException();
                    ParseTreeWalker walker = new ParseTreeWalker();
                    ASTBuilder AstBuilder = new ASTBuilder();
                    walker.walk(AstBuilder, tree);
                    SemanticChecker SC = new SemanticChecker();
                    SC.SetProgram(AstBuilder.getGlobalAST());
                    SC.SemanticCheck();
                    if(SC.HasLambda()) return;

                    PostASTBuilder PostAstBuilder = new PostASTBuilder();
                    PostAstBuilder.SetProgram(AstBuilder.getGlobalAST());
                    PostAstBuilder.ConstSpread();
                    GlobalAST Program = PostAstBuilder.GetProgram();
                    IRBuilder IR = new IRBuilder();
                    IR.RunIR(Program);
                    IR.FileRun(IROutputFile);
                PostIRBuilder PostIrBuilder = new PostIRBuilder();
                PostIrBuilder.setModuleList(IR.GetModuleList());
                PostIrBuilder.StartOpt(1);
                //   PostIrBuilder.BlockMerging();
                //PostIrBuilder.RemoveRedundant();
                IR.FileRun(OptIROutputFile);
                    CodeGenerator CodeGen = new CodeGenerator();
                    CodeGen.setIRInit(IR.GetModuleList(),IR.getConstStrs());
                    CodeGen.CodeGenerate();
                    PrintStream Os = new PrintStream(CGOutputFile);
                    CodeGen.CodeOutput(Os);
                    is.close();
       //         }
            } catch (java.io.IOException E) {
                System.out.print("Input Error");
            }  catch (ErrorInfo E){
                throw new RuntimeException();
            }
    }
}