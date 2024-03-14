package compiler.exceptions.lexical_exceptions;

public class UnclosedCharException extends LexicalException {
    public UnclosedCharException(int line, int column) {
        super(line, column, "FALTA CERRAR COMILLAS DE CARACTER");
    }
}
