package unit;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetter;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetterFactory;
import org.lambda_n2t.codegenerator.Machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/18/2016.
 */

public class MachineTests {
    private Map map;
    private Machine machine;
    private DefaultsSetter defaultsSetter;

    @Before
    public void instantiations(){
        this.map = new HashMap();

        this.map.put("type", new HashMap());
        this.map.put("pname", "");
        this.map.put("clsname", "");
        this.map.put("extends", "");
        this.map.put("implements", new HashMap());
        this.map.put("imports", new HashMap());
        this.map.put("constructors", new LinkedHashMap());
        this.map.put("vars", new LinkedHashMap());
        this.map.put("fns", new LinkedHashMap());
        this.map.put("s", new LinkedHashMap());
        this.map.put("g", new LinkedHashMap());

        this.defaultsSetter = DefaultsSetterFactory.create("java");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowException(){
        this.machine = new Machine("c++");
    }

    @Test
    public void shouldGenerateTheSameStringEvenWithNoInput(){
        this.machine = new Machine("java");
        this.machine.add("clsname", "Test1");
        this.machine.setLanguage("java");
        this.machine.generate();

        this.map.put("clsname", "Test1");
        this.defaultsSetter.set(this.map);
        try {
            assertTrue(this.loadFile("Test1.java").equals(this.produceStringFromMap()));

            File file = new File("Test1.java");
            file.delete();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            assertTrue(false);
        }
    }

    @Test
    public void shouldGenerateTheSameWithTheGivenVarsInput(){
        this.machine = new Machine("java");

        this.machine.add("vars", "String|var");
        this.machine.add("vars", "int|n");
        this.machine.add("vars", "boolean|b");
        this.machine.add("clsname", "Test2");

        this.machine.generate();

        this.map.put("clsname", "Test2");
        Map vars = (Map) this.map.get("vars");
        Map var = new HashMap();
        Map n = new HashMap();
        Map b = new HashMap();

        var.put("dataType", "String");
        var.put("identifier", "var");

        n.put("dataType", "int");
        n.put("identifier", "n");

        b.put("dataType", "boolean");
        b.put("identifier", "b");

        vars.put("var", var);
        vars.put("n", n);
        vars.put("b", b);

        Map s = (Map) this.map.get("s");
        s.put("var", new Pair<String, String>("String", "var"));
        s.put("n", new Pair<String, String>("int", "n"));
        s.put("b", new Pair<String, String>("boolean", "b"));

        Map g = (Map) this.map.get("g");
        g.put("var", new Pair<String, String>("String", "var"));
        g.put("n", new Pair<String, String>("int", "n"));
        g.put("b", new Pair<String, String>("boolean", "b"));

        this.defaultsSetter.set(this.map);
        try {
            assertTrue(this.loadFile("Test2.java").equals(produceStringFromMap()));

            File file = new File("Test2.java");
            file.delete();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            assertTrue(false);
        }
    }

    @Test
    public void shouldGenerateTheSameGivenFns(){
        this.machine = new Machine("java");
        this.machine.setLanguage("java");

        this.machine.add("fns", "String|processHouseName(String|houseName)");
        this.machine.add("fns", "String|returnToOwner(Owner|owner, Date|date)");
        this.machine.add("fns", "String|resolveHouseIssue()");
        this.machine.add("clsname", "Test3");

        this.machine.generate();

        this.map.put("clsname", "Test3");
        Map fns = (Map) this.map.get("fns");
        Map f1 = new HashMap();
        Map f2 = new HashMap();
        Map f3 = new HashMap();

        Map f1v = new LinkedHashMap();
        Map f2v = new LinkedHashMap();
        Map f3v = new LinkedHashMap();

        f1.put("accessibility", "+");
        f1.put("identifier", "processHouseName");
        f1.put("dataType", "String");
        f1.put("versions", f1v);
        f1v.put("String", "String houseName");

        f2.put("accessibility", "+");
        f2.put("identifier", "returnToOwner");
        f2.put("dataType", "String");
        f2.put("versions", f2v);
        f2v.put("Owner|Date", "Owner owner, Date date");

        f3.put("accessibility", "+");
        f3.put("identifier", "resolveHouseIssue");
        f3.put("dataType", "String");
        f3.put("versions", f3v);
        f3v.put("null", "");

        fns.put("processHouseName", f1);
        fns.put("returnToOwner", f2);
        fns.put("resolveHouseIssue", f3);

        this.defaultsSetter.set(this.map);
        try {
            assertTrue(this.loadFile("Test3.java").equals(produceStringFromMap()));

            File file = new File("Test3.java");
            file.delete();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            assertTrue(false);
        }
    }

    private String produceStringFromMap(){
        StringBuilder stringBuilder = new StringBuilder();
        CodeGeneratorUnit curGeneratorUnit;

        String[] units = new String[]{"header", "imports",
                "class_begin", "variables", "constructors",
                "setters", "getters", "functions", "class_end"};

        for (String unit: units) {
            curGeneratorUnit = JavaCodeGeneratorUnitFactory.create(unit);
            curGeneratorUnit.generate(this.map);
            stringBuilder.append(curGeneratorUnit.getGeneratedCode());
        }

        return stringBuilder.toString() + "\n";
    }

    private String loadFile(String filename) throws IOException{
        String curLine;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        while((curLine = bufferedReader.readLine()) != null){
            stringBuilder.append(curLine + "\n");
        }

        bufferedReader.close();

        return stringBuilder.toString();
    }
}
