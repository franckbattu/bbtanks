public class Indestructible implements Solidity {

    @Override
    public boolean canBeDestroyed() {
        return false;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void hit() {
    }
}
