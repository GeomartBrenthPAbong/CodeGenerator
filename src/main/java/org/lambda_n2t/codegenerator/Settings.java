package org.lambda_n2t.codegenerator;

import java.util.HashMap;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class Settings {
    private static Settings instance;
    private HashMap<String, String> data;

    private Settings(){}

    public static Settings getInstance(){
        if (instance == null) {
            instance = new Settings();
            instance.init();
        }
        return instance;
    }

    public String get(String name) throws IllegalArgumentException{
        if (this.data.containsKey(name))
            return this.data.get(name);

        throw new IllegalArgumentException("Invalid setting name.");
    }

    private void init(){
        this.data = new HashMap<String, String>();
        String rawConfig = ResourceLoader.toString(ResourceLoader.load("config"));
        String[] lines = rawConfig.split("\n");
        String[] setting;

        for (String line: lines) {
            line = line.trim();

            if (line.length() == 0)
                continue;

            setting = line.split("=");

            if (setting.length != 2)
                continue;

            this.data.put(setting[0].trim(), setting[1].trim());
        }
    }
}
