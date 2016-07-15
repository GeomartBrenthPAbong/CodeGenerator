package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ImportsUnit implements CodeGeneratorUnit{
    String generatedCode;

    public ImportsUnit(){
        this.generatedCode = "";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public void generate(Map map) {
        Map<String, String> imports = (Map) map.get("imports");

        if (imports.isEmpty())
            return;

        for (Map.Entry<String, String> i: imports.entrySet())
            this.generatedCode += "import " + i.getValue() + ";\n";
    }
}
