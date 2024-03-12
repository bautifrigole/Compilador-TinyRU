/*
 * Clase encargada de almacenar la información de un token: su ID, su lexema y su ubicación en el código fuente.
 */
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

    @Override
    public String toString() {
        return tokenID.name() + ", " + lexeme + ", Line: " + line + ", Column: " + column + "\n";
    }
}
