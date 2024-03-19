package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando un identificador se encuentra formado de manera incorrecta.
 * Ejemplo: _hola
 * @author Bautista Frigolé
 */
public class InvalidIdentifierException extends LexicalException {
    public InvalidIdentifierException(int line, int column, String lexeme, Character unexpectedCh) {
        super(line, column, "IDENTIFICADOR MAL FORMADO \"" + lexeme + "\", CARACTER INVALIDO: \"" + unexpectedCh + "\"");
    }
}
