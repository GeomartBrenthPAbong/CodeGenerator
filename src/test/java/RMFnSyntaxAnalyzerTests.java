import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class RMFnSyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("rmfns");
    }

    @Test
    public void shouldAcceptSingleInput(){
        String input = "process(String|str)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptNoPar(){
        String input = "process";

        this.accept(input);
    }

    @Test
    public void shouldAcceptNoParam(){
        String input = "process()";

        this.accept(input);
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "  process  (   String|  type) ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleParams(){
        String input = "process  (   String|  type, int|time,  char| s)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "process(Pair<int, Pair<int, Pair<int, String>>>|type)";

        this.accept(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptGenericInName(){
        String input = "house<test>()";

        this.a.analyze(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptHasPrefix(){
        String input = "+.house()";

        this.a.analyze(input);
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
