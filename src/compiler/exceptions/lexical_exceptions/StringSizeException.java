package compiler.exceptions.lexical_exceptions;

/**
 * @author Francisco Devaux
 * Excepción cuando un lexema de tipo String excede su longitud máxima.
 */
public class StringSizeException extends LexicalException {
    public StringSizeException(int line, int column) {
        super(line, column, "TAMAÑO DE STRING EXCEDE LA LONGITUD MAXIMA");
    }
}
