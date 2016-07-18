import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.MapChanger.MapChanger;
import org.lambda_n2t.codegenerator.MapChanger.MapChangerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Geomart Brenth Abong on 7/15/2016.
 */
public class PNameMapChangerTests {
    private MapChanger mapChanger;
    private Map map;

    @Before
    public void instantiations(){
        this.mapChanger = MapChangerFactory.create("pname");
        this.map = new HashMap();

        this.map.put("pname", "");
    }

    @Test
    public void pnameShouldBeAdded(){
        String input = "Geomart Brenth Abong";

        List<Pair<String, String>> tokens = new ArrayList<Pair<String, String>>();
        tokens.add(new Pair<String, String>("identifier", input));
        mapChanger.change(tokens, this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("pname", "Geomart Brenth Abong");

        assertTrue(this.map.equals(expectedMap));
    }

    @Test
    public void pnameShouldBeChanged(){
        String input = "Geomart Brenth Abong";

        List<Pair<String, String>> tokens = new ArrayList<Pair<String, String>>();
        tokens.add(new Pair<String, String>("identifier", input));
        mapChanger.change(tokens, this.map);

        input = "New Name";

        tokens = new ArrayList<Pair<String, String>>();
        tokens.add(new Pair<String, String>("identifier", input));
        mapChanger.change(tokens, this.map);

        Map expectedMap = new HashMap();
        expectedMap.put("pname", "New Name");

        assertTrue(this.map.equals(expectedMap));
    }
}
