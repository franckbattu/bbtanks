
/**
 * This class represents a Bullet (Projectile)
 */
public class Bullet extends Projectile {


	/**
	 * Constructor
	 * @param tank the tank associated
	 * @param gameInfo the initial info of the game
	 */
	public Bullet (Tank tank, GameInfo gameInfo) {
		super(1,"paper-ball-small", tank,1,	gameInfo);
	}

}
