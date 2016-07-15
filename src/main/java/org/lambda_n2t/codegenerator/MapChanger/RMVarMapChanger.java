package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class RMVarMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map vars = (Map) map.get("vars");
        Map s = (Map) map.get("s");
        Map g = (Map) map.get("g");

        if (tokens.size() == 1 && tokens.get(0).getKey().equals("all")) {
            vars.clear();
            s.clear();
            g.clear();
            return;
        }

        String varName = "";

        // Doesn't really need a for loop but just to be safe.
        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("identifier"))
                varName = token.getValue();

        vars.remove(varName);
        s.remove(varName);
        g.remove(varName);
    }
}
