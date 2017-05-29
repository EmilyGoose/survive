/**
 * Bush.java
 * It's a bush with berries on it, but sometimes no berries
 * Misha larionov
 */

public class Bush extends GameObject implements ResourceGenerator{
    private boolean full; //Tracks whether the bush has berries to gather

    //Constructors start here
    Bush() {
        super(2500, 2500);
        this.full = true;
    }

    Bush(int x, int y) {
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
