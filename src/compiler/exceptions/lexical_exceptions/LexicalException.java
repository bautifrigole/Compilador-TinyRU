package compiler.exceptions.lexical_exceptions;

import compiler.exceptions.ErrorType;
import compiler.exceptions.TinyRUException;

public abstract class LexicalException extends TinyRUException {
    public LexicalException(int line, int column, String description) {
        super(line, column, description, ErrorType.LEXICO);
    }
}
