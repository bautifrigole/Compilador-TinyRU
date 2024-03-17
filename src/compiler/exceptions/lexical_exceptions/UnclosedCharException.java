package compiler.exceptions.lexical_exceptions;

/**
 * @author Francisco Devaux
 * Excepción cuando en un literal de tipo Character no se cerraron comillas simples.
 */
public class UnclosedCharException extends LexicalException {
    public UnclosedCharException(int line, int column) {
        super(line, column, "FALTA CERRAR COMILLAS DE CARACTER");
    }
}
