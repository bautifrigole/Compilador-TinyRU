package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.LexicalException;
import compiler.exceptions.lexical_exceptions.MissingOperationCharacter;
import compiler.exceptions.lexical_exceptions.UnclosedStrException;
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
     * @author Bautista Frigolé
     * Busca el siguiente token a formar en el código fuente.
     * @return compiler.lexical_analyzer.LexerToken con la información del siguiente token
     */
    public LexerToken getNextToken() throws LexicalException {
        currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();

        if (ch == null) {
            return new LexerToken(TokenID.TOKEN_EOF, null,
                    reader.getCurrentLine(), tokenStartingColumn);
        }

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
            while (!TokenSeparator.isSeparator(ch) && ch!=' ' && ch!='\n') {
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

    private LexerToken getDoubleLexerToken(Character ch) throws MissingOperationCharacter {
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

    private LexerToken getSingleOrDoubleLexerToken(Character ch) throws LexicalException {
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

    private Character getStringContent(Character ch) throws UnclosedStrException {
        currentToken.append(ch);
        reader.nextChar();
        ch = reader.getCurrentChar();

        if (ch == null || ch == (char)-1){
            throw new UnclosedStrException(reader.getCurrentLine(), tokenStartingColumn);
        }
        else {
            //TODO: Chequear si ch es un carácter de nuestro alfabeto

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
