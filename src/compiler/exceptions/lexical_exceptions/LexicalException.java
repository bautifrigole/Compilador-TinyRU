package compiler.exceptions.lexical_exceptions;

import compiler.exceptions.ErrorType;
import compiler.exceptions.TinyRUException;

/**
 * Clase base de las excepciones de tipo léxicas.
 * @author Bautista Frigolé
 */
public abstract class LexicalException extends TinyRUException {
    public LexicalException(int line, int column, String description) {
        super(line, column, description, ErrorType.LEXICO);
    }
}
