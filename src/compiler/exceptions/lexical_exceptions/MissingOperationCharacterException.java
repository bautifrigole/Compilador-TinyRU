package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando falta un caracter en un operador de doble caracter.
 * Ejemplo: & (Debe ser &&)
 * @author Bautista Frigolé
 */
public class MissingOperationCharacterException extends LexicalException {
    public MissingOperationCharacterException(int line, int column, Character lexeme, Character expectedCh) {
        super(line, column, "CARACTER DE OPERACION FALTANTE EN \"" + lexeme + "\", SE ESPERABA \""
                + lexeme + expectedCh + "\"");
    }
}
