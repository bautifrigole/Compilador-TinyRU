package compiler.exceptions.lexical_exceptions;

public class CannotResolveSymbolException extends LexicalException {
    public CannotResolveSymbolException(int line, int column, Character symbol) {
        super(line, column, "SIMBOLO INVALIDO: \"" + symbol + "\"");
    }
}
