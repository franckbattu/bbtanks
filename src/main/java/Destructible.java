public class Destructible implements Solidity {

    private int health;

    public Destructible(int health) {
        this.health = health;
    }

    @Override
    public void hit() {
        if (this.health > 0) {
            this.health--;
        }
    }

    @Override
    public boolean canBeDestroyed() {
        return true;
    }

    @Override
    public boolean isDestroyed() {
        return health == 0;
    }

    public int getHealth() {
        return this.health;
    }
}
