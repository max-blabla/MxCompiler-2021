public class Main {
    public static void main(String args[]) {
        try {
            String InputFile = "";
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