import utils.ObjectIdentifier;
import jgame.JGObject;
import jgame.JGRectangle;

public class Obstacle extends JGObject {

    private Solidity solidity;
    private GameUtilities gameUtilities;
	private GameInfo localInfo;

    Obstacle(String name, int posX, int posY, String image, Solidity solidity, GameInfo gameInfo) {
        super(name, true, posX, posY, ObjectIdentifier.OBSTACLE.id, image);
        this.solidity = solidity;
        this.localInfo = gameInfo;
        this.gameUtilities = new GameUtilities();
        if (this.solidity.canBeDestroyed()) {
            this.localInfo.getObjects().add(getBBox());
        }
    }

    /**
     * This method is used when an Obstacle is hit by another JGObject
     * @param obj
     */
    public void hit(JGObject obj) {
        obj.remove();

        if (this.solidity.canBeDestroyed()) {
            this.solidity.hit();
            if (this.solidity.isDestroyed()) {
                this.remove();
                for (JGRectangle removing : localInfo.objects) {
                    localInfo.objectIndex++;
                    if (gameUtilities.rectEquals(this.getBBox(), removing)) {
                        break;
                    }
                }
                localInfo.objects.remove(localInfo.objectIndex);
                localInfo.objectIndex = -1;
            }
        }
    }

    public void paint() {
    }

    public Solidity getSolidity() {
        return solidity;
    }

    public GameUtilities getGameUtilities() {
        return gameUtilities;
    }

    public void setGameUtilities(GameUtilities gameUtilities) {
        this.gameUtilities = gameUtilities;
    }
}

