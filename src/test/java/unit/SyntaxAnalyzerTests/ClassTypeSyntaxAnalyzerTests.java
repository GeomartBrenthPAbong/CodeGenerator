import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */

public class ClassTypeSyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("type");
    }

    @Test
    public void shouldAcceptFullWithSpaces(){
        String input = " + ! i ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptFullWithNoSpaces(){
        String input = "-!c";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMinimum(){
        String input = "a";

        this.accept(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAccept(){
        String input = "ab";

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
