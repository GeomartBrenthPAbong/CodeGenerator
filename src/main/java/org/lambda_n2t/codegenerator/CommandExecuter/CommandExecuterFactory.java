package org.lambda_n2t.codegenerator.CommandExecuter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class CommandExecuterFactory {
    public static CommandExecuter create(String[] args){
        if (args.length == 1 && args[0].equals("help")){
            return new HelpCommand();
        }
        else if (args.length == 1){
            String p = "^\\-.*$";
            Pattern pattern = Pattern.compile(p);
            Matcher m = pattern.matcher(args[0].trim());

            if (args[0].contains("@"))
                return new AllInOneCommand();
            else
                return new StepByStepCommand();
        }
        else if(args.length == 2) {
            return new SettingChangerCommand();
        }
        else
            return new StepByStepCommand();
    }
}
