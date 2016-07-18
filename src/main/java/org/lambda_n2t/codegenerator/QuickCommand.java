package org.lambda_n2t.codegenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */

public class QuickCommand implements CommandExecuter {
    private Map<String, String> cmdVarsToCommands;
    private String[] args;

    public QuickCommand(){
        this.cmdVarsToCommands = new HashMap<String, String>();

        this.cmdVarsToCommands.put("-c", "clsname");
        this.cmdVarsToCommands.put("-v", "vars");
        this.cmdVarsToCommands.put("-f", "fns");
        this.cmdVarsToCommands.put("-t", "type");
        this.cmdVarsToCommands.put("-p", "pname");
        this.cmdVarsToCommands.put("-ctrs", "constructors");
        this.cmdVarsToCommands.put("-m", "imports");
        this.cmdVarsToCommands.put("-i", "implements");
        this.cmdVarsToCommands.put("-e", "extends");
        this.cmdVarsToCommands.put("-l", "language");
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        System.out.println("Processing...");

        String curCommand = null;
        String curArg = null;
        StringBuilder stringBuilder = new StringBuilder();
        Settings settings = Settings.getInstance();
        Machine machine = new Machine(settings.get("language"));

        for (int i = 0; i < this.args.length; i++){
            curArg = this.args[i];

            if (this.cmdVarsToCommands.containsKey(curArg)){
                curCommand = curArg;
                stringBuilder.delete(0, stringBuilder.length());

                for (int j = 0; j < this.args.length; j++){
                    if (this.cmdVarsToCommands.containsKey(this.args[i]))
                        break;

                    stringBuilder.append(this.args[i].trim());
                }

                try {
                    if (stringBuilder.length() > 0 && !curCommand.equals("-l")) {
                        curArg = stringBuilder.toString();

                        for (String arg: curArg.split(";"))
                            machine.add(curCommand, arg);
                    }
                    else if (stringBuilder.length() > 0)
                        machine.setLanguage(stringBuilder.toString());
                }
                catch (Exception e){}
            }
        }

        machine.generate();
    }
}
