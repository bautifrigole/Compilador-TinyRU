package reader;

import java.io.File;
import java.util.Scanner;

public class FileReader extends Reader {
    private Scanner scanner;
    private int currentIndex = 0;
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
    public Character getNextChar() {

        if (currentIndex < line.length()) {
            Character nextChar = line.charAt(currentIndex);
            currentIndex++;
            return nextChar;
        } else {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
                currentIndex = 0;
            } else {
                return null;
            }
        }


        return ' ';
    }
}
