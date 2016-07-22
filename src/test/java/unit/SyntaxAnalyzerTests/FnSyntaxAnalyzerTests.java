import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class FnSyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("fns");
    }

    @Test
    public void shouldAcceptNoPar(){
        String input = "-   ^ ! String|str";

        this.accept(input);
    }

    @Test
    public void shouldAcceptNoParams(){
        String input = "String|str(  )";

        this.accept(input);
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "String|str(HashMap<String, Pair<String, Pair<String, int>>>|hey, HashMap<String, Pair<String, Pair<String, int>>>|wow)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleParams(){
        String input = "unsigned long int|str(String|str,  int|num, char  |  s)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptArray(){
        String input = "unsigned long int[][]|str(String[]|str,  int[][]|num, char[]  |  s)";

        this.accept(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptMultiplePar(){
        String input = "unsigned long int|str((String|int))";

        this.a.analyze(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptNoPipeline(){
        String input = "unsigned long int|strhello(String hello)";

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
