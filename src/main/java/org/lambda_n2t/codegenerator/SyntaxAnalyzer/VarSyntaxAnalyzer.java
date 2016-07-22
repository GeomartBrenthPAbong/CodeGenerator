package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class VarSyntaxAnalyzer extends SyntaxAnalyzer {
    public VarSyntaxAnalyzer(){
        String pattern = "^ *[\\+\\-#]? *\\^? *!? *" +
                         "[a-zA-Z\\$_][a-zA-Z0-9\\$_ ]*( *<[a-zA-Z0-9\\$_ <>,]*> *)?( *\\[ *\\] *)*" +
                         " *\\| *[a-zA-Z\\$_][a-zA-Z0-9\\$_]*" +
                         " *(\\( *" +
                            "(([a-zA-Z\\$_][a-zA-Z0-9\\$_]*)|" +
                            "((g *: *[a-zA-Z\\$_][a-zA-Z0-9\\$_]* *)(, *s *: *[a-zA-Z\\$_][a-zA-Z0-9\\$_]*)?)|" +
                            "((s *: *[a-zA-Z\\$_][a-zA-Z0-9\\$_]* *)(, *g *: *[a-zA-Z\\$_][a-zA-Z0-9\\$_]*)?))?" +
                         " *\\))? *$";
        this.p = Pattern.compile(pattern);
    }
}
