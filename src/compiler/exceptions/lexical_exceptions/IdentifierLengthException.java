package compiler.exceptions.lexical_exceptions;

public class IdentifierLengthException extends LexicalException {
    public IdentifierLengthException(int line, int column) {
        super(line, column, "TAMAÑO DE IDENTIFICADOR EXCEDE LA LONGITUD MAXIMA");
    }
}
