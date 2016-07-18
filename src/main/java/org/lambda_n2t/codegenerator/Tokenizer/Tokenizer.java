package org.lambda_n2t.codegenerator.Tokenizer;

import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public class Tokenizer implements AbstractTokenizer {
    private List<Pair<String, String>> tokens;
    private ArrayList<Pair<String, Pattern>> tokenClassPatterns;
    private Map oppositesMap;

    public Tokenizer(){
        this.tokenClassPatterns = new ArrayList<Pair<String, Pattern>>();
        this.tokenClassPatterns.add(new Pair<String, Pattern>("accessibility", TokenClass.accessibilityPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("static", TokenClass.cgStaticPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("final", TokenClass.cgFinalPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("identifier", TokenClass.identifierPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("dataType", TokenClass.dataTypePattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("openPar", TokenClass.openParPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("closePar", TokenClass.closeParPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("imports", TokenClass.importsSpecificPattern));
        this.tokenClassPatterns.add(new Pair<String, Pattern>("all", TokenClass.allPattern));

        this.oppositesMap = new HashMap();
        this.oppositesMap.put("<", 1);
        this.oppositesMap.put(">", -1);
        this.oppositesMap.put(1, "<");
        this.oppositesMap.put(-1, ">");
    }

    public List<Pair<String, String>> getTokens(){
        return this.tokens;
    }

    public void tokenize(String input){
        this.tokens = new ArrayList<Pair<String, String>>();

        input = input.trim();
        if (input.length() == 0)
            return;

        StringBuilder strBuilder = new StringBuilder();
        String currentChar;
        String accumulatedChars;
        String tokenClass;
        int opposites = 0;
        int pendingPar = 0;
        boolean hasComma = false;
        boolean hasPar = false;

        for (int i = 0; i < input.length(); i++){
            strBuilder.append(input.charAt(i));
            currentChar = String.valueOf(input.charAt(i));

            Matcher specialCharMatcher = TokenClass.specialCharPattern.matcher(currentChar);
            Matcher separatorMatcher = TokenClass.separatorPattern.matcher(currentChar);
            Matcher oppositesMatcher = TokenClass.oppositesPattern.matcher(currentChar);

            if (oppositesMatcher.matches())
                opposites += (Integer) this.oppositesMap.get(currentChar);

            if (specialCharMatcher.matches() && opposites == 0){
                accumulatedChars = strBuilder.toString().trim();
                Pair<String, String> token = this.createToken(this.identifyTokenClass(accumulatedChars),
                        accumulatedChars);
                strBuilder.delete(0, strBuilder.length());

                this.tokens.add(token);
            }
            else if(separatorMatcher.matches() && opposites == 0){
                if (!currentChar.equals("|"))
                    strBuilder.deleteCharAt(strBuilder.length() - 1);

                if (strBuilder.toString().trim().length() > 0) {
                    accumulatedChars = strBuilder.toString().trim();
                    tokenClass = this.identifyTokenClass(accumulatedChars);

                    if (currentChar.equals("|"))
                        accumulatedChars = accumulatedChars.substring(0, accumulatedChars.length() - 1).toString().trim();

                    Pair<String, String> token = this.createToken(tokenClass, accumulatedChars);
                    strBuilder.delete(0, strBuilder.length());

                    this.tokens.add(token);
                }

                Matcher parMatcher = TokenClass.parPattern.matcher(currentChar);

                if (parMatcher.matches()){
                    Pair<String, String> parToken = this.createToken(this.identifyTokenClass(currentChar),
                                                                     currentChar);
                    this.tokens.add(parToken);

                    if (currentChar.equals("(")) {
                        pendingPar += 1;
                        hasComma = false;
                        hasPar = true;
                    }
                    else if(hasComma) {
                        tokens.add(this.createToken("closePar", ")"));
                        hasPar = false;
                    }
                }
                else if (currentChar.equals(",")) {
                    hasComma = true;

                    if(pendingPar > 0) {
                        tokens.add(null);
                        this.encloseInPar(tokens);
                        pendingPar -= 1;
                    }

                    if(hasPar)
                        tokens.add(this.createToken("closePar", ")"));

                    tokens.add(this.createToken("comma", ","));

                    if(hasPar)
                        tokens.add(this.createToken("openPar", "("));
                }
                else if(currentChar.equals(":")) {
                    tokens.add(this.createToken("colon", ":"));
                }
            }
        }

        accumulatedChars = strBuilder.toString().trim();

        if (accumulatedChars.length() > 0){
            Pair<String, String> token = this.createToken(this.identifyTokenClass(accumulatedChars),
                                                          accumulatedChars);

            this.tokens.add(token);
        }
    }

    private String identifyTokenClass(String value){
        value = value.trim();
        Matcher matcher;

        for (Pair<String, Pattern> tokenClassPattern: this.tokenClassPatterns){
            matcher = tokenClassPattern.getValue().matcher(value);

            if(matcher.matches())
                return tokenClassPattern.getKey();
        }

        return "NONE";
    }

    private Pair<String, String> createToken(String clsName, String value){
        return new Pair<String, String>(clsName, value.trim());
    }

    private void encloseInPar(List<Pair<String, String>> tokens){
        for (int i = tokens.size() - 2; i > 0; i--){
            tokens.set(i + 1, tokens.get(i));

            if (tokens.get(i).getKey().equals("openPar")){
                tokens.set(i, this.createToken("openPar", "("));
                break;
            }
        }
    }
}
