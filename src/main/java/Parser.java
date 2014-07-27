import java.util.ArrayList;

public class Parser {

    public static final String CLASS_MISSING_NAME = "Invalid Class Definition, <class Name> missing 'name'";
    public static final String CLASS_MISSING_BEGIN_BLOCK = "Missing Class Begin Block, '{' after class name";
    public static final String CLASS_UNKNOWN_WORD = "Class should contain 'def' and not unknown words";
    public static final String FUNC_MISSING_NAME = "Invalid Definition, <def Name> missing 'name'";

    public static void syntaxicAnalysis(ArrayList<Token> tokens) {

        for (int i = 1; i < tokens.size() - 1; i++) {

            Token token = tokens.get(i);

            if (token.type == Token.Type.CLASS_KEYWORD) {
                ClassDef classDef = findClasses(tokens, i);
                System.out.println(classDef);
            }

//            tokens.add(tokens.get(i));
        }
    }

    public static ClassDef findClasses(ArrayList<Token> tokens, int k) {


        if (tokens.get(k + 1).type != Token.Type.WORD) {
            crash(CLASS_MISSING_NAME);
        }

        if (tokens.get(k + 2).type != Token.Type.BEGIN_BLOCK) {
            crash(CLASS_MISSING_BEGIN_BLOCK);
        }

        ClassDef c = new ClassDef(tokens.get(k + 1).content);

        for (int i = k + 2; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token.type == Token.Type.FUNC_KEYWORD) {
                c.functions = findFunctions(tokens, i);
            }
        }


        return c;
    }

    public static ArrayList<FunctionDef> findFunctions(ArrayList<Token> tokens, int k) {

        ArrayList<FunctionDef> functions = new ArrayList<>();

        int m = findNextTokenTypeFrom(Token.Type.WORD, tokens, k);

        if (m == -1) {
            crash(CLASS_UNKNOWN_WORD);
        } else if (tokens.get(m).type != Token.Type.WORD) {
            crash(FUNC_MISSING_NAME);
        } else {
            String funcname = tokens.get(m).content;
            FunctionDef func = new FunctionDef(funcname);

            for (int i = k + 2; i < tokens.size(); i++) {
                Token token = tokens.get(i);

                if (token.type == Token.Type.VAR_KEYWORD) {
                    int z = findNextTokenTypeFrom(Token.Type.END_STATEMENT, tokens, i);
                    StatementDef statem = new StatementDef();
                    statem.tokens = new ArrayList<>(tokens.subList(i, z));
                    func.statements.add(statem);
                }
            }

            functions.add(func);
        }

        return functions;
    }

    public static int findNextTokenTypeFrom(Token.Type tokenType, ArrayList<Token> tokens, int k) {

        for (int i = k; i < tokens.size(); i++) {
            if (tokens.get(i).type == tokenType) {
                return i;
            }
        }

        return -1;
    }


    public static void crash(String reason) {
        System.out.println("Parsing failed: " + reason);
        System.exit(0);
    }

    public static class ClassDef {

        String name;
        ArrayList<FunctionDef> functions = new ArrayList<>();

        public ClassDef(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "\n{c " + this.name + "} \n\t" + functions.toString();
        }
    }

    public static class FunctionDef {

        String name;
        ArrayList<StatementDef> statements = new ArrayList<>();

        public FunctionDef(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "<f " + this.name + "> \n\t" + statements.toString();
        }
    }

    public static class StatementDef {

        ArrayList<Token> tokens;
        String statement;

        @Override
        public String toString() {
            return "(s " + this.tokens.toString() + ")";
        }
    }
}
