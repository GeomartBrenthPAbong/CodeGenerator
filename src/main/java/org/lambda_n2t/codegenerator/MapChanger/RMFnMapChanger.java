package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class RMFnMapChanger implements MapChanger{
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map fns = (Map) map.get("fns");

        if (tokens.size() == 1 && tokens.get(0).getKey().equals("all")) {
            fns.clear();
            return;
        }

        String fnName = "";
        int index = 0;

        for (Pair<String, String> token: tokens) {
            if (token.getKey().equals("openPar"))
                break;
            else if(token.getKey().equals("identifier"))
                fnName = token.getValue();
            index++;
        }

        String versionKey = this.extractVersionKey(tokens, index);

        if (fns.containsKey(fnName)) {
            Map fnData = (Map) fns.get(fnName);
            Map versions = (Map) fnData.get("versions");

            versions.remove(versionKey);

            if (versions.isEmpty())
                fns.remove(fnName);
        }
    }

    private String extractVersionKey(List<Pair<String, String>> tokens, int index){
        if (index < tokens.size() - 1){
            StringBuilder key = new StringBuilder();

            for (Pair<String, String> token: tokens) {
                if (token.getKey().equals("dataType"))
                    key.append(token.getValue() + "|");
            }

            if (key.length() > 0)
                key.deleteCharAt(key.length() - 1);
            else
                key.append("null");

            return key.toString();
        }
        else
            return "null";
    }
}
