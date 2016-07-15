package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ConstructorsUnit implements CodeGeneratorUnit {
    String generatedCode;

    public ConstructorsUnit(){
        this.generatedCode = "";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    // TODO: Add option to change accessibility.
    public void generate(Map map) {
        Map<String, String> constructors = (Map) map.get("constructors");

        if (constructors.isEmpty())
            return;

        this.generatedCode += this.getComment();

        if (constructors.containsKey("class")){
            this.generateConstructor(constructors.get("class"), (String) map.get("clsname"));
            constructors.remove("class");
        }

        for (Map.Entry<String, String> constructor: constructors.entrySet())
            this.generateConstructor(constructor.getValue(), (String) map.get("clsname"));
    }

    private String getComment(){
        return "\t/**********************************************\n" +
                "\t* Constructor(s)\n" +
                "\t**********************************************/\n\n";
    }

    private void generateConstructor(String constructorParams, String clsName){
        this.generatedCode += "\tpublic " + clsName + "(" + constructorParams + ") {\n\n\t}\n\n";
    }
}
