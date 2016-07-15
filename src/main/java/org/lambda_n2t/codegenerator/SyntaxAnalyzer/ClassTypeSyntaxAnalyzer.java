package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class ClassTypeSyntaxAnalyzer extends SyntaxAnalyzer {
    public ClassTypeSyntaxAnalyzer(){
        String pattern = "^ *[\\+\\-#]? *!? *[iac] *$";
        this.p = Pattern.compile(pattern);
    }
}
