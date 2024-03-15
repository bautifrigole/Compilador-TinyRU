package compiler;

import compiler.lexical_analyzer.LexerToken;

import java.util.ArrayList;

public class Utils {


    public static boolean areTheSameTokenLists(ArrayList<LexerToken> tokens1, ArrayList<LexerToken> tokens2) {
        if (tokens1.size() != tokens2.size()) {
            return false;
        }

        for (int i = 0; i < tokens1.size(); i++) {
            LexerToken token1 = tokens1.get(i);
            LexerToken token2 = tokens2.get(i);
            //TODO: Imprimir lo que es distinto

            if (!token1.getTokenID().equals(token2.getTokenID())
                    || !token1.getLexemeString().equals(token2.getLexemeString())
                    || token1.getLine() != token2.getLine()
                    || token1.getColumn() != token2.getColumn()) {
                System.out.println(token1.getLexemeString() + " distinto a " + token2.getLexemeString());
                return false;
            }
        }
        return true;
    }
}