import jgame.JGRectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameUtilitiesTest {

    @Test
    public void rectEqualsTest() {
        int x = 10;
        int y = 10;
        int width = 20;
        int height = 20;
        JGRectangle rectangle1 = new JGRectangle(x, y, width, height);
        JGRectangle rectangle2 = new JGRectangle(x, y, width, height);

        GameUtilities gameUtilities = new GameUtilities();
        assertTrue(gameUtilities.rectEquals(rectangle1, rectangle2));
    }
}
