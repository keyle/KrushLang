import java.util.ArrayList;

public class Parser {

    public static final String KEYWORD_CLASS = "class";
    public static final String KEYWORD_FUNCTION = "def";

    public static final String CLASS_DEF_FAILED = "Invalid Class Definition, <class Name> missing 'name'";

    public static ArrayList<Token> syntaxicAnalysis(ArrayList<Token> tokenList) {

        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 1; i < tokenList.size() - 1; i++) {

            Token prevToken = tokenList.get(i - 1);
            Token token = tokenList.get(i);
            Token nextToken = tokenList.get(i + 1);

            switch (token.type) {

                case WORD:

                    switch (token.content) {
                        case KEYWORD_CLASS:
                            parseClass(tokenList, i);
                            break;
                        case KEYWORD_FUNCTION:
                            // method
                            break;
                    }

                    break;

                default:
            }

            tokens.add(tokenList.get(i));

        }

        return tokens;
    }

    public static void parseClass(ArrayList<Token> tokenList, int k) {

        if (tokenList.get(k + 1).type != Token.Type.WORD) {
            crash(CLASS_DEF_FAILED);
        }

    }

    public static void crash(String reason) {
        System.out.println("Parsing failed: " + reason);
        System.exit(0);
    }

    public class ClassDef {

        String name;
        ArrayList<FunctionDef> functions;
    }

    public class FunctionDef {

    }
}
