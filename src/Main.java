import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String codesample = "print 'hello world'";

    public static void main(String[] args) {
        ArrayList<Token> tokens = lex(codesample);
        System.out.println(Arrays.toString(tokens.toArray()));
    }

    public static ArrayList<Token> lex(String str) {
        int pos = 0;
        int line = 1;

        ArrayList<Token> tokens = new ArrayList<>();
        char[] code = str.toCharArray();
        int total = code.length;

        while (pos < total) {
            char c = str.charAt(pos);
            System.out.println(c);

            if (Character.isLetter(c)) {
                String tempword = "";
                int temp = pos;

                while (Character.isLetterOrDigit(code[temp])) {
                    tempword += code[temp];
                    temp++;
                }

                tokens.add(new Token(Tok.AWORD, tempword));
                pos += temp - pos;

            } else if (Character.isSpaceChar(c)) {
                pos++;
            } else {
                pos++;
            }
        }

        return tokens;
    }

    public enum Tok {
        AWORD, STRING_LITERAL, DIGIT_LITERAL, SYMBOL
    }

    public static class Token {

        Tok type;
        String content;

        public Token(Tok type, String content) {
            this.type = type;
            this.content = content;
        }

        @Override
        public String toString() {
            return "[" + this.type.toString() + " " + this.content + "]";
        }
    }
}
