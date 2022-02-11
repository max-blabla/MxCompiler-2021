


import ASTNode.GlobalAST;
import CodeGenerator.CodeGenerator;
import IRBuilder.IRBuilder;
import MxParser.MxParser;
import PostASTBuilder.PostASTBuilder;
import org.antlr.v4.runtime.ANTLRInputStream;
import MxParser.MxLexer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.Vector;
import ASTBuilder.ASTBuilder;
import IRBuilder.IRBuilder;
import IRBuilder.IROutput;
import IRBuilder.PostIRBuilder;

import javax.lang.model.type.TypeVisitor;

public class Main {
    public static void main(String args[]) {
        try {
      //      String InputFile = "./Compiler-2021-testcases/codegen/e10.mx";
          //  String IROutputFile = "./src/IROutput/test.ll";
        //    String CGOutputFile = "D://Coding/ravel-master/build/test.s";
            String CGOutputFile = "./src/output.s";
            InputStream is = System.in;
       //     InputStream is = new FileInputStream(InputFile);

            ANTLRInputStream input = new ANTLRInputStream(is);
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MxParser parser = new MxParser(tokens);
            ParseTree tree = parser.program();

            if (parser.getNumberOfSyntaxErrors() != 0) throw new RuntimeException();
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder AstBuilder = new ASTBuilder();
            walker.walk(AstBuilder, tree);
            PostASTBuilder PostAstBuilder = new PostASTBuilder();
            PostAstBuilder.SetProgram(AstBuilder.getGlobalAST());
            PostAstBuilder.ParRevise();
            PostAstBuilder.ConstSpread();
            GlobalAST Program = PostAstBuilder.GetProgram();
            IRBuilder IR = new IRBuilder(Program);
     //       IR.setProgram(AST);
            IR.ProgramIR();
            PostIRBuilder PostIrBuilder = new PostIRBuilder();
            PostIrBuilder.setModuleList(IR.GetModuleList());
         //   PostIrBuilder.BlockMerging();
            PostIrBuilder.RemoveRedundant();

          //  IROutput IROut = new IROutput(IR.GetModuleList());
        //    IROut.FileRun(IROutputFile);
            CodeGenerator CodeGen = new CodeGenerator();
            CodeGen.setModuleList(IR.GetModuleList());
            CodeGen.CodeGenerate();
            PrintStream Os = new PrintStream(CGOutputFile);
            CodeGen.CodeOutput(Os);
            /*TypeVisitor typeVisitor = new TypeVisitor(parser);
            walker.walk(typeVisitor, tree);

            GlobalVisitor globalVisitor = new G

            lobalVisitor(typeVisitor.GetTypeMap(), parser);
            walker.walk(globalVisitor, tree);

            SemanticChecker semanticChecker = new SemanticChecker(globalVisitor.GetGlobalTable(), parser);
            walker.walk(semanticChecker, tree);*/
        } catch (java.io.IOException E) {
            System.out.print("Input Error");
        }

    }
}