package JavaCodeGeneratorUnitTests;

import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class HeaderUnitTests {
    private CodeGeneratorUnit unit;
    private Map map;

    @Before
    public void instantiations(){
        this.unit = JavaCodeGeneratorUnitFactory.create("header");
        this.map = new HashMap();
    }

    @Test
    public void headerGeneratedCorrectly(){
        map.put("pname", "Geomart Brenth Abong");
        this.unit.generate(this.map);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String curDate = dateFormat.format(date);

        String expectedStr = "//----------------------------------------------|\n";
        expectedStr += "//\n";
        expectedStr += "// Programmer: Geomart Brenth Abong\n";
        expectedStr += "// Date Created: " + curDate + "\n";
        expectedStr += "//\n";
        expectedStr += "//----------------------------------------------|";

        assertTrue(expectedStr.equals(this.unit.getGeneratedCode()));
    }
}
