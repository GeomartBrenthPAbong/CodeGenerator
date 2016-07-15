package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class RMConstructorMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map constructors = (Map) map.get("constructors");

        if (tokens.size() == 1 && tokens.get(0).getKey().equals("all"))
            constructors.clear();
        else
            constructors.remove(this.extractConstructorParamType(tokens));
    }

    private String extractConstructorParamType(List<Pair<String, String>> tokens){
        StringBuilder key = new StringBuilder();

        for (Pair<String, String> token: tokens) {
            if (token.getKey().equals("dataType"))
                key.append(token.getValue() + "|");
        }

        if (key.length() > 0)
            key.deleteCharAt(key.length() - 1);
        else
            key.append("constructor");

        return key.toString();
    }
}
