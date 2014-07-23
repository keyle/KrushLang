import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    // :main --
    // print "hello world"
    // set bla 3
    // set bla "word"
    // :main.loop item --
    // print item
    // --
    // iter bla main.loop
    // --
    public static void main(String[] args) {
        String codesample = "print \'hello world\' 2.12 5.4";
        ArrayList<Token> tokens = Lexer.lex(codesample);
        System.out.println(Arrays.toString(tokens.toArray()));
    }
}
