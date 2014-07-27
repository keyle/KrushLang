import java.util.ArrayList;
import java.util.Arrays;

public class Definition {

    public static final ArrayList<Token> aFunction = new ArrayList<>(Arrays.asList(
            new Token(Token.Type.FUNC_KEYWORD, null),
            new Token(Token.Type.WORD, null),
            new Token(Token.Type.BEGIN_BLOCK, null),
            new Token(), //any
            new Token(Token.Type.END_BLOCK, null)
    ));
}
