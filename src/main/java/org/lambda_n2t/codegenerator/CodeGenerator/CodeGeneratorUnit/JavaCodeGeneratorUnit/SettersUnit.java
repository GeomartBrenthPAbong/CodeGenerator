package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import javafx.util.Pair;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */

public class SettersUnit implements CodeGeneratorUnit {
    StringBuilder generatedCode;
    String lastGeneratedCode;

    public SettersUnit(){
        this.generatedCode = new StringBuilder();
        this.lastGeneratedCode = "";
    }

    public String getGeneratedCode() {
        return this.lastGeneratedCode;
    }

    public void generate(Map map) {
        Map<String, Pair<String, String>> setters = (Map) map.get("s");

        if (setters.isEmpty())
            return;

        Pair<String, String> paramTypeAndName;
        this.generatedCode.append(this.getComment());

        for (Map.Entry<String, Pair<String, String>> setter: setters.entrySet()){
            paramTypeAndName = setter.getValue();
            this.generatedCode.append("\tpublic void set" + paramTypeAndName.getValue());
            this.generatedCode.append("(" + paramTypeAndName.getKey() + " " + setter.getKey() + ") {\n");
            this.generatedCode.append("\t\tthis." + setter.getKey() + " = " + setter.getKey() + ";\n");
            this.generatedCode.append("\t}\n\n");
        }

        this.lastGeneratedCode = this.generatedCode.toString();
    }

    private String getComment(){
        return "\t//----------------------------------------------|\n" +
                "\t// Setter(s)\n" +
                "\t//----------------------------------------------|\n\n";
    }
}