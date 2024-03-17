package compiler.lexical_analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bautista Frigolé y Francisco Devaux
 * Clase encargada de reconocer las cadenas y caracteres que separan a los tokens.
 */
public class TokenSeparator {
    /**
     * Lista de todos los tokens separadores de un sólo carácter.
     */
    public static final List<Character> singleSeparators = Arrays.asList(new Character[]{'\t', '\r', '\f', '\b',
            '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
            '*', '`'});
    /**
     * Lista de todos los tokens separadores de uno o dos caracteres.
     */
    public static final List<Character> singleOrDoubleSeparators = Arrays.asList(new Character[]{'+', '-', '>', '<', '=', '!', '/'});
    /**
     * Lista de todos los tokens separadores de dos caracteres.
     */
    public static final List<Character> doubleSeparators = Arrays.asList(new Character[]{'&', '|'});

    /**
     * @author Bautista Frigolé y Francisco Devaux
     * Devuelve las posibles continuaciones de un carácter para armar tokens.
     * @param key Character a buscar sus posibles continuaciones
     * @return List de Character con todos los posibles caracteres
     */
    public static List<Character> getSepContinuation(Character key) {
        List<Character> result = new ArrayList<>();
        switch (key) {
            case '+':
                result.add('+');
                break;
            case '-':
                result.addAll(Arrays.asList('-', '>'));
                break;
            case '>':
            case '!':
            case '<':
            case '=':
                result.add('=');
                break;
            case '/':
                result.add('?');
                break;
            default:
                result = null;
        }
        return result;
    }

    /**
     * @author Bautista Frigolé y Francisco Devaux
     * Indica si un determinado carácter es un separador
     * @param key Character a analizar si es un separador
     * @return boolean que indica si es separador
     */
    public static boolean isSeparator(Character key) {
        return singleSeparators.contains(key) || singleOrDoubleSeparators.contains(key) || doubleSeparators.contains(key);
    }
}
