package compiler.exceptions.lexical_exceptions;

public class InvalidIdentifierException extends LexicalException {
    public InvalidIdentifierException(int line, int column, String lexeme, Character unexpectedCh) {
        super(line, column, "IDENTIFICADOR MAL FORMADO \"" + lexeme + "\", CARACTER INVALIDO: \"" + unexpectedCh + "\"");
    }
}
