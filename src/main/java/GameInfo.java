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

	// object removal
    public int objectIndex = -1;
	public List<JGRectangle> objects = new ArrayList<>();
	ArrayList<Obstacle> map1 = new ArrayList<>();

	int pfWidth;
	int pfHeight;

	// Bullet Data
	public double bulletSpeedMultiplier = 3.5;
	int maxBullets = 6;

	ArrayList<Tank> allTanks = new ArrayList<>();
    ArrayList<Effect> activeEffects = new ArrayList<>();

    // Friction
    double initialFriction = 0.5;
    double friction = initialFriction;

    // Tank Speed
	int tankSpeed = 3;

	// Reloading
	//int[] bulletTimer = new int[5];
	int reloadTime = 10;

	// Winner
	private String winner;
	private String loser;

    JGColor winnerColor;
    private boolean gameLost;
    GameInfo(JGEngine eng) {
    	pfWidth = eng.pfWidth();
    	pfHeight = eng.pfHeight();
    };

	public List<JGRectangle> getObjects() {
		return objects;
	}
}
