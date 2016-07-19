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
public class RMConstructorMapChangerTests {
    private MapChanger constructorMapChanger;
    private MapChanger rmConstructorMapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.constructorMapChanger = MapChangerFactory.create("constructors");
        this.rmConstructorMapChanger = MapChangerFactory.create("rmconstructors");
        this.map = new HashMap();

        this.map.put("constructors", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void constructorShouldBeRemoved(){
        String input = "String|input, int|n";

        tokenizer.tokenize(input);
        this.constructorMapChanger.change(tokenizer.getTokens(), this.map);

        input = "String|input, int|n";

        tokenizer.tokenize(input);
        this.rmConstructorMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map constructors = new HashMap();

        expectedMap.put("constructors", constructors);

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void allShouldBeRemoved(){
        String input = "String|input, int|n";

        tokenizer.tokenize(input);
        this.constructorMapChanger.change(tokenizer.getTokens(), this.map);

        input = "String|input";

        tokenizer.tokenize(input);
        this.constructorMapChanger.change(tokenizer.getTokens(), this.map);

        input = "*";

        tokenizer.tokenize(input);
        this.rmConstructorMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map constructors = new HashMap();

        expectedMap.put("constructors", constructors);

        assertTrue(this.map.equals(expectedMap));
    }
}
