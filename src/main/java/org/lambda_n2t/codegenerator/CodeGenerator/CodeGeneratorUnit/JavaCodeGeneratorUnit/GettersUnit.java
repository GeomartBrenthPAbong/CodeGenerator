package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import javafx.util.Pair;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */

public class GettersUnit implements CodeGeneratorUnit {
    StringBuilder generatedCode;
    String lastGeneratedCode;

    public GettersUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    public void generate(Map map) {
        Map<String, Pair<String, String>> setters = (Map) map.get("g");

        if (setters.isEmpty())
            return;

        Pair<String, String> paramTypeAndName;
        this.generatedCode.append("\n");
        this.generatedCode.append("\n");
        this.generatedCode.append(this.getComment());

        Map type = (Map) map.get("type");

        int counter = 0;

        for (Map.Entry<String, Pair<String, String>> setter: setters.entrySet()){
            paramTypeAndName = setter.getValue();

            if (!type.get("identifier").equals("interface") || counter == 0)
                this.generatedCode.append("\n");

            this.generatedCode.append("\n\tpublic " + paramTypeAndName.getKey() + " get" + paramTypeAndName.getValue() + "()");

            if (type.get("identifier").equals("interface"))
                this.generatedCode.append(";");
            else {
                this.generatedCode.append(" {\n\t\treturn this." + setter.getKey() + ";\n");
                this.generatedCode.append("\t}");
            }

            counter++;
        }

        this.lastGeneratedCode = this.generatedCode.toString();
        this.generatedCode.delete(0, this.generatedCode.length());
    }

    private String getComment(){
        return ResourceLoader.toString(ResourceLoader.load("getters"));
    }
}
