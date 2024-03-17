package compiler.exceptions.lexical_exceptions;

/**
 * @author Bautista Frigolé
 * Excepción cuando en un literal de tipo String no se cerraron comillas dobles.
 */
public class UnclosedStrException extends LexicalException {
    public UnclosedStrException(int line, int column) {
        super(line, column, "FALTA CERRAR COMILLAS DE STRING");
    }
}
