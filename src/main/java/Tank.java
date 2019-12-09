import jgame.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This abstract class represents a Tank
 */
public abstract class Tank extends JGObject {

    public int orientation;
    char up;
    char down;
    char rotateLeft;
    char rotateRight;
    char fire;
    char secondaryFireKey;
    String secondaryWeapon = "";
    String player;
    JGColor playerColor;
    GameInfo localInfo;
    private int lives;
    private int primaryFire;
    private int secondaryFire;
    private boolean gotFlag;
    int startX;
    int startY;

    private int id;

    // Fire Rate
    int timer = 1;
    int bulletFireRate = 10;
    int secondaryFireRate = 50;
    double tanksFriction;
    int tankWidth = 7;
    int tankLength = 15;
    public int tankCentreX = 21;
    public int tankCentreY = 24;

    // Bullet Starting Distances
    public int tankGunXDistance = 35; //21;
    public int tankGunYDistance = 35;//48;

    boolean keyDown;
    int hit;
    boolean itHit;
    GameUtilities gameUtil = new GameUtilities();

    public void changeFriction(double newFriction) {
        tanksFriction = newFriction;
    }

    /**
     * Constructor.
     */
    Tank(int collisionID, String name, int tankOrientation, int startX, int startY, GameInfo gameInfo) {
        super(name,true, startX, startY, collisionID, "tank_0000");
        this.startX = startX;
        this.startY = startY;
        this.id = collisionID;
        this.localInfo = gameInfo;
        this.orientation = tankOrientation;
        this.tanksFriction = localInfo.initialFriction;
        this.setBBox(7, 9, 27, 30);
    }

    private void fireRate() {
        timer++;
        if (timer % bulletFireRate == 0) {
            if (this.primaryFire > 0)
                this.primaryFire--;
        }
        if (timer % secondaryFireRate == 0) {
            if (this.secondaryFire > 0)
                this.secondaryFire--;
        }
    }

    private void collisionDetection() {
        for (JGRectangle collisionCheck : localInfo.objects) {
            if (collisionCheck.intersects(getBBox())) {
                if (y + tankCentreY <= collisionCheck.y + 8) {
                    hit = 0;
                } else if (y + tankCentreY >= collisionCheck.y + collisionCheck.height - 8) {
                    hit = 2;
                } else if (x + tankCentreX <= collisionCheck.x + 8) {
                    hit = 3;
                } else if (x + tankCentreX >= collisionCheck.x + collisionCheck.width - 8) {
                    hit = 1;
                }
                itHit = true;
            }
        }
    }

    /**
     * This methods is used when the player pressed the up key
     */
    private void upKeyEvent() {
        if (TankCalculation.faceDirection(true, this)) {
            yspeed = -localInfo.tankSpeed * Math.cos(Math.toRadians(orientation));
            xspeed = -localInfo.tankSpeed * Math.sin(Math.toRadians(orientation));
            keyDown = true;
        }
    }

    /**
     * This methods is used when the player pressed the down key
     */
    private void downKeyEvent() {
        if (TankCalculation.faceDirection(false, this)) {
            yspeed = localInfo.tankSpeed * Math.cos(Math.toRadians(orientation));
            xspeed = localInfo.tankSpeed * Math.sin(Math.toRadians(orientation));
            keyDown = true;
        }
    }

    /**
     * This methods is used to move the tank
     */
    private void moveInputs() {
        if (eng.getKey(up)) upKeyEvent();
        if (eng.getKey(down)) downKeyEvent();
        if (eng.getKey(rotateLeft)) {
            orientation = TankCalculation.changeOrientation(orientation, 5);
        }
        if (eng.getKey(rotateRight)) {
            orientation = TankCalculation.changeOrientation(orientation, -5);
        }
    }

