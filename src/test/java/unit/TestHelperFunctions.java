package unit;

import javafx.util.Pair;

import java.util.List;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public class TestHelperFunctions {
    public static boolean equalPairs(Pair<String, String> p1, Pair<String, String> p2){
        if (p1 == null && p2 == null)
            return true;

        if (p1 == null || p2 == null)
            return false;

        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    public static boolean equalTokens(List<Pair<String, String>> tokens1, List<Pair<String, String>> tokens2){
        if(tokens1 == null && tokens2 == null)
            return true;

        if(tokens1 == null || tokens2 == null)
            return false;

        if (tokens1.size() != tokens2.size())
            return false;

        boolean isEqual = true;

        for (int i = 0; i < tokens1.size(); i++){
            Pair<String, String> token1Pair = tokens1.get(i);
            Pair<String, String> token2Pair = tokens2.get(i);

            isEqual = TestHelperFunctions.equalPairs(token1Pair, token2Pair);

            if (!isEqual)
                break;
        }

        return isEqual;
    }
}
