package compiler.lexical_analyzer;

/*
 * Clase encargada de almacenar la información de un token: su ID, su lexema y su ubicación en el código fuente.
 */
public class LexerToken {
    private TokenID tokenID;
    private String lexemeString;
    private Character lexemeChar;
    private int line;
    private int column;

    public LexerToken(TokenID tokenID, String lexeme, int line, int column) {
        this.tokenID = tokenID;
        this.lexemeString = lexeme;
        this.line = line;
        this.column = column;
    }
    public LexerToken(TokenID tokenID, Character lexeme, int line, int column) {
        this.tokenID = tokenID;
        this.lexemeChar = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenID getTokenID(){
        return tokenID;
    }

    public String getLexemeString() {
        return lexemeString;
    }
    public Character getLexemeChar(){return lexemeChar;}

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        String lexeme;
        if (lexemeString == null){
            lexeme = lexemeChar.toString();
        }
        else{
            lexeme = lexemeString;
        }
        return "| " + tokenID.name() + " | " + lexeme + " | LINEA "
                + line + " (COLUMNA " + column + ") |\n";
    }
}
