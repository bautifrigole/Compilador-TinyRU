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
import static org.junit.jupiter.api.Assertions.*;

class IdentifiersTests {

    @Test
    @DisplayName("ERROR: Invalid class identifier (Ends in underscore) Test")
    void invalidClassIdentifierTest1() {
        assertThrows(InvalidIdentifierException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/identifiers/invalidClassIdentifier1.ru"));
    }
    
    @Test
    @DisplayName("ERROR: Invalid class identifier (Ends in number) Test")
    void invalidClassIdentifierTest2() {
        assertThrows(InvalidIdentifierException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/identifiers/invalidClassIdentifier2.ru"));
    }

    @Test
    @DisplayName("ERROR: Invalid object identifier (Starts with underscore) Test")
    void invalidObjectIdentifierTest1() {
        assertThrows(InvalidIdentifierException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/identifiers/invalidObjIdentifier.ru"));
    }


    @Test
    @DisplayName("ERROR: Max length in identifier Test")
    void maxIdentifierLengthTest() {
        assertThrows(IdentifierLengthException.class,
                () -> Executor.getAllTokensFromPath("tests/lexical_tests/identifiers/maxLengthIdentifier.ru"));
    }

    @Test
    @DisplayName("Verification of correct identifiers Test")
    void validIntTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<>();
        tokens.add(new LexerToken(TokenID.TOKEN_ID_CLASS, "Holahola", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_CLASS, "HoLALSASD", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_CLASS, "Hola_HOLA", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_CLASS, "H", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_CLASS, "Hola123123a", 4, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "holahola", 5, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "hoLALSASD", 6, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "hola_HOLA", 7, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "h", 8, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "hola_", 9, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "hola123123", 10, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_ID_OBJ, "holaholaholaholaholaholaholaholaholaholaholaholaholaholaholahola", 11, 0));


        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/identifiers/validIdentifiers.ru")));
    }

}
