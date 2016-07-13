package org.lambda_n2t.codegenerator;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class TokenizerFactory {
    public static AbstractTokenizer create(String type){
        Tokenizer tokenizer = new Tokenizer();

        if (type.equals("vars"))
            return new VarsTokenizerDecorator(tokenizer);
        else if(type.equals("fns"))
            return new FnsTokenizerDecorator(tokenizer);
        else if(type.equals("tokenizer"))
            return tokenizer;
        else
            throw new IllegalArgumentException("Invalid tokenizer.");
    }
}
