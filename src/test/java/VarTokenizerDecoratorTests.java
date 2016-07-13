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

public class VarTokenizerDecoratorTests {
    private AbstractTokenizer tokenizer;

    @Before
    public void initialization(){
        this.tokenizer = TokenizerFactory.create("vars");
    }

    @Test
    public void shouldBeAccessibilityStaticFinalDataTypeIdentifierOpenParIdentifierClosePar(){
        String input = "+^.String|str(House)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "+  ^ . String |  str (  s  : House, g : Home ) ";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "s"));
        expectedTokens.add(new Pair<String, String>("colon", ":"));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "g"));
        expectedTokens.add(new Pair<String, String>("colon", ":"));
        expectedTokens.add(new Pair<String, String>("identifier", "Home"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldStillBeIdentifier(){
        String input = "+^.String|str(House,Home)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Home"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }
}
