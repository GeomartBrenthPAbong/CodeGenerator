package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class JavaCodeGeneratorUnitFactory {
    public static CodeGeneratorUnit create(String type){
        if (type.equals("header"))
            return new HeaderUnit();
        else if(type.equals("space"))
            return new SpaceUnit();
        else if(type.equals("imports"))
            return new ImportsUnit();
        else if(type.equals("class_begins"))
            return new ClassBeginUnit();
        else if(type.equals("variables"))
            return new VariablesUnit();
        else
            throw new IllegalArgumentException("Invalid java code generator unit.");
    }
}