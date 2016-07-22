import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;

import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class DataTypeOnlySyntaxAnalyzerTests {
    private SyntaxAnalyzer a;

    @Before
    public void instantiations(){
        this.a = SyntaxAnalyzerFactory.create("rmconstructors");
    }

    @Test
    public void shouldAcceptSingleDataType(){
        String input = "String";

        this.accept(input);
    }

    @Test
    public void shouldAcceptSingleGenericDataType(){
        String input = "List<String>";

        this.accept(input);
    }

    @Test
    public void shouldAcceptMultipleDataTypes(){
        String input = "String, int , char ,   boolean,  String,   Integer  ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptGenerics(){
        String input = "Pair<String, int>, List<Pair<String, int>> , char ,   boolean,  String,   Integer  ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptUnacceptableJavaSyntax(){
        String input = "Pair<String, int> List<Pair<String, int>> , char ,   boolean, String,   Integer  ";

        this.accept(input);
    }

    @Test
    public void shouldAcceptArray(){
        String input = "Pair<String, int> List<Pair<String, int>>[] , char[][] ,   boolean[], String[][][],   Integer[]  ";

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
