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
public class ExtendsMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("extends");
        this.map = new HashMap();

        this.map.put("extends", "");
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void clsNameShouldBeAdded(){
        String input = "Test";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("extends", "Test");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void clsNameShouldBeChanged(){
        String input = "Test";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "ClassName";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("extends", "ClassName");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void clsNameShouldBeRemoved(){
        String input = "Test";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("extends", "");

        assertTrue(this.map.equals(expectedMap));
    }
}
