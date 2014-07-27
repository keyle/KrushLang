public class Token {

    Type type;
    String content;

    public Token(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public Token() {

    }

    @Override
    public String toString() {

        if (this.content != null)
            return "" + this.type.toString() + " '" + this.content + "'";
        else
            return "" + this.type.toString() + "";
    }

    public static enum Type {
        WORD, STRING_LITERAL, DIGIT_LITERAL, NEWLINE, DOT, END_BLOCK, BEGIN_BLOCK, CLASS_KEYWORD, FUNC_KEYWORD, VAR_KEYWORD,
        BEGIN_STATEMENT, END_STATEMENT
    }
}
