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
public class ImplementsMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("implements");
        this.map = new HashMap();

        this.map.put("implements", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void implementNamesShouldBeAdded(){
        String input = "Test";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map implementNames = new HashMap();

        expectedMap.put("implements", implementNames);
        implementNames.put("Test", "Test");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void multipleImplementNamesShouldBeAdded(){
        String input = "Test, Test2, Test3";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map implementNames = new HashMap();

        expectedMap.put("implements", implementNames);
        implementNames.put("Test", "Test");
        implementNames.put("Test2", "Test2");
        implementNames.put("Test3", "Test3");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void newImplementNamesShouldBeAdded(){
        String input = "Test";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "Test, Test2, Test3";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map implementNames = new HashMap();

        expectedMap.put("implements", implementNames);
        implementNames.put("Test", "Test");
        implementNames.put("Test2", "Test2");
        implementNames.put("Test3", "Test3");

        assertTrue(this.map.equals(expectedMap));
    }
}
