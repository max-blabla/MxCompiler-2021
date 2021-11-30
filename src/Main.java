

import GlobalVisitor.GlobalVisitor;
import GlobalVisitor.TypeVisitor;
import MxParser.MxParser;
import SemanticChecker.SemanticChecker;
import org.antlr.v4.runtime.ANTLRInputStream;
import MxParser.MxLexer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.Vector;

public class Main {
    public static void main(String args[]) {
        try {
           String InputFile = "./src/array-package/array-4.mx";
           InputStream is = System.in;
           is = new FileInputStream(InputFile);

            ANTLRInputStream input = new ANTLRInputStream(is);
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MxParser parser = new MxParser(tokens);
            ParseTree tree = parser.program();
            if (parser.getNumberOfSyntaxErrors() != 0) throw new RuntimeException();
            ParseTreeWalker walker = new ParseTreeWalker();
            TypeVisitor typeVisitor = new TypeVisitor(parser);
            walker.walk(typeVisitor, tree);

            GlobalVisitor globalVisitor = new GlobalVisitor(typeVisitor.GetTypeMap(), parser);
            walker.walk(globalVisitor, tree);

            SemanticChecker semanticChecker = new SemanticChecker(globalVisitor.GetGlobalTable(), parser);
            walker.walk(semanticChecker, tree);
        } catch (java.io.IOException E) {
            System.out.print("Input Error");
        } catch (RuntimeException E) {

        }

    }
}