package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando un literal de tipo Int excede su valor máximo permitido.
 * @author Francisco Devaux
 */
public class IntSizeException extends LexicalException {
    public IntSizeException(int line, int column) {
        super(line, column, "EL NUMERO ENTERO EXCEDE EL VALOR MAXIMO");
    }
}
