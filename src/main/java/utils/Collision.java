package utils;

/**
 * This class represents a Collision between two objects
 * The objects are represented with ObjectIdentifier
 */
public class Collision {

    private int first;
    private int second;

    public Collision(ObjectIdentifier first, ObjectIdentifier second) {
        this.first = first.id;
        this.second = second.id;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }
}
