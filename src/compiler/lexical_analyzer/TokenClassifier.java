package compiler.lexical_analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bautista Frigolé y Francisco Devaux
 * Clase encargada de almacenar los tokens junto con su ID.
 */
public class TokenClassifier {
    /**
     * HashMap en el cual se vincula un Character con un {@link TokenID}.
     */
    private static final Map<Character, TokenID> tokenCharMap = new HashMap<>();

    static {
        // Operators
        tokenCharMap.put('+', TokenID.TOKEN_OP_PLUS);
        tokenCharMap.put('-', TokenID.TOKEN_OP_MINUS);
        tokenCharMap.put('*', TokenID.TOKEN_OP_MULT);
        tokenCharMap.put('/', TokenID.TOKEN_OP_DIV);
        tokenCharMap.put('%', TokenID.TOKEN_OP_MOD);
        tokenCharMap.put('!', TokenID.TOKEN_OP_NOT);
        tokenCharMap.put('=', TokenID.TOKEN_OP_ASSIGN);
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
    }

    /**
     * HashMap en el cual se vincula un String con un {@link TokenID}.
     */
    private static final Map<String, TokenID> tokenStrMap = new HashMap<>();

    static {
        // Operators
        tokenStrMap.put("&&", TokenID.TOKEN_OP_AND);
        tokenStrMap.put("||", TokenID.TOKEN_OP_OR);
        tokenStrMap.put("==", TokenID.TOKEN_OP_EQ);
        tokenStrMap.put("!=", TokenID.TOKEN_OP_NEQ);
        tokenStrMap.put(">=", TokenID.TOKEN_OP_LARGEREQ);
        tokenStrMap.put("<=", TokenID.TOKEN_OP_SMALLEREQ);
        tokenStrMap.put("++", TokenID.TOKEN_OP_DOUBLEPLUS);
        tokenStrMap.put("--", TokenID.TOKEN_OP_DOUBLEMINUS);
        tokenStrMap.put("->", TokenID.TOKEN_OP_ARROW);

        //Keywords
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

        // Types
        tokenStrMap.put("Int", TokenID.TOKEN_TYPE_INT);
        tokenStrMap.put("Bool", TokenID.TOKEN_TYPE_BOOL);
        tokenStrMap.put("Char", TokenID.TOKEN_TYPE_CHAR);
        tokenStrMap.put("Str", TokenID.TOKEN_TYPE_STR);
        tokenStrMap.put("void", TokenID.TOKEN_TYPE_VOID);

        // Predefined Structures
        tokenStrMap.put("Object", TokenID.TOKEN_STRUCT_OBJECT);
        tokenStrMap.put("Array", TokenID.TOKEN_STRUCT_ARRAY);
        tokenStrMap.put("IO", TokenID.TOKEN_STRUCT_IO);
    }

    /**
     * @author Bautista Frigolé
     * Busca el ID de la key especificada.
     * @param key Character a buscar su ID
     * @return TokenID del Character especificado
     */
    public static TokenID getTokenCharID(Character key) {
        return tokenCharMap.get(key);
    }

    /**
     * @author Bautista Frigolé
     * Busca el ID de la key especificada.
     * @param key String a buscar su ID
     * @return TokenID del String especificado
     */
    public static TokenID getTokenStrID(String key) {
        return tokenStrMap.get(key);
    }
}