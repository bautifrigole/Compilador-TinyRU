package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.*;
import compiler.lexical_analyzer.reader.Reader;
import java.util.*;

/**
 * Clase encargada de realizar el análisis léxico.
 */
public class Lexer {
    private Reader reader;
    private StringBuilder currentToken;
    private int tokenStartingColumn;
    private boolean isStringOpen = false;

    /**
     * @author Bautista Frigolé
     * Constructor de la clase.
     * @param reader Reader de dónde se irán solicitando los caracteres a analizar
     */
    public Lexer(Reader reader) {
        this.reader = reader;
    }

    /**
     * @author Bautista Frigolé
     * Busca el siguiente token a formar en el código fuente.
     * @return compiler.lexical_analyzer.LexerToken con la información del siguiente token
     * @throws LexicalException Excepción de tipo léxica
     */
    public LexerToken getNextToken() throws LexicalException {
        currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();


        if (ch == null) {
            return new LexerToken(TokenID.TOKEN_EOF, null,
                    reader.getCurrentLine(), tokenStartingColumn);
        }

        if (ch == '\0') {
            throw new CannotResolveSymbolException(reader.getCurrentLine(),
                    tokenStartingColumn, ch);
        }

        while(ch==' ' || ch=='\n'){
            reader.nextChar();
            ch = reader.getCurrentChar();
        }

        tokenStartingColumn = reader.getCurrentColumn();

        if (TokenSeparator.singleSeparators.contains(ch)) {
            return getSingleLexerToken();
        } else {
            if (TokenSeparator.doubleSeparators.contains(ch)) {
                return getDoubleLexerToken();
            }
            else{
                if (TokenSeparator.singleOrDoubleSeparators.contains(ch)){
                    return getSingleOrDoubleLexerToken();
                }
            }
        }

        if(!isStringOpen && ch=='"'){
            return getStringLiteralLexerToken(ch);
        }
        else{
            if(Character.isDigit(ch)){
                return getIntLiteralLexerToken(ch);
            }
            else{
                ch = buildIdentifierOrKeyWordLexeme();
                TokenID tokenID = TokenClassifier.getTokenStrID(currentToken.toString());

                if (tokenID != null) {
                    return new LexerToken(tokenID, currentToken.toString(),
                            reader.getCurrentLine(), tokenStartingColumn);
                }
                else {
                    tokenID = getIdentifierTokenID(currentToken.toString());
                    if (tokenID != null) {
                        return new LexerToken(tokenID, currentToken.toString(),
                                reader.getCurrentLine(), tokenStartingColumn);
                    }
                }
            }

            if (ch == null) {
                return new LexerToken(TokenID.TOKEN_EOF, null,
                        reader.getCurrentLine(), tokenStartingColumn);
            }

            while (!TokenSeparator.isSeparator(ch) && ch!=' ' && ch!='\n') {
                currentToken.append(ch);
                reader.nextChar();
                ch = reader.getCurrentChar();
            }
        }

        return new LexerToken(TokenID.NONE, currentToken.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getSingleLexerToken() {
        Character ch = reader.getCurrentChar();
        reader.nextChar();
        TokenID tokenID = TokenClassifier.getTokenCharID(ch);
        return new LexerToken(tokenID, ch.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getDoubleLexerToken() throws MissingOperationCharacter {
        Character ch = reader.getCurrentChar();
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        if (ch == nextCh){
            reader.nextChar();
            currentToken.append(ch);
            currentToken.append(nextCh);
            return new LexerToken(TokenClassifier.getTokenStrID(currentToken.toString()), currentToken.toString(),
                    reader.getCurrentLine(), tokenStartingColumn);
        }else{
            throw new MissingOperationCharacter(reader.getCurrentLine(), tokenStartingColumn, ch, ch);
        }
    }

    private LexerToken getSingleOrDoubleLexerToken() throws LexicalException {
        Character ch = reader.getCurrentChar();
        currentToken.append(ch);
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        List<Character> continuationList = TokenSeparator.getSepContinuation(ch);
        if(continuationList!= null && continuationList.contains(nextCh)){
            currentToken.append(nextCh);

            if(currentToken.toString().equals("/?")){
                while (ch != '\n') {
                    reader.nextChar();
                    ch = reader.getCurrentChar();
                }
                reader.nextChar();
                return getNextToken();
            }
            else{
                reader.nextChar();
                return new LexerToken(TokenClassifier.getTokenStrID(currentToken.toString()),
                        currentToken.toString(), reader.getCurrentLine(), tokenStartingColumn);
            }
        }

        return new LexerToken(TokenClassifier.getTokenCharID(ch), ch.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getStringLiteralLexerToken(Character ch) throws UnclosedStrException, CannotResolveSymbolException {
        isStringOpen = true;
        while (isStringOpen){
            ch = buildStringLiteral();
        }
        currentToken.append(ch);
        reader.nextChar();
        return new LexerToken(TokenID.TOKEN_LITERAL_STR, currentToken.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private Character buildStringLiteral() throws UnclosedStrException, CannotResolveSymbolException {
        Character ch = reader.getCurrentChar();
        currentToken.append(ch);
        reader.nextChar();
        ch = reader.getCurrentChar();

        if (ch == null || ch == (char)-1 || ch == '\n'){
            throw new UnclosedStrException(reader.getCurrentLine(), tokenStartingColumn);
        }
        else {
            if(!isValidStringCharacter(ch) ){
                throw new CannotResolveSymbolException(reader.getCurrentLine(),
                        tokenStartingColumn, ch);
            }


        }
        isStringOpen = ch != '"';
        return ch;
    }

    private LexerToken getIntLiteralLexerToken(Character ch) throws UnexpectedCharacterException {
        while (!TokenSeparator.isSeparator(ch) && ch !=' '){
            if(!Character.isDigit(ch)){
                throw new UnexpectedCharacterException(reader.getCurrentLine(),
                        tokenStartingColumn, currentToken.toString(), ch);
            }
            currentToken.append(ch);
            reader.nextChar();
            ch = reader.getCurrentChar();
        }

        return new LexerToken(TokenID.TOKEN_LITERAL_INT, currentToken.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private Character buildIdentifierOrKeyWordLexeme() throws InvalidIdentifierException {
        Character ch = reader.getCurrentChar();
        while (ch != null && !TokenSeparator.isSeparator(ch) && ch!=' ' && ch!='\n'){
            currentToken.append(ch);
            if(!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch!='_'){
                throw new InvalidIdentifierException(reader.getCurrentLine(),
                        tokenStartingColumn, currentToken.toString(), ch);
            }
            reader.nextChar();
            ch = reader.getCurrentChar();
        }
        return ch;
    }

    private TokenID getIdentifierTokenID(String str) throws InvalidIdentifierException {
        char firstChar = str.charAt(0);
        if (Character.isUpperCase(firstChar)){
            if (str.length() == 1){
                return TokenID.TOKEN_ID_CLASS;
            }
            char lastChar = str.charAt(str.length()-1);
            if (Character.isAlphabetic(lastChar)) {
                return TokenID.TOKEN_ID_CLASS;
            }
            else {
                throw new InvalidIdentifierException(reader.getCurrentLine(),
                        tokenStartingColumn, str, lastChar);
            }
        }
        if (Character.isAlphabetic(firstChar)) {
            return TokenID.TOKEN_ID_OBJ;
        }
        else {
            throw new InvalidIdentifierException(reader.getCurrentLine(),
                    tokenStartingColumn, str, firstChar);
        }
    }

    private boolean isValidStringCharacter(Character ch){
        List<Character> spanishCharacters = Arrays.stream(new Character[]{'á','é','í','ó','ú', 'Á','É','Í','Ó','Ú','ñ','Ñ','ü','Ü'}).toList();

        int asciiCode = (int) ch;
        return (asciiCode >= 32 && asciiCode <= 126)|| spanishCharacters.contains(ch);
    }
}
