import calculator.java.Calculator;
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
        assertTrue(areTheSameLists(tokens, Executor.getAllTokensFromPath("tests/sum.ru")));
    }

    private static boolean areTheSameLists(ArrayList<LexerToken> tokens1, ArrayList<LexerToken> tokens2) {
        if (tokens1.size() != tokens2.size()) {
            return false;
        }

        for (int i = 0; i < tokens1.size(); i++) {
            LexerToken token1 = tokens1.get(i);
            LexerToken token2 = tokens2.get(i);

            if (!token1.getTokenID().equals(token2.getTokenID())
                    || !token1.getLexeme().equals(token2.getLexeme())
                    || token1.getLine() != token2.getLine()
                    || token1.getColumn() != token2.getColumn()) {
                return false;
            }
        }
        return true;
    }
}