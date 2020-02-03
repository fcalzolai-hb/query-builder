import org.junit.Test;

import com.babylonhealth.GameFactory;

/**
 * @author alex.collins
 */
public class GameFactoryTest {

    @Test
    public void testBuild() throws Exception {
        new GameFactory().createGame(getClass().getResourceAsStream("/example.field"));

    }
}
