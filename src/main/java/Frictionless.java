/**
 * This class represents FrictionLess
 */
class Frictionless extends Effect {

	private boolean isAffected;
	private GameInfo localInfo;

	Frictionless(int duration, Tank effected, GameInfo gameInfo) {
    	super(duration,effected);
    	isAffected = true;
    	localInfo = gameInfo;
    }

    public void start() {
    	effected.changeFriction(0);
    }

    public void finish() {
    	if (!isAffected) {
        	effected.changeFriction(localInfo.initialFriction);
            isAffected = false;
        }
    }
}
