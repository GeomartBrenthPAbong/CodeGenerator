package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class RMImportsMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map importNames = (Map) map.get("imports");

        if (tokens.size() == 1 && tokens.get(0).getKey().equals("all")) {
            importNames.clear();
            return;
        }

        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier") || token.getKey().equals("imports"))
                importNames.remove(token.getValue());
    }
}
