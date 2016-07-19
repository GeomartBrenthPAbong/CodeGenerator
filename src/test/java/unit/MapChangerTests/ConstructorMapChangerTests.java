import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.MapChanger.MapChanger;
import org.lambda_n2t.codegenerator.MapChanger.MapChangerFactory;
import org.lambda_n2t.codegenerator.Tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ConstructorMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("constructors");
        this.map = new HashMap();

        this.map.put("constructors", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void constructorShouldBeAdded(){
        String input = "String|input, int|n";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map constructors = new HashMap();

        expectedMap.put("constructors", constructors);
        constructors.put("String|int", "String input, int n");
        
        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void constructorShouldBeChanged(){
        String input = "String|input, int|n";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "String|inputssss, int|n";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map constructors = new HashMap();

        expectedMap.put("constructors", constructors);
        constructors.put("String|int", "String inputssss, int n");

        assertTrue(this.map.equals(expectedMap));
    }
}
