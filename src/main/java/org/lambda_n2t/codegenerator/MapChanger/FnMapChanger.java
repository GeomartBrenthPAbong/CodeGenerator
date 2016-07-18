package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */

public class FnMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map<String, String> fnData = new HashMap<String, String>();
        Map<String, String> versions;

        int index = 0;

        for (Pair<String, String> token: tokens) {
            if (token.getKey().equals("openPar"))
                break;

            fnData.put(token.getKey(), token.getValue());
            index++;
        }

        Map fnsmap = (Map) map.get("fns");
        Map curFn = fnData;

        if (fnsmap.containsKey(fnData.get("identifier"))){
            curFn = (Map) fnsmap.get(fnData.get("identifier"));
            String[] keyLists = new String[]{"accessibility", "static", "final", "dataType", "identifier"};

            for (String key: keyLists){
                if (!fnData.containsKey(key))
                    curFn.remove(key);
                else
                    curFn.put(key, fnData.get(key));
            }
        }
        else
            fnsmap.put(fnData.get("identifier"), curFn);

        Pair<String, String> version = this.extractVersion(tokens, index);

        if (curFn.containsKey("versions"))
            versions = (Map<String, String>) curFn.get("versions");
        else {
            versions = new LinkedHashMap<String, String>();
            curFn.put("versions", versions);
        }

        versions.put(version.getKey(), version.getValue());
    }

    private Pair<String, String> extractVersion(List<Pair<String, String>> tokens, int index){
        if (index < tokens.size() - 1){
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();
            Pair<String, String> token;

            for (; index < tokens.size(); index++) {
                token = tokens.get(index);

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
                key.append("null");

            return new Pair<String, String>(key.toString(), value.toString());
        }
        else
            return new Pair<String, String>("null", "");
    }
}
