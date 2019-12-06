import jgame.JGObject;

/**
 * This class represents a Flag
 */
class Flag extends JGObject {

    private GameInfo obsList;
    private Tank localTank;

    /**
     * Constructor
     */
    Flag(int x, int y, GameInfo gameInfo, Tank tank, String colour) {
        super("flag", true, x, y, 8, colour + "flag", -1);
        this.obsList = gameInfo;
        this.localTank = tank;
    }

    /**
     * This method is used when an object comes to touch the Flag
     */
    public void hit(JGObject obj) {
        if (!obj.equals(this.localTank)) {
            remove();
            ((Tank) obj).setGotFlag(true);
        }
    }
}
