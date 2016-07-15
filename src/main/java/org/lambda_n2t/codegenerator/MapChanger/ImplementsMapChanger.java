package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ImplementsMapChanger implements MapChanger{
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map implementNames = (Map) map.get("implements");

        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier"))
                implementNames.put(token.getValue(), token.getValue());
    }
}
