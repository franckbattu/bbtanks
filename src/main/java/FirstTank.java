import jgame.JGColor;
import utils.ObjectIdentifier;

/**
 * This class represents the Tank of the first player
 */
public class FirstTank extends Tank {

    /**
     * Constructor.
     *
     * @param tankOrientation the initial orientation of the tank
     * @param startX the initial position x
     * @param startY the initial position y
     * @param gameInfo the initial info of game
     */
    FirstTank(int tankOrientation, int startX, int startY, GameInfo gameInfo) {
        super(ObjectIdentifier.TANK_PLAYER_1.id, "tank1", tankOrientation, startX, startY, gameInfo);
        this.player = "Player 1";
        this.playerColor = JGColor.red;
        this.up = 'W';
        this.down = 'S';
        this.rotateLeft = 'A';
        this.rotateRight = 'D';
        this.fire = 'Z';
        this.secondaryFireKey = 'S';

        this.setLives(10);
        this.setPrimaryFire(0);
        this.setGotFlag(false);
    }
}
