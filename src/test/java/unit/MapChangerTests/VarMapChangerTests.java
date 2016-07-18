import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.MapChanger.MapChanger;
import org.lambda_n2t.codegenerator.MapChanger.MapChangerFactory;
import org.lambda_n2t.codegenerator.Tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class VarMapChangerTests {
    private MapChanger mapChanger;
    private Map map;
    private Tokenizer tokenizer;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("vars");
        this.map = new HashMap();

        this.map.put("vars", new HashMap());
        this.map.put("s", new HashMap());
        this.map.put("g", new HashMap());

        this.tokenizer = new Tokenizer();
    }

    @Test
    public void setterAndGetterShouldBeTheSameAsTheIdentifier(){
        String input = "+^!String|str";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "str"));
        g.put("str", new Pair<String, String>("String", "str"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveSetterAndGetter(){
        String input = "+^!String|str()";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldHaveTheSameSetterAndGetterButDifferentFromIdentifier(){
        String input = "+^!String|str(newName)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "newName"));
        g.put("str", new Pair<String, String>("String", "newName"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldHaveDifferentSetterAndGetter(){
        String input = "+^!String|str(s:newName, g:gName)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "newName"));
        g.put("str", new Pair<String, String>("String", "gName"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveGetterAndDifferentSetterName(){
        String input = "+^!String|str(s:newName)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "newName"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveSetterAndDifferentGetterName(){
        String input = "+^!String|str(g:gName)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        g.put("str", new Pair<String, String>("String", "gName"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveGetter(){
        String input = "+^!String|str(s)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "str"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldNotHaveSetter(){
        String input = "+^!String|str(g)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        g.put("str", new Pair<String, String>("String", "str"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void setterAndGetterCanBeInterchanged(){
        String input = "+^!String|str(g:gName, s:newName)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "newName"));
        g.put("str", new Pair<String, String>("String", "gName"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void setterHaveSetterAndGetterWithTheSameNameAsIdentifier(){
        String input = "+^!String|str(g, s)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "str"));
        g.put("str", new Pair<String, String>("String", "str"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldHaveDifferentAccessibilityAndNotStatic(){
        String input = "+^!String|str(g, s)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        input = "-!String|str(g, s)";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "-");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "str"));
        g.put("str", new Pair<String, String>("String", "str"));

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void shouldIgnoreSpaces(){
        String input = " +  ^ !  String |  str(s  :    newName, g   :  gName  )";

        tokenizer.tokenize(input);
        mapChanger.change(tokenizer.getTokens(), this.map);

        Map expectedMap = new HashMap();
        Map varsMap = new HashMap();
        Map s = new HashMap();
        Map g = new HashMap();
        Map varData = new HashMap();

        expectedMap.put("vars", varsMap);
        expectedMap.put("s", s);
        expectedMap.put("g", g);

        varsMap.put("str", varData);

        varData.put("identifier", "str");
        varData.put("dataType", "String");
        varData.put("accessibility", "+");
        varData.put("static", "^");
        varData.put("final", "!");

        s.put("str", new Pair<String, String>("String", "newName"));
        g.put("str", new Pair<String, String>("String", "gName"));

        assertTrue(this.map.equals(expectedMap));
    }
}
