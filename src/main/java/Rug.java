/**
 * This class represents a Rug
 */
public class Rug extends Obstacle {

	/**
	 * Constructor
	 * Call the super contructor of Obstacle
	 * @param posX the initial position x
	 * @param posY the initial position y
	 * @param orientation the initial oriention
	 * @param gameInfo the info of the game
	 * @param solidity the solidity of the rug
	 */
	public Rug(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("rug", posX, posY, "rug" + orientation, solidity, gameInfo);
	}

}
