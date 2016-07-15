package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ClassBeginUnit implements CodeGeneratorUnit{
    String generatedCode;

    public ClassBeginUnit(){
        this.generatedCode = "";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public void generate(Map map) {
        this.generateType(map);
        this.generatedCode += map.get("clsname");
        this.generateExtends(map);
        this.generateImplements(map);
        this.generatedCode += " {\n";
    }

    private void generateType(Map map){
        Map type = (Map) map.get("type");

        this.generatedCode += type.get("accessibility") + " ";

        if (type.containsKey("final"))
            this.generatedCode += type.get("final") + " ";

        this.generatedCode += type.get("identifier") + " ";
    }

    private void generateExtends(Map map){
        String extendsName = (String) map.get("extends");

        if (extendsName.trim().length() > 0)
            this.generatedCode += " extends " + extendsName;
    }

    private void generateImplements(Map map){
        Map<String, String> implementNames = (Map) map.get("implements");

        if (!implementNames.isEmpty()){
            StringBuilder str = new StringBuilder();
            str.append(" implements");
            for (Map.Entry<String, String> i: implementNames.entrySet())
                str.append(" " + i.getValue() + ",");

            this.generatedCode += str.substring(0, str.length() - 1);
        }
    }
}
