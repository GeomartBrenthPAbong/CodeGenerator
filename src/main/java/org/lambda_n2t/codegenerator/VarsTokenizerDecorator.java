package org.lambda_n2t.codegenerator;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class VarsTokenizerDecorator extends TokenizerDecorator {
    public VarsTokenizerDecorator(AbstractTokenizer tokenizer){
        super(tokenizer);
    }

    @Override
    public void tokenize(String input) {
        this.tokenizer.tokenize(input);

        this.markDataTypes(this.tokenizer.getTokens());
    }
}
