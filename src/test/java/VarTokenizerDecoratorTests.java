import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.lambda_n2t.codegenerator.AbstractTokenizer;
import org.lambda_n2t.codegenerator.TokenizerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by Geomart Brenth Abong on 7/13/2016.
 */

public class VarTokenizerDecoratorTests {
    private AbstractTokenizer tokenizer;

    @Before
    public void initialization(){
        this.tokenizer = TokenizerFactory.create("vars");
    }
}
