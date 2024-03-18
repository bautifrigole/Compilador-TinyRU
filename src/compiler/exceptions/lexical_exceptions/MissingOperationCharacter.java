package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando falta un carácter de operadoración en un lexema.
 * @author Bautista Frigolé
 */
public class MissingOperationCharacter extends LexicalException {
    public MissingOperationCharacter(int line, int column, Character lexeme, Character expectedCh) {
        super(line, column, "CARACTER DE OPERACION FALTANTE EN \"" + lexeme + "\", SE ESPERABA \""
                + lexeme + expectedCh + "\"");
    }
}
