package org.lambda_n2t.codegenerator.CommandExecuter;

import org.lambda_n2t.codegenerator.Settings;

/**
 * Created by Geomart Brenth Abong on 7/20/2016.
 */
public class SettingChangerCommand implements CommandExecuter {
    private String[] args;

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() {
        Settings settings = Settings.getInstance();
        settings.set(this.args[0], this.args[1]);

        settings.save();
    }
}
