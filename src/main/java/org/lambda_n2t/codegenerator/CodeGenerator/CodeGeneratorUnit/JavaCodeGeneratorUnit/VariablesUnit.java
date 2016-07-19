package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class VariablesUnit implements CodeGeneratorUnit {
    StringBuilder generatedCode;
    String lastGeneratedCode;

    public VariablesUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    // TODO: Add option to include variable value.
    public void generate(Map map) {
        Map<String, Map> vars = (Map) map.get("vars");

        if (vars.isEmpty())
            return;

        this.generatedCode.append("\n");

        for (Map.Entry<String, Map> var: vars.entrySet())
            this.generateVar(var.getValue());

        this.lastGeneratedCode = this.generatedCode.toString();
        this.generatedCode.delete(0, this.generatedCode.length());
    }

    private void generateVar(Map var){
        this.generatedCode.append("\n\t" + var.get("accessibility") + " ");

        if(var.containsKey("static"))
            this.generatedCode.append(var.get("static") + " ");

        if(var.containsKey("final"))
            this.generatedCode.append(var.get("final") + " ");

        this.generatedCode.append(var.get("dataType") + " ");
        this.generatedCode.append(var.get("identifier") + ";");
    }
}
