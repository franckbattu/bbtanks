import jgame.JGRectangle;

import java.lang.Math;

/**
 * This class contains utilities functions for the game
 */
public class GameUtilities {

	/**
	 * Test if two rectangles are equals
	 * @param rect1 the first rectangle
	 * @param rect2 the second rectangle
	 * @return true if the two rectangles are equals, else false
	 */
	public boolean rectEquals(JGRectangle rect1, JGRectangle rect2) {
		return
			(rect1.x == rect2.x) &&
			(rect1.y == rect2.y) &&
			(rect1.width == rect2.width) &&
			(rect1.height == rect2.height);
	}

	/**
	 * Gives the full speed
	 * @param xSpeed the initial x speed
	 * @param ySpeed the initial y speed
	 * @return the full speed
	 */
	public double fullSpeed(double xSpeed, double ySpeed) {
		double sqrt = Math.sqrt((xSpeed * xSpeed) + (ySpeed * ySpeed));

		if (xSpeed>=0 && ySpeed>=0)
			return sqrt;
		else
			return -sqrt;
	}

	/**
	 * Gives the normal speed
	 * @param xSpeed the initial x speed
	 * @param fullSpeed the full speed
	 * @return the normal speed
	 */
	public double normalSpeed(double xSpeed, double fullSpeed) {
		if (fullSpeed>=0)
			return Math.sqrt((fullSpeed*fullSpeed)-(xSpeed*xSpeed));
		else
			return -Math.sqrt((fullSpeed*fullSpeed)-(xSpeed*xSpeed));
	}

	/**
	 * Creates a random integer between start and finish on the increment specified
	 * @param start the start number
	 * @param finish the end number
	 * @param inc the increment
	 * @return a random number between start and finish
	 */
	public int random(int start, int finish, int inc) {
		int range = ((finish - start)/inc)+1;
		int[] selection = new int[range];
		for (int i = 0; i < range; i++) {
			selection[i] = start;
			start += inc;
		}
		int randomIndex = (int)(range * Math.random());
		return selection[randomIndex];
	}

	/**
	 * Creates a random number between start and finish on the increment of 1
	 * @param start the start number
	 * @param finish the end number
	 * @return a random number between start and finish
	 */
	public int random(int start, int finish) {
		return this.random(start, finish, 1);
	}
}
