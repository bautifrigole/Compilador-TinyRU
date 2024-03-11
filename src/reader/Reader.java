package reader;

public abstract class Reader {
    private String inputContent;
    private int currentLine = 0;
    private int currentColumn = 0;

    public int getCurrentLine(){
        return currentLine;
    }

    public void setCurrentLine(int currentLine){
        this.currentLine = currentLine;
    }

    protected void incrementCurrentLineByOne(){
        currentLine++;
    }

    public int getCurrentColumn(){
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn){
        this.currentColumn = currentColumn;
    }

    protected void incrementCurrentColumnByOne(){
        currentColumn++;
    }

    public abstract void nextChar();

    public abstract Character getCurrentChar();

    public void setInputContent(String inputContent){
        this.inputContent = inputContent;
    }
}
