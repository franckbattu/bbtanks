import utils.CheckCollisions;
import utils.Collision;
import jgame.*;
import jgame.platform.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.ObjectIdentifier.*;

@SuppressWarnings("serial")
public class Framework extends JGEngine {

    int powerUpCountDown = 200;
    CheckCollisions checkCollisions;

    public static void main(String[] args) {
        new Framework(StdGame.parseSizeArgs(args, 0));
    }

    public Framework() {
        initEngineApplet();
    }

    public Framework(JGPoint size) {
        initEngine(size.x, size.y);
        this.checkCollisions = new CheckCollisions();
    }

    public void initCanvas() {
        setCanvasSettings(64,48,16,16, null, new JGColor(0, 0, 0),null);
    }

    GameInfo gameInfo = new GameInfo(this);

    public void initGame() {
        setFrameRate(35,2);
        defineMedia("data.tbl");

        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
        }

        addGameState("GUI");
        addGameState("StartGame");
        addGameState("InGame");
        addGameState("GameOver");

        playAudio("channel1", "menu", true);
        gameInfo.pfWidth = pfWidth();
        gameInfo.pfHeight = pfHeight();
        new GUI(gameInfo, this);
        setGameState("StartGame");
    }

    public void doFrameStartGame() {
        if (getKey(' ')) {
            setGameState("InGame");
        }
    }

    public void paintFrameStartGame() {
        setColor(JGColor.black);
        drawString("Press spacebar to start!", viewWidth() / 2, viewHeight() / 2, 0);
    }

    /**
     * Initialization of the game
     * A new random audio is launched
     * If the game is in test, some boxes are created
     */
    public void startInGame() {
        new RandomAudio(this);
        if (gameInfo.isTest) {
            new CurseBox(900, 600, gameInfo);
            new HealthBox(850, 600, gameInfo);
            new FrictionBox(800, 600, gameInfo);
            new WeaponBox(750, 600, gameInfo, "fireball-box", "HomingMissile");
            new WeaponBox(700, 600, gameInfo, "grenade-box", "Grenade");
        }
    }

    /**
     * Spawn all the power ups on the map
     */
    private void spawnPowerUps() {
        if (!gameInfo.isGameLost()) {
            if (powerUpCountDown <= 0) {
                double newPowerUpNo = random(0, 100);
                if (newPowerUpNo > 75) {
                    new CurseBox((int) random(0, pfWidth()), (int) random(0, pfHeight()), gameInfo);
                } else if (newPowerUpNo > 33) {
                    new HealthBox((int) random(0, pfWidth()), (int) random(0, pfHeight()), gameInfo);
                } else if (newPowerUpNo > 0) {
                    new FrictionBox((int) random(0, pfWidth()), (int) random(0, pfHeight()), gameInfo);
                }
                if (newPowerUpNo > 0) {
                    new WeaponBox((int) random(0, pfWidth()), (int) random(0, pfHeight()), gameInfo, "fireball-box", "HomingMissile");
                }
                if (newPowerUpNo > 0) {
                    new WeaponBox((int) random(0, pfWidth()), (int) random(0, pfHeight()), gameInfo, "grenade-box", "Grenade");
                }
                powerUpCountDown += random(50, 1000);
            } else {
                powerUpCountDown--;
            }
        }
    }

    /**
     * This methods is executed at each frame
     */
    public void doFrameInGame() {
        if (!gameInfo.isTest) spawnPowerUps();
        this.win();
        for (Effect effect : gameInfo.activeEffects) {
            effect.framesLeft--;
            if (effect.framesLeft <= 0) {
                effect.finish();
            }
        }
        // moves all the objects that need to move
        moveObjects(null, 0);
        this.checkCollisions.getCollisions().forEach(collision -> checkCollision(collision.getFirst(), collision.getSecond()));
    }

    /**
     * Check if there is a winner
     * End the game and display some data
     */
    private void win() {
        if (!gameInfo.isGameLost()) {
            for (Tank tank : this.gameInfo.allTanks) {
                if (tank.getLives() <= 0) {

                    if (tank instanceof FirstTank) {
                        gameInfo.setWinner("Player 2");
                        gameInfo.setLoser("Player 1");
                        gameInfo.winnerColor = JGColor.blue;
                    }

                    if (tank instanceof SecondTank) {
                        gameInfo.setWinner("Player 1");
                        gameInfo.setLoser("Player 2");
                        gameInfo.winnerColor = JGColor.red;
                    }
//
                    gameInfo.setGameLost(true);
                    setGameState("GameOver");
                }
            }
        }
    }


    /**
     * Any graphics drawing beside objects or tiles should be done here.
     * Usually, only status / HUD information needs to be drawn here.
     */
    public void paintFrame() {
    }

    public void startGameOver() {
    }

    public void doFrameGameOver() {
        doFrameInGame();
    }

    /**
     * Creates a panel to show the winner and the looser
     */
    public void paintFrameGameOver() {
        setColor(gameInfo.winnerColor);
        setFont(new JGFont(null, -1, 54));
        drawString("Game 0ver", viewWidth() / 2, viewHeight() / 2 - 90, 0);
        setFont(new JGFont(null, -1, 24));
        drawString("All " + gameInfo.getLoser() + "'s base are belong to " + gameInfo.getWinner() + ".", viewWidth() / 2, viewHeight() / 2, 0);
        drawString("Press space-bar to continue.", viewWidth() / 2, viewHeight() / 2 + 52, 0);
        if (getKey(' ')) {
            gameInfo = new GameInfo(this);
            removeObjects(null, 0);
            new GUI(gameInfo, this);
            setGameState("StartGame");
        }
    }
}
