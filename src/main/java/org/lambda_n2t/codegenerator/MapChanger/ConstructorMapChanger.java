package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class ConstructorMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Pair<String, String> params = this.extractConstructorParams(tokens);
        Map constructors = (Map) map.get("constructors");
        constructors.put(params.getKey(), params.getValue());
    }

    private Pair<String, String> extractConstructorParams(List<Pair<String, String>> tokens){
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        for (Pair<String, String> token: tokens) {
            if (token.getKey().equals("dataType")) {
                key.append(token.getValue() + "|");
                value.append(token.getValue() + " ");
            }
            else if(token.getKey().equals("identifier")){
                value.append(token.getValue());
            }
            else if(token.getKey().equals("comma"))
                value.append(", ");
        }

        if (key.length() > 0)
            key.deleteCharAt(key.length() - 1);
        else
            key.append("class");

        return new Pair<String, String>(key.toString(), value.toString());
    }
}
