package unit;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.Tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class TokenizerTests {
    private Tokenizer tokenizer;

    @Before
    public void initialization(){
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void shouldBeAccessibilityStaticFinalIdentifierIdentifierOpenParIdentifierClosePar(){
        String input = "+^!String|str(House)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "+  ^ ! String |  str (  s  : House, g : Home ) ";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "s"));
        expectedTokens.add(new Pair<String, String>("colon", ":"));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("comma", ","));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "g"));
        expectedTokens.add(new Pair<String, String>("colon", ":"));
        expectedTokens.add(new Pair<String, String>("identifier", "Home"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptSpacesOnDataType(){
        String input = "+^!unsigned long int|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "unsigned long int"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "+^!Pair<int, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptNestedGenerics(){
        String input = "+^!List<Pair<unsigned long int, String>, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "List<Pair<unsigned long int, String>, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptDoubleNestedGenerics(){
        String input = "+^!List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("dataType", "List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldHaveNoneIfHasUnclosedOpposite(){
        String input = "+^!List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "!"));
        expectedTokens.add(new Pair<String, String>("NONE", "List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>|pair(Twins)"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptFnsMultiParams(){
        String input = "String|pair(Twins|twin, Pair<int, String>|pair, Pair<int, Pair<int, String>>|test)";

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
        expectedTokens.add(new Pair<String, String>("comma", ","));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("comma", ","));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, Pair<int, String>>"));
        expectedTokens.add(new Pair<String, String>("identifier", "test"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldHaveNoParends(){
        String input = "name1";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();
        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();
        expectedTokens.add(new Pair<String, String>("identifier", "name1"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldBeMarkedAsAll(){
        String input = "*";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();
        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();
        expectedTokens.add(new Pair<String, String>("all", "*"));

        assertTrue(TestHelperFunctions.equalTokens(generatedTokens, expectedTokens));
    }
}
