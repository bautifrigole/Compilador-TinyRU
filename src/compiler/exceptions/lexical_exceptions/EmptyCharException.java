package compiler.exceptions.lexical_exceptions;

/**
 * @author Francisco Devaux
 * Excepción cuando un literal caracter está vacío.
 */
public class EmptyCharException extends LexicalException {
    public EmptyCharException(int line, int column) {
        super(line, column, "LITERAL CARACTER VACIO");
    }
}
