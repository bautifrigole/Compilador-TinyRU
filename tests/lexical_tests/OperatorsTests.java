package lexical_tests;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.exceptions.lexical_exceptions.MissingOperationCharacter;
import compiler.lexical_analyzer.Executor;
import compiler.lexical_analyzer.LexerToken;
import compiler.lexical_analyzer.TokenID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static compiler.Utils.areTheSameTokenLists;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperatorsTests {

    @Test
    @DisplayName("ERROR: Incomplete double operator Test")
    void incompleteDoubleOperatorsTest() {
        assertThrows(MissingOperationCharacter.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/operators/incompleteDoubleOperator.ru"));
    }

    @Test
    @DisplayName("Verification of correct Key Words, Predefined Structures and Types Test")
    void validDoubleOperatorsTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_OP_AND, "&&", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_OR, "||", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_EQ, "==", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_NEQ, "!=", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_LARGEREQ, ">=", 4, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_SMALLEREQ, "<=", 5, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_DOUBLEPLUS, "++", 6, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_DOUBLEMINUS, "--", 7, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_ARROW, "->", 8, 0));


        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/operators/validDoubleOperators.ru")));
    }

    @Test
    @DisplayName("Verification of correct Key Words, Predefined Structures and Types Test")
    void validSingleOperatorTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_OP_PLUS, "+", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_MINUS, "-", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_MULT, "*", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_DIV, "/", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_MOD, "%", 4, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_NOT, "!", 5, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_ASSIGN, "=", 6, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_LARGER, ">", 7, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OP_SMALLER, "<", 8, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OPEN_BRACKET, "[", 9, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_CLOSE_BRACKET, "]", 10, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OPEN_PAREN, "(", 11, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_CLOSE_PAREN, ")", 12, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_OPEN_BRACE, "{", 13, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_CLOSE_BRACE, "}", 14, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_COMMA, ",", 15, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_DOT, ".", 16, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_COLON, ":", 17, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_SEMICOLON, ";", 18, 0));

        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/operators/validSingleOperators.ru")));
    }


}