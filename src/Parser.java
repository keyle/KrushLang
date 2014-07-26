import java.util.ArrayList;

public class Parser {

    public static final String KEYWORD_CLASS = "class";
    public static final String KEYWORD_FUNCTION = "def";

    public static final String CLASS_MISSING_NAME = "Invalid Class Definition, <class Name> missing 'name'";
    public static final String CLASS_MISSING_BEGIN_BLOCK = "Missing Class Begin Block, '{' after class name";

    public static ArrayList<Token> syntaxicAnalysis(ArrayList<Token> tokenList) {

        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 1; i < tokenList.size() - 1; i++) {

            Token token = tokenList.get(i);

            if (token.type == Token.Type.WORD && token.content.equals(KEYWORD_CLASS)) {
                findClasses(tokenList, i);
            }

//            tokens.add(tokenList.get(i));
        }

        return tokens;
    }

    public static ArrayList<ClassDef> findClasses(ArrayList<Token> tokenList, int k) {

        ArrayList<ClassDef> classes = new ArrayList<ClassDef>();

        if (tokenList.get(k + 1).type != Token.Type.WORD) {
            crash(CLASS_MISSING_NAME);
        }

        if (tokenList.get(k + 2).type != Token.Type.BEGIN_BLOCK) {
            crash(CLASS_MISSING_BEGIN_BLOCK);
        }

        for (int i = k + 2; i < tokenList.size(); i++) {
            Token token = tokenList.get(i);

            if (token.type == Token.Type.WORD && token.content.equals(KEYWORD_FUNCTION)) {
                findFunctions(tokenList, i);
            }
        }

        ClassDef c = new ClassDef(tokenList.get(k + 1).content);

        return classes;
    }

    public static void findFunctions(ArrayList<Token> tokenList, int k) {


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
