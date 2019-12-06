import jgame.JGObject;

/**
 * This class represents a Grenade
 */
public class Grenade extends Projectile {

	int timer;
	int spread;

	private GameUtilities gameUtil;

	/** Constructor. */
	public Grenade (Tank tank, GameInfo gameInfo) {
		super(1,"grenade",	tank,2,	gameInfo);
		this.gameUtil = new GameUtilities();
		this.timer = 90;
		this.spread = 20;
		// Grenade spread
		targetX = x + gameUtil.random(-spread, spread);
		targetY = y + gameUtil.random(-spread, spread);
	}

	/**
	 * When the grenade moves
	 * If the timer has ended, random objects "boom" are created
	 */
	public void move() {
		timer--;
		// Spread
		if (timer == 20) {
			xspeed = spread/10;
			yspeed = spread/10;
			setSpeed();
		}
		// Stop the grenade from moving
		if (timer == 10) {
			xspeed = 0;
			yspeed = 0;
		}
		// Explosion Code
		if (timer == 0) {
			remove();
			for (int i = 0; i < gameUtil.random(12,15,1); i++){
				new JGObject("boom",true,x,y,1,"tank-bit"+gameUtil.random(1,9,1),gameUtil.random(-10,10),gameUtil.random(-10,10),100);
			}
		}
	}

	public void hit(JGObject obj){
	}
}
