/**
 * This class represents a Pencil
 */
public class Pencil extends Obstacle {

	/**
	 * Constructor
	 * Call the super contructor of Obstacle
	 * @param posX the initial position x
	 * @param posY the initial position y
	 * @param orientation the initial oriention
	 * @param gameInfo the info of the game
	 * @param solidity the solidity of the pencil
	 */
	public Pencil(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("pencil", posX, posY, "pencil" + orientation, solidity, gameInfo);
	}
}
