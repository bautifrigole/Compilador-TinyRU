package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando se recibe un carácter inesperado en la formación de un literal.
 * Ejemplo: 9898923a
 * @author Bautista Frigolé
 */
public class UnexpectedCharacterException extends LexicalException {
    public UnexpectedCharacterException(int line, int column, String lexeme, Character unexpectedCh) {
        super(line, column, "CARACTER INESPERADO: \"" + unexpectedCh + "\", EN \"" + lexeme + "\"");
    }
}
