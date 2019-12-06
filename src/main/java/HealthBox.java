/**
 * This class represents a HealthBox
 * A HealthBox gives to the player an extra life
 */
class HealthBox extends PowerUp {

    private GameInfo localInfo;

    /**
     * Constructor
     * @param x the initial position x
     * @param y the initial position y
     * @param gameInfo the initial info of the game
     */
        HealthBox(int x, int y, GameInfo gameInfo) {
                super("Health", x, y, "health-box");
                this.localInfo = gameInfo;
        }

    /**
     * What happens when the tank runs ower the power-up
     * @param activator the tank that runs over the power-up
     */
    void effect(Tank activator) {
            activator.setLives(activator.getLives() + 1);
            this.remove();
            super.effect(activator);
        }

}
