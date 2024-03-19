package compiler.lexical_analyzer;

import compiler.exceptions.lexical_exceptions.*;
import compiler.lexical_analyzer.reader.Reader;

import java.util.*;

/**
 * Clase encargada de realizar el análisis léxico.
 * @author Bautista Frigolé y Francisco Devaux
 */
public class Lexer {
    /**
     * Reader a utilizar.
     */
    private final Reader reader;
    /**
     * StringBuilder sobre el cual se irá formando el lexema actual.
     */
    private StringBuilder currentLexeme;
    /**
     * Número de columna en la cual empieza a formarse el lexema.
     */
    private int lexemeStartingColumn;
    /**
     * Indica si en el lexema actual se está formando un literal String.
     */
    private boolean isStringOpen = false;
    /**
     * Lista con los caracteres especiales a considerar después de '\'.
     */
    private final List<Character> specialCharacters = Arrays.asList('t', 'r', 'n');
    /**
     * Lista con los caracteres de uso en español.
     */
    private final List<Character> spanishCharacters = Arrays.asList(
            'á', 'é', 'í', 'ó', 'ú', 'Á', 'É', 'Í', 'Ó', 'Ú', 'ñ', 'Ñ', 'ü', 'Ü');


    /**
     * Constructor de la clase.
     * @param reader Reader de dónde se irán solicitando los caracteres a analizar
     * @author Bautista Frigolé
     */
    public Lexer(Reader reader) {
        this.reader = reader;
    }

