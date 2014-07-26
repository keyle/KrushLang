import java.util.ArrayList;

public class Parser {

    public static final String KEYWORD_CLASS = "class";
    public static final String KEYWORD_FUNCTION = "def";

    public static final String CLASS_MISSING_NAME = "Invalid Class Definition, <class Name> missing 'name'";
    public static final String CLASS_MISSING_BEGIN_BLOCK = "Missing Class Begin Block, '{' after class name";

    public static Object syntaxicAnalysis(ArrayList<Token> tokens) {

        for (int i = 1; i < tokens.size() - 1; i++) {

            Token token = tokens.get(i);

            if (token.type == Token.Type.WORD && token.content.equals(KEYWORD_CLASS)) {
                findClasses(tokens, i);
            }

//            tokens.add(tokens.get(i));
        }

        return null;
    }

    public static ArrayList<ClassDef> findClasses(ArrayList<Token> tokens, int k) {

        ArrayList<ClassDef> classes = new ArrayList<>();

        if (tokens.get(k + 1).type != Token.Type.WORD) {
            crash(CLASS_MISSING_NAME);
        }

        if (tokens.get(k + 2).type != Token.Type.BEGIN_BLOCK) {
            crash(CLASS_MISSING_BEGIN_BLOCK);
        }

        for (int i = k + 2; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token.type == Token.Type.WORD && token.content.equals(KEYWORD_FUNCTION)) {
                findFunctions(tokens, i);
            }
        }

        ClassDef c = new ClassDef(tokens.get(k + 1).content);

        return classes;
    }

    public static void findFunctions(ArrayList<Token> tokens, int k) {

        ArrayList<FunctionDef> functions = new ArrayList<>();

        int index = findNextTokenTypeFrom(Token.Type.WORD, tokens, k);

    }

    public static int findNextTokenTypeFrom(Token.Type tokenType, ArrayList<Token> tokens, int k) {

        return 0;
    }


    public static void crash(String reason) {
        System.out.println("Parsing failed: " + reason);
        System.exit(0);
    }

    public static class ClassDef {

        String name;
        ArrayList<FunctionDef> functions;

        public ClassDef(String name) {
            this.name = name;
        }
    }

    public static class FunctionDef {

        String name;
    }
}
