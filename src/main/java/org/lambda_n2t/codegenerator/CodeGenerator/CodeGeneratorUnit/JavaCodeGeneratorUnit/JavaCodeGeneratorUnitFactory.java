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
        else if(type.equals("class_begin"))
            return new ClassBeginUnit();
        else if(type.equals("variables"))
            return new VariablesUnit();
        else if(type.equals("constructors"))
            return new ConstructorsUnit();
        else if(type.equals("functions"))
            return new FunctionsUnit();
        else if(type.equals("setters"))
            return new SettersUnit();
        else if(type.equals("getters"))
            return new GettersUnit();
        else if(type.equals("class_end"))
            return new ClassEndUnit();
        else
            throw new IllegalArgumentException("Invalid java code generator unit.");
    }
}
