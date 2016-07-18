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
public class RMImplementsMapChangerTests {
    private MapChanger implementsMapChanger;
    private MapChanger rmImplementsMapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.implementsMapChanger = MapChangerFactory.create("implements");
        this.rmImplementsMapChanger = MapChangerFactory.create("rmimplements");
        this.map = new HashMap();

        this.map.put("implements", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void implementNamesShouldBeRemoved(){
        String input = "Test, Test2, Test3";

        tokenizer.tokenize(input);
        implementsMapChanger.change(tokenizer.getTokens(), this.map);

        input = "Test, Test2, test5";

        tokenizer.tokenize(input);
        this.rmImplementsMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map implementNames = new HashMap();

        expectedMap.put("implements", implementNames);
        implementNames.put("Test3", "Test3");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void allShouldBeRemoved(){
        String input = "Test, Test2, Test3";

        tokenizer.tokenize(input);
        implementsMapChanger.change(tokenizer.getTokens(), this.map);

        input = "*";

        tokenizer.tokenize(input);
        this.rmImplementsMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map implementNames = new HashMap();

        expectedMap.put("implements", implementNames);

        assertTrue(this.map.equals(expectedMap));
    }
}
