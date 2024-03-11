import reader.Reader;

import java.util.*;

public class Lexer {
    private Reader reader;
    private String currentStrToken;
    private final List<Character> singleSeparators = Arrays.asList(new Character[]{' ', '\n', '\t', '\r', '\f', '\b',
            '(', ')', '{', '}', '[', ']', ';', ',', '.', ':', '#', '%', '^',
            '*', '\\', '!', '"', '\'', '`'});

    private final List<Character> doubleSeparators = Arrays.asList(new Character[]{'+', '-', '>', '<', '=', '&', '|'});

    Map<String, String> myMap = new HashMap<String, String>() {{
        put("foo", "bar");
        put("key", "value");
        //etc
    }};

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public LexerToken getNextToken() {
        StringBuilder currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();
        int startingColumn = reader.getCurrentColumn();

        if (singleSeparators.contains(ch)) {
            reader.nextChar();
            return new LexerToken(TokenID.NONE, ch.toString(),
                    reader.getCurrentLine(), startingColumn);
        } else {
            if (doubleSeparators.contains(ch)) {
                //TODO: Verificar el siguiente simbolo y armar token
                return new LexerToken(TokenID.NONE, ch.toString(),
                        reader.getCurrentLine(), startingColumn);
            }
            else{
                //TODO: Checkear la apariencia de un comentario
                if(ch == '/'){
                    reader.nextChar();
                    ch= reader.getCurrentChar();
                    if(ch=='?'){
                        while(ch!='\n'){
                            reader.nextChar();
                            ch= reader.getCurrentChar();
                        }
                    }
                }
            }
        }

        while (!singleSeparators.contains(ch)) {
            reader.nextChar();
            currentToken.append(ch);
            ch = reader.getCurrentChar();
        }

        return new LexerToken(TokenID.NONE, currentToken.toString(),
                reader.getCurrentLine(), startingColumn);
    }
}
