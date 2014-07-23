import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String codesample = "print \'hello world\' 2.12 5.4";
        ArrayList<Token> tokens = Lexer.lex(codesample);
        System.out.println(Arrays.toString(tokens.toArray()));
    }
}
