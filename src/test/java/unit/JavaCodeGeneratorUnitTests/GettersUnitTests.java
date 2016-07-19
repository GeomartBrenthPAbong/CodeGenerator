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
public class GettersUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("getters");
        this.map = new HashMap();
        this.map.put("g", new LinkedHashMap());
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
    public void shouldGenerateTheCorrespondingGetters(){
        Map getters = (Map) this.map.get("g");

        getters.put("houseName", new Pair<String, String>("String", "HouseName"));
        getters.put("ownerName", new Pair<String, String>("String", "OwnerName"));

        this.unit.generate(this.map);

        String expectedStr = "\n\n" + ResourceLoader.toString(ResourceLoader.load("getters"));
        expectedStr += "\n\n\tpublic String getHouseName() {\n\t\treturn this.houseName;\n\t}\n\n";
        expectedStr += "\tpublic String getOwnerName() {\n\t\treturn this.ownerName;\n\t}";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldHaveNoFunctionBodyIfInterface(){
        Map type = (Map) this.map.get("type");
        type.put("identifier", "interface");

        Map getters = (Map) this.map.get("g");

        getters.put("houseName", new Pair<String, String>("String", "HouseName"));
        getters.put("ownerName", new Pair<String, String>("String", "OwnerName"));

        this.unit.generate(this.map);

        String expectedStr = "\n\n" + ResourceLoader.toString(ResourceLoader.load("getters"));
        expectedStr += "\n\n\tpublic String getHouseName();\n";
        expectedStr += "\tpublic String getOwnerName();";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
