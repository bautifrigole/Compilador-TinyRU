package compiler.exceptions.lexical_exceptions;

/**
 * @author Bautista Frigolé
 * Excepción cuando un identificador contiene un carácter inválido.
 */
public class InvalidIdentifierException extends LexicalException {
    public InvalidIdentifierException(int line, int column, String lexeme, Character unexpectedCh) {
        super(line, column, "IDENTIFICADOR MAL FORMADO \"" + lexeme + "\", CARACTER INVALIDO: \"" + unexpectedCh + "\"");
    }
}
