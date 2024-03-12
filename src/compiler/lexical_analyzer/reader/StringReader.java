package compiler.lexical_analyzer.reader;

/*
 * Clase encargada de leer caracteres desde un String fuente.
 */
public class StringReader extends Reader{
    /**
     * Constructor de la clase.
     * @param string String a procesar como fuente
     */
    public StringReader(String string) {
        setInputContent(string);
    }

    @Override
    public void nextChar() {

    }

    @Override
    public Character getCurrentChar() {
        return null;
    }
}
