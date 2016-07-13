package org.lambda_n2t.codegenerator;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public class FnsTokenizerDecorator extends TokenizerDecorator{
    public FnsTokenizerDecorator(AbstractTokenizer tokenizer){
        super(tokenizer);
    }

    @Override
    public void tokenize(String input) {
        this.tokenizer.tokenize(input);

        this.markDataTypes(this.tokenizer.getTokens());
    }
}
