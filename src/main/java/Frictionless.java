/**
 * This class represents FrictionLess
 */
class Frictionless extends Effect {

	private boolean isAffected;
	private GameInfo localInfo;

	/**
	 * Constructor.
	 * @param duration the initial duration of the friction
	 * @param effected the tank affected
	 * @param gameInfo the info of the game
	 */
	Frictionless(int duration, Tank effected, GameInfo gameInfo) {
    	super(duration, effected);
    	isAffected = true;
    	localInfo = gameInfo;
    }

	/**
	 * Start of the effet
	 * The friction of the tank is changed to 0
	 */
	public void start() {
    	effected.changeFriction(0);
    }

	/**
	 * End of the effect
	 * The effect is removed and the tank has its initial friction
	 */
	public void finish() {
    	if (!isAffected) {
        	effected.changeFriction(localInfo.initialFriction);
            isAffected = false;
        }
    }
}
