public class Main {

    public static String code = "abcdefgh";

    public static void main(String[] args) {
        lex(code);
    }

    public static void lex(String str) {
        int pos = 0;
        int line = 1;
    }

    public enum Tok {
        AWORD, STRING, DIGIT, SYMBOL
    }

    public class Token {
        Tok type;
        String content;
    }
}
