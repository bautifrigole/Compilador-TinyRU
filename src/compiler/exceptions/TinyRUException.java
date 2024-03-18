package compiler.exceptions;

/**
 * Clase base de las excepciones en TinyRU.
 * @author Bautista Frigolé y Francisco Devaux
 */
public abstract class TinyRUException extends Exception {
    /**
     * Constructor de la clase, crea la estructura del mensaje a mostrar en pantalla.
     * @author Bautista Frigolé
     */
    public TinyRUException(int line, int column, String description, ErrorType errorType) {
        super("\nERROR: " + errorType + " \n| NUMERO DE LINEA: | NUMERO DE COLUMNA: | DESCRIPCION: | \n"
                + "|LINEA " + line + " | COLUMNA " + column + " | " + description + "|");
    }
}
