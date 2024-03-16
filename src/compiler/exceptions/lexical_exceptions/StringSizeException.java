package compiler.exceptions.lexical_exceptions;

public class StringSizeException extends LexicalException {
    public StringSizeException(int line, int column) {
        super(line, column, "TAMAÑO DE STRING EXCEDE LA LONGITUD MAXIMA");
    }
}
