package org.lambda_n2t.codegenerator;

import java.io.*;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class ResourceLoader {
    private static String[] loadables = new String[]{"header", "config"};


    public static InputStream load(String resource){
        if (!bruteForceSearchItem(resource))
            return null;

        try {
            String jarPath = ClassLoader.getSystemClassLoader().getResource(".").getPath();
            File userResource = new File(jarPath + "/" + resource + ".txt");

            if (userResource.exists()) {
                return new FileInputStream(userResource);
            } else {
                return ResourceLoader.class.getClassLoader().getResourceAsStream(resource + ".txt");
            }
        }
        catch(IOException e){
            System.err.println( "Error: " + e );
            return null;
        }
    }

    public static String toString(InputStream input){
        try {
            String line;
            StringBuilder str = new StringBuilder();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            while((line = bufferedReader.readLine()) != null )
                str.append(line + "\n");

            return str.toString();
        }
        catch(IOException e) {
            System.err.println( "Error: " + e );

            return "";
        }
    }

    private static boolean bruteForceSearchItem(String resource){
        for (String loadable: loadables)
            if (loadable.equals(resource))
                return true;
        return false;
    }
}
