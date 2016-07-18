package org.lambda_n2t.codegenerator.DefaultsSetter;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */
public class DefaultsSetterFactory {
    public static DefaultsSetter create(String type) throws IllegalArgumentException{
        if (type.equals("java"))
            return new JavaDefaultsSetter(new MapDefaultsSetter());
        else if(type.equals("map_defaults"))
            return new MapDefaultsSetter();
        else
            throw new IllegalArgumentException("Invalid map defaults setter.");
    }
}
