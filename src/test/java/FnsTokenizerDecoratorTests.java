import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.AbstractTokenizer;
import org.lambda_n2t.codegenerator.TokenizerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class FnsTokenizerDecoratorTests {
    private AbstractTokenizer tokenizer;

    @Before
    public void initialization(){
        this.tokenizer = TokenizerFactory.create("fns");
    }

    @Test
    public void shouldChangeSomeIdentifiersToDataTypes(){
        String input = "String|pair(Twins|twin, Pair|pair, Pair<int, Pair<int, String>>|test)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Twins"));
        expectedTokens.add(new Pair<String, String>("identifier", "twin"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, Pair<int, String>>"));
        expectedTokens.add(new Pair<String, String>("identifier", "test"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }
}
