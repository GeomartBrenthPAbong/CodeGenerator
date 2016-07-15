package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ImportsMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map importNames = (Map) map.get("imports");

        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier") || token.getKey().equals("imports"))
                importNames.put(token.getValue(), token.getValue());
    }
}
