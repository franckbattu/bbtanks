public class TankCalculation {

    /**
     * This methods is used to check if a value is between two others values
     *
     * @param x the value to check
     * @param low the low bound of interval
     * @param high the high bound of interval
     * @param comp true if the value needs to be beetween low and high, false otherwise
     * @return true if the value is between two other values
     */
    public static boolean btw(float x, float low, float high, boolean comp) {
        if (comp) return (x > low) || (x < high);
        else return (x > low) && (x < high);
    }

    public static int rangeMaker(int orientation) {
        for (int i = 0; i <= 15; i++) {
            if ((orientation > (i * 22.5)) && (orientation <= ((i * 22.5) + 22.5)))
                return 15 - i;
        }
        return 0;
    }

    /**
     * This methods is used to keep the orientation between 0 and 360°.
     * Equivalent of a 360 modulo in other langage implementations
     *
     * @param orientation the initial orientation of a tank
     * @param angle the angle to add
     * @return the orientation between 0 and 360°
     */
    public static int changeOrientation(int orientation, int angle) {
        if ((orientation + angle) >= 360)
            return (orientation + angle) - 360;
        if ((orientation + angle) < 0)
            return (orientation + angle) + 360;
        return orientation + angle;
    }

    public static boolean faceDirection(boolean isUpEvent, Tank tank) {
        float height = tank.eng.pfHeight();
        float width = tank.eng.pfWidth();
        boolean flag = true;

        if (btw(tank.orientation, isUpEvent ? 90 : 270, isUpEvent ? 270 : 90, !isUpEvent) && ((tank.y + tank.tankCentreY > height - tank.tankCentreY) || (tank.itHit && tank.hit == 0)))
            flag = false;
        if (btw(tank.orientation, isUpEvent ? 0 : 180, isUpEvent ? 180 : 360, false) && ((tank.x < 0) || (tank.itHit && tank.hit == 1)))
            flag = false;
        if (btw(tank.orientation, isUpEvent ? 270 : 90, isUpEvent ? 90 : 270, isUpEvent) && ((tank.y < 0) || (tank.itHit && tank.hit == 2)))
            flag = false;
        if (btw(tank.orientation, isUpEvent ? 180 : 0, isUpEvent ? 360 : 180, false) && ((tank.x + tank.tankCentreX > width - tank.tankCentreX) || (tank.itHit && tank.hit == 3)))
            flag = false;

        return flag;
    }

}
