package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class SpaceUnit implements CodeGeneratorUnit{
    String generatedCode;

    public SpaceUnit(){
        this.generatedCode = "\n";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public void generate(Map map) {
        return;
    }
}
