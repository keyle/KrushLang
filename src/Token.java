public class Token {

    Type type;
    String content;

    public Token(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + this.type.toString() + " " + this.content + "]";
    }

    public static enum Type {
        WORD, STRING_LITERAL, DIGIT_LITERAL
    }
}
