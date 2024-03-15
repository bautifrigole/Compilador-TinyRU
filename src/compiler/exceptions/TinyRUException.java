package compiler.exceptions;

public abstract class TinyRUException extends Throwable {
    public TinyRUException(int line, int column, String description, ErrorType errorType) {
        super("\nERROR: " + errorType + " \n| NUMERO DE LINEA: | NUMERO DE COLUMNA: | DESCRIPCION: | \n"
                + "|LINEA "+ line + " | COLUMNA " + column + " | " + description + "|");
    }
}
