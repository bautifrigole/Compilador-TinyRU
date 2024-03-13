package compiler.exceptions.lexical_exceptions;

public class MissingOperationCharacter extends LexicalException {
    public MissingOperationCharacter(int line, int column, Character lexeme, Character expectedCh) {
        super(line, column, "CARACTER DE OPERACION FALTANTE EN \"" + lexeme + "\", SE ESPERABA \""
                + lexeme + expectedCh + "\"");
    }
}
