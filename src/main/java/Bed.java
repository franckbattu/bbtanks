import jgame.JGObject;

/**
 * This class represents a Bed
 */
public class Bed extends Obstacle {

	/**
	 * Constructor
	 * @param posX the initial position x
	 * @param posY the initial position y
	 * @param orientation the initial orientation
	 * @param gameInfo the initial info of game
	 * @param solidity the initial solidity
	 */
	public Bed(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("bed", posX, posY, "bed" + orientation, solidity, gameInfo);
	}

	/**
	 * This method is used if an object touches a Used.
	 * An object "boom" is generated
	 *
	 * @param obj the object that hits
	 */
	public void hit(JGObject obj) {
		obj.remove();
		new JGObject("boom",true,obj.x,obj.y,1,"tank-bit"+super.getGameUtilities().random(1,9,1),super.getGameUtilities().random(-10,10),super.getGameUtilities().random(-10,10),100);
	}
}
