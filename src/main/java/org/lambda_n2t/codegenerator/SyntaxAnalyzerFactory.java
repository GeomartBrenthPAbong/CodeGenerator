package org.lambda_n2t.codegenerator;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class SyntaxAnalyzerFactory {
    public static SyntaxAnalyzer create(String type) throws IllegalArgumentException{
        if(type.equals("vars"))
            return new VarSyntaxAnalyzer();
        else if(type.equals("fns"))
            return new FnSyntaxAnalyzer();
        else
            throw new IllegalArgumentException("Invalid Syntax Analyzer.");
    }
}
