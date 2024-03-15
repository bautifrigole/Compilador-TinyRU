package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.reader.FileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de ejecutar llamados a Lexer para que éste busque el siguiente token en su fuente.
 */
public class Executor {

    /**
     * @author Bautista Frigolé y Francisco Devaux
     * Método main.
     * @param args Argumentos de main.
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                ArrayList<LexerToken> tokensList = getAllTokensFromPath(args[0]);

                if (args.length > 1) {
                    File outputFile = new File(args[1]);
                    outputFile.createNewFile();
                    FileWriter writer = new FileWriter(args[1]);
                    writeTokenTable(writer, tokensList);
                }
                else {
                    printTokenTable(tokensList);
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    /**
     * @author Bautista Frigolé
     * Busca todos los tokens dentro del archivo especificado en la ruta.
     * @param path Ruta del archivo.
     * @return Lista de LexerToken.
     */
    public static ArrayList<LexerToken> getAllTokensFromPath(String path) throws LexicalException {
        File file = new File(path);
        Lexer lexer = new Lexer(new FileReader(file.getPath()));
        return getAllTokens(lexer);
    }

    /**
     * @author Bautista Frigolé
     * Busca todos los tokens utilizando el Lexer dado.
     * @param lexer Lexer con el cual obtendrá los tokens.
     * @return Lista de LexerToken.
     */
    private static ArrayList<LexerToken> getAllTokens(Lexer lexer) throws LexicalException {
        LexerToken lexerToken = lexer.getNextToken();
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();

        while (lexerToken.getTokenID() != TokenID.TOKEN_EOF) {
            tokens.add(lexerToken);
            lexerToken = lexer.getNextToken();
        }

        return tokens;
    }

    /**
     * @author Bautista Frigolé
     * Imprime por pantalla todos los LexerToken dados.
     * @param tokensList Lista de LexerToken.
     */
    private static void printTokenTable(ArrayList<LexerToken> tokensList) {
        System.out.println("CORRECTO: ANALISIS LEXICO\n" +
                "| TOKEN | LEXEMA | NUMERO DE LINEA (NUMERO DE COLUMNA) |");

        for (LexerToken token : tokensList) {
            System.out.println(token);
        }
    }

    /**
     * @author Bautista Frigolé
     * Escribe en un archivo la tabla con todos los LexerToken dados.
     * @param writer FileWriter del archivo a llenar.
     * @param tokensList Lista de LexerToken.
     */
    private static void writeTokenTable(FileWriter writer, ArrayList<LexerToken> tokensList)
            throws IOException {
        writer.write(" CORRECTO: ANALISIS LEXICO\n" +
                "| TOKEN | LEXEMA | NUMERO DE LINEA (NUMERO DE COLUMNA)\n");

        for (LexerToken token : tokensList) {
            writer.write(token.toString());
        }
        writer.close();
    }
}