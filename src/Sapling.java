/**
 * Sapling.java
 * This is where the player gets twigs
 * Misha larionov
 */

public class Sapling extends GameObject implements ResourceGenerator{
    private boolean full; //Tracks whether the sapling has sticks to gather

    //Constructors start here
    Sapling() {
        super(2500, 2500);
        this.full = true;
    }

    Sapling(int x, int y) {
        super(x, y);
        this.full = true;
    }

    public boolean hasResource() {
        return this.full;
    }

    public boolean pick() {
        if (this.full) {
            this.full = false;
            return true;
        } else {
            return false;
        }
    }

}
