package org.lambda_n2t.codegenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class Settings {
    private static Settings instance;
    private HashMap<String, String> data;
    private HashMap<String, String> settables;

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

    public void set(String key, String value) {
        if (this.settables.containsKey(key))
            this.settables.put(key, value);
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(ResourceLoader.getUserResourcesPath() + "config.txt", "UTF-8");

            for (Map.Entry<String, String> setting: this.settables.entrySet()) {
                if (setting.getValue() == null && this.data.containsKey(setting.getKey()))
                    writer.println(setting.getKey() + " = " + this.data.get(setting.getKey()));
                else if (setting.getValue() != null && !setting.getValue().trim().equals("")) {
                    this.data.put(setting.getKey(), setting.getValue());
                    writer.println(setting.getKey() + " = " + setting.getValue());
                }
            }

            writer.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
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

        this.settables = new HashMap<String, String>();
        this.settables.put("language", null);
        this.settables.put("format", null);
        this.settables.put("pname", null);
        this.settables.put("package", null);
    }
}
