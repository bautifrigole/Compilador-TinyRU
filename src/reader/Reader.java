package reader;

public abstract class Reader {
    private String inputContent;

    public abstract Character getNextChar();

    public void setInputContent(String inputContent){
        this.inputContent = inputContent;
    }
}
