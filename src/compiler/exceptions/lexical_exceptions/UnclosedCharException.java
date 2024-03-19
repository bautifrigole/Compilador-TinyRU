package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando en un literal de tipo Character no se cerraron comillas simples.
 * Ejemplo: 'a
 * @author Francisco Devaux
 */
public class UnclosedCharException extends LexicalException {
    public UnclosedCharException(int line, int column) {
        super(line, column, "FALTA CERRAR COMILLAS DE CARACTER");
    }
}
