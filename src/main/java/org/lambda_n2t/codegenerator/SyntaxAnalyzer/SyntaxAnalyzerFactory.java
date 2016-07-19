package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

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
        else if(type.equals("constructors"))
            return new ParamSyntaxAnalyzer();
        else if(type.equals("rmconstructors"))
            return new DataTypeOnlySyntaxAnalyzer();
        else if(type.equals("imports") || type.equals("rmimports"))
            return new ImportsSyntaxAnalyzer();
        else if(type.equals("pname"))
            return new AlwaysPassesSyntaxAnalyzer();
        else
            throw new IllegalArgumentException("Invalid command.");
    }
}
