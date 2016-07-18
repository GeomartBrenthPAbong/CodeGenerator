package org.lambda_n2t.codegenerator.Tokenizer;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public interface AbstractTokenizer {
    public List<Pair<String, String>> getTokens();
    public void tokenize(String input);
}
