import reader.FileReader;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                File file = new File(args[0]);
                Lexer lexer = new Lexer(new FileReader(file.getPath()));
                LexerToken lexerToken = lexer.getNextToken();

                while(lexerToken != null) {
                    System.out.println(lexerToken);
                    lexerToken = lexer.getNextToken();
                }
            }
            catch (Exception e) {
                // TODO: Throw exception
                System.out.println("File not found");
                System.out.print(e);
            }

            if (args.length > 1) {
                // TODO: Implement output file
                File outputFile = new File(args[1]);
            }
        }
        else {
            // TODO: Throw exception
            System.out.println("No file provided");
        }

        // TODO: Save result in outputFile if it isn't null
    }
}