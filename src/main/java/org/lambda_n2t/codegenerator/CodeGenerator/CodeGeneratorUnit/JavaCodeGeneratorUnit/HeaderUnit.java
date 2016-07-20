package org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.JavaCodeGeneratorUnit;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorUnit.CodeGeneratorUnit;
import org.lambda_n2t.codegenerator.ResourceLoader;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */

public class HeaderUnit implements CodeGeneratorUnit {
    String generatedCode;
    String header;

    public HeaderUnit(){
        this.header = ResourceLoader.toString(ResourceLoader.load("header"));
        this.generatedCode = "";
    }

    public String getGeneratedCode() {
        return this.generatedCode;
    }

    public void generate(Map map) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String curDate = dateFormat.format(date);

        this.generatedCode = this.header.replaceAll("%programmer_name%", (String) map.get("pname"));
        this.generatedCode = this.generatedCode.replaceAll("%date_created%", curDate);

        String pkg = (String) map.get("package");

        if (pkg != null && !pkg.equals(""))
            this.generatedCode = "package " + pkg + ";\n\n" + this.generatedCode;
    }
}
