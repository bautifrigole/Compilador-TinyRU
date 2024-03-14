package compiler.exceptions.lexical_exceptions;

public class EmptyCharException extends LexicalException {
    public EmptyCharException(int line, int column) {
        super(line, column, "LITERAL CARACTER VACIO");
    }
}
