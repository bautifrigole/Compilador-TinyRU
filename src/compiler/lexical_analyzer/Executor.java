package compiler.lexical_analyzer;

import compiler.lexical_analyzer.reader.FileReader;
import java.io.File;

/**
 * Clase encargada de ejecutar llamados a Lexer para que éste busque el siguiente token en su fuente.
 */
public class Executor {

    /**
     * Método main.
     * @param args de main
     */
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
                System.out.print(e.toString());
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