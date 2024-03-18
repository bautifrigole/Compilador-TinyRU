package compiler.lexical_analyzer.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase encargada de leer caracteres desde un archivo fuente.
 * @author Bautista Frigolé y Francisco Devaux
 */
public class FileReader extends Reader {
    private final Scanner scanner;
    private String line = "";

    /**
     * Constructor de la clase.
     * @author Francisco Devaux
     * @param path String con la ruta hacia el archivo fuente
     */
    public FileReader(String path) throws FileNotFoundException {
        File file = new File(path);
        scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            this.line = scanner.nextLine();
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
                resetCurrentColumn();
            }
        }
    }

    @Override
    public Character getCurrentChar() {
        if (getCurrentColumn() < line.length()) {
            return line.charAt(getCurrentColumn());
        } else {
            if (scanner.hasNextLine()) {
                return '\n';
            } else {
                return null;
            }
        }
    }
}
