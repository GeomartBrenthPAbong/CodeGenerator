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
public class ClassBeginsTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("class_begin");
        this.map = new HashMap();

        this.map.put("clsname", "");
        this.map.put("extends", "");
        this.map.put("implements", new HashMap());
        this.map.put("type", new HashMap());
    }

    @Test
    public void fullCBGeneratedCorrectly(){
        this.map.put("clsname", "Labrador");
        this.map.put("extends", "Dog");

        Map implementNames = (Map) this.map.get("implements");
        Map type = (Map) this.map.get("type");

        implementNames.put("Animal", "Animal");
        implementNames.put("Pet", "Pet");

        type.put("accessibility", "public");
        type.put("final", "final");
        type.put("identifier", "class");

        this.unit.generate(this.map);

        String expectedStr = "\n\npublic final class Labrador extends Dog implements Animal, Pet {";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
