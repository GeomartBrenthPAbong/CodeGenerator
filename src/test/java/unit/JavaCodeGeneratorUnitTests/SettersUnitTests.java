package unit.JavaCodeGeneratorUnitTests;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */
public class SettersUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("setters");
        this.map = new HashMap();
        this.map.put("s", new LinkedHashMap());
        this.map.put("type", new HashMap());

        Map type = (Map) this.map.get("type");
        type.put("identifier", "class");
    }

    @Test
    public void shouldBeNoGeneration(){
        this.unit.generate(this.map);

        String expectedStr = "";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldGenerateTheCorrespondingSetters(){
        Map setters = (Map) this.map.get("s");

        setters.put("houseName", new Pair<String, String>("String", "HouseName"));
        setters.put("ownerName", new Pair<String, String>("String", "OwnerName"));

        this.unit.generate(this.map);

        String expectedStr = "\n\n" + ResourceLoader.toString(ResourceLoader.load("setters"));
        expectedStr += "\n\n\tpublic void setHouseName(String houseName) {\n\t\tthis.houseName = houseName;\n\t}\n";
        expectedStr += "\n\tpublic void setOwnerName(String ownerName) {\n\t\tthis.ownerName = ownerName;\n\t}";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldHaveNoFunctionBodyIfInterface(){
        Map type = (Map) this.map.get("type");
        type.put("identifier", "interface");

        Map setters = (Map) this.map.get("s");

        setters.put("houseName", new Pair<String, String>("String", "HouseName"));
        setters.put("ownerName", new Pair<String, String>("String", "OwnerName"));

        this.unit.generate(this.map);

        String expectedStr = "\n\n" + ResourceLoader.toString(ResourceLoader.load("setters"));
        expectedStr += "\n\n\tpublic void setHouseName(String houseName);\n";
        expectedStr += "\tpublic void setOwnerName(String ownerName);";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
