package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.reader.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar llamados a Lexer para que éste busque el siguiente token en su fuente.
 */
public class Executor {

    /**
     * Método main.
     * @param args de main
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                ArrayList<LexerToken> tokensList = getAllTokensFromPath(args[0]);

                if (args.length > 1) {
                    // TODO: Implement output file
                    File outputFile = new File(args[1]);
                } else {
                    printTokenTable(tokensList);
                }
            }
        }
        catch (LexicalException e) {
            System.out.print(e.toString());
        }
    }

    public static ArrayList<LexerToken> getAllTokensFromPath(String path) throws LexicalException {
        File file = new File(path);
        Lexer lexer = new Lexer(new FileReader(file.getPath()));
        return getAllTokens(lexer);
    }

    private static ArrayList<LexerToken> getAllTokens(Lexer lexer) throws LexicalException {
        LexerToken lexerToken = lexer.getNextToken();
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();

        while(lexerToken.getTokenID() != TokenID.TOKEN_EOF) {
            tokens.add(lexerToken);
            // TODO: ACORDARSE de borrar la línea de abajo
            System.out.println(lexerToken);
            lexerToken = lexer.getNextToken();
        }

        return tokens;
    }

    private static void printTokenTable(ArrayList<LexerToken> tokensList){
        System.out.println("CORRECTO: ANALISIS LEXICO\n" +
                "| TOKEN | LEXEMA | NUMERO DE LINEA (NUMERO DE COLUMNA) |");

        for(LexerToken token : tokensList) {
            System.out.println(token);
        }
    }
}