package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.ResourceLoader;

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

    public void generate(Map map){
        Map<String, Map> fns = (Map<String, Map>) map.get("fns");

        if (fns.isEmpty())
            return;

        Map fnData;
        Map<String, String> versions;
        int counter = 0;
        for (Map.Entry<String, Map> fn: fns.entrySet()) {
            fnData = fn.getValue();

            if (fnData.get("versions") == null)
                continue;

            versions = (Map<String, String>) fnData.get("versions");

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
            Map type = (Map) map.get("type");

            for (Map.Entry<String, String> version : versions.entrySet()) {
                if (!type.get("identifier").equals("interface") || counter == 0)
                    this.generatedCode.append("\n");

                this.generatedCode.append("\n\t" + preVersion + version.getValue() + ")");

                if (type.get("identifier").equals("interface"))
                    this.generatedCode.append(";");
                else
                    this.generatedCode.append(" {\n\t\t" + ret + "\n\t}");

                counter++;
            }
        }

        this.lastGeneratedCode = this.generatedCode.toString();

        if (this.lastGeneratedCode.trim().length() > 0)
            this.lastGeneratedCode = "\n\n" + this.getComment() + this.lastGeneratedCode;

        this.generatedCode.delete(0, this.generatedCode.length());
    }

    private String getComment(){
        return ResourceLoader.toString(ResourceLoader.load("functions"));
    }

    private String getReturn(String returnType){
        if (returnType.equals("boolean"))
            return "return false;";
        else if(returnType.equals("void"))
            return "";
        else
            return "return null;";
    }
}
