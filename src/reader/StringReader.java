package reader;

public class StringReader extends Reader{
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
