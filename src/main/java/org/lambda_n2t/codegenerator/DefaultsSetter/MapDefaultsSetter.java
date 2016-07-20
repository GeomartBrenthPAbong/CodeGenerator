package org.lambda_n2t.codegenerator.DefaultsSetter;

import org.lambda_n2t.codegenerator.Settings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/16/2016.
 */

public class MapDefaultsSetter implements DefaultsSetter{
    private HashMap<String, String> codeEquals;
    private String[] commonLoopables;

    public MapDefaultsSetter(){
        this.codeEquals = new HashMap<String, String>();

        this.codeEquals.put("+", "public");
        this.codeEquals.put("-", "private");
        this.codeEquals.put("#", "protected");
        this.codeEquals.put("^", "static");
        this.codeEquals.put("!", "final");
        this.codeEquals.put("i", "interface");
        this.codeEquals.put("a", "abstract class");
        this.codeEquals.put("c", "class");

        this.commonLoopables = new String[]{"accessibility", "static", "final"};
    }

    public void set(Map map){
        this.setPackage(map);
        this.setClassTypeDefaults(map);
        this.setClsName(map);
        this.setVarsDefaults((Map<String, Map>) map.get("vars"));
        this.setFnsDefaults((Map<String, Map>) map.get("fns"));
        this.setProgrammerName(map);
    }

    private void setPackage(Map map){
        String pkg = (String) map.get("package");

        if (pkg == null || pkg.trim().equals("")) {
            Settings settings = Settings.getInstance();
            try {
                map.put("package", settings.get("package"));
            }
            catch (Exception e){}
        }
    }

    private void setClassTypeDefaults(Map map){
        Map<String, String> type = (Map<String, String>) map.get("type");

        if (!type.containsKey("accessibility"))
            type.put("accessibility", "+");

        if (!type.containsKey("identifier"))
            type.put("identifier", "c");

        if (type.get("identifier").equals("i")) {
            Map constructors = (Map) map.get("constructors");
            constructors.clear();
        }

        for (Map.Entry<String, String> typeItem: type.entrySet())
            type.put(typeItem.getKey(), this.codeEquals.get(typeItem.getValue()));
    }

    private void setClsName(Map map){
        if (map.get("clsname").equals(""))
            map.put("clsname", "ClassName");
    }

    private void setVarsDefaults(Map<String, Map> vars){
        if (vars.isEmpty())
            return;

        Map var;

        for (Map.Entry<String, Map> varData: vars.entrySet()){
            var = vars.get(varData.getKey());

            if (!var.containsKey("accessibility"))
                var.put("accessibility", "-");

            for (String item: this.commonLoopables) {
                if (var.containsKey(item))
                    var.put(item, this.codeEquals.get(var.get(item)));
            }
        }
    }

    private void setFnsDefaults(Map<String, Map> fns){
        if (fns.isEmpty())
            return;

        Map fn;

        for (Map.Entry<String, Map> fnData: fns.entrySet()){
            fn = fns.get(fnData.getKey());

            if (!fn.containsKey("accessibility"))
                fn.put("accessibility", "+");

            for (String item: this.commonLoopables) {
                if (fn.containsKey(item))
                    fn.put(item, this.codeEquals.get(fn.get(item)));
            }
        }
    }

    private void setProgrammerName(Map map){
        String pname = (String) map.get("pname");

        if (pname.trim().equals("")) {
            Settings settings = Settings.getInstance();

            try {
                map.put("pname", settings.get("pname"));
            }
            catch (Exception e) {}
        }
    }
}