    /**
     * Busca el siguiente token a formar en el código fuente.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    public LexerToken getNextToken() throws LexicalException {
        currentLexeme = new StringBuilder();
        Character ch = reader.getCurrentChar();

        if (ch == null) {
            return new LexerToken(TokenID.TOKEN_EOF, null,
                    reader.getCurrentLine(), lexemeStartingColumn);
        }

        if (ch == '\0') {
            throw new CannotResolveSymbolException(reader.getCurrentLine(),
                    reader.getCurrentColumn(), ch);
        }

        while (ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r' || ch == (char) 11) {
            reader.nextChar();
            ch = reader.getCurrentChar();
            if (ch == null) {
                return new LexerToken(TokenID.TOKEN_EOF, null,
                        reader.getCurrentLine(), lexemeStartingColumn);
            }
        }

        lexemeStartingColumn = reader.getCurrentColumn();

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
                    return getIntLiteralLexerToken();
                } else {
                    ch = buildIdentifierOrKeyWordLexeme();
                    TokenID tokenID = TokenClassifier.getTokenStrID(currentLexeme.toString());

                    if (tokenID != null) {
                        return new LexerToken(tokenID, currentLexeme.toString(),
                                reader.getCurrentLine(), lexemeStartingColumn);
                    } else {
                        tokenID = getIdentifierTokenID(currentLexeme.toString());
                        if (tokenID != null) {
                            return new LexerToken(tokenID, currentLexeme.toString(),
                                    reader.getCurrentLine(), lexemeStartingColumn);
                        }
                    }
                }

                if (ch == null) {
                    return new LexerToken(TokenID.TOKEN_EOF, null,
                            reader.getCurrentLine(), lexemeStartingColumn);
                }
            }
        }

        return new LexerToken(TokenID.NONE, currentLexeme.toString(),
                reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Devuelve el LexerToken formado por un token separador compuesto por un sólo carácter.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     */
    private LexerToken getSingleLexerToken() {
        Character ch = reader.getCurrentChar();
        reader.nextChar();
        TokenID tokenID = TokenClassifier.getTokenCharID(ch);
        return new LexerToken(tokenID, ch.toString(), reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Devuelve el LexerToken formado por un token separador compuesto por dos caracteres.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws MissingOperationCharacterException {@link MissingOperationCharacterException}.
     */
    private LexerToken getDoubleLexerToken() throws MissingOperationCharacterException {
        Character ch = reader.getCurrentChar();
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        if (ch == nextCh) {
            reader.nextChar();
            currentLexeme.append(ch);
            currentLexeme.append(nextCh);
            return new LexerToken(TokenClassifier.getTokenStrID(currentLexeme.toString()), currentLexeme.toString(),
                    reader.getCurrentLine(), lexemeStartingColumn);
        } else {
            throw new MissingOperationCharacterException(reader.getCurrentLine(), lexemeStartingColumn, ch, ch);
        }
    }

    /**
     * Devuelve el LexerToken formado por un token separador compuesto por uno o dos caracteres.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private LexerToken getSingleOrDoubleLexerToken() throws LexicalException {
        Character ch = reader.getCurrentChar();
        currentLexeme.append(ch);
        reader.nextChar();
        Character nextCh = reader.getCurrentChar();
        List<Character> continuationList = TokenSeparator.getSepContinuation(ch);
        if (continuationList != null && continuationList.contains(nextCh)) {
            currentLexeme.append(nextCh);

            if (currentLexeme.toString().equals("/?")) {

                reader.nextChar();
                ch = reader.getCurrentChar();
                while (ch != null && ch != '\n') {
                    if (ch == '\\') {
                        reader.nextChar();
                        ch = reader.getCurrentChar();
                        if (ch != null && ch == '0') {
                            throw new CannotResolveSymbolException(reader.getCurrentLine(),
                                    reader.getCurrentColumn(), '\0');
                        }
                    }

                    if (isInvalidCharacter(ch)) {
                        throw new CannotResolveSymbolException(reader.getCurrentLine(), reader.getCurrentColumn(), ch);
                    }
                    reader.nextChar();
                    ch = reader.getCurrentChar();
                }

                reader.nextChar();
                return getNextToken();
            } else {
                reader.nextChar();
                return new LexerToken(TokenClassifier.getTokenStrID(currentLexeme.toString()),
                        currentLexeme.toString(), reader.getCurrentLine(), lexemeStartingColumn);
            }
        }

        return new LexerToken(TokenClassifier.getTokenCharID(ch), ch.toString(),
                reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Devuelve el LexerToken formado por un token válido de tipo literal String.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private LexerToken getStringLiteralLexerToken() throws LexicalException {
        int maxStringLength = 1024;
        isStringOpen = true;
        reader.nextChar();
        while (isStringOpen) {
            if (currentLexeme.length() > maxStringLength) {
                throw new StringSizeException(reader.getCurrentLine(), lexemeStartingColumn);
            }

            buildStringLiteral();
            reader.nextChar();
        }

        return new LexerToken(TokenID.TOKEN_LITERAL_STR, currentLexeme.toString(),
                reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Construye un literal String válido dentro de {@link Lexer#currentLexeme}.
     * @author Bautista Frigolé y Francisco Devaux
     * @throws LexicalException Excepción de tipo léxica.
     */
    private void buildStringLiteral() throws LexicalException {
        Character ch = reader.getCurrentChar();

        if (ch == null || ch == '\n') {
            throw new UnclosedStrException(reader.getCurrentLine(), lexemeStartingColumn);
        } else {
            if (ch == '\\') {
                Character nextCh;
                reader.nextChar();
                nextCh = reader.getCurrentChar();

                // End of File is received
                if (nextCh == null) {
                    throw new UnclosedStrException(reader.getCurrentLine(), lexemeStartingColumn);
                } else {
                    // Invalid character '\0'
                    if (nextCh == '0') {
                        throw new CannotResolveSymbolException(reader.getCurrentLine(),
                                lexemeStartingColumn, '\0');
                    } else {

                        if(nextCh!='"' && nextCh!='\\'){
                            currentLexeme.append(ch);

                        }
                        currentLexeme.append(nextCh);
                    }
                }
            } else {
                if (isInvalidCharacter(ch)) {
                    throw new CannotResolveSymbolException(reader.getCurrentLine(),
                            reader.getCurrentColumn(), ch);
                }
                else {
                    isStringOpen = ch != '"';
                    if (isStringOpen) {
                        currentLexeme.append(ch);
                    }
                }
            }
        }
    }

    /**
     * Devuelve el LexerToken formado por un token válido de tipo literal Character.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private LexerToken getCharLiteralLexerToken() throws LexicalException {
        reader.nextChar();
        Character ch = reader.getCurrentChar();
        if (ch == null) {
            throw new UnclosedCharException(reader.getCurrentLine(), lexemeStartingColumn);
        }
        if (ch == '\\') {
            LexerToken lexerTokenBackslash = getLexerTokenStartingWithBackslash();
            if (lexerTokenBackslash != null) {
                return lexerTokenBackslash;
            }
        } else {
            if (ch == '\'') {
                throw new EmptyCharException(reader.getCurrentLine(), lexemeStartingColumn);
            } else {
                if (ch == '\n') {
                    throw new UnclosedCharException(reader.getCurrentLine(), lexemeStartingColumn);
                } else {
                    if (isInvalidCharacter(ch)) {
                        throw new CannotResolveSymbolException(reader.getCurrentLine(), reader.getCurrentColumn(), ch);
                    }
                    currentLexeme.append(ch);
                }
            }

        }
        reader.nextChar();
        ch = reader.getCurrentChar();

        if (ch == null || ch != '\'') {
            throw new UnclosedCharException(reader.getCurrentLine(), lexemeStartingColumn);
        }

        reader.nextChar();
        return new LexerToken(TokenID.TOKEN_LITERAL_CHAR, currentLexeme.toString(),
                reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Devuelve el LexerToken formado por un token válido de tipo literal Character para
     * @author Bautista Frigolé y Francisco Devaux
     * el caso particular en el que comienza con '\'.
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private LexerToken getLexerTokenStartingWithBackslash() throws LexicalException {
        Character ch = reader.getCurrentChar();
        Character nextCh;
        reader.nextChar();
        nextCh = reader.getCurrentChar();

        // End of File is received
        if (nextCh == null) {
            throw new UnclosedCharException(reader.getCurrentLine(), lexemeStartingColumn);
        } else {
            // Invalid character '\0'
            if (nextCh == '0') {
                throw new CannotResolveSymbolException(reader.getCurrentLine(),
                        lexemeStartingColumn, '\0');
            } else {
                if (nextCh == '\'') {
                    reader.nextChar();
                    nextCh = reader.getCurrentChar();

                    // Invalid character '\'
                    if (nextCh == null || nextCh != '\'') {
                        throw new UnclosedCharException(reader.getCurrentLine(), lexemeStartingColumn);
                    } else {
                        // Valid character '\''
                        currentLexeme.append(nextCh);
                        reader.nextChar();
                        return new LexerToken(TokenID.TOKEN_LITERAL_CHAR, currentLexeme.toString(),
                                reader.getCurrentLine(), lexemeStartingColumn);
                    }
                } else {
                    if (specialCharacters.contains(nextCh)) {
                        currentLexeme.append(ch);
                    }
                }
                if (isInvalidCharacter(nextCh)) {
                    throw new CannotResolveSymbolException(reader.getCurrentLine(), lexemeStartingColumn, nextCh);
                }
                currentLexeme.append(nextCh);
            }
        }
        return null;
    }

    /**
     * Devuelve el LexerToken formado por un token válido de tipo literal Integer.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private LexerToken getIntLiteralLexerToken() throws LexicalException {
        int maxIntValue = 2147483647;
        int maxIntLength = 10;
        Character ch = reader.getCurrentChar();

        while (ch != null && !TokenSeparator.isSeparator(ch) && ch != ' ' && ch != '\n') {
            if (!Character.isDigit(ch)) {
                currentLexeme.append(ch);
                throw new UnexpectedCharacterException(reader.getCurrentLine(),
                        reader.getCurrentColumn(), currentLexeme.toString(), ch);
            }
            currentLexeme.append(ch);
            if (currentLexeme.toString().length() > maxIntLength || Long.parseLong(currentLexeme.toString()) > maxIntValue) {
                throw new IntSizeException(reader.getCurrentLine(), lexemeStartingColumn);
            }
            reader.nextChar();
            ch = reader.getCurrentChar();
        }


        return new LexerToken(TokenID.TOKEN_LITERAL_INT, currentLexeme.toString(),
                reader.getCurrentLine(), lexemeStartingColumn);
    }

    /**
     * Construye un lexema de tipo identificador o palabra reservada en {@link Lexer#currentLexeme}
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link LexerToken} con información del token.
     * @throws LexicalException {@link LexicalException}.
     */
    private Character buildIdentifierOrKeyWordLexeme() throws LexicalException {
        Character ch = reader.getCurrentChar();
        int maxIdentifierLength = 64;
        while (ch != null && !TokenSeparator.isSeparator(ch) && ch != ' ' && ch != '\n') {
            currentLexeme.append(ch);
            if (currentLexeme.length() > maxIdentifierLength) {
                throw new IdentifierLengthException(reader.getCurrentLine(), lexemeStartingColumn);
            }
            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch != '_') {
                throw new InvalidIdentifierException(reader.getCurrentLine(),
                        reader.getCurrentColumn(), currentLexeme.toString(), ch);
            }
            reader.nextChar();
            ch = reader.getCurrentChar();
        }
        return ch;
    }

    /**
     * Clasifica el String dado en identificador de clase o de objeto.
     * @author Bautista Frigolé y Francisco Devaux
     * @return {@link TokenID} del identificador.
     * @throws InvalidIdentifierException {@link InvalidIdentifierException}.
     */
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
                        lexemeStartingColumn, str, lastChar);
            }
        }
        if (Character.isAlphabetic(firstChar)) {
            return TokenID.TOKEN_ID_OBJ;
        } else {
            throw new InvalidIdentifierException(reader.getCurrentLine(),
                    lexemeStartingColumn, str, firstChar);
        }
    }

    /**
     * Indica si un caracter es inválido según nuestro alfabeto.
     * @author Francisco Devaux
     * @return boolean que es True si el caracter es invalido.
     */
    private boolean isInvalidCharacter(Character ch) {
        int asciiCode = (int) ch;
        return !((asciiCode >= 32 && asciiCode <= 126) || spanishCharacters.contains(ch));
    }
}
