import jgame.JGObject;

/**
 * This class represents a Projectile
 * A Projectile is a Bullet
 */
public class Projectile extends JGObject {

    public GameInfo localInfo;
    double xSpeed;
    double ySpeed;
    protected double targetX;
    protected double targetY;

    /**
     * Constructor.
     */
    public Projectile(int colid, String image, Tank tank, double minDistance, GameInfo gameInfo) {

        super("bullet", true, (tank.x) + (tank.tankCentreX) + (minDistance * (tank.tankGunXDistance) * -Math.sin(Math.toRadians(tank.orientation))),
                (tank.y) + (tank.tankCentreY) + (minDistance * (tank.tankGunYDistance) * -Math.cos(Math.toRadians(tank.orientation))),
                colid, image, 600
        );

        xSpeed = -Math.sin(Math.toRadians(tank.orientation));
        ySpeed = -Math.cos(Math.toRadians(tank.orientation));
        xspeed = xSpeed * gameInfo.bulletSpeedMultiplier;
        yspeed = ySpeed * gameInfo.bulletSpeedMultiplier;
        localInfo = gameInfo;
    }

    /**
     * Update the object. This method is called by moveObjects.
     */
    public void move() {

        // bounce off the borders of the screen.
        if (x > eng.pfWidth() - 8 && xspeed > 0) xspeed = -xspeed;
        if (x < 0 && xspeed < 0) xspeed = -xspeed;
        if (y > eng.pfHeight() - 8 && yspeed > 0) yspeed = -yspeed;
        if (y < 0 && yspeed < 0) yspeed = -yspeed;
        // xspeed and yspeed are added to x and y automatically at the end
        // of the move() method.
    }// bounce off the borders of the screen.

    protected void setSpeed() {
        if (x < targetX && xspeed < 0) xspeed = -xspeed;
        if (x > targetX && xspeed > 0) xspeed = -xspeed;
        if (y < targetY && yspeed < 0) yspeed = -yspeed;
        if (y > targetY && yspeed > 0) yspeed = -yspeed;
    }
}
