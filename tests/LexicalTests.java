import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.Executor;
import compiler.lexical_analyzer.LexerToken;
import compiler.lexical_analyzer.TokenID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LexicalTests {

    @Test
    @DisplayName("Sum Test")
    void sumTest() throws LexicalException {
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();
        tokens.add(new LexerToken(TokenID.TOKEN_OP_EQ, "=", 0, 0));
        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/sum.ru")));
    }

    @Test
    @DisplayName("Null in character Test")
    void nullCharTest() throws LexicalException {
    }

    @Test
    @DisplayName("Verification of correct characters Test")
    void validDifferentCharacters() throws LexicalException {
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "n", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "\n", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "a", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "\\", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_LITERAL_CHAR, "'", 4, 0));
        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/char/validCharOutput.ru")));
    }

    private static boolean areTheSameTokenLists(ArrayList<LexerToken> tokens1, ArrayList<LexerToken> tokens2) {
        if (tokens1.size() != tokens2.size()) {
            return false;
        }

        for (int i = 0; i < tokens1.size(); i++) {
            LexerToken token1 = tokens1.get(i);
            LexerToken token2 = tokens2.get(i);

            if (!token1.getTokenID().equals(token2.getTokenID())
                    || !token1.getLexemeString().equals(token2.getLexemeString())
                    || !token1.getLexemeChar().equals(token2.getLexemeChar())
                    || token1.getLine() != token2.getLine()
                    || token1.getColumn() != token2.getColumn()) {
                return false;
            }
        }
        return true;
    }
}