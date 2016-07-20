package org.lambda_n2t.codegenerator;

import org.lambda_n2t.codegenerator.CodeGenerator.CodeGenerator;
import org.lambda_n2t.codegenerator.CodeGenerator.CodeGeneratorFactory;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetter;
import org.lambda_n2t.codegenerator.DefaultsSetter.DefaultsSetterFactory;
import org.lambda_n2t.codegenerator.MapChanger.MapChanger;
import org.lambda_n2t.codegenerator.MapChanger.MapChangerFactory;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzer;
import org.lambda_n2t.codegenerator.SyntaxAnalyzer.SyntaxAnalyzerFactory;
import org.lambda_n2t.codegenerator.Tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Geomart Brenth Abong on 7/17/2016.
 */

public class Machine {
    private Map map;
    private Tokenizer tokenizer;
    private SyntaxAnalyzer syntaxAnalyzer;
    private MapChanger mapChanger;
    private CodeGenerator codeGenerator;
    private DefaultsSetter defaultsSetter;
    private HashMap<String, SyntaxAnalyzer> syntaxAnalyzerPool;
    private HashMap<String, MapChanger> mapChangerPool;
    private HashMap<String, String> supportedLanguages;

    public Machine(String language){
        this.supportedLanguages = new HashMap<String, String>();
        this.supportedLanguages.put("java", null);
        this.setLanguage(language);
        this.init();
    }

    public void setLanguage(String language) throws IllegalArgumentException{
        if (this.supportedLanguages.containsKey(language)) {
            this.codeGenerator = CodeGeneratorFactory.create(language);
            this.defaultsSetter = DefaultsSetterFactory.create(language);
        }
        else
            throw new IllegalArgumentException("Language not supported.");
    }

    public void add(String command, String arg){
        this.tokenizer.tokenize(arg);

        if (!this.syntaxAnalyzerPool.containsKey(command)) {
            this.syntaxAnalyzer = SyntaxAnalyzerFactory.create(command);
            this.syntaxAnalyzerPool.put(command, this.syntaxAnalyzer);
        } else
            this.syntaxAnalyzer = this.syntaxAnalyzerPool.get(command);

        this.syntaxAnalyzer.analyze(arg, this.tokenizer.getTokens());

        if (!this.mapChangerPool.containsKey(command)){
            this.mapChanger = MapChangerFactory.create(command);
            this.mapChangerPool.put(command, this.mapChanger);
        }
        else
            this.mapChanger = this.mapChangerPool.get(command);

        this.mapChanger.change(this.tokenizer.getTokens(), this.map);
    }

    public void generate(){
        this.defaultsSetter.set(this.map);
        this.codeGenerator.generate(this.map);
    }

    private void init(){
        this.mapChangerPool = new HashMap<String, MapChanger>();
        this.syntaxAnalyzerPool = new HashMap<String, SyntaxAnalyzer>();

        this.tokenizer = new Tokenizer();
        this.map = new HashMap();

        this.map.put("type", new HashMap());
        this.map.put("pname", "");
        this.map.put("package", "");
        this.map.put("clsname", "");
        this.map.put("extends", "");
        this.map.put("implements", new HashMap());
        this.map.put("imports", new HashMap());
        this.map.put("constructors", new LinkedHashMap());
        this.map.put("vars", new LinkedHashMap());
        this.map.put("fns", new LinkedHashMap());
        this.map.put("s", new LinkedHashMap());
        this.map.put("g", new LinkedHashMap());
    }
}
