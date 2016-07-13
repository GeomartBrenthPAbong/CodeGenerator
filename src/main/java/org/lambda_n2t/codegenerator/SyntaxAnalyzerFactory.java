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
        else if(type.equals("type"))
            return new ClassTypeSyntaxAnalyzer();
        else if(type.equals("rmvars") || type.equals("clsname") || type.equals("extends") ||
                type.equals("implements") || type.equals("rmimplements"))
            return new IdentifierOnlySyntaxAnalyzer();
        else if(type.equals("rmfns"))
            return new RMFnSyntaxAnalyzer();
        else if(type.equals("constructor"))
            return new ParamSyntaxAnalyzer();
        else if(type.equals("rmconstructor"))
            return new DataTypeOnlySyntaxAnalyzer();
        else
            throw new IllegalArgumentException("Invalid Syntax Analyzer.");
    }
}
