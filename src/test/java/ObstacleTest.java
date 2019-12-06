import jgame.JGColor;
import jgame.platform.JGEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObstacleTest extends JGEngine {

    @Test
    public void isIndestructibleTest() {
        initEngine(100, 100);
        Obstacle bed = new Bed(700, 100, 'V', null, new Indestructible());
        Assertions.assertFalse(bed.getSolidity().canBeDestroyed());
    }

    @Test
    public void isDestructibleTest() {
        JGEngine engine = new JGEngine() {
            @Override
            public void initCanvas() {
            }

            @Override
            public void initGame() {
            }
        };
        initEngine(100, 100);
        GameInfo gameInfo = new GameInfo(engine);
        Obstacle chair = new Pencil(450, 170, 'V', gameInfo, new Destructible(10));
        Assertions.assertTrue(chair.getSolidity().canBeDestroyed());
    }

    @Test
    public void hitObstacle() {
        JGEngine engine = new JGEngine() {
            @Override
            public void initCanvas() {
            }

            @Override
            public void initGame() {
            }
        };
        initEngine(100, 100);
        GameInfo gameInfo = new GameInfo(engine);
        Obstacle chair = new Pencil(450, 170, 'V', gameInfo, new Destructible(1));
        chair.getSolidity().hit();
        Assertions.assertTrue(chair.getSolidity().isDestroyed());
    }

    // Initialization of JGEngine
    @Override
    public void initCanvas() {
        setCanvasSettings(
                64,  // width of the canvas in tiles
                48,  // height of the canvas in tiles
                16,  // width of one tile
                16,  // height of one tile
                //    (note: total size = 20*16=320  x  15*16=240)
                null,// foreground colour -> use default colour white
                new JGColor(0, 0, 0),// background colour -> use default colour black
                null // standard font -> use default font
        );
    }

    @Override
    public void initGame() {
    }
}
