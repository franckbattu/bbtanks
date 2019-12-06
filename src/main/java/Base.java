import jgame.JGColor;
import jgame.JGObject;

/**
 * This class represents a Base.
 * Used in duel game
 */
class Base extends JGObject {

    private Tank localTank;
    private GameInfo localInfo;
    private String localColour;
    private int xOffset;
    private int yOffset;

    /**
     * Constructor.
     * A new Flag is produced for one Base.
     * @param x the initial position x
     * @param y the initial position y
     * @param gameInfo the initial info of game
     * @param tank the tank associated to the Base
     * @param colour the color of the base
     */
    Base(int x, int y, GameInfo gameInfo, Tank tank, String colour) {
        super("base", true, x, y, 8, colour + "base");
        new Flag(x + xOffset, y + yOffset, gameInfo, tank, colour);
        this.localInfo = gameInfo;
        this.localTank = tank;
        this.localColour = colour;
        this.xOffset = 20;
        this.yOffset = -10;
    }

    /**
     * This method is used if a tank touches the Base.
     * End the game and display the winner
     *
     * @param obj the object that hits
     */
    public void hit(JGObject obj) {
        if (!localInfo.isGameLost()) {
            if (obj.equals(localTank) && (localColour.equals("blue")) && localInfo.allTanks.get(1).getGotFlag()) {
                localInfo.setWinner("Player 2");
                localInfo.setLoser("Player 1");
                localInfo.winnerColor = JGColor.blue;
                localInfo.setGameLost(true);
                eng.setGameState("GameOver");
            } else if (obj.equals(localTank) && (localColour.equals("red")) && localInfo.allTanks.get(0).getGotFlag()) {
                localInfo.setWinner("Player 1");
                localInfo.setLoser("Player 2");
                localInfo.winnerColor = JGColor.red;
                localInfo.setGameLost(true);
                eng.setGameState("GameOver");
            }
        }
    }
}
