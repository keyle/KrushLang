import java.util.ArrayList;

public class Parser2 {

    public static void parse(ArrayList<Token> tokens) {

        boolean inclass = false;
        boolean classopen = false;

        ClassDef c;

        for (Token token : tokens) {

            if (inclass) {
                if (!classopen) {
                    if (!(token.type == Token.Type.BEGIN_BLOCK)) {
                        crash("invalid class def");
                    } else {
                        classopen = true;
                    }
                }
            }

            if (token.type == Token.Type.CLASS_KEYWORD) {
                inclass = true;
            }
        }

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

        public FunctionDef(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "<f " + this.name + ">";
        }
    }

    public static class StatementDef {

        String statement;
    }
}
