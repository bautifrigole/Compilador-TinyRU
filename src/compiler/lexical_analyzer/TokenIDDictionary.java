package compiler.lexical_analyzer;

import java.util.HashMap;
import java.util.Map;

/*
 * Clase encargada de almacenar los tokens junto con su ID.
 */
public class TokenIDDictionary {
    private static final Map<Character, TokenID> tokenCharMap = new HashMap<>();

    static {
        // Operators
        tokenCharMap.put('+', TokenID.TOKEN_OP_PLUS);
        tokenCharMap.put('-', TokenID.TOKEN_OP_MINUS);
        tokenCharMap.put('*', TokenID.TOKEN_OP_MULT);
        tokenCharMap.put('/', TokenID.TOKEN_OP_DIV);
        tokenCharMap.put('%', TokenID.TOKEN_OP_MOD);
        tokenCharMap.put('&', TokenID.TOKEN_OP_AND);
        tokenCharMap.put('|', TokenID.TOKEN_OP_OR);
        tokenCharMap.put('!', TokenID.TOKEN_OP_NOT);
        tokenCharMap.put('=', TokenID.TOKEN_OP_EQ);
        tokenCharMap.put('>', TokenID.TOKEN_OP_LARGER);
        tokenCharMap.put('<', TokenID.TOKEN_OP_SMALLER);
        tokenCharMap.put('[', TokenID.TOKEN_OPEN_BRACKET);
        tokenCharMap.put(']', TokenID.TOKEN_CLOSE_BRACKET);
        tokenCharMap.put('(', TokenID.TOKEN_OPEN_PAREN);
        tokenCharMap.put(')', TokenID.TOKEN_CLOSE_PAREN);
        tokenCharMap.put('{', TokenID.TOKEN_OPEN_BRACE);
        tokenCharMap.put('}', TokenID.TOKEN_CLOSE_BRACE);
        tokenCharMap.put(',', TokenID.TOKEN_COMMA);
        tokenCharMap.put('.', TokenID.TOKEN_DOT);
        tokenCharMap.put(':', TokenID.TOKEN_COLON);
        tokenCharMap.put(';', TokenID.TOKEN_SEMICOLON);

        // Characters
        // tokenCharMap.put('\n', compiler.lexical_analyzer.TokenID.TOKEN_NEWLINE);
        tokenCharMap.put('\t', TokenID.TOKEN_TAB);
        tokenCharMap.put('v', TokenID.TOKEN_VERTICALTAB);
        tokenCharMap.put('\r', TokenID.TOKEN_CARRIAGERETURN);
    }

    private static final Map<String, TokenID> tokenStrMap = new HashMap<>();

    static {
        // Keywords
        tokenStrMap.put("struct", TokenID.TOKEN_KW_STRUCT);
        tokenStrMap.put("impl", TokenID.TOKEN_KW_IMPL);
        tokenStrMap.put("else", TokenID.TOKEN_KW_ELSE);
        tokenStrMap.put("false", TokenID.TOKEN_KW_FALSE);
        tokenStrMap.put("if", TokenID.TOKEN_KW_IF);
        tokenStrMap.put("ret", TokenID.TOKEN_KW_RET);
        tokenStrMap.put("while", TokenID.TOKEN_KW_WHILE);
        tokenStrMap.put("true", TokenID.TOKEN_KW_TRUE);
        tokenStrMap.put("nil", TokenID.TOKEN_KW_NIL);
        tokenStrMap.put("new", TokenID.TOKEN_KW_NEW);
        tokenStrMap.put("fn", TokenID.TOKEN_KW_FN);
        tokenStrMap.put("st", TokenID.TOKEN_KW_ST);
        tokenStrMap.put("pri", TokenID.TOKEN_KW_PRI);
        tokenStrMap.put("self", TokenID.TOKEN_KW_SELF);

        // Identifiers
        tokenStrMap.put("obj", TokenID.TOKEN_ID_OBJ);
        tokenStrMap.put("class", TokenID.TOKEN_ID_CLASS);

        // Operators
        tokenStrMap.put("==", TokenID.TOKEN_OP_EQ);
        tokenStrMap.put("!=", TokenID.TOKEN_OP_NEQ);
        tokenStrMap.put(">=", TokenID.TOKEN_OP_LARGEREQ);
        tokenStrMap.put("<=", TokenID.TOKEN_OP_SMALLEREQ);
        tokenStrMap.put("=", TokenID.TOKEN_OP_ASSIGN);
        tokenStrMap.put("++", TokenID.TOKEN_OP_DOUBLEPLUS);
        tokenStrMap.put("--", TokenID.TOKEN_OP_DOUBLEMINUS);
        tokenStrMap.put("->", TokenID.TOKEN_OP_ARROW);

        // Types
        tokenStrMap.put("int", TokenID.TOKEN_TYPE_INT);
        tokenStrMap.put("float", TokenID.TOKEN_TYPE_FLOAT);
        tokenStrMap.put("bool", TokenID.TOKEN_TYPE_BOOL);
        tokenStrMap.put("char", TokenID.TOKEN_TYPE_CHAR);
        tokenStrMap.put("str", TokenID.TOKEN_TYPE_STR);
        tokenStrMap.put("void", TokenID.TOKEN_TYPE_VOID);
        tokenStrMap.put("object", TokenID.TOKEN_TYPE_OBJECT);
        tokenStrMap.put("array", TokenID.TOKEN_TYPE_ARRAY);
    }

    /**
     * Busca el ID de la key especificada.
     * @param key Character a buscar su ID
     * @return compiler.lexical_analyzer.TokenID del Character especificado
     */
    public static TokenID getTokenCharID(Character key) {
        return tokenCharMap.get(key);
    }

    /**
     * Busca el ID de la key especificada.
     * @param key String a buscar su ID
     * @return compiler.lexical_analyzer.TokenID del String especificado
     */
    public static TokenID getTokenStrID(String key) {
        return tokenStrMap.get(key);
    }
}

