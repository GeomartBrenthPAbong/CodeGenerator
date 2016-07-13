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
}
