package reader;

import java.io.File;
import java.util.Scanner;

public class FileReader extends Reader {
    private Scanner scanner;
    private String line = "";

    public FileReader(String path) {
        try {
            File file = new File(path);
            scanner = new Scanner(file);
            this.line = scanner.nextLine();
        }
        catch (Exception e) {
            System.out.println("File not found");
        }
    }

    @Override
    public void nextChar() {
        if (getCurrentColumn() < line.length()) {
            incrementCurrentColumnByOne();
        } else {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
                incrementCurrentLineByOne();
                setCurrentColumn(0);
            }
        }
    }

    @Override
    public Character getCurrentChar() {
        if (getCurrentColumn() < line.length()) {
            return line.charAt(getCurrentColumn());
        } else {
            if (scanner.hasNextLine()) {
                // TODO: '\r\n' in Windows
                return '\n';
            } else {
                return null;
            }
        }
    }
}
