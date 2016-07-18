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
public class RMImportsMapChangerTests {
    private MapChanger importsMapChanger;
    private MapChanger rmImportsMapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.importsMapChanger = MapChangerFactory.create("imports");
        this.rmImportsMapChanger = MapChangerFactory.create("rmimports");
        this.map = new HashMap();

        this.map.put("imports", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void importNamesShouldBeRemoved(){
        String input = "Test, Test2, java.io.*";

        tokenizer.tokenize(input);
        importsMapChanger.change(tokenizer.getTokens(), this.map);

        input = "Test, java.io.*, test5";

        tokenizer.tokenize(input);
        this.rmImportsMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map importNames = new HashMap();

        expectedMap.put("imports", importNames);
        importNames.put("Test2", "Test2");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void allShouldBeRemoved(){
        String input = "Test, Test2, java.io.*";

        tokenizer.tokenize(input);
        importsMapChanger.change(tokenizer.getTokens(), this.map);

        input = "*";

        tokenizer.tokenize(input);
        this.rmImportsMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map importNames = new HashMap();

        expectedMap.put("imports", importNames);

        assertTrue(this.map.equals(expectedMap));
    }
}
