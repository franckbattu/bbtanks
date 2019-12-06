/**
 * This class represents a Rug
 */
public class Rug extends Obstacle {

	public Rug(int posX, int posY, char orientation, GameInfo gameInfo, Solidity solidity) {
		super("rug", posX, posY, "rug" + orientation, solidity, gameInfo);
	}

}
