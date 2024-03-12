package compiler.lexical_analyzer;

import java.util.Arrays;
import java.util.List;

/**
 * Clase encargada de reconocer las cadenas y caracteres que separan a los tokens.
 */
public class TokenSeparator {
    public static final List<Character> singleSeparators = Arrays.asList(new Character[]{ '\t', '\r', '\f', '\b',
            '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
            '*', '!', '\'', '`'});
    public static final List<Character> singleOrDoubleSeparators = Arrays.asList(new Character[]{'+', '-', '>', '<', '=', '!', '/'});

    public static final List<Character> doubleSeparators = Arrays.asList(new Character[]{ '&', '|'});

    /**
     * Devuelve las posibles continuaciones de un carácter para armar tokens.
     * @param key Character a buscar sus posibles continuaciones
     * @return List de Character con todos los posibles caracteres
     */
    public static List<Character> getSepContinuation(Character key) {
        return switch (key) {
            case '+' -> Arrays.asList(new Character[]{'+'});
            case '-' -> Arrays.asList(new Character[]{'-', '>'});
            case '>', '!', '<', '=' -> Arrays.asList(new Character[]{'='});
            case '/' -> Arrays.asList(new Character[]{'?'});
            default -> null;
        };
    }

    /**
     * Indica si un determinado carácter es un separador
     * @param key Character a analizar si es un separador
     * @return boolean que indica si es separador
     */
    public static boolean isSeparator(Character key){
        return singleSeparators.contains(key) || singleOrDoubleSeparators.contains(key) || doubleSeparators.contains(key);
    }

}

