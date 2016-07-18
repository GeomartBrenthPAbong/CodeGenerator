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
public class RMFnMapChangerTests {
    private MapChanger rmFnMapChanger;
    private MapChanger fnMapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.rmFnMapChanger = MapChangerFactory.create("rmfns");
        this.fnMapChanger = MapChangerFactory.create("fns");

        this.map = new HashMap();
        this.map.put("fns", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void fnDataShouldBeRemoved(){
        String input = "+String|str";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "str";

        tokenizer.tokenize(input);
        rmFnMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();

        expectedMap.put("fns", fns);

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveTheVersionSpecifiedAndDifferentReturnTypeAndNoStatic(){
        String input = "-^String|str";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "+int|str(boolean|b, string|s)";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "str";

        tokenizer.tokenize(input);
        rmFnMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "int");

        versions.put("boolean|string", "boolean b, string s");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldBeUntouched(){
        String input = "-String|str";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "+int|str(boolean|b, string|s)";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "str(boolean|s)";

        tokenizer.tokenize(input);
        rmFnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "strs";

        tokenizer.tokenize(input);
        rmFnMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "int");

        versions.put("null", "");
        versions.put("boolean|string", "boolean b, string s");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void allShouldBeRemoved(){
        String input = "-String|str";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "+int|str(boolean|b, string|s)";

        tokenizer.tokenize(input);
        fnMapChanger.change(tokenizer.getTokens(), this.map);

        input = "*";

        tokenizer.tokenize(input);
        rmFnMapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();

        expectedMap.put("fns", fns);

        assertTrue(this.map.equals(expectedMap));
    }
}
