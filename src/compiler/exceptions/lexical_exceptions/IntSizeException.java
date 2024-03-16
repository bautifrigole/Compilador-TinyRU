package compiler.exceptions.lexical_exceptions;

public class IntSizeException extends LexicalException {
    public IntSizeException(int line, int column) {
        super(line, column, "EL NUMERO ENTERO EXCEDE EL VALOR MAXIMO");
    }
}
