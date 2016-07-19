package unit.JavaCodeGeneratorUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class ImportsUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("imports");
        this.map = new HashMap();
        this.map.put("imports", new HashMap());
    }

    @Test
    public void importsGeneratedCorrectly(){
        Map imports = (Map) this.map.get("imports");
        imports.put("Test", "Test");
        imports.put("java.io.*", "java.io.*");

        this.unit.generate(this.map);

        String expectedStr = "\n\nimport Test;\n";
        expectedStr += "import java.io.*;";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
