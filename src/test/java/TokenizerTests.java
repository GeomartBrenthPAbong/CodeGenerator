import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.Tokenizer;

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
        String input = "+^.String|str(House)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("identifier", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "str"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "House"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
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
        expectedTokens.add(new Pair<String, String>("identifier", "String"));
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

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptSpacesOnDataType(){
        String input = "+^.unsigned long int|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "unsigned long int"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "+^.Pair<int, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptNestedGenerics(){
        String input = "+^.List<Pair<unsigned long int, String>, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "List<Pair<unsigned long int, String>, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptDoubleNestedGenerics(){
        String input = "+^.List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("dataType", "List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldHaveNoneIfHasUnclosedOpposite(){
        String input = "+^.List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>|pair(Twins)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("accessibility", "+"));
        expectedTokens.add(new Pair<String, String>("static", "^"));
        expectedTokens.add(new Pair<String, String>("final", "."));
        expectedTokens.add(new Pair<String, String>("NONE", "List<Pair<unsigned long int, String>, Pair<Pair<unsigned long int, String>, String>|pair(Twins)"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldAcceptFnsMultiParams(){
        String input = "String|pair(Twins|twin, Pair<int, String>|pair, Pair<int, Pair<int, String>>|test)";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("identifier", "String"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("identifier", "Twins"));
        expectedTokens.add(new Pair<String, String>("identifier", "twin"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, String>"));
        expectedTokens.add(new Pair<String, String>("identifier", "pair"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("openPar", "("));
        expectedTokens.add(new Pair<String, String>("dataType", "Pair<int, Pair<int, String>>"));
        expectedTokens.add(new Pair<String, String>("identifier", "test"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));
        expectedTokens.add(new Pair<String, String>("closePar", ")"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    @Test
    public void shouldHaveNoParends(){
        String input = "name1, name2, name3";

        this.tokenizer.tokenize(input);
        List<Pair<String, String>> generatedTokens = this.tokenizer.getTokens();

        List<Pair<String, String>> expectedTokens = new ArrayList<Pair<String, String>>();

        expectedTokens.add(new Pair<String, String>("identifier", "name1"));
        expectedTokens.add(new Pair<String, String>("identifier", "name2"));
        expectedTokens.add(new Pair<String, String>("identifier", "name3"));

        assertTrue(this.equalTokens(generatedTokens, expectedTokens));
    }

    private boolean equalTokens(List<Pair<String, String>> tokens1, List<Pair<String, String>> tokens2){
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

            isEqual = this.equalPairs(token1Pair, token2Pair);

            if (!isEqual)
                break;
        }

        return isEqual;
    }


    private boolean equalPairs(Pair<String, String> p1, Pair<String, String> p2){
        if (p1 == null && p2 == null)
            return true;

        if (p1 == null || p2 == null)
            return false;

        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    /**
     * Example Usage:
         System.out.println("The generated: ");
         this.printTokens(generatedTokens, "\n");

         System.out.println("The expected: ");
         this.printTokens(expectedTokens, "\n");
     *
     * @param p1
     * @param p2
     * @return
     */
    private void printTokens(List<Pair<String, String>> tokens, String divider){
        System.out.println("{");

        for (Pair<String, String> token: tokens)
            System.out.print("['" + token.getKey() + "', '" + token.getValue() + "'], " + divider);

        System.out.println();
        System.out.println("}");
    }
}
