package lexical_tests;

import compiler.exceptions.lexical_exceptions.CannotResolveSymbolException;
import compiler.exceptions.lexical_exceptions.EmptyCharException;
import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.exceptions.lexical_exceptions.UnclosedCharException;
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

class CharacterTests {
    /**
     * Clase para ejecutar tests con JUnit relacionados a caracteres.
     * @author Francisco Devaux
     */
    @Test
    @DisplayName("ERROR: Empty character Test")
    void emptyCharTest() {
        assertThrows(EmptyCharException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/emptyChar.ru"));
    }

    @Test
    @DisplayName("ERROR: Invalid slash character Test")
    void invalidSlashSymbolCharTest() {
        assertThrows(UnclosedCharException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/invalidSlashChar.ru"));
    }


    @Test
    @DisplayName("ERROR: Invalid symbol in character Test (With \\)")
    void invalidSymbolWithSlashCharTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/invalidSymbolInChar1.ru"));
    }

    @Test
    @DisplayName("ERROR: Invalid symbol in character Test")
    void invalidSymbolCharTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/invalidSymbolInChar2.ru"));
    }

    @Test
    @DisplayName("ERROR: New line between ' Test")
    void newLineCharTest() {
        assertThrows(UnclosedCharException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/newLineChar.ru"));
    }

    @Test
    @DisplayName("ERROR: Null in character Test")
    void nullCharTest() {
        assertThrows(CannotResolveSymbolException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/nullChar.ru"));
    }

    @Test
    @DisplayName("ERROR: Triple ' Test")
    void tripleMarkCharTest() {
        assertThrows(EmptyCharException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/char/tripleMarkChar.ru"));
    }

    @Test
    @DisplayName("Verification of correct characters Test")
    void validDifferentCharactersTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "n", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "\\" + "n", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "a", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "\\", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "'", 4, 0));
        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/char/validCharOutput.ru")));
    }


}