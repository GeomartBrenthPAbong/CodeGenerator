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
 * Created by Geomart Brenth Abong on 7/16/2016.
 */
public class FunctionsUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("functions");
        this.map = new HashMap();
        this.map.put("fns", new LinkedHashMap());
        this.map.put("type", new HashMap());

        Map type = (Map) this.map.get("type");
        type.put("identifier", "class");
    }

    @Test
    public void shouldBeNoGeneration(){
        this.unit.generate(this.map);

        String expectedStr = "";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));

        Map functions = (Map) this.map.get("fns");
        Map versions = new LinkedHashMap();

        functions.put("accessibility", "public");
        functions.put("dataType", "String");
        functions.put("identifier", "processHouseName");
        functions.put("versions", versions);

        this.unit.generate(this.map);

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldGenerateFunctionWithNoParams(){
        Map functions = (Map) this.map.get("fns");
        Map versions = new LinkedHashMap();

        functions.put("accessibility", "public");
        functions.put("dataType", "String");
        functions.put("identifier", "processHouseName");
        functions.put("versions", versions);

        versions.put("null", "");
        this.unit.generate(this.map);

        String expectedStr = "\t//----------------------------------------------|\n" +
                "\t// Other Function(s)\n" +
                "\t//----------------------------------------------|\n\n";
        expectedStr += "\tpublic String processHouseName() {\n\t\treturn null;\n\t}\n\n";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldGenerateFunctionWithParams(){
        Map functions = (Map) this.map.get("fns");
        Map versions = new LinkedHashMap();

        functions.put("accessibility", "public");
        functions.put("dataType", "String");
        functions.put("identifier", "processHouseName");
        functions.put("versions", versions);

        versions.put("null", "");
        versions.put("String|int", "String daddyName, int age");
        versions.put("String|String", "String daddyName, String motherName");

        this.unit.generate(this.map);

        String expectedStr = "\t//----------------------------------------------|\n" +
                "\t// Other Function(s)\n" +
                "\t//----------------------------------------------|\n\n";
        expectedStr += "\tpublic String processHouseName() {\n\t\treturn null;\n\t}\n\n";
        expectedStr += "\tpublic String processHouseName(String daddyName, int age) {\n\t\treturn null;\n\t}\n\n";
        expectedStr += "\tpublic String processHouseName(String daddyName, String motherName) {\n\t\treturn null;\n\t}\n\n";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }

    @Test
    public void shouldHaveNoFunctionBodyIfInterface(){
        Map type = (Map) this.map.get("type");
        type.put("identifier", "interface");

        Map functions = (Map) this.map.get("fns");
        Map versions = new LinkedHashMap();

        functions.put("accessibility", "public");
        functions.put("dataType", "String");
        functions.put("identifier", "processHouseName");
        functions.put("versions", versions);

        versions.put("null", "");
        versions.put("String|int", "String daddyName, int age");
        versions.put("String|String", "String daddyName, String motherName");

        this.unit.generate(this.map);

        String expectedStr = "\t//----------------------------------------------|\n" +
                "\t// Other Function(s)\n" +
                "\t//----------------------------------------------|\n\n";
        expectedStr += "\tpublic String processHouseName();\n";
        expectedStr += "\tpublic String processHouseName(String daddyName, int age);\n";
        expectedStr += "\tpublic String processHouseName(String daddyName, String motherName);\n";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
