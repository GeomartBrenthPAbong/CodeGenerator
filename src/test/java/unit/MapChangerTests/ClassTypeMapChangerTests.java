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
public class ClassTypeMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("type");
        this.map = new HashMap();

        this.map.put("type", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void typeShouldBeAdded(){
        String input = "+!i";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map type = new HashMap();

        expectedMap.put("type", type);
        type.put("accessibility", "+");
        type.put("final", "!");
        type.put("identifier", "i");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void typeShouldBeChanged(){
        String input = "+!i";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "-a";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map type = new HashMap();

        expectedMap.put("type", type);
        type.put("accessibility", "-");
        type.put("identifier", "a");

        assertTrue(this.map.equals(expectedMap));
    }
}
