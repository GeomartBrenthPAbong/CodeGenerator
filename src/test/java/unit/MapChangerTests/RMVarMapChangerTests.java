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
public class RMVarMapChangerTests {
    private MapChanger rmVarMapChanger;
    private MapChanger varMapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.rmVarMapChanger = MapChangerFactory.create("rmvars");
        this.varMapChanger = MapChangerFactory.create("vars");

        this.map = new HashMap();
        this.map.put("vars", new HashMap());
        this.map.put("s", new HashMap());
        this.map.put("g", new HashMap());

        this.tokenizer = new Tokenizer();
    }

    @Test
    public void varsSGShouldBeEmpty(){
        String input = "-String|str";

        tokenizer.tokenize(input);
        varMapChanger.change(tokenizer.getTokens(), this.map);

        input = "str";

        tokenizer.tokenize(input);
        rmVarMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("vars", new HashMap());
        expectedMap.put("s", new HashMap());
        expectedMap.put("g", new HashMap());

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void allShouldBeRemoved(){
        String input = "-String|str";

        tokenizer.tokenize(input);
        varMapChanger.change(tokenizer.getTokens(), this.map);

        input = "-String|house";

        tokenizer.tokenize(input);
        varMapChanger.change(tokenizer.getTokens(), this.map);

        input = "*";

        tokenizer.tokenize(input);
        rmVarMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("vars", new HashMap());
        expectedMap.put("s", new HashMap());
        expectedMap.put("g", new HashMap());

        assertTrue(this.map.equals(expectedMap));
    }
}
