package compiler.lexical_analyzer.reader;

/**
 * Clase encargada de leer caracteres desde una fuente.
 */
public abstract class Reader {
    private int currentLine = 0;
    private int currentColumn = 0;

    /**
     * Devuelve el número de la línea que se está procesando.
     * @return int con el número de la línea actual
     */
    public int getCurrentLine(){
        return currentLine;
    }

    /**
     * Incrementa el número de la línea actual en uno.
     */
    protected void incrementCurrentLineByOne(){
        currentLine++;
    }

    /**
     * Devuelve el número de la columna en la línea que se está procesando.
     * @return int con el número de la columna actual
     */
    public int getCurrentColumn(){
        return currentColumn;
    }

    /**
     * @author Bautista Frigolé
     * Actualiza el valor del número de la columna a 0.
     */
    public void resetCurrentColumn(){
        this.currentColumn = 0;
    }

    /**
     * Incrementa el número de la columna actual en uno.
     */
    protected void incrementCurrentColumnByOne(){
        currentColumn++;
    }

    /**
     * Avanza hacia el siguiente carácter en la fuente si es posible.
     */
    public abstract void nextChar();

    /**
     * Devuelve el carácter que se está procesando.
     * @return Character con el carácter actual
     */
    public abstract Character getCurrentChar();
}
