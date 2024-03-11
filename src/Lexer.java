import reader.Reader;

import java.util.Arrays;
import java.util.List;

public class Lexer {
    private Reader reader;
    private String currentStrToken;
    private List<Character> separators = Arrays.asList(new Character[]{' ', '\n', '\t', '\r', '\f', '\b', '\0',
        '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
        '&', '*', '-', '+', '=', '|', '<', '>', '/', '\\', '!', '"', '\'', '`'});

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public LexerToken getNextToken() {
        StringBuilder currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();
        int startingColumn = reader.getCurrentColumn();

        if(separators.contains(ch)){
            reader.nextChar();
            return new LexerToken(TokenID.NONE, ch.toString(),
                    reader.getCurrentLine(), startingColumn);
        }

        while (!separators.contains(ch)) {
            reader.nextChar();
            currentToken.append(ch);
            ch = reader.getCurrentChar();
        }

        return new LexerToken(TokenID.NONE, currentToken.toString(),
                reader.getCurrentLine(), startingColumn);
    }
}
