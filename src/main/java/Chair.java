import jgame.*;

/**
 * This class represents a Chair
 */
public class Chair extends Obstacle {

	private GameUtilities gameUtil;

	/**
	 * Constructor
	 * @param posX the initial position x
	 * @param posY the initial position y
	 * @param orientation the initial orientation
	 * @param gameInfo the info of the game
	 * @param solidity the initial solidity (Destructible or not)
	 */
	public Chair(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("chair", posX, posY, "chair" + orientation, solidity, gameInfo);
		this.gameUtil = new GameUtilities();
	}

	/**
	 * This method is used if an object touches a Chair.
	 * If the Chair is destroyed, a random number of object "boom" is produced with different directions
	 *
	 * @param obj the object that hits
	 */
	public void hit(JGObject obj) {
		super.hit(obj);
		if (super.getSolidity().isDestroyed()) {
			for (int i = 0; i < gameUtil.random(12,15,1); i++){
				new JGObject("boom",true,x,y,1,"tank-bit"+gameUtil.random(1,9,1),gameUtil.random(-10,10),gameUtil.random(-10,10),100);
			}
		}
	}
}
