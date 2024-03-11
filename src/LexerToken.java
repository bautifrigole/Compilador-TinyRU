public class LexerToken {
    private TokenID tokenID;
    private String lexeme;
    private int line;
    private int column;

    public LexerToken(TokenID tokenID, String lexeme, int line, int column) {
        this.tokenID = tokenID;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }
}
