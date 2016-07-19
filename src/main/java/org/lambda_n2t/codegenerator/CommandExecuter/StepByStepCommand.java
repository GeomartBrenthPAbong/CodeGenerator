package org.lambda_n2t.codegenerator.CommandExecuter;

import org.lambda_n2t.codegenerator.Machine;
import org.lambda_n2t.codegenerator.Settings;

import java.util.Scanner;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */

public class StepByStepCommand implements CommandExecuter {
    private String language;

    public void setArgs(String[] args) {
        if (args.length == 0) {
            Settings settings = Settings.getInstance();
            this.language = settings.get("language");
        }
        else
            this.language = args[0];
    }

    public void execute() {
        Machine machine = new Machine(this.language);
        Scanner scanner = new Scanner(System.in);
        String input, curCommand, curArg;
        int firstSeparator;

        do {
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.equals("create")){
                machine.generate();
                System.out.println("Code generated.");
                break;
            }
            else if(!input.equals("quit")) {
                try {
                    System.out.println("Processing...");

                    firstSeparator = input.indexOf(":");

                    if (firstSeparator == -1)
                        throw new Exception("Invalid input. It should in the form:\ncommand: arg\n" +
                                "command can be vars|fns|type|pname|constructors|imports|implements|extends\n\n" +
                                "Type 'create' without quotes to quit and generate or just 'quit' if you want to quit.");

                    curCommand = input.substring(0, firstSeparator).trim();
                    curArg = input.substring(firstSeparator + 1);

                    for (String arg : curArg.split(";"))
                        machine.add(curCommand, arg);

                    System.out.println("Done.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        while(!input.equals("quit"));
    }
}
