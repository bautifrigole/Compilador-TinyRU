package reader;

/**
 * Clase encargada de leer caracteres desde una fuente.
 */
public abstract class Reader {
    private String inputContent;
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
     * Actualiza el valor del número de la línea que se está procesando.
     * @param currentLine int con el número de la línea actual
     */
    public void setCurrentLine(int currentLine){
        this.currentLine = currentLine;
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
     * Actualiza el valor del número de la columna en la línea que se está procesando.
     * @param currentColumn int con el número de la columna actual
     */
    public void setCurrentColumn(int currentColumn){
        this.currentColumn = currentColumn;
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

    public void setInputContent(String inputContent){
        this.inputContent = inputContent;
    }
}
