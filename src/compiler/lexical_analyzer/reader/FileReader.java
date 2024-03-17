package compiler.lexical_analyzer.reader;

import java.io.File;
import java.util.Scanner;

/**
 * Clase encargada de leer caracteres desde un archivo fuente.
 */
public class FileReader extends Reader {
    private Scanner scanner;
    private String line = "";

    /**
     * Constructor de la clase.
     * @param path String con la ruta hacia el archivo fuente
     */
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
