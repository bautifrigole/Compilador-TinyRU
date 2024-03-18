package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando un lexema de tipo String excede su longitud máxima.
 * @author Francisco Devaux
 */
public class StringSizeException extends LexicalException {
    public StringSizeException(int line, int column) {
        super(line, column, "TAMAÑO DE STRING EXCEDE LA LONGITUD MAXIMA");
    }
}
