import jgame.JGObject;

/**
 * This abstract class represents a power-up
 */
public abstract class PowerUp extends JGObject {

    private String powerUpName;

    /**
     * Constructor
     * @param name the name of the power-up
     * @param xLoc the initial position x
     * @param yLoc the initial position y
     * @param image the string image
     */
    PowerUp(String name, int xLoc, int yLoc, String image) {
        super(name, true, xLoc, yLoc, 16, image);
        powerUpName = name;
    }

    /**
     * What happens when a tank hits over the power-up.
     * @param obj the object which hits the power-up
     */
    public void hit(JGObject obj) {
        effect((Tank) obj);
    }

    /**
     * What happens when a tank runs over the power-up.
     * @param activator the tank that runs over the power-up
     */
    void effect(Tank activator) {
        this.remove();
    }

    /**
     * Paint of the power-up
     */
    public void paint() {
        eng.drawString(powerUpName, x + 6, y - 15, 0);
    }

}
