/**
 * This class represents a FrictionBox
 */
class FrictionBox extends PowerUp {

	private GameInfo localInfo;

	/**
	 * Constructor
	 * @param x the initial position x
	 * @param y the initial position y
	 * @param gameInfo the initial info of game
	 */
	public FrictionBox(int x, int y, GameInfo gameInfo) {
		super("Frictionless",x,y, "friction-box");
		localInfo = gameInfo;
    }

	/**
	 * Affects other tanks
	 * Frictions others tanks than the activator
	 * Remove the box
	 *
	 * @param activator the tank which takes the box
	 */
	void effect(Tank activator) {
		for (Tank tank : localInfo.allTanks) {
			if (tank != activator) {
				localInfo.activeEffects.add(new Frictionless(2000,tank, localInfo));
			}
       	}
        remove();
	}
}
