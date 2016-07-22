import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public class VarSyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("vars");
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "-   ^ ! String  | str   ( s: NewName, g  :oldName)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptOneParam(){
        String input = "-   ^ ! String  | str   ( s: NewName)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptOneParamWithNoSorG(){
        String input = "-   ^ ! String  | str   (NewName)";

        this.accept(input);
    }

    @Test
    public void shouldAcceptArray(){
        String input = "-   ^ ! String[][]  | str   (NewName)";

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
