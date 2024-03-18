package lexical_tests;

import compiler.exceptions.lexical_exceptions.*;
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

/**
 * Clase para ejecutar tests con JUnit relacionados a números enteros.
 * @author Francisco Devaux
 */
class IntLiteralsTests {

    @Test
    @DisplayName("ERROR: Incorrect formation of int literal Test")
    void invalidIntLiteralTest() {
        assertThrows(UnexpectedCharacterException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/int_literals/invalidIntLiteral.ru"));
    }

    @Test
    @DisplayName("ERROR: Max int length Test")
    void maxIntLengthTest() {
        assertThrows(IntSizeException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/int_literals/maxLengthInt.ru"));
    }

    @Test
    @DisplayName("ERROR: Max int size Test")
    void maxIntSizeTest() {
        assertThrows(IntSizeException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/int_literals/maxSizeInt.ru"));
    }

    @Test
    @DisplayName("Verification of correct int identification Test 1")
    void validIntTest1() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_INT, "45678", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_SEMICOLON, ";", 0, 5));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_INT, "123123", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_SEMICOLON, ";", 1, 6));


        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/int_literals/validInt1.ru")));
    }

    @Test
    @DisplayName("Verification of correct int identification Test 2")
    void validIntTest2() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_INT, "2147483646", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_SEMICOLON, ";", 0, 10));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_INT, "123123", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_SEMICOLON, ";", 1, 6));


        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/int_literals/validInt2.ru")));
    }

}
