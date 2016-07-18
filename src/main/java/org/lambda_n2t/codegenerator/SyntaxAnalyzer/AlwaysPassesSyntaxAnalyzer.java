package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/18/2016.
 */
public class AlwaysPassesSyntaxAnalyzer extends SyntaxAnalyzer {
    public AlwaysPassesSyntaxAnalyzer() {
        String pattern = "^.*$";
        this.p = Pattern.compile(pattern);
    }

    @Override
    protected boolean noneAllowed() {
        return true;
    }
}
