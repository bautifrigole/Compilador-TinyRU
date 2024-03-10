import reader.Reader;

public class Lexer {
    private String currentStrToken;
    private final Character[] separators = {' ', '\n', '\t', '\r', '\f', '\b', '\0',
        '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
        '&', '*', '-', '+', '=', '|', '<', '>', '/', '\\', '!', '"', '\'', '`'};

    public Lexer(Reader reader) {
        Character ch = reader.getNextChar();
        while (ch != null) {
            System.out.println(ch);
            ch = reader.getNextChar();
        }
    }

    public LexerToken getNextToken() {
        return null;
    }
}
