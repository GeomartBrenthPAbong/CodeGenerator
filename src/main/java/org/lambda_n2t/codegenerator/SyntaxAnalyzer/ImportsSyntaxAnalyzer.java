package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class ImportsSyntaxAnalyzer extends SyntaxAnalyzer {

    public ImportsSyntaxAnalyzer(){
        String pattern = "^ *[a-zA-Z\\$_][a-zA-Z0-9\\$_\\.\\*]* *(, *[a-zA-Z\\$_][a-zA-Z0-9\\$_\\.\\*]*)*$";
        this.p = Pattern.compile(pattern);
    }
}