    private void fireInputs() {
        if (eng.getKey(fire)) {
            if (this.primaryFire == 0) {
                new Bullet(this, localInfo);
                this.primaryFire++;
            }
        }
        if (eng.getKey(secondaryFireKey)) {
            if (this.secondaryFire == 0) {
                if (secondaryWeapon.equals("HomingMissile")) {
                    new HomingMissile(this, localInfo);
                } else if (secondaryWeapon.equals("Grenade")) {
                    new Grenade(this, localInfo);
                } else if (secondaryWeapon.equals("Pulse")) {
                    new Pulse(this, localInfo);
                }
                this.secondaryFire++;
            }
        }
    }

    public void move() {
        this.fireRate();
        keyDown = false; // records whether a key has been pressed this frame
        hit = 0; //Detects direction from hit
        itHit = false;

        this.collisionDetection();

        // bounce off the borders of the screen.
        if (tanksFriction <= 0) {
            this.changeSpeedBorderX(posX -> posX > eng.pfWidth());
            this.changeSpeedBorderX(posX -> posX < 0);
            this.changeSpeedBorderY(posY -> posY > eng.pfHeight());
            this.changeSpeedBorderY(posY -> posY < 0);
        }

        this.moveInputs();

        // slows the tank down when there are no keys pressed to a halt when it gets close to 0
        // this stops the movement from being jerky

        if (!keyDown) {
            this.slow();
        }

        fireInputs();
        this.changeGraphic(TankCalculation.rangeMaker(orientation));
    }

    private void changeSpeedBorderX(Predicate<Double> predicate) {
        if (predicate.test(this.x)) {
            this.xspeed = -this.xspeed;
        }
    }

    private void changeSpeedBorderY(Predicate<Double> predicate) {
        if (predicate.test(this.y)) {
            this.yspeed = -this.yspeed;
        }
    }

    private void slow() {
        if ((xspeed < tanksFriction && xspeed > -tanksFriction) || (yspeed < tanksFriction && yspeed > -tanksFriction)) {
            xspeed = 0;
            yspeed = 0;
        } else {
            xspeed += tanksFriction * -java.lang.Math.signum(xspeed);
            yspeed += tanksFriction * -java.lang.Math.signum(yspeed);
        }
    }

    // Removes the tank and bullet when it is hit
    public void hit(JGObject object) {
        object.remove();
        this.lives--;
        if (this.lives <= 0) {
            this.remove();
            for (int i = 0; i < gameUtil.random(50, 80, 1); i++) {
                new JGObject("boom", true, x, y, 1, "tank-bit" + gameUtil.random(1, 9, 1), gameUtil.random(-10, 10), gameUtil.random(-10, 10), 100);
            }
            eng.playAudio("explosion");
        }
    }

    public void paint() {
        eng.setColor(playerColor);
        eng.drawString(player, x + tankCentreX, y + 62, 0);
        eng.drawString("Lives:" + this.lives, x + tankCentreX, y + 74, 0);
        if (this.gotFlag)
            eng.drawString("Got Flag!", x + tankCentreX, y + 86, 0);
        eng.drawRect(x + tankCentreX + (tankGunXDistance * -Math.sin(Math.toRadians(orientation))),
                y + tankCentreY + (tankGunYDistance * -Math.cos(Math.toRadians(orientation))), 8, 8, true, true);
    }

    private void changeGraphic(int id) {
        List<String> graphics = Stream
                .of("tank_0000", "tank_0225", "tank_0450", "tank_0675", "tank_0900", "tank_1125",
                        "tank_1350", "tank_1575", "tank_1800", "tank_2025", "tank_2250", "tank_2475", "tank_2700", "tank_2925",
                        "tank_3150", "tank_3375")
                .collect(Collectors.toList());

        setGraphic(graphics.get(id));
    }

    public int getId() {
        return this.id;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int value) {
        this.lives = value;
    }

    public int getPrimaryFire() {
        return this.primaryFire;
    }

    public void setPrimaryFire(int value) {
        this.primaryFire = value;
    }

    public boolean getGotFlag() {
        return this.gotFlag;
    }

    public void setGotFlag(boolean value) {
        this.gotFlag = value;
    }
}
