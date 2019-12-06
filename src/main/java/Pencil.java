/**
 * This class represents a Pencil
 */
public class Pencil extends Obstacle {

	public Pencil(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("pencil", posX, posY, "pencil" + orientation, solidity, gameInfo);
	}
}
