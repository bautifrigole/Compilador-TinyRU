import java.util.Arrays;
import java.util.List;

public class TokenSeparator {



    public static final List<Character> singleSeparators = Arrays.asList(new Character[]{ '\t', '\r', '\f', '\b',
            '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
            '*', '\\', '!', '\'', '`'});
    public static final List<Character> singleOrDoubleSeparators = Arrays.asList(new Character[]{'+', '-', '>', '<', '=', '!', '/'});

    public static final List<Character> doubleSeparators = Arrays.asList(new Character[]{ '&', '|'});

    // Se retornan las posibles continuaciones de algunos simbolos para armar tokens
    public static List<Character> getSepContinuation(Character key) {
        switch(key){
            case '+':
                return Arrays.asList(new Character[]{'+'});
            case '-':
                return Arrays.asList(new Character[]{'-','>'});
            case '>', '!', '<', '=':
                return Arrays.asList(new Character[]{'='});
            case '/':
                return Arrays.asList(new Character[]{'?'});
        }
        return null;

    }

    // Se retorna True si es un posible separador
    public static Boolean isSeparator(Character key){
        return singleSeparators.contains(key) || singleOrDoubleSeparators.contains(key) || doubleSeparators.contains(key);
    }

}

