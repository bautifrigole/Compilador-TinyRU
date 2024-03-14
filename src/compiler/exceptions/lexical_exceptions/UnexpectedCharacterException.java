package compiler.exceptions.lexical_exceptions;

public class UnexpectedCharacterException extends LexicalException {
    public UnexpectedCharacterException(int line, int column, String lexeme, Character unexpectedCh) {
        super(line, column, "CARACTER INESPERADO: \"" + unexpectedCh + "\", EN \"" + lexeme + "\"");
    }
}
