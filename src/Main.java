


import ASTNode.GlobalAST;
import MxParser.MxParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import MxParser.MxLexer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.Vector;
import ASTBuilder.ASTBuilder;
public class Main {
    public static void main(String args[]) {
        try {
            String InputFile = "./src/test.mx";
            InputStream is = System.in;
            is = new FileInputStream(InputFile);

            ANTLRInputStream input = new ANTLRInputStream(is);
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MxParser parser = new MxParser(tokens);
            ParseTree tree = parser.program();
            if (parser.getNumberOfSyntaxErrors() != 0) throw new RuntimeException();
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder AstBuilder = new ASTBuilder();
            walker.walk(AstBuilder, tree);
            GlobalAST AST = AstBuilder.getGlobalAST();
            /*TypeVisitor typeVisitor = new TypeVisitor(parser);
            walker.walk(typeVisitor, tree);

            GlobalVisitor globalVisitor = new G

            lobalVisitor(typeVisitor.GetTypeMap(), parser);
            walker.walk(globalVisitor, tree);

            SemanticChecker semanticChecker = new SemanticChecker(globalVisitor.GetGlobalTable(), parser);
            walker.walk(semanticChecker, tree);*/
        } catch (java.io.IOException E) {
            System.out.print("Input Error");
        } catch (RuntimeException E) {

        }

    }
}