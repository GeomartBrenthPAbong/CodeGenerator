package unit.JavaCodeGeneratorUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ConstructorsUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("constructors");
        this.map = new HashMap();
        this.map.put("constructors", new LinkedHashMap());
        this.map.put("clsname", "Test");
    }

    @Test
    public void constructorsGeneratedCorrectly(){
        Map constructors = (Map) this.map.get("constructors");

        constructors.put("String|int", "String str, int n");
        constructors.put("String", "String str");
        constructors.put("class", "");

        this.unit.generate(this.map);

        String expectedStr = "\t//----------------------------------------------|\n" +
                             "\t// Constructor(s)\n" +
                             "\t//----------------------------------------------|\n\n";
        expectedStr += "\tpublic Test() {\n\n\t}\n\n";
        expectedStr += "\tpublic Test(String str, int n) {\n\n\t}\n\n";
        expectedStr += "\tpublic Test(String str) {\n\n\t}\n\n";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
