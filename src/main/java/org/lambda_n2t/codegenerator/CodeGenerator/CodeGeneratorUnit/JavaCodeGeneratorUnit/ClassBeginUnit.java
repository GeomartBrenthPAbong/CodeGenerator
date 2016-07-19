package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ClassBeginUnit implements CodeGeneratorUnit{
    StringBuilder generatedCode;
    String lastGeneratedCode;

    public ClassBeginUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    public void generate(Map map) {
        this.generatedCode.append("\n\n");
        this.generateType(map);
        this.generatedCode.append(map.get("clsname"));
        this.generateExtends(map);
        this.generateImplements(map);
        this.generatedCode.append(" {");

        this.lastGeneratedCode = this.generatedCode.toString();
        this.generatedCode.delete(0, this.generatedCode.length());
    }

    private void generateType(Map map){
        Map type = (Map) map.get("type");

        this.generatedCode.append(type.get("accessibility") + " ");

        if (type.containsKey("final"))
            this.generatedCode.append(type.get("final") + " ");

        this.generatedCode.append(type.get("identifier") + " ");
    }

    private void generateExtends(Map map){
        String extendsName = (String) map.get("extends");

        if (extendsName.trim().length() > 0)
            this.generatedCode.append(" extends " + extendsName);
    }

    private void generateImplements(Map map){
        Map<String, String> implementNames = (Map) map.get("implements");

        if (!implementNames.isEmpty()){
            StringBuilder str = new StringBuilder();
            str.append(" implements");
            for (Map.Entry<String, String> i: implementNames.entrySet())
                str.append(" " + i.getValue() + ",");

            this.generatedCode.append(str.substring(0, str.length() - 1));
        }
    }
}
