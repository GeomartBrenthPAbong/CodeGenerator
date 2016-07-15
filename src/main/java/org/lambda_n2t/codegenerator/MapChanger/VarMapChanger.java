package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class VarMapChanger implements MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map) {
        Map<String, String> varsData = new HashMap<String, String>();
        List<Pair<String, String>> setterAndGetter;
        int index = 0;

        for (Pair<String, String> token: tokens) {
            if (token.getKey().equals("openPar"))
                break;

            varsData.put(token.getKey(), token.getValue());
            index++;
        }

        Map varsmap = (Map) map.get("vars");
        Map varmap;

        if (varsmap.containsKey(varsData.get("identifier")))
            varmap = (Map) varsmap.get(varsData.get("identifier"));
        else{
            varmap = new HashMap();
            varsmap.put(varsData.get("identifier"), varmap);
        }

        String[] keyLists = new String[]{"accessibility", "static", "final", "dataType", "identifier"};

        for (String key: keyLists){
            if (!varsData.containsKey(key))
                varmap.remove(key);
            else
                varmap.put(key, varsData.get(key));
        }

        setterAndGetter = this.processSettersAndGetters(tokens, varsData, index);
        Map sgHolder;
        for (Pair<String, String> sg: setterAndGetter) {
            sgHolder = (Map) map.get(sg.getKey());

            sgHolder.put(varsData.get("identifier"),
                    new Pair<String, String>(varsData.get("dataType"), sg.getValue()));
        }
    }

    private List<Pair<String, String>> processSettersAndGetters(List<Pair<String, String>> tokens,
                                                                Map<String, String> varsData,
                                                                int index){
        List<Pair<String, String>> setterAndGetter = new ArrayList<Pair<String, String>>();

        if (index < tokens.size() - 1){
            Stack<String> idHolder = new Stack<String>();
            String curKey;

            for (; index < tokens.size(); index++){
                curKey = tokens.get(index).getKey();

                if (curKey.equals("identifier"))
                    idHolder.push(tokens.get(index).getValue());
                else if(curKey.equals("colon"))
                    setterAndGetter.add(new Pair<String, String>(idHolder.pop(), tokens.get(++index).getValue()));
            }

            String id;
            while (!idHolder.empty()){
                id = idHolder.pop();

                if (id.equals("s"))
                    setterAndGetter.add(new Pair<String, String>("s", varsData.get("identifier")));
                else if(id.equals("g"))
                    setterAndGetter.add(new Pair<String, String>("g", varsData.get("identifier")));
                else {
                    setterAndGetter.add(new Pair<String, String>("s", id));
                    setterAndGetter.add(new Pair<String, String>("g", id));
                }
            }
        }
        else{
            setterAndGetter.add(new Pair<String, String>("s", varsData.get("identifier")));
            setterAndGetter.add(new Pair<String, String>("g", varsData.get("identifier")));
        }

        return setterAndGetter;
    }
}
