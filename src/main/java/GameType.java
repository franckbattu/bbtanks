/**
 * This class represents the type of the Game
 */
public class GameType {

    /**
     * Constructor
     * Creation of two tanks for each player
     * FirstTank for Player 1
     * SecondTank for Player 2
     *
     * @param gameInfo the info of the game
     */
    GameType(GameInfo gameInfo) {
        Tank tank1 = new FirstTank(180, gameInfo.isTest ? 750 : 10, gameInfo.isTest ? 668 : 10, gameInfo);
        Tank tank2 = new SecondTank(0, 924, 668, gameInfo);
        gameInfo.allTanks.add(tank1);
        gameInfo.allTanks.add(tank2);
    }
}
