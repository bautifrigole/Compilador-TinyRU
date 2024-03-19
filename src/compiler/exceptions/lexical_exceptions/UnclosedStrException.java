package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando en un literal de tipo String no se cerraron comillas dobles.
 * Ejemplo: "Hola
 * @author Bautista Frigolé
 */
public class UnclosedStrException extends LexicalException {
    public UnclosedStrException(int line, int column) {
        super(line, column, "FALTA CERRAR COMILLAS DE STRING");
    }
}
