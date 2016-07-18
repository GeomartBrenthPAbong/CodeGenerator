package unit;

import org.junit.Test;
import org.lambda_n2t.codegenerator.Settings;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */
public class SettingsTests {
    private Settings settings;

    @Test
    public void shouldReturnCorrespondingData(){
        settings = Settings.getInstance();

        assertTrue(settings.get("language").equals("java") &&
                   settings.get("format").equals("type@clsname@extends@implements@vars@fns@constructors@pname@imports"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSettingDoesNotExists(){
        settings = Settings.getInstance();

        settings.get("This is a random string.");
    }
}
