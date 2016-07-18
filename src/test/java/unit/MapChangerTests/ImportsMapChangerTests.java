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
public class ImportsMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("imports");
        this.map = new HashMap();

        this.map.put("imports", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void importNamesShouldBeAdded(){
        String input = "Test, java.io.*, java.util.*";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map importNames = new HashMap();

        expectedMap.put("imports", importNames);
        importNames.put("Test", "Test");
        importNames.put("java.io.*", "java.io.*");
        importNames.put("java.util.*", "java.util.*");

        assertTrue(this.map.equals(expectedMap));
    }
}
