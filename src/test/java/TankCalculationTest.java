import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TankCalculationTest {

    @Test
    public void btwTest() {
        float x = 0;
        float low = 0;
        float high = 0;
        assertFalse(TankCalculation.btw(x, low, high, true));
        assertFalse(TankCalculation.btw(x, low, high, false));

        x = 10;
        low = 15;
        high = 20;

        assertFalse(TankCalculation.btw(x, low, high, false));
        assertTrue(TankCalculation.btw(x, low, high, true));
    }

    @Test
    public void changeOrientationTest() {
        int orientation = 359;
        int angle = 10;
        assertEquals(9, TankCalculation.changeOrientation(orientation, angle));

        orientation = 0;
        angle = -20;
        assertEquals(340, TankCalculation.changeOrientation(orientation, angle));
    }

    @Test
    public void rangeMarkerTest() {
        int orientation = 360;
        assertEquals(0, TankCalculation.rangeMaker(orientation));
        orientation = 180;
        assertEquals(8, TankCalculation.rangeMaker(orientation));
    }
}
