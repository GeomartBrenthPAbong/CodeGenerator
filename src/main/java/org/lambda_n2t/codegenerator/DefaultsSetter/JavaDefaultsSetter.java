package org.lambda_n2t.codegenerator.DefaultsSetter;

import javafx.util.Pair;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */

public class JavaDefaultsSetter extends DefaultsSetterDecorator {
    private DefaultsSetter setter;

    public JavaDefaultsSetter(DefaultsSetter setter){
        this.setter = setter;
    }

    public void set(Map map) {
        setter.set(map);
        this.toUppercaseSettersAndGettersNames(map);
    }

    private void toUppercaseSettersAndGettersNames(Map map){
        Map<String, Pair<String, String>> ss = (Map) map.get("s");
        Map<String, Pair<String, String>> gs = (Map) map.get("g");

        for (Map.Entry<String, Pair<String, String>> s: ss.entrySet()){
            ss.put(s.getKey(), new Pair<String, String>(s.getValue().getKey(),
                                                         this.toUppercaseFirstLetter(s.getValue().getValue())));
        }

        for (Map.Entry<String, Pair<String, String>> g: gs.entrySet()){
            gs.put(g.getKey(), new Pair<String, String>(g.getValue().getKey(),
                                                         this.toUppercaseFirstLetter(g.getValue().getValue())));
        }
    }

    private String toUppercaseFirstLetter(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
