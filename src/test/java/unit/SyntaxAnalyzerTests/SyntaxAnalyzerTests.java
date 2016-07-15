import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class SyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("vars");
    }

    @Test
    public void shouldAcceptNoPar(){
        String input = "-   ^ ! String  | str   ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptSimplestInput(){
        String input = "String|str";

        this.accept(input);
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "HashMap<String, Pair<String, Pair<String, int>>>|str";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleSpacesDataType(){
        String input = "unsigned long int|str";

        this.accept(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptMultipleSpacesDataTypeWithComma(){
        String input = "unsigned long ,int|str";

        this.a.analyze(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptDoubleIdentifiers(){
        String input = "unsigned long int|str, hello";

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
