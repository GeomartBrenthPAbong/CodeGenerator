package org.lambda_n2t.codegenerator;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */

public class Driver {
    public static void main(String[] args){
        CommandExecuter commandExecuter = CommandExecuterFactory.create(args);
        commandExecuter.setArgs(args);
        commandExecuter.execute();
    }
}
