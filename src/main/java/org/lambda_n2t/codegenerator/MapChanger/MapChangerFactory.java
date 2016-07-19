package org.lambda_n2t.codegenerator.MapChanger;

/**
 * Created by Geomart Brenth Abong on 7/14/2016.
 */
public class MapChangerFactory {
    public static MapChanger create(String type){
        if (type.equals("vars"))
            return new VarMapChanger();
        else if(type.equals("fns"))
            return new FnMapChanger();
        else if(type.equals("rmfns"))
            return new RMFnMapChanger();
        else if(type.equals("rmvars"))
            return new RMVarMapChanger();
        else if(type.equals("type"))
            return new ClassTypeMapChanger();
        else if(type.equals("pname"))
            return new PNameMapChanger();
        else if(type.equals("clsname"))
            return new ClassNameMapChanger();
        else if(type.equals("constructors"))
            return new ConstructorMapChanger();
        else if(type.equals("rmconstructors"))
            return new RMConstructorMapChanger();
        else if(type.equals("extends"))
            return new ExtendsMapChanger();
        else if(type.equals("implements"))
            return new ImplementsMapChanger();
        else if(type.equals("rmimplements"))
            return new RMImplementsMapChanger();
        else if(type.equals("imports"))
            return new ImportsMapChanger();
        else if(type.equals("rmimports"))
            return new RMImportsMapChanger();
        else
            throw new IllegalArgumentException("Invalid map changer.");
    }
}
