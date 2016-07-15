package org.lambda_n2t.codegenerator.CodeGenerator;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit.JavaCodeGeneratorUnitFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class JavaCodeGenerator implements CodeGenerator {
    private static JavaCodeGenerator instance = null;
    List<CodeGeneratorUnit> codeGenUnits = new ArrayList<CodeGeneratorUnit>();

    public JavaCodeGenerator(){}

    public static JavaCodeGenerator getInstance(){
        if (instance == null) {
            instance = new JavaCodeGenerator();
            instance.init();
        }
        return instance;
    }

    public void generate(Map map) {
        this.defaultsAndMappings(map);

        try {
            PrintWriter writer = new PrintWriter(map.get("clsname") + ".java", "UTF-8");

            for (CodeGeneratorUnit unit: this.codeGenUnits) {
                unit.generate(map);
                writer.print(unit.getGeneratedCode());
            }

            writer.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    private void init(){
        String[] units = new String[]{"header", "space", "imports",
                "class_begin", "variables", "constructors",
                "setters", "getters", "other_functions", "class_end"};

        for (String unit: units)
            this.codeGenUnits.add(JavaCodeGeneratorUnitFactory.create(unit));
    }

    private void defaultsAndMappings(Map map){
        // Includes uppercasing of first letters
    }
}
