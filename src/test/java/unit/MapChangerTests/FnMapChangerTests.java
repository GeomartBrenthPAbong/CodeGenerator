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
public class FnMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("fns");
        this.map = new HashMap();

        this.map.put("fns", new HashMap());
        this.tokenizer = new Tokenizer();
    }

    @Test
    public void shouldHaveNullVersionIfNoPar(){
        String input = "+String|str";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "String");

        versions.put("null", "");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldHaveNullVersion(){
        String input = "+String|str()";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "String");

        versions.put("null", "");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldHaveTheCorrespondingVersion(){
        String input = "+String|str(String|s, int|n, boolean|b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "String");

        versions.put("String|int|boolean", "String s, int n, boolean b");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = "+ String  | str (  String  |  s,   int|   n,  boolean  |b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "+");
        fnData.put("identifier", "str");
        fnData.put("dataType", "String");

        versions.put("String|int|boolean", "String s, int n, boolean b");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void fnDataShouldBeChangedExceptVersion(){
        String input = "+String|str(String|s, int|n, boolean|b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "-^!int|str(String|s, int|n, boolean|b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "-");
        fnData.put("identifier", "str");
        fnData.put("static", "^");
        fnData.put("final", "!");
        fnData.put("dataType", "int");

        versions.put("String|int|boolean", "String s, int n, boolean b");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void fnDataStaticShouldBeRemovedTwoVersions(){
        String input = "+^!String|str";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "-!int|str(String|s, int|n, boolean|b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "-");
        fnData.put("identifier", "str");
        fnData.put("final", "!");
        fnData.put("dataType", "int");

        versions.put("null", "");
        versions.put("String|int|boolean", "String s, int n, boolean b");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void newVersionShouldBeAdded(){
        String input = "+String|str(String|s, int|n, boolean|b)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "-^!String|str(String|s, int|n)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map fns = new HashMap();
        Map versions = new HashMap();
        Map fnData = new HashMap();

        expectedMap.put("fns", fns);

        fns.put("str", fnData);
        fnData.put("versions", versions);

        fnData.put("accessibility", "-");
        fnData.put("identifier", "str");
        fnData.put("static", "^");
        fnData.put("final", "!");
        fnData.put("dataType", "String");

        versions.put("String|int|boolean", "String s, int n, boolean b");
        versions.put("String|int", "String s, int n");

        assertTrue(this.map.equals(expectedMap));
    }
}
