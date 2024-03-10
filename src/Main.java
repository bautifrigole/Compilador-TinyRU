import reader.FileReader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                File file = new File(args[0]);
            }
            catch (Exception e) {
                System.out.println("File not found");
            }

            if (args.length > 1) {
                // TODO: Implement output file
                File outputFile = new File(args[1]);
            }

        }
        else {
            System.out.println("No file provided");
        }

        File file = new File("src/test/tests/fibonacci.ru");
        Lexer lexer = new Lexer(new FileReader(file.getPath()));
        lexer.getNextToken();
    }
}