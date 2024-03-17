package compiler.exceptions.lexical_exceptions;

/**
 * @author Bautista Frigolé
 * Excepción cuando se utiliza un símbolo inválido.
 */
public class CannotResolveSymbolException extends LexicalException {
    public CannotResolveSymbolException(int line, int column, Character symbol) {
        super(line, column, "SIMBOLO INVALIDO: \"" + symbol + "\"");
    }
}
