/**
 * This class represents a WeaponBox.
 * A WeaponBox gives an extra life to the player
 */
class WeaponBox extends PowerUp {
    private GameInfo localInfo;
    private String localWeapon;

    /**
     * Constructor
     * @param x the initial position x
     * @param y the initial position y
     * @param gameInfo the initial info of the game
     * @param icon the initial icon
     * @param weapon the initial weapon
     */
    WeaponBox(int x, int y, GameInfo gameInfo, String icon, String weapon) {
        super(weapon, x, y, icon);
        localInfo = gameInfo;
        localWeapon = weapon;
    }

    /**
     * Effect of the box
     * Removes the box when used
     * @param activator the tank that activates the box
     */
    void effect(Tank activator) {
        activator.secondaryWeapon = localWeapon;
        this.remove();
        super.effect(activator);
    }
}
