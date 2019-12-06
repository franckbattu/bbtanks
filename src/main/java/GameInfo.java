import java.util.ArrayList;
import java.util.List;

import jgame.JGColor;
import jgame.JGRectangle;
import jgame.platform.JGEngine;
import lombok.Data;

/**
 * This class represents all the information of a game
 */
@Data
public class GameInfo {

	boolean isTest = false;
	boolean guiDone = false;

    public int objectIndex = -1;
	public List<JGRectangle> objects = new ArrayList<>();
	ArrayList<Obstacle> map1 = new ArrayList<>();

	int pfWidth;
	int pfHeight;

	public double bulletSpeedMultiplier = 3.5;
	int maxBullets = 6;

	ArrayList<Tank> allTanks = new ArrayList<>();
    ArrayList<Effect> activeEffects = new ArrayList<>();

    double initialFriction = 0.5;
    double friction = initialFriction;
	int tankSpeed = 3;
	int reloadTime = 10;

	private String winner;
	private String loser;

    JGColor winnerColor;
    private boolean gameLost;

	/**
	 * Constructor
	 * @param eng the engine
	 */
	GameInfo(JGEngine eng) {
    	pfWidth = eng.pfWidth();
    	pfHeight = eng.pfHeight();
    };

	public List<JGRectangle> getObjects() {
		return objects;
	}
}
