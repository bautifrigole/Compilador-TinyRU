package compiler.lexical_analyzer;

/**
 * Clase encargada de almacenar la información de un token: su ID, su lexema y su ubicación en el código fuente.
 * @author Bautista Frigolé y Francisco Devaux
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
     * Constructor de la clase.
     * @author Bautista Frigolé
     */
    public LexerToken(TokenID tokenID, String lexeme, int line, int column) {
        this.tokenID = tokenID;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    /**
     * Getter de {@link LexerToken#tokenID}.
     * @author Bautista Frigolé
     * @return TokenID.
     */
    public TokenID getTokenID(){
        return tokenID;
    }

    /**
     * Getter de {@link LexerToken#lexeme}.
     * @author Bautista Frigolé
     * @return String lexema.
     */
    public String getLexemeString() {
        return lexeme;
    }

    /**
     * Getter de {@link LexerToken#line}.
     * @author Bautista Frigolé
     * @return int número de línea.
     */
    public int getLine() {
        return line;
    }

    /**
     * Getter de {@link LexerToken#column}.
     * @author Bautista Frigolé
     * @return int número de columna.
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
