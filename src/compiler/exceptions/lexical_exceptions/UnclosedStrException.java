package compiler.exceptions.lexical_exceptions;

public class UnclosedStrException extends LexicalException {
    public UnclosedStrException(int line, int column) {
        super(line, column, "TIPO STR MAL FORMADO");
    }
}
