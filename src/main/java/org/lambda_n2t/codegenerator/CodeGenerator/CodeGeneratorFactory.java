package org.lambda_n2t.codegenerator.CodeGenerator;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class CodeGeneratorFactory {
    public static CodeGenerator create(String type){
        if (type.equals("java"))
            return JavaCodeGenerator.getInstance();
        else
            throw new IllegalArgumentException("Invalid code generator");
    }
}
