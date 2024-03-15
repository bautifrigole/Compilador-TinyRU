package lexical_tests;

import compiler.exceptions.lexical_exceptions.CannotResolveSymbolException;
import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.exceptions.lexical_exceptions.UnclosedStrException;
import compiler.lexical_analyzer.Executor;
import compiler.lexical_analyzer.LexerToken;
import compiler.lexical_analyzer.TokenID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static compiler.Utils.areTheSameTokenLists;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringTests {


    @Test
    @DisplayName("ERROR: Single slash in string Test")
    void invalidSymbolStringTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/string/invalidSymbolInString.ru"));
    }

    @Test
    @DisplayName("ERROR: Null in string Test")
    void nullInStringTest() {
        assertThrows(UnclosedStrException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/string/nullInStr.ru"));
    }

    @Test
    @DisplayName("ERROR: Triple mark Test")
    void tripleMarkStringTest() {
        assertThrows(UnclosedStrException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/string/tripleMarkString.ru"));
    }

    @Test
    @DisplayName("ERROR: Unclosed string Test")
    void unclosedStringTest() {
        assertThrows(UnclosedStrException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/string/unclosedStr.ru"));
    }

    @Test
    @DisplayName("Valid string Test")
    void validStringTest() throws LexicalException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_STR, "hola que tal", 0, 0));
        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/string/validStringOutput.ru")));
    }


}