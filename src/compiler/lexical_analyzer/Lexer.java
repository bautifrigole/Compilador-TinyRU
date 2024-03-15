package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.*;
import compiler.lexical_analyzer.reader.Reader;

import java.util.*;

/**
 * Clase encargada de realizar el análisis léxico.
 */
public class Lexer {
    private final Reader reader;
    private StringBuilder currentLexeme;
    private int tokenStartingColumn;
    private boolean isStringOpen = false;
    private List<Character> specialCharacters = Arrays.stream(new Character[]{'t', 'r', 'n'}).toList();
    private List<Character> spanishCharacters = Arrays.stream(new Character[]{
            'á', 'é', 'í', 'ó', 'ú', 'Á', 'É', 'Í', 'Ó', 'Ú', 'ñ', 'Ñ', 'ü', 'Ü'}).toList();

    /**
     * @param reader Reader de dónde se irán solicitando los caracteres a analizar
     * @author Bautista Frigolé
     * Constructor de la clase.
     */
    public Lexer(Reader reader) {
        this.reader = reader;
    }

    /**
     * @return compiler.lexical_analyzer.LexerToken con la información del siguiente token
     * @throws LexicalException Excepción de tipo léxica
     * @author Bautista Frigolé
     * Busca el siguiente token a formar en el código fuente.
     */
    public LexerToken getNextToken() throws LexicalException {
        currentLexeme = new StringBuilder();
        Character ch = reader.getCurrentChar();

        if (ch == null) {
            return new LexerToken(TokenID.TOKEN_EOF, (String) null,
                    reader.getCurrentLine(), tokenStartingColumn);
        }

        if (ch == '\0') {
            throw new CannotResolveSymbolException(reader.getCurrentLine(),
                    tokenStartingColumn, ch);
        }

        while (ch == ' ' || ch == '\n') {
            reader.nextChar();
            ch = reader.getCurrentChar();
            if (ch == null) {
                return new LexerToken(TokenID.TOKEN_EOF, (String) null,
                        reader.getCurrentLine(), tokenStartingColumn);
            }
        }

        tokenStartingColumn = reader.getCurrentColumn();

        if (TokenSeparator.singleSeparators.contains(ch)) {
            return getSingleLexerToken();
        } else {
            if (TokenSeparator.doubleSeparators.contains(ch)) {
                return getDoubleLexerToken();
            } else {
                if (TokenSeparator.singleOrDoubleSeparators.contains(ch)) {
                    return getSingleOrDoubleLexerToken();
                }
            }
        }

        if (ch == '"') {
            return getStringLiteralLexerToken();
        } else {
            if (ch == '\'') {
                return getCharLiteralLexerToken();
            } else {
                if (Character.isDigit(ch)) {
                    return getIntLiteralLexerToken(ch);
                } else {
                    ch = buildIdentifierOrKeyWordLexeme();
                    TokenID tokenID = TokenClassifier.getTokenStrID(currentLexeme.toString());

                    if (tokenID != null) {
                        return new LexerToken(tokenID, currentLexeme.toString(),
                                reader.getCurrentLine(), tokenStartingColumn);
                    } else {
                        tokenID = getIdentifierTokenID(currentLexeme.toString());
                        if (tokenID != null) {
                            return new LexerToken(tokenID, currentLexeme.toString(),
                                    reader.getCurrentLine(), tokenStartingColumn);
                        }
                    }
                }

                if (ch == null) {
                    return new LexerToken(TokenID.TOKEN_EOF, (String) null,
                            reader.getCurrentLine(), tokenStartingColumn);
                }
            }
        }

        return new LexerToken(TokenID.NONE, currentLexeme.toString(),
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
        if (ch == nextCh) {
            reader.nextChar();
            currentLexeme.append(ch);
            currentLexeme.append(nextCh);
            return new LexerToken(TokenClassifier.getTokenStrID(currentLexeme.toString()), currentLexeme.toString(),
                    reader.getCurrentLine(), tokenStartingColumn);
        } else {
            throw new MissingOperationCharacter(reader.getCurrentLine(), tokenStartingColumn, ch, ch);
        }
    }

    private LexerToken getSingleOrDoubleLexerToken() throws LexicalException {
        Character ch = reader.getCurrentChar();
        currentLexeme.append(ch);
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        List<Character> continuationList = TokenSeparator.getSepContinuation(ch);
        if (continuationList != null && continuationList.contains(nextCh)) {
            currentLexeme.append(nextCh);

            if (currentLexeme.toString().equals("/?")) {
                while (ch != '\n') {
                    reader.nextChar();
                    ch = reader.getCurrentChar();
                }
                reader.nextChar();
                return getNextToken();
            } else {
                reader.nextChar();
                return new LexerToken(TokenClassifier.getTokenStrID(currentLexeme.toString()),
                        currentLexeme.toString(), reader.getCurrentLine(), tokenStartingColumn);
            }
        }

        return new LexerToken(TokenClassifier.getTokenCharID(ch), ch.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getStringLiteralLexerToken() throws LexicalException {
        isStringOpen = true;
        reader.nextChar();
        while (isStringOpen) {
            buildStringLiteral();
            reader.nextChar();
        }

        return new LexerToken(TokenID.TOKEN_LITERAL_STR, currentLexeme.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private void buildStringLiteral() throws LexicalException {
        Character ch = reader.getCurrentChar();

        if (ch == null || ch == '\n') {
            throw new UnclosedStrException(reader.getCurrentLine(), tokenStartingColumn);
        } else {
            if (ch == '\\'){
                Character nextCh;
                reader.nextChar();
                nextCh = reader.getCurrentChar();

                // End of File is received
                if (nextCh == null) {
                    throw new UnclosedStrException(reader.getCurrentLine(), tokenStartingColumn);
                } else {
                    // Invalid character '\0'
                    if (nextCh == '0') {
                        throw new CannotResolveSymbolException(reader.getCurrentLine(),
                                tokenStartingColumn, '\0');
                    }
                    else {
                        currentLexeme.append(ch);
                        currentLexeme.append(nextCh);
                    }
                }
            }
            else {
                if (isInvalidCharacter(ch)) {
                    throw new CannotResolveSymbolException(reader.getCurrentLine(),
                            tokenStartingColumn, ch);
                }
            }
        }

        isStringOpen = ch != '"';
        if (isStringOpen){
            currentLexeme.append(ch);
        }
    }

    private LexerToken getCharLiteralLexerToken() throws LexicalException {
        Character ch;
        reader.nextChar();
        ch = reader.getCurrentChar();
        if (ch == null) {
            throw new UnclosedCharException(reader.getCurrentLine(), tokenStartingColumn);
        }
        if (ch == '\\') {
            LexerToken lexerTokenBackslash = getLexerTokenStartingWithBackslash(ch);
            if (lexerTokenBackslash != null) {
                return lexerTokenBackslash;
            }
        } else {
            if (ch == '\'') {
                throw new EmptyCharException(reader.getCurrentLine(), tokenStartingColumn);
            } else {
                if (ch == '\n') {
                    throw new UnclosedCharException(reader.getCurrentLine(), tokenStartingColumn);
                } else {
                    if (isInvalidCharacter(ch)) {
                        throw new CannotResolveSymbolException(reader.getCurrentLine(), tokenStartingColumn, ch);
                    }
                    currentLexeme.append(ch);
                }
            }

        }
        reader.nextChar();
        ch = reader.getCurrentChar();

        if (ch != '\'') {
            throw new UnclosedCharException(reader.getCurrentLine(), tokenStartingColumn);
        }

        reader.nextChar();
        return new LexerToken(TokenID.TOKEN_LITERAL_CHAR, currentLexeme.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private LexerToken getLexerTokenStartingWithBackslash(Character ch) throws LexicalException {
        Character nextCh;
        reader.nextChar();
        nextCh = reader.getCurrentChar();

        // End of File is received
        if (nextCh == null) {
            throw new UnclosedCharException(reader.getCurrentLine(), tokenStartingColumn);
        } else {
            // Invalid character '\0'
            if (nextCh == '0') {
                throw new CannotResolveSymbolException(reader.getCurrentLine(),
                        tokenStartingColumn, '\0');
            } else {
                if (nextCh == '\'') {
                    reader.nextChar();
                    nextCh = reader.getCurrentChar();

                    // Invalid character '\'
                    if (nextCh == null || nextCh != '\'') {
                        throw new UnclosedCharException(reader.getCurrentLine(), tokenStartingColumn);
                    } else {
                        // Valid character '\''
                        currentLexeme.append(nextCh);
                        reader.nextChar();
                        return new LexerToken(TokenID.TOKEN_LITERAL_CHAR, currentLexeme.toString(),
                                reader.getCurrentLine(), tokenStartingColumn);
                    }
                } else {
                    if (specialCharacters.contains(nextCh)) {
                        currentLexeme.append(ch);
                    }
                }
                if (isInvalidCharacter(nextCh)) {
                    throw new CannotResolveSymbolException(reader.getCurrentLine(), tokenStartingColumn, nextCh);
                }
                currentLexeme.append(nextCh);
            }
        }
        return null;
    }

    private LexerToken getIntLiteralLexerToken(Character ch) throws UnexpectedCharacterException {
        while (!TokenSeparator.isSeparator(ch) && ch != ' ') {
            if (!Character.isDigit(ch)) {
                throw new UnexpectedCharacterException(reader.getCurrentLine(),
                        tokenStartingColumn, currentLexeme.toString(), ch);
            }
            currentLexeme.append(ch);
            reader.nextChar();
            ch = reader.getCurrentChar();
        }

        return new LexerToken(TokenID.TOKEN_LITERAL_INT, currentLexeme.toString(),
                reader.getCurrentLine(), tokenStartingColumn);
    }

    private Character buildIdentifierOrKeyWordLexeme() throws InvalidIdentifierException {
        Character ch = reader.getCurrentChar();
        while (ch != null && !TokenSeparator.isSeparator(ch) && ch != ' ' && ch != '\n') {
            currentLexeme.append(ch);
            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch != '_') {
                throw new InvalidIdentifierException(reader.getCurrentLine(),
                        tokenStartingColumn, currentLexeme.toString(), ch);
            }
            reader.nextChar();
            ch = reader.getCurrentChar();
        }
        return ch;
    }

    private TokenID getIdentifierTokenID(String str) throws InvalidIdentifierException {
        char firstChar = str.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            if (str.length() == 1) {
                return TokenID.TOKEN_ID_CLASS;
            }
            char lastChar = str.charAt(str.length() - 1);
            if (Character.isAlphabetic(lastChar)) {
                return TokenID.TOKEN_ID_CLASS;
            } else {
                throw new InvalidIdentifierException(reader.getCurrentLine(),
                        tokenStartingColumn, str, lastChar);
            }
        }
        if (Character.isAlphabetic(firstChar)) {
            return TokenID.TOKEN_ID_OBJ;
        } else {
            throw new InvalidIdentifierException(reader.getCurrentLine(),
                    tokenStartingColumn, str, firstChar);
        }
    }

    private boolean isInvalidCharacter(Character ch) {
        int asciiCode = (int) ch;
        return !((asciiCode >= 32 && asciiCode <= 126) || spanishCharacters.contains(ch));
    }
}
