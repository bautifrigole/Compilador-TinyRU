package compiler.lexical_analyzer.reader;

/**
 * @author Bautista Frigolé
 * Clase encargada de leer caracteres desde un String fuente.
 */
public class StringReader extends Reader{
    private String inputContent;

    /**
     * @author Bautista Frigolé
     * Constructor de la clase.
     * @param inputContent String a procesar como fuente
     */
    public StringReader(String inputContent) {
        this.inputContent = inputContent;
    }

    @Override
    public void nextChar() {
        if (getCurrentColumn() < inputContent.length()) {
            incrementCurrentColumnByOne();
        }
    }

    @Override
    public Character getCurrentChar() {
        if (getCurrentColumn() < inputContent.length()) {
            return inputContent.charAt(getCurrentColumn());
        } else {
            return null;
        }
    }
}
