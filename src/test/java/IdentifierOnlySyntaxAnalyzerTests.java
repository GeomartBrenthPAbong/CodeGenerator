import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class IdentifierOnlySyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("rmvars");
    }

    @Test
    public void shouldAcceptSingleIdentifier(){
        String input = "string";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleIdentifiers(){
        String input = "inte, home, house";

        this.accept(input);
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "   twin   , rock,  scissors  ";

        this.accept(input);
    }

    @Test(expected = InputMismatchException.class)
    public void shouldNotAcceptNoComma(){
        String input = "house rock";

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
