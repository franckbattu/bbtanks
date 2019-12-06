public abstract class Effect {
    int framesLeft;
    Tank effected;

    /**
     * Constructor
     * @param duration the duration of the effect
     * @param obj the object affected
     */
    public Effect(int duration, Tank obj) {
        framesLeft = duration;
        this.effected = obj;
        this.start();
    }

    /**
     * What happens when the effect starts
     */
    public abstract void start();

    /**
     * What happens when the framesLeft equal zero
     */
    public abstract void finish();
}
