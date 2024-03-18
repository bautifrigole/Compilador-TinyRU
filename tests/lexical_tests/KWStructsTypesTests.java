package lexical_tests;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.lexical_analyzer.Executor;
import compiler.lexical_analyzer.LexerToken;
import compiler.lexical_analyzer.TokenID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static compiler.Utils.areTheSameTokenLists;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KWStructsTypesTests {


    @Test
    @DisplayName("Verification of correct Key Words, Predefined Structures and Types Test")
    void validKWStructsTypesTest() throws LexicalException, FileNotFoundException {
        ArrayList<LexerToken> tokens = new ArrayList<LexerToken>();
        tokens.add(new LexerToken(TokenID.TOKEN_KW_STRUCT, "struct", 0, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_IMPL, "impl", 1, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_ELSE, "else", 2, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_FALSE, "false", 3, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_IF, "if", 4, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_RET, "ret", 5, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_WHILE, "while", 6, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_TRUE, "true", 7, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_NIL, "nil", 8, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_NEW, "new", 9, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_FN, "fn", 10, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_ST, "st", 11, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_PRI, "pri", 12, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_KW_SELF, "self", 13, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_TYPE_INT, "Int", 14, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_TYPE_BOOL, "Bool", 15, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_TYPE_CHAR, "Char", 16, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_TYPE_STR, "Str", 17, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_TYPE_VOID, "void", 18, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_STRUCT_OBJECT, "Object", 19, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_STRUCT_ARRAY, "Array", 20, 0));
        tokens.add(new LexerToken(TokenID.TOKEN_STRUCT_IO, "IO", 21, 0));


        assertTrue(areTheSameTokenLists(tokens, Executor.getAllTokensFromPath("tests/lexical_tests/kw_structs_types/validKWStructsTypes.ru")));
    }


}