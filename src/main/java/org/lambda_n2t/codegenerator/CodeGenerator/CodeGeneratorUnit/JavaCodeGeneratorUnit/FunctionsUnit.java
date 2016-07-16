package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */
public class FunctionsUnit implements CodeGeneratorUnit {
    private StringBuilder generatedCode;
    private String lastGeneratedCode;

    public FunctionsUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    public void generate(Map map) {
        Map<String, Map> fns = (Map) map.get("fns");

        if (fns.isEmpty())
            return;

        for (Map.Entry<String, Map> fn: fns.entrySet())
            this.generateFunction(fn.getValue());
    }

    private void generateFunction(Map fnData){
        Map<String, String> versions = (Map) fnData.get("versions");

        if (versions.isEmpty())
            return;

        StringBuilder preVersionString = new StringBuilder();
        String preVersion, ret;

        preVersionString.append(fnData.get("accessibility") + " ");

        if (fnData.containsKey("static"))
            preVersionString.append(fnData.get("static") + " ");

        if (fnData.containsKey("final"))
            preVersionString.append(fnData.get("final") + " ");

        preVersionString.append(fnData.get("dataType") + " ");
        preVersionString.append(fnData.get("identifier") + "(");

        preVersion = preVersionString.toString();
        ret = this.getReturn((String) fnData.get("dataType"));

        for (Map.Entry<String, String> version: versions.entrySet()){
            this.generatedCode.append("\t" + preVersion + version.getValue() + ") {\n");
            this.generatedCode.append("\t" + ret + "\n\t}\n\n");
        }

        this.lastGeneratedCode = this.generatedCode.toString();
    }

    private String getReturn(String returnType){
        if (returnType.equals("boolean"))
            return "return false;";
        else if(returnType.equals("void"))
            return "";
        else
            return "return null";
    }
}
