package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class VariablesUnit implements CodeGeneratorUnit {
    String generatedCode;

    public VariablesUnit(){
        this.generatedCode = "";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public void generate(Map map) {
        Map<String, Map> vars = (Map) map.get("vars");

        if (vars.isEmpty())
            return;

        for (Map.Entry<String, Map> var: vars.entrySet())
            this.generateVar(var.getValue());
    }

    private void generateVar(Map var){
        this.generatedCode += "\t" + var.get("accessibility") + " ";

        if(var.containsKey("static"))
            this.generatedCode += var.get("static") + " ";

        if(var.containsKey("final"))
            this.generatedCode += var.get("final") + " ";

        this.generatedCode += var.get("dataType") + " ";
        this.generatedCode += var.get("identifier") + ";\n";
    }
}
