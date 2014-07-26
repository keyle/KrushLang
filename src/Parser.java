import java.util.ArrayList;

public class Parser {

    public static ArrayList<Token> syntaxicAnalysis(ArrayList<Token> _tokens) {

        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 1; i < _tokens.size() - 1; i++) {

            Token prevToken = _tokens.get(i - 1);
            Token token = _tokens.get(i);
            Token nextToken = _tokens.get(i + 1);

            switch (token.type) {

                case WORD:

                    break;

                default:
            }

            tokens.add(_tokens.get(i));

        }

        return tokens;
    }

//    public static void parseWord(Token token) {
//
//        switch (token.content) {
//
//
//        }
//    }

}
