import java.util.ArrayList;

import jgame.JGRectangle;
import jgame.platform.JGEngine;

public class MapGenerator {
	JGEngine localEng;
	GameInfo localInfo;
	GameUtilities gameUtil = new GameUtilities();
	int mapWidth = 64;
	int mapHeight = 48;
	int x;
	int y;
	boolean[][] objectMap = new boolean[mapWidth][mapHeight];
	ArrayList<int[]> usableMap = new ArrayList<int[]>();
	int maxBeds = 1;
	int maxPencils = 15;
	int maxChairs = 2;

	MapGenerator(GameInfo gameInfo, JGEngine eng, boolean isTestMap) {
		localEng = eng;
		localInfo = gameInfo;
		objectMap = initialiseObjectMap(objectMap);
		if(isTestMap)
		{
			placeObject(new Bed(700, 100, 'V', gameInfo, new Indestructible()), false);
			placeObject(new Chair(500, 200, 'V', gameInfo, new Destructible(15)), false);
			placeObject(new Pencil(450, 170, 'V', gameInfo, new Destructible(5)), false);
			placeObject(new Rug(450, 400, 'H', gameInfo, new Destructible(5)), false);
		}
		else
		{
			placeObject(new Rug(0, 0, 'V', gameInfo, new Destructible(5)), true);
			char orientation;
			for (int i =  1; i <= eng.random(1, maxBeds); i++) {
				placeObject(new Bed(500, 0, getRandomOrientation(), gameInfo, new Indestructible()), true);
			}
			for (int i =  1; i <= eng.random(1, maxChairs); i++) {
				placeObject(new Chair(0, 0, getRandomOrientation(), gameInfo, new Destructible(15)), true);
			}
			for (int i =  1; i <= eng.random(1, maxPencils); i++) {
				placeObject(new Pencil(0, 0, getRandomOrientation(), gameInfo, new Destructible(5)), true);
			}
		}

		//printGrid(objectMap); //Prints the board
	}

	char getRandomOrientation()
	{
		return ((Math.random() < 0.5) ? 'H' : 'V');
	}

	boolean[][] initialiseObjectMap(boolean[][] fullObjectMap) {
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				fullObjectMap[i][j] = true;
			}
		}
		fullObjectMap = clearBases(fullObjectMap);
		fullObjectMap = clearPath(fullObjectMap);
		return fullObjectMap;
	}
	void printGrid(boolean[][] printMap){
		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				if (printMap[i][j])
					System.out.print("1,");
				else
					System.out.print("0,");
			}
			System.out.println();
		}
	}

	void findUsableMapForShape(int width, int height){
		usableMap =  new ArrayList<int[]>();
		int searchGridX = mapWidth - width;
		int searchGridY = mapHeight - height;

		// Main grid search
		for (int i = 0; i < searchGridX; i++) {
			for (int j = 0; j < searchGridY; j++) {
				// Shape grid check
				boolean usable = true;
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++){
						if (objectMap[i+x][j+y] == false) {
							usable = false;
						}
					}
				}
				int[] coords = {i,j};
				if (usable) {
					usableMap.add(coords);
				}
			}
		}
	};

	boolean placeShapeRandomly(int width, int height){
		if (usableMap.size() != 0) {
			int randomPoint = (int) localEng.random(1,(double)usableMap.size()-1);
			x = usableMap.get(randomPoint)[0]*16;
			y = usableMap.get(randomPoint)[1]*16;

			for (int j = 0; j < height; j++){
				for (int i = 0; i < width; i++){
					objectMap[usableMap.get(randomPoint)[0]+i][usableMap.get(randomPoint)[1]+j] = false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	void printList(ArrayList<int[]> arrayList){
		for (int[] item : arrayList) {
			System.out.print("(");
			System.out.print(item[0]);
			System.out.print(", ");
			System.out.print(item[1]);
			System.out.print(")");
			System.out.println();
		}
	}

	boolean[][] clearBases(boolean[][] fullObjectMap) {
		int squareSide = 15;
		int gameWidth = 64;
		int gameHeight = 48;
		for (int i = 0; i < squareSide; i++) {
			for (int j = 0; j < squareSide; j++) {
				fullObjectMap[i][j] = false;
				fullObjectMap[(gameWidth-1)-i][(gameHeight-1)-j] = false;
			}
		}
		return fullObjectMap;
	}

	boolean[][] clearPath(boolean[][] fullObjectMap) {

		DiagonalPath diag = new DiagonalPath(0,0,mapWidth-1,mapHeight-1);
		for (int[] item : diag.queue) {
			for (int i = -2; i < 3; i++) {
				if (item[0]+i >= 0 && item[0]+i < mapWidth)
					fullObjectMap[item[0]+i][item[1]] = false;
				if (item[1]+i >= 0 && item[1]+i < mapHeight)
					fullObjectMap[item[0]][item[1]+i] = false;
			}
		}
		return fullObjectMap;
	}

	void placeObject(Obstacle obs, boolean randomLocation){
		int obsWidth = (obs.getBBox().width/16)+1;
		int obsHeight = (obs.getBBox().height/16)+1;
		findUsableMapForShape(obsWidth, obsHeight);

		for ( JGRectangle removing : localInfo.objects ) {
			localInfo.objectIndex++;
			if (gameUtil.rectEquals(obs.getBBox(), removing)) {
				break;
			}
		}
		if (localInfo.objects.size()!=0)
			localInfo.objects.remove(localInfo.objectIndex);
		localInfo.objectIndex = -1;

		if(randomLocation)
		{
			if (placeShapeRandomly(obsWidth, obsHeight)) {
				obs.x = x;
				obs.y = y;
				localInfo.objects.add(obs.getBBox());
				localInfo.map1.add(obs);
			} else {
				System.out.println("Cant place 2");
				localEng.removeObjects(obs.getName(),0,true);
			}
		}
		else
		{
			localInfo.objects.add(obs.getBBox());
			localInfo.map1.add(obs);
		}

	}
}
