package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.reader.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de ejecutar llamados a Lexer para que éste busque el siguiente token en su fuente.
 * @author Bautista Frigolé y Francisco Devaux
 */
public class Executor {

    /**
     * Método main.
     * @param args Argumentos de main.
     * @author Bautista Frigolé y Francisco Devaux
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                ArrayList<LexerToken> tokensList = getAllTokensFromPath(args[0]);
                if (tokensList == null) {
                    System.out.println("ERROR: EL ARCHIVO \"" + args[0] + "\" NO EXISTE.");
                    return;
                }

                if (args.length > 1) {
                    File outputFile = new File(args[1]);
                    outputFile.createNewFile();
                    FileWriter writer = new FileWriter(args[1]);
                    writeTokenTable(writer, tokensList);
                } else {
                    printTokenTable(tokensList);
                }
            } else {
                System.out.println("ERROR: ARCHIVO DE ENTRADA NO ESPECIFICADO.");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Busca todos los tokens dentro del archivo especificado en la ruta. Si el archivo no existe, devuelve null.
     * @param path Ruta del archivo.
     * @return Lista de {@link LexerToken}.
     * @author Bautista Frigolé
     */
    public static ArrayList<LexerToken> getAllTokensFromPath(String path) throws LexicalException, FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        Lexer lexer = new Lexer(new FileReader(file.getPath()));
        return getAllTokens(lexer);
    }

    /**
     * Busca todos los tokens utilizando el {@link Lexer} dado.
     * @param lexer Lexer con el cual obtendrá los tokens.
     * @return Lista de {@link LexerToken}.
     * @author Bautista Frigolé
     */
    private static ArrayList<LexerToken> getAllTokens(Lexer lexer) throws LexicalException {
        LexerToken lexerToken = lexer.getNextToken();
        ArrayList<LexerToken> tokens = new ArrayList<>();

        while (lexerToken.getTokenID() != TokenID.TOKEN_EOF) {
            tokens.add(lexerToken);
            lexerToken = lexer.getNextToken();
        }

        return tokens;
    }

    /**
     * Imprime por pantalla todos los {@link LexerToken} dados.
     * @param tokensList Lista de {@link LexerToken}.
     * @author Bautista Frigolé
     */
    private static void printTokenTable(ArrayList<LexerToken> tokensList) {
        System.out.println("CORRECTO: ANALISIS LEXICO\n" +
                "| TOKEN | LEXEMA | NUMERO DE LINEA (NUMERO DE COLUMNA) | \n");

        for (LexerToken token : tokensList) {
            System.out.println(token);
        }
    }

    /**
     * Escribe en un archivo la tabla con todos los {@link LexerToken} dados.
     * @param writer FileWriter del archivo a llenar.
     * @param tokensList Lista de {@link LexerToken}.
     * @author Bautista Frigolé
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