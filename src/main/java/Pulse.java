import jgame.JGColor;


//Class representing the bullet object

public class Pulse extends Projectile {

	double startWidth = 60;
	double startHeight = 60;

	/** Constructor. */
	public Pulse(Tank tank, GameInfo gameInfo) {

		super(	1,
				"paper-ball-small",
				tank,
				1,
				gameInfo
		);

	}
	public void move() {
		xspeed = 0;
		yspeed = 0;
		startWidth+=5;
		startHeight+=5;
	}
	public void paint(){
		eng.drawOval(x, y, startWidth, startHeight, false, true, 3, JGColor.green);
	}

}
