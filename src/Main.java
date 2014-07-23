import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String codesample = "print 'hello world' 2.12 5.4";

    public static void main(String[] args) {
        ArrayList<Token> tokens = lex(codesample);
        System.out.println(Arrays.toString(tokens.toArray()));
    }

    public static ArrayList<Token> lex(String str) {
        int pos = 0;
        ArrayList<Token> tokens = new ArrayList<>();
        char[] code = str.toCharArray();
        int total = code.length;

        str = str + "\n"; // pad

        while (pos < total) {
            char c = str.charAt(pos);
            System.out.println(c);

            switch (Character.getType(c)) {

                case Character.UPPERCASE_LETTER:
                case Character.LOWERCASE_LETTER:
                    String word = lexWord(pos, code);
                    tokens.add(new Token(Tok.WORD, word));
                    pos = pos + word.length();
                    break;

                case Character.DECIMAL_DIGIT_NUMBER:
                    String digits = LexDigit(pos, code);
                    tokens.add(new Token(Tok.DIGIT_LITERAL, digits));
                    pos = pos + digits.length();
                    break;

                case Character.SPACE_SEPARATOR:
                case Character.LINE_SEPARATOR:
                case Character.PARAGRAPH_SEPARATOR:
                    pos++;
                    break;

                default:
                    pos++;
                    break;
            }
        }

        return tokens;
    }

    private static String LexDigit(int pos, char[] code) {
        String digits = "";
        while (Character.isDigit(code[pos]) || code[pos] == '.') {
            digits += code[pos];
            pos++;
        }
        return digits;
    }

    public static String lexWord(int pos, char[] code) {
        String word = "";

        while (Character.isLetterOrDigit(code[pos])) {
            word += code[pos];
            pos++;
        }
        return word;
    }

    public enum Tok {
        WORD, STRING_LITERAL, DIGIT_LITERAL, SYMBOL
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
