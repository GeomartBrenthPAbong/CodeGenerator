package unit.JavaCodeGeneratorUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class VariablesUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("variables");
        this.map = new HashMap();
        this.map.put("vars", new LinkedHashMap());
    }

    @Test
    public void variablesGeneratedCorrectly(){
        Map vars = (Map) this.map.get("vars");
        Map var1 = new HashMap();
        Map var2 = new HashMap();

        vars.put("var1", var1);
        vars.put("var2", var2);

        var1.put("accessibility", "private");
        var1.put("dataType", "String");
        var1.put("identifier", "var1");

        var2.put("accessibility", "private");
        var2.put("static", "static");
        var2.put("final", "final");
        var2.put("dataType", "String");
        var2.put("identifier", "var2");

        this.unit.generate(this.map);

        String expectedStr = "\n\n\tprivate String var1;\n";
        expectedStr += "\tprivate static final String var2;";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
