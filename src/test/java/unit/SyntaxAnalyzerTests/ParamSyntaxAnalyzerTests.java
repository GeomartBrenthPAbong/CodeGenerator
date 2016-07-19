import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class ParamSyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("constructors");
    }

    @Test
    public void shouldAcceptSingleParam(){
        String input = "String|string";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleParams(){
        String input = "String  | inte, int|home, char   |house";

        this.accept(input);
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "   String  |  spaces   ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "   List<String>  |  spaces   , Pair<int, Pair<int, Pair<string, string>>> |  test";

        this.accept(input);
    }

    private void accept(String input){
        try{
            this.a.analyze(input);
            assertTrue(true);
        }
        catch (InputMismatchException e){
            assertTrue(false);
        }
    }
}
