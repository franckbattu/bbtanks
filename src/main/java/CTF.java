import jgame.platform.JGEngine;

/**
 * This class reprensents the game "Capture the Flag"
 */
public class CTF extends GameType {

    CTF(GameInfo gameInfo, JGEngine eng) {
        super(gameInfo);
        new MapGenerator(gameInfo, eng, gameInfo.isTest);
        new Base(50, 50, gameInfo, gameInfo.allTanks.get(0), "red");
        new Base(gameInfo.pfWidth - 100, gameInfo.pfHeight - 100, gameInfo, gameInfo.allTanks.get(1), "blue");
    }
}
