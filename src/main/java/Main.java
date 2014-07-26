import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String codeString = null;

        try {
            Path path = Paths.get("test.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            codeString = "";
            codeString = joinLines(lines);
        } catch (IOException ignored) {
            System.out.println("Oh poop! File not found, or encoding issues");
        }

        if (codeString == null) {
            System.out.println("Could not parse file");
            System.exit(0);
        }

        ArrayList<Token> rawTokens = Lexer.lex(codeString);

        System.out.println(Arrays.toString(rawTokens.toArray()));

        Parser.syntaxicAnalysis(rawTokens);
    }

    public static String joinLines(List<String> lines) {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line).append(String.format("%n"));
        }
        return builder.toString();
    }
}

