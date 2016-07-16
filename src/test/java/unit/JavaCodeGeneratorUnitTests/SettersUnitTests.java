package unit.JavaCodeGeneratorUnitTests;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;

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

        String expectedStr = "\t//----------------------------------------------|\n" +
                "\t// Setter(s)\n" +
                "\t//----------------------------------------------|\n\n";
        expectedStr += "\tpublic void setHouseName(String houseName) {\n\t\tthis.houseName = houseName;\n\t}\n\n";
        expectedStr += "\tpublic void setOwnerName(String ownerName) {\n\t\tthis.ownerName = ownerName;\n\t}\n\n";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
