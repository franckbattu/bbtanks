/**
 * This class represents the Muddle effect
 */
class Muddle extends Effect {

    char oriUp, oriDown, oriLeft, oriRight;

    /**
     * Constructor
     * @param duration the initial duration of the muddle effect
     * @param effected the tank effected
     */
    Muddle(int duration, Tank effected) {
        super(duration, effected);
        oriUp = effected.up;
        oriDown = effected.down;
        oriLeft = effected.rotateLeft;
        oriRight = effected.rotateRight;
    }

    /**
     * Start of the effet
     * Changes the UI controls of the tank
     */
	public void start() {
        effected.up = oriDown;
        effected.rotateLeft = oriRight;
        effected.down = oriUp;
        effected.rotateRight = oriLeft;
    }

    /**
     * End of the effect
     * The UI controls are the initial controls
     */
	public void finish() {
        effected.up = oriUp;
        effected.rotateLeft = oriLeft;
        effected.down = oriDown;
        effected.rotateRight = oriRight;
    }
}
