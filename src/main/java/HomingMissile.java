import jgame.JGObject;

/**
 * This class represents a HomingMissible (the bullet)
 */
class HomingMissile extends Projectile {

	Tank localShooter;
	int targetCentreX;
	int targetCentreY;
	double localXSpeed = 0.75;
	double localYSpeed = 0.75;

	/**
	 * Constructor
	 * @param tank the local shooter tank
	 * @param gameInfo the info of the game
	 */
	HomingMissile(Tank tank, GameInfo gameInfo) {
		super(64,"fireball", tank,3, gameInfo);
		localInfo = gameInfo;
		localShooter = tank;
	}

	/**
	 * Returns all the data of the tanks (except the local tank shooter)
	 */
	private void getTankDatas()
	{
		for (Tank tank : localInfo.allTanks){
			if (tank != localShooter) {
				targetX = (int) (tank.x +tank.tankCentreX);
				targetCentreX = tank.tankCentreX/2;
				targetY = (int) (tank.y +tank.tankCentreY);
				targetCentreY = tank.tankCentreY/2;
			}
		}
	}

	/**
	 * Update the object.
	 * This method is called by moveObjects.
	 */
	public void move() {
		getTankDatas();

		xspeed = localXSpeed*localInfo.bulletSpeedMultiplier;
		yspeed = localYSpeed*localInfo.bulletSpeedMultiplier;
		setSpeed();

		if (x > (targetX-targetCentreX) && x < (targetX+targetCentreX))
			xspeed = 0;
		if (y > (targetY-targetCentreY) && y < (targetY+targetCentreY))
			yspeed = 0;

		super.move();
	}

	public void hit(JGObject obj) {
		obj.remove();
		remove();
	}
}
