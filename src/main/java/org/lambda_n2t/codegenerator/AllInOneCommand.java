package org.lambda_n2t.codegenerator;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class AllInOneCommand implements CommandExecuter {
    private String arg;

    public void setArgs(String[] args) {
        this.arg = args[0];
    }

    public void execute() {
        System.out.println("Processing...");

        Settings settings = Settings.getInstance();
        String language = settings.get("language");
        String[] commands = settings.get("format").split("@");
        String[] args = this.arg.split("@");

        Machine machine = new Machine(language);

        try {
            int index = 0;
            String curArgs;

            for (String command : commands) {
                curArgs = args[index];

                try {
                    for (String curArg: curArgs.split(";"))
                        machine.add(command, curArg);
                }
                catch (Exception e){
                    System.out.println(e);
                    continue;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        finally {
            machine.generate();
        }
    }
}
