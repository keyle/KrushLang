import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String codesample = "print \"hello world\" 2.12 5.4";

    public static void main(String[] args) {
        ArrayList<Token> tokens = lex(codesample);
        System.out.println(Arrays.toString(tokens.toArray()));
    }

    public static ArrayList<Token> lex(String rawCode) {
        int pos = 0;
        ArrayList<Token> tokens = new ArrayList<>();
        rawCode += "\n"; // pad
        char[] code = rawCode.toCharArray();
        int total = code.length;

        while (pos < total) {
            char c = rawCode.charAt(pos);
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

                case Character.OTHER_PUNCTUATION:
                    if (c == '"') {
                        String literal = LexStringLiteral(pos, code);
                        tokens.add(new Token(Tok.STRING_LITERAL, literal));
                        pos = pos + literal.length();
                    } else {
                        pos++;
                    }
                    break;

                default:
                    pos++;
                    break;
            }
        }

        return tokens;
    }

    private static String LexStringLiteral(int k, char[] chars) {
        String literal = "";
        while (chars[k] == '"') {
            literal += chars[k];
            k++;
        }
        return literal;
    }


    private static String LexDigit(int k, char[] chars) {
        String digits = "";
        while (Character.isDigit(chars[k]) || chars[k] == '.') {
            digits += chars[k];
            k++;
        }
        return digits;
    }

    public static String lexWord(int k, char[] chars) {
        String word = "";
        while (Character.isLetterOrDigit(chars[k])) {
            word += chars[k];
            k++;
        }
        return word;
    }

    public enum Tok {
        WORD, STRING_LITERAL, DIGIT_LITERAL
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
