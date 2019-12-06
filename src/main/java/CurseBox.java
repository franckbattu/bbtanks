/**
 * This class represents a CurseBox.
 * It removes a life from every other player
 */
class CurseBox extends PowerUp {

	private GameInfo localInfo;

    /**
     * Constructor
     * @param x the initial position x
     * @param y the initial position y
     * @param gameInfo the initial info of game
     */
    CurseBox(int x, int y, GameInfo gameInfo) {
    	super("Curse", x, y, "curse-box");
        this.localInfo = gameInfo;
    }

    /**
     * Affects other tanks
     * Removes a life for others tanks than the activator
     * Destroys the activator
     *
     * @param activator the tank which takes the box
     */
    void effect(Tank activator) {
    	for (Tank tank : localInfo.allTanks) {
    		if (tank != activator) {
    		    tank.setLives(tank.getLives() - 1);
            }
        }
        super.effect(activator);
    }
}
