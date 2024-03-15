package compiler.lexical_analyzer;

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


    public TokenID getTokenID(){
        return tokenID;
    }

    public String getLexemeString() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "| " + tokenID.name() + " | " + lexeme + " | LINEA "
                + line + " (COLUMNA " + column + ") |\n";
    }
}
