package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class PNameMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        String varName = "";

        // Doesn't really need a for loop but just to be safe.
        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier"))
                varName = token.getValue();

        map.put("pname", varName.trim());
    }
}
