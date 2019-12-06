import java.util.ArrayList;

public class DiagonalPath {

	ArrayList<int[]> queue;
	private int localStartX;
	private int localStartY;
	private int localFinishX;
	private int localFinishY;

	DiagonalPath(int startX, int startY, int finishX, int finishY) {
		this.localStartX = startX;
		this.localStartY = startY;
		this.localFinishX = finishX;
		this.localFinishY = finishY;
		this.queue = this.findPath();
	}

	private ArrayList<int[]> findPath() {
		ArrayList<int[]> queue = new ArrayList<>();
		double xDistance = localFinishX - localStartX;
		double yDistance = localFinishY - localStartY;
		double gradient = yDistance / xDistance;
		if (gradient < 1 && gradient >= 0 && yDistance > 0) {
			for (int i = localStartX; i <= localFinishX; i++) {
				queue.add(new int[]{i,localStartY + (int) (i*gradient)});
			}
		} else if (gradient >= 1 && yDistance > 0) {
			for (int i = localStartY; i <= localFinishY; i++) {
				queue.add(new int[]{localStartX + (int) (i/gradient), i});
			}
		} else if (gradient < 1 && gradient >= 0 && yDistance < 0) {
			for (int i = localStartX; i >= localFinishX; i--) {
				queue.add(new int[]{i, localFinishY + (int) (i*gradient)});
			}
		}  else  {
			for (int i = localStartY; i >= localFinishY; i--) {
				queue.add(new int[]{localFinishX + (int) (i*gradient), i});
			}
		}
		return queue;
	}
}

