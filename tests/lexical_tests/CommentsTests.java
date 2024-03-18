package lexical_tests;

import compiler.exceptions.lexical_exceptions.CannotResolveSymbolException;
import compiler.exceptions.lexical_exceptions.LexicalException;
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

class CommentsTests {
    /**
     * Clase para ejecutar tests con JUnit relacionados a comentarios.
     * @author Francisco Devaux
     */
    @Test
    @DisplayName("EndOfFile after comment Test")
    void endOfFileAfterCommentTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();

        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/comments/EOFAfterComment.ru")));
    }

    @Test
    @DisplayName("ERROR: Invalid symbol in comment Test")
    void invalidSymbolCommentTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/comments/invalidSymbolInComment.ru"));
    }

    @Test
    @DisplayName("ERROR: Null in comment Test")
    void nullInCommentTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/comments/nullInComment.ru"));
    }

    @Test
    @DisplayName("Comment ignored correctly Test")
    void validCommentIgnoreTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_STR, "hola", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_STR, "que tal", 1, 0));

        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/comments/ValidCommentIgnore.ru")));

    }

}