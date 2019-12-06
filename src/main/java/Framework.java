import utils.Collision;
import jgame.*;
import jgame.platform.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.ObjectIdentifier.*;

@SuppressWarnings("serial")
public class Framework extends JGEngine {

    public static void main(String[] args) {
        new Framework(StdGame.parseSizeArgs(args, 0));
    }

    public Framework() {
        initEngineApplet();
    }

    public Framework(JGPoint size) {
        initEngine(size.x, size.y);
    }

    public void initCanvas() {
        setCanvasSettings(64,48,16,16, null, new JGColor(0, 0, 0),null);
    }

    GameInfo gameInfo = new GameInfo(this);

    public void initGame() {
        setFrameRate(
                35,// 35 = frame rate, 35 frames per second
                2  //  2 = frame skip, skip at most 2 frames before displaying
                //      a frame again
        );

        defineMedia("data.tbl");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
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

    public void startInGame() {
        // Music initialization
        new RandomAudio(this);
        if (gameInfo.isTest) {
            new CurseBox(900, 600, gameInfo);
            new HealthBox(850, 600, gameInfo);
            new FrictionBox(800, 600, gameInfo);
            new WeaponBox(750, 600, gameInfo, "fireball-box", "HomingMissile");
            new WeaponBox(700, 600, gameInfo, "grenade-box", "Grenade");
        }
    }

    // Power-Ups

    // 200 is the number of frames before first power-up.
    int powerUpCountDown = 200;


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
            } else { // count down.
                powerUpCountDown--;
            }
        }
    }

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
        this.checkCollisions();
    }

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

    private List<Collision> collisions = Stream.of(
            new Collision(BED, TANK_PLAYER_1),
            new Collision(BED, TANK_PLAYER_2),
            new Collision(HOMING_MISSILE, TANK_PLAYER_1),
            new Collision(HOMING_MISSILE, TANK_PLAYER_2),
            new Collision(TANK_PLAYER_1, FLAG),
            new Collision(TANK_PLAYER_2, FLAG),
            new Collision(TANK_PLAYER_1, POWER_UP),
            new Collision(TANK_PLAYER_2, POWER_UP),
            new Collision(BULLET, OBSTACLE),
            new Collision(HOMING_MISSILE, OBSTACLE),
            new Collision(BED, HOMING_MISSILE))
            .collect(Collectors.toList());

    private void checkCollisions() {
        this.collisions.forEach(collision -> checkCollision(collision.getFirst(), collision.getSecond()));
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

    public void paintFrameGameOver() {
        setColor(gameInfo.winnerColor);
        setFont(new JGFont(null, -1, 54));
        drawString("Game 0ver", viewWidth() / 2, viewHeight() / 2 - 90, 0);
        setFont(new JGFont(null, -1, 24));
        drawString("All " + gameInfo.getLoser() + "'s base are belong to " + gameInfo.getWinner() + ".", viewWidth() / 2, viewHeight() / 2, 0);
        //	drawString(winner +" you're winner.",viewWidth()/2,viewHeight()/2+26,0);
        drawString("Press space-bar to continue.", viewWidth() / 2, viewHeight() / 2 + 52, 0);
        if (getKey(' ')) {
            gameInfo = new GameInfo(this);
            removeObjects(null, 0);
            new GUI(gameInfo, this);
            setGameState("StartGame");
        }
    }
}
