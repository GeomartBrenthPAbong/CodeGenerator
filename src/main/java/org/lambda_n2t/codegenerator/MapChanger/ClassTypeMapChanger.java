package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class ClassTypeMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map<String, String> classType = (Map<String, String>) map.get("type");
        Map<String, String> inputType = new HashMap<String, String>();

        for (Pair<String, String> token: tokens)
            inputType.put(token.getKey(), token.getValue());

        if (classType.size() > 0) {
            for (Map.Entry<String, String> entry : classType.entrySet()) {
                if (inputType.containsKey(entry.getKey()))
                    classType.put(entry.getKey(), inputType.get(entry.getKey()));
                else
                    classType.remove(entry.getKey());
            }
        }
        else
            map.put("type", inputType);
    }
}
