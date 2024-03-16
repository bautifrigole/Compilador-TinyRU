package lexical_tests;

import compiler.exceptions.lexical_exceptions.CannotResolveSymbolException;
import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.Executor;
import compiler.lexical_analyzer.LexerToken;
import compiler.lexical_analyzer.TokenID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static compiler.Utils.areTheSameTokenLists;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommentsTests {

    @Test
    @DisplayName("EndOfFile after comment Test")
    void validKWStructsTypesTest() throws LexicalException {
        ArrayList<LexerToken> tokens = new ArrayList<>();

        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/comments/EOFAfterComment.ru")));
    }

    @Test
    @DisplayName("ERROR: Invalid symbol in comment Test")
    void invalidSymbolInCommentTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/comments/invalidSymbolInComment.ru"));
    }

    @Test
    @DisplayName("ERROR: Null in comment Test")
    void nullInCommentTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/comments/nullInComment.ru"));
    }


}