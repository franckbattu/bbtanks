import jgame.JGColor;
import jgame.impl.JGEngineInterface;
import utils.ObjectIdentifier;

public class SecondTank extends Tank {
    /**
     * Constructor.
     *
     * @param tankOrientation
     * @param startX
     * @param startY
     * @param gameInfo
     */
    SecondTank(int tankOrientation, int startX, int startY, GameInfo gameInfo) {
        super(ObjectIdentifier.TANK_PLAYER_2.id, "tank2", tankOrientation, startX, startY, gameInfo);
        this.player = "Player 2";
        this.playerColor = JGColor.blue;
        this.up = (char) JGEngineInterface.KeyUp;
        this.down = (char) JGEngineInterface.KeyDown;
        this.rotateLeft = (char) JGEngineInterface.KeyLeft;
        this.rotateRight = (char) JGEngineInterface.KeyRight;
        this.fire = 'M';
        this.secondaryFireKey = ',';

        this.setLives(10);
        this.setPrimaryFire(0);
        this.setGotFlag(false);
    }
}
