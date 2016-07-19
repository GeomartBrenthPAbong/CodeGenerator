package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.util.Map;

import static javax.swing.UIManager.get;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ConstructorsUnit implements CodeGeneratorUnit {
    StringBuilder generatedCode;
    String lastGenerateCode;

    public ConstructorsUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGenerateCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGenerateCode;
    }

    // TODO: Add option to change accessibility.
    public void generate(Map map) {
        Map<String, String> constructors = (Map) map.get("constructors");
        Map type = (Map) map.get("type");

        if (constructors.isEmpty())
            return;

        this.generatedCode.append("\n");
        this.generatedCode.append("\n");
        this.generatedCode.append(this.getComment());

        if (constructors.containsKey("class")){
            this.generateConstructor(constructors.get("class"), (String) map.get("clsname"));
            constructors.remove("class");
        }

        for (Map.Entry<String, String> constructor: constructors.entrySet())
            this.generateConstructor(constructor.getValue(), (String) map.get("clsname"));

        this.lastGenerateCode = this.generatedCode.toString();
        this.generatedCode.delete(0, this.generatedCode.length());
    }

    private String getComment(){
        return ResourceLoader.toString(ResourceLoader.load("constructors"));
    }

    private void generateConstructor(String constructorParams, String clsName){
        this.generatedCode.append("\n\n\tpublic " + clsName + "(" + constructorParams + ") {\n\n\t}");
    }
}
