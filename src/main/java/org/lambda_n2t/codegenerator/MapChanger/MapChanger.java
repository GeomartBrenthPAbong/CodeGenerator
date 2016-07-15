package org.lambda_n2t.codegenerator.MapChanger;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */

public interface MapChanger {
    public void change(List<Pair<String, String>> tokens, Map map);
}
