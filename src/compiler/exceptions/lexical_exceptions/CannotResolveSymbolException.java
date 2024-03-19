package compiler.exceptions.lexical_exceptions;

/**
 * Excepción cuando se utiliza un símbolo inválido, ya sea en la creación de un token o en un comentario.
 * Ejemplo: "Hola @"
 * @author Bautista Frigolé
 */
public class CannotResolveSymbolException extends LexicalException {
    public CannotResolveSymbolException(int line, int column, Character symbol) {
        super(line, column, "SIMBOLO INVALIDO: \"" + symbol + "\"");
    }
}
