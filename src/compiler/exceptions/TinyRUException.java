package compiler.exceptions;

/**
 * @author Bautista Frigolé y Francisco Devaux
 * Clase base de las excepciones en el TinyRU.
 */
public abstract class TinyRUException extends Exception {
    /**
     * @author Bautista Frigolé
     * Constructor de la clase, crea la estructura del mensaje a mostrar en pantalla.
     */
    public TinyRUException(int line, int column, String description, ErrorType errorType) {
        super("\nERROR: " + errorType + " \n| NUMERO DE LINEA: | NUMERO DE COLUMNA: | DESCRIPCION: | \n"
                + "|LINEA " + line + " | COLUMNA " + column + " | " + description + "|");
    }
}
