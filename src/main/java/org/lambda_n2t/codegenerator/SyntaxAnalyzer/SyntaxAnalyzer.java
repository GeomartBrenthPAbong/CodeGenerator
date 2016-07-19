package org.lambda_n2t.codegenerator.SyntaxAnalyzer;

import javafx.util.Pair;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */
public abstract class SyntaxAnalyzer {
    protected Pattern p;

    public void analyze(String input, List<Pair<String, String>> tokens){
        this.analyze(input);
        this.analyze(tokens);
    }

    public void analyze(String input) throws InputMismatchException{
        Matcher m = this.p.matcher(input);

        if (!m.matches())
            throw new InputMismatchException("Invalid argument syntax: " + input);
    }

    public void analyze(List<Pair<String, String>> tokens) throws InputMismatchException{
        for (Pair<String, String> token: tokens)
            if (token.getKey().equals("NONE") && !this.noneAllowed())
                throw new InputMismatchException();
    }

    protected boolean noneAllowed() {
        return false;
    }
}
