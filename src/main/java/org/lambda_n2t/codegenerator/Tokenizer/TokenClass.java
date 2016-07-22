package org.lambda_n2t.codegenerator.Tokenizer;

import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public class TokenClass {
    public static String accessibility = "^[\\+\\-#]$";
    public static String cgStatic = "^\\^$";
    public static String cgFinal = "^!$";
    public static String specialChar = "^[\\+\\-#\\^\\!]$";
    public static String identifier = "^[a-zA-Z\\$_][a-zA-Z0-9\\$_]*$";
    public static String separator = "^[\\|:;,\\(\\)]$";
    public static String dataType = "^[a-zA-Z\\$_][a-zA-Z0-9\\$_ <>,]*( *\\[ *\\] *)* *\\|$";
    public static String openPar = "^\\($";
    public static String closePar = "^\\)$";
    public static String par = "^[\\(\\)]$";
    public static String opposites = "^[<>]$";
    public static String importsSpecific = "^[a-zA-Z\\$_][a-zA-Z0-9\\$_\\.\\*]*$";
    public static String all = "^ *\\* *$";

    public static Pattern accessibilityPattern = Pattern.compile(accessibility);
    public static Pattern cgStaticPattern = Pattern.compile(cgStatic);
    public static Pattern cgFinalPattern = Pattern.compile(cgFinal);
    public static Pattern specialCharPattern = Pattern.compile(specialChar);
    public static Pattern identifierPattern = Pattern.compile(identifier);
    public static Pattern separatorPattern = Pattern.compile(separator);
    public static Pattern dataTypePattern = Pattern.compile(dataType);
    public static Pattern openParPattern = Pattern.compile(openPar);
    public static Pattern closeParPattern = Pattern.compile(closePar);
    public static Pattern parPattern = Pattern.compile(par);
    public static Pattern oppositesPattern = Pattern.compile(opposites);
    public static Pattern importsSpecificPattern = Pattern.compile(importsSpecific);
    public static Pattern allPattern = Pattern.compile(all);
}
