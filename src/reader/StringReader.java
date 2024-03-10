package reader;

public class StringReader extends Reader{
    public StringReader(String string) {
        setInputContent(string);
    }

    @Override
    public Character getNextChar() {
        return null;
    }
}
