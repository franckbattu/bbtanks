import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Collision;
import static utils.ObjectIdentifier.*;

public class CollisionTest {

    @Test
    public void correctIdTest() {
        Collision collision = new Collision(TANK_PLAYER_1, TANK_PLAYER_2);
        Assertions.assertEquals(2, collision.getFirst());
        Assertions.assertEquals(4, collision.getSecond());
    }
}
