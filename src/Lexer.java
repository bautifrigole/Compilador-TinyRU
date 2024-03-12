import reader.Reader;

import java.util.*;

public class Lexer {
    private Reader reader;
    private String currentStrToken;

    private boolean isStringOpen = false;
    public Lexer(Reader reader) {
        this.reader = reader;
    }



    public LexerToken getNextToken() {
        StringBuilder currentToken = new StringBuilder();
        Character ch = reader.getCurrentChar();

        while(ch==' ' || ch=='\n'){
            reader.nextChar();
            ch = reader.getCurrentChar();
        }

        int startingColumn = reader.getCurrentColumn();

        if (TokenSeparator.singleSeparators.contains(ch)) {
            reader.nextChar();
            TokenID tokenID = TokenIDDictionary.getTokenCharID(ch);
            return new LexerToken(tokenID, ch.toString(),
                    reader.getCurrentLine(), startingColumn);
        } else {
            if (TokenSeparator.doubleSeparators.contains(ch)) {
                reader.nextChar();
                Character nextCh = reader.getCurrentChar();
                if (ch == nextCh){
                    currentToken.append(ch + nextCh);
                    return new LexerToken(TokenIDDictionary.getTokenStrID(currentToken.toString()), currentToken.toString(),
                            reader.getCurrentLine(), startingColumn);
                }else{
                    //TODO: Tirar error y parar ejecución (Falta de caracter)
                    System.out.println("Falta caracter: " + ch);
                }
            }
            else{
                if (TokenSeparator.singleOrDoubleSeparators.contains(ch)){
                    currentToken.append(ch);
                    reader.nextChar();
                    Character nextCh = reader.getCurrentChar();
                    List<Character> continuationList = TokenSeparator.getSepContinuation(ch);
                    if( continuationList!= null && continuationList.contains(nextCh)){
                        currentToken.append(nextCh);

                        if(currentToken.toString().equals("/?")){
                            while (ch != '\n') {
                                reader.nextChar();
                                ch = reader.getCurrentChar();
                            }
                            reader.nextChar();
                            ch = reader.getCurrentChar();
                            return getNextToken();
                        }
                        else{
                            reader.nextChar();
                            return new LexerToken(TokenIDDictionary.getTokenStrID(currentToken.toString()), currentToken.toString(),
                                    reader.getCurrentLine(), startingColumn);
                        }

                    }

                    return new LexerToken(TokenIDDictionary.getTokenCharID(ch), ch.toString(),
                            reader.getCurrentLine(), startingColumn);

                }



            }
        }

        if(!isStringOpen && ch=='"'){
            isStringOpen = true;
            while (isStringOpen){
                currentToken.append(ch);
                reader.nextChar();
                ch = reader.getCurrentChar();
                if (ch == null || ch==(char)-1){
                    System.out.println("No se cerró comillas");
                    //TODO: Tirar error y parar ejecución (Falta cerrar comillas)
                }
                isStringOpen = ch != '"';

            }
            currentToken.append(ch);
            reader.nextChar();
            ch = reader.getCurrentChar();

        }
        else{
            while (!TokenSeparator.isSeparator(ch) && ch!=' ') {
                currentToken.append(ch);
                reader.nextChar();
                ch = reader.getCurrentChar();
            }
        }





        return new LexerToken(TokenID.NONE, currentToken.toString(),
                reader.getCurrentLine(), startingColumn);
    }
}
