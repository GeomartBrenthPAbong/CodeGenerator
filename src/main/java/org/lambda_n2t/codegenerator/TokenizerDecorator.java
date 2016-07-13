package org.lambda_n2t.codegenerator;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public abstract class TokenizerDecorator implements AbstractTokenizer {
    protected AbstractTokenizer tokenizer;

    public TokenizerDecorator(AbstractTokenizer tokenizer){
        this.tokenizer = tokenizer;
    }

    public List<Pair<String, String>> getTokens(){
        return this.tokenizer.getTokens();
    }

    public void tokenize(String input){
        this.tokenizer.tokenize(input);
    }

    protected void markDataTypes(List<Pair<String, String>> tokens){
        Pair<String, String> curToken;

        for (int i = 0; i < tokens.size(); i++){
            curToken = tokens.get(i);

            if (curToken.getKey().equals("identifier") &&
                    (i + 1 < tokens.size() && tokens.get(i + 1).getKey().equals("identifier"))){
                tokens.set(i, new Pair<String, String>("dataType", curToken.getValue()));
            }
            else if(curToken.getKey().equals("dataType"))
                i++;
        }
    }
}
