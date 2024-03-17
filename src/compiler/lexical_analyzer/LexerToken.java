package compiler.lexical_analyzer;

/**
 * @author Bautista Frigolé y Francisco Devaux
 * Clase encargada de almacenar la información de un token: su ID, su lexema y su ubicación en el código fuente.
 */
public class LexerToken {
    /**
     * {@link TokenID} del token.
     */
    private TokenID tokenID;
    /**
     * Lexema del token.
     */
    private String lexeme;
    /**
     * Número de línea en el código fuente del token.
     */
    private int line;
    /**
     * Número de columna en el código fuente del token.
     */
    private int column;

    /**
     * @author Bautista Frigolé
     * Constructor de la clase.
     */
    public LexerToken(TokenID tokenID, String lexeme, int line, int column) {
        this.tokenID = tokenID;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    /**
     * @author Bautista Frigolé
     * Getter de {@link LexerToken#tokenID}.
     */
    public TokenID getTokenID(){
        return tokenID;
    }

    /**
     * @author Bautista Frigolé
     * Getter de {@link LexerToken#lexeme}.
     */
    public String getLexemeString() {
        return lexeme;
    }

    /**
     * @author Bautista Frigolé
     * Getter de {@link LexerToken#line}.
     */
    public int getLine() {
        return line;
    }

    /**
     * @author Bautista Frigolé
     * Getter de {@link LexerToken#column}.
     */
    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "| " + tokenID.name() + " | " + lexeme + " | LINEA "
                + line + " (COLUMNA " + column + ") |\n";
    }
}
