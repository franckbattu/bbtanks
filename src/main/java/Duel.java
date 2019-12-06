import jgame.platform.JGEngine;

/**
 * This class represents the game "Duel" (classic game)
 */
public class Duel extends GameType {

    /**
     * Constructor
     * @param gameInfo the info of the game
     * @param eng the engine
     */
    Duel(GameInfo gameInfo, JGEngine eng) {
        super(gameInfo);
        new MapGenerator(gameInfo, eng, gameInfo.isTest);
    }
}
