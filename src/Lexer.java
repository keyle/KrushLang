import java.util.ArrayList;

public class Lexer {

    final static char RETURNCHAR = String.format("%n").charAt(0);
    final static char DASH = '-';
    final static char QUOTE = '\'';
    final static char DQUOTE = '"';

    public static ArrayList<Token> lex(String rawCode) {
        int pos = 0;
        ArrayList<Token> tokens = new ArrayList<>();
        rawCode = "\r\n" + rawCode + " \r\n"; // pad
        char[] code = rawCode.toCharArray();
        int total = code.length;

        System.out.println(rawCode);

        while (pos < total) {
            char c = rawCode.charAt(pos);

            switch (Character.getType(c)) {

                case Character.UPPERCASE_LETTER:
                case Character.LOWERCASE_LETTER:
                    String word = lexWord(pos, code);
                    tokens.add(new Token(Token.Type.WORD, word));
                    pos = pos + word.length();
                    break;

                case Character.DECIMAL_DIGIT_NUMBER:
                    String digits = LexDigit(pos, code);
                    tokens.add(new Token(Token.Type.DIGIT_LITERAL, digits));
                    pos = pos + digits.length();
                    break;

                case Character.OTHER_PUNCTUATION:
                    if (c == QUOTE || c == DQUOTE) {
                        String literal = LexStringLiteral(pos, code, c);
                        tokens.add(new Token(Token.Type.STRING_LITERAL, literal));
                        pos = pos + literal.length() + 2;
                    } else if (c == ':') {
                        if (code[pos + 1] == ':') {
                            tokens.add(new Token(Token.Type.BEGIN_BLOCK, null));
                            pos += 2;
                        }
                    } else if (c == '.') {
                        tokens.add(new Token(Token.Type.DOT, null));
                        pos++;
                    } else {
                        pos++;
                    }
                    break;

                default:
                    if (c == RETURNCHAR) {
                        tokens.add(new Token(Token.Type.NEWLINE, null));
                        pos++;
                    } else if (c == DASH) {
                        pos += lexLineComment(pos, code);
                    }
                    pos++;
                    break;
            }
        }

        return tokens;
    }

    private static String LexStringLiteral(int k, char[] chars, char c) {
        String literal = "";
        k++;
        while (chars[k] != c) {
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

    public static int lexLineComment(int k, char[] chars) {
        String comment = "";
        while (chars[k] != RETURNCHAR) {
            comment += chars[k];
            k++;
        }
        return comment.length();
    }

}
