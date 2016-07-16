package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ImportsUnit implements CodeGeneratorUnit{
    StringBuilder generatedCode;
    String lastGeneratedCode;

    public ImportsUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    public void generate(Map map) {
        Map<String, String> imports = (Map) map.get("imports");

        if (imports.isEmpty())
            return;

        for (Map.Entry<String, String> i: imports.entrySet())
            this.generatedCode.append("import " + i.getValue() + ";\n");

        this.lastGeneratedCode = this.generatedCode.toString();
    }
}
