package compiler.lexical_analyzer;

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
     * Constructor de la clase.
     * @param reader Reader de dónde se irán solicitando los caracteres a analizar
     */
    public Lexer(Reader reader) {
        this.reader = reader;
    }

    /**
     * Busca el siguiente token a formar en el código fuente.
     * @return compiler.lexical_analyzer.LexerToken con la información del siguiente token
     */
    public LexerToken getNextToken() {
        currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();

        while(ch==' ' || ch=='\n'){
            reader.nextChar();
            ch = reader.getCurrentChar();
        }

        tokenStartingColumn = reader.getCurrentColumn();

        if (TokenSeparator.singleSeparators.contains(ch)) {
            return getSingleLexerToken(ch);
        } else {
            if (TokenSeparator.doubleSeparators.contains(ch)) {
                return getDoubleLexerToken(ch);
            }
            else{
                if (TokenSeparator.singleOrDoubleSeparators.contains(ch)){
                    return getSingleOrDoubleLexerToken(ch);
                }
            }
        }

        if(!isStringOpen && ch=='"'){
            isStringOpen = true;
            while (isStringOpen){
                ch = getStringContent(ch);
            }
            currentToken.append(ch);
            reader.nextChar();
        }
        else{
            while (!TokenSeparator.isSeparator(ch) && ch!=' ') {
                currentToken.append(ch);
                reader.nextChar();
                ch = reader.getCurrentChar();
            }
        }

        return new LexerToken(TokenID.NONE, currentToken.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getSingleLexerToken(Character ch) {
        reader.nextChar();
        TokenID tokenID = TokenClassifier.getTokenCharID(ch);
        return new LexerToken(tokenID, ch.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getDoubleLexerToken(Character ch) {
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        if (ch == nextCh){
            currentToken.append(ch + nextCh);
            return new LexerToken(TokenClassifier.getTokenStrID(currentToken.toString()), currentToken.toString(),
                    reader.getCurrentLine(), tokenStartingColumn);
        }else{
            //TODO: Tirar error y parar ejecución (Falta de caracter)
            System.out.println("Falta caracter: " + ch);
        }
        return null;
    }

    private LexerToken getSingleOrDoubleLexerToken(Character ch) {
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

    private Character getStringContent(Character ch) {
        currentToken.append(ch);
        reader.nextChar();
        ch = reader.getCurrentChar();

        if (ch == null || ch == (char)-1){
            System.out.println("No se cerró comillas");
            //TODO: Tirar error y parar ejecución (Falta cerrar comillas)
        }
        else {
            if (ch == '\\') {
                reader.nextChar();
                ch = reader.getCurrentChar();
                if (ch == '0') {

                    System.out.println("Caracter inválido");
                    //TODO: Tirar error y parar ejecución (Caracter inválido)
                } else {
                    currentToken.append(ch);
                }
            }
        }
        isStringOpen = ch != '"';
        return ch;
    }
}
