package org.lambda_n2t.codegenerator.CommandExecuter;

import org.lambda_n2t.codegenerator.ResourceLoader;

/**
 * Created by Geomart Brenth Abong on 7/18/2016.
 */
public class HelpCommand implements CommandExecuter {
    public void setArgs(String[] args) {}

    public void execute() {
        System.out.println(ResourceLoader.toString(ResourceLoader.load("help")));
    }
}
