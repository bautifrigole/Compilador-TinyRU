package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando un literal caracter está vacío.
 * Ejemplo: ''
 * @author Francisco Devaux
 */
public class EmptyCharException extends LexicalException {
    public EmptyCharException(int line, int column) {
        super(line, column, "LITERAL CARACTER VACIO");
    }
}
