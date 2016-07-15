package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class RMImplementsMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map implementNames = (Map) map.get("implements");

        if (tokens.size() == 1 && tokens.get(0).getKey().equals("all")) {
            implementNames.clear();
            return;
        }

        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier"))
                implementNames.remove(token.getValue());
    }
}
