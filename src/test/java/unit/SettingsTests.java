package unit;

import org.junit.AfterClass;
import org.junit.Test;
import org.lambda_n2t.codegenerator.ResourceLoader;
import org.lambda_n2t.codegenerator.Settings;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class SettingsTests {
    @Test
    public void shouldReturnCorrespondingData(){
        Settings settings = Settings.getInstance();

        assertTrue(settings.get("language").equals("java") &&
                   settings.get("format").equals("type@clsname@extends@implements@vars@fns@constructors@pname@imports"));
    }

    @Test
    public void settingShouldBeAdded(){
        Settings settings = Settings.getInstance();
        settings.set("package", "org.lambda_n2t.codegenerator");
        settings.set("pname", "Geomart Abong");
        settings.save();

        Map newSettings = this.loadSettings();
        assertTrue(newSettings.get("package").equals("org.lambda_n2t.codegenerator"));
        assertTrue(newSettings.get("pname").equals("Geomart Abong"));
        assertTrue(newSettings.get("language").equals("java"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSettingDoesNotExists(){
        Settings settings = Settings.getInstance();
        settings.get("This is a random string.");
    }

    @AfterClass
    public static void deleteFile(){
        Settings settings = Settings.getInstance();
        settings.set("package", "");
        settings.set("pname", "");
        settings.save();
    }

    private Map loadSettings(){
        Map data = new HashMap<String, String>();
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

            data.put(setting[0].trim(), setting[1].trim());
        }

        return data;
    }
}
