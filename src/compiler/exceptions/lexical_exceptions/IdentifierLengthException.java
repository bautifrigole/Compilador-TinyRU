package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando un identificador excede su longitud máxima.
 * @author Francisco Devaux
 */
public class IdentifierLengthException extends LexicalException {
    public IdentifierLengthException(int line, int column) {
        super(line, column, "TAMAÑO DE IDENTIFICADOR EXCEDE LA LONGITUD MAXIMA");
    }
}
