package unit;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetter;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetterFactory;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */

public class JavaDefaultsSetterTests {
    private DefaultsSetter d;
    private Map map;

    @BeforeClass
    public static void deleteConfigFile(){
        File f = new File(ResourceLoader.getUserResourcesPath() + "config.txt");
        f.delete();
    }

    @Before
    public void instantiations(){
        this.d = DefaultsSetterFactory.create("java");
        this.map = new HashMap();

        this.map.put("type", new HashMap());
        this.map.put("pname", "");
        this.map.put("package", "");
        this.map.put("clsname", "");
        this.map.put("extends", "");
        this.map.put("implements", new HashMap());
        this.map.put("imports", new HashMap());
        this.map.put("constructors", new LinkedHashMap());
        this.map.put("vars", new LinkedHashMap());
        this.map.put("fns", new LinkedHashMap());
        this.map.put("s", new LinkedHashMap());
        this.map.put("g", new LinkedHashMap());
    }

    @Test
    public void shouldSetDefaults(){
        Map type = (Map) this.map.get("type");
        type.put("final", "!");
        type.put("static", "^");

        Map constructors = (Map) this.map.get("constructors");
        constructors.put("String", "String str");

        Map vars = (Map) this.map.get("vars");
        Map var1 = new HashMap();
        var1.put("final", "!");
        var1.put("static", "^");

        Map var2 = new HashMap();

        vars.put("var1", var1);
        vars.put("var2", var2);

        Map fns = (Map) this.map.get("fns");
        Map fn1 = new HashMap();
        fn1.put("final", "!");
        fn1.put("static", "^");

        Map fn2 = new HashMap();

        fns.put("fn1", fn1);
        fns.put("fn2", fn2);

        Map s = (Map) this.map.get("s");

        s.put("var1", new Pair<String, String>("String", "varOne"));
        s.put("var2", new Pair<String, String>("String", "varTwo"));

        Map g = (Map) this.map.get("g");

        g.put("var1", new Pair<String, String>("String", "varOne"));
        g.put("var2", new Pair<String, String>("String", "varTwo"));

        this.d.set(this.map);

        //END OF GENERATED --------------------------------------------

        Map expectedMap = new HashMap();

        expectedMap.put("type", new HashMap());
        expectedMap.put("pname", "");
        expectedMap.put("package", "");
        expectedMap.put("clsname", "ClassName");
        expectedMap.put("extends", "");
        expectedMap.put("implements", new HashMap());
        expectedMap.put("imports", new HashMap());
        expectedMap.put("constructors", new LinkedHashMap());
        expectedMap.put("vars", new LinkedHashMap());
        expectedMap.put("fns", new LinkedHashMap());
        expectedMap.put("s", new LinkedHashMap());
        expectedMap.put("g", new LinkedHashMap());

        Map eType = (Map) expectedMap.get("type");
        eType.put("accessibility", "public");
        eType.put("final", "final");
        eType.put("static", "static");
        eType.put("identifier", "class");

        Map eConstructors = (Map) expectedMap.get("constructors");
        eConstructors.put("String", "String str");

        Map eVars = (Map) expectedMap.get("vars");
        Map eVar1 = new HashMap();
        eVar1.put("accessibility", "private");
        eVar1.put("final", "final");
        eVar1.put("static", "static");

        Map eVar2 = new HashMap();
        eVar2.put("accessibility", "private");

        eVars.put("var1", eVar1);
        eVars.put("var2", eVar2);

        Map eFns = (Map) expectedMap.get("fns");
        Map eFn1 = new HashMap();
        eFn1.put("accessibility", "public");
        eFn1.put("final", "final");
        eFn1.put("static", "static");

        Map eFn2 = new HashMap();
        eFn2.put("accessibility", "public");

        eFns.put("fn1", eFn1);
        eFns.put("fn2", eFn2);

        Map es = (Map) expectedMap.get("s");

        es.put("var1", new Pair<String, String>("String", "VarOne"));
        es.put("var2", new Pair<String, String>("String", "VarTwo"));

        Map eg = (Map) expectedMap.get("g");

        eg.put("var1", new Pair<String, String>("String", "VarOne"));
        eg.put("var2", new Pair<String, String>("String", "VarTwo"));

        assertTrue(expectedMap.equals(this.map));
    }

    @Test
    public void constructorsShouldBeRemoved(){
        Map type = (Map) this.map.get("type");
        type.put("final", "!");
        type.put("static", "^");
        type.put("identifier", "i");

        Map constructors = (Map) this.map.get("constructors");
        constructors.put("String", "String str");

        Map vars = (Map) this.map.get("vars");
        Map var1 = new HashMap();
        var1.put("final", "!");
        var1.put("static", "^");

        Map var2 = new HashMap();

        vars.put("var1", var1);
        vars.put("var2", var2);

        Map fns = (Map) this.map.get("fns");
        Map fn1 = new HashMap();
        fn1.put("final", "!");
        fn1.put("static", "^");

        Map fn2 = new HashMap();

        fns.put("fn1", fn1);
        fns.put("fn2", fn2);

        Map s = (Map) this.map.get("s");

        s.put("var1", new Pair<String, String>("String", "varOne"));
        s.put("var2", new Pair<String, String>("String", "varTwo"));

        Map g = (Map) this.map.get("g");

        g.put("var1", new Pair<String, String>("String", "varOne"));
        g.put("var2", new Pair<String, String>("String", "varTwo"));

        this.d.set(this.map);

        //END OF GENERATED --------------------------------------------

        Map expectedMap = new HashMap();

        expectedMap.put("type", new HashMap());
        expectedMap.put("pname", "");
        expectedMap.put("package", "");
        expectedMap.put("clsname", "ClassName");
        expectedMap.put("extends", "");
        expectedMap.put("implements", new HashMap());
        expectedMap.put("imports", new HashMap());
        expectedMap.put("constructors", new LinkedHashMap());
        expectedMap.put("vars", new LinkedHashMap());
        expectedMap.put("fns", new LinkedHashMap());
        expectedMap.put("s", new LinkedHashMap());
        expectedMap.put("g", new LinkedHashMap());

        Map eType = (Map) expectedMap.get("type");
        eType.put("accessibility", "public");
        eType.put("final", "final");
        eType.put("static", "static");
        eType.put("identifier", "interface");

        Map eVars = (Map) expectedMap.get("vars");
        Map eVar1 = new HashMap();
        eVar1.put("accessibility", "private");
        eVar1.put("final", "final");
        eVar1.put("static", "static");

        Map eVar2 = new HashMap();
        eVar2.put("accessibility", "private");

        eVars.put("var1", eVar1);
        eVars.put("var2", eVar2);

        Map eFns = (Map) expectedMap.get("fns");
        Map eFn1 = new HashMap();
        eFn1.put("accessibility", "public");
        eFn1.put("final", "final");
        eFn1.put("static", "static");

        Map eFn2 = new HashMap();
        eFn2.put("accessibility", "public");

        eFns.put("fn1", eFn1);
        eFns.put("fn2", eFn2);

        Map es = (Map) expectedMap.get("s");

        es.put("var1", new Pair<String, String>("String", "VarOne"));
        es.put("var2", new Pair<String, String>("String", "VarTwo"));

        Map eg = (Map) expectedMap.get("g");

        eg.put("var1", new Pair<String, String>("String", "VarOne"));
        eg.put("var2", new Pair<String, String>("String", "VarTwo"));

        assertTrue(expectedMap.equals(this.map));
    }
}
