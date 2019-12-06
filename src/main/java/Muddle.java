class Muddle extends Effect {
    char oriUp, oriDown, oriLeft, oriRight;

    Muddle(int duration, Tank effected) {
        super(duration, effected);
        oriUp = effected.up;
        oriDown = effected.down;
        oriLeft = effected.rotateLeft;
        oriRight = effected.rotateRight;
        System.out.println(oriUp);
    }

	public void start() {
        effected.up = oriDown;
        effected.rotateLeft = oriRight;
        effected.down = oriUp;
        effected.rotateRight = oriLeft;
    }

	public void finish() {
        effected.up = oriUp;
        effected.rotateLeft = oriLeft;
        effected.down = oriDown;
        effected.rotateRight = oriRight;
    }
}
