package org.lambda_n2t.codegenerator.CommandExecuter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class CommandExecuterFactory {
    public static CommandExecuter create(String[] args){
        if (args.length == 1){
            String p = "^\\-.*$";
            Pattern pattern = Pattern.compile(p);
            Matcher m = pattern.matcher(args[0].trim());

            if (m.matches())
                return new AllInOneCommand();
            else
                return new StepByStepCommand();
        }
        else if(args.length > 1)
            return new QuickCommand();
        else
            return new StepByStepCommand();
    }
}
