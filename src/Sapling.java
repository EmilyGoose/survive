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
        this.setImageName("sapling");
    }

    Sapling(int x, int y) {
        super(x, y);
        this.full = true;
        this.setImageName("sapling");
    }

    public boolean hasResource() {
        return this.full;
    }

    public InventoryObject pick() {
        if (this.full) {
            this.full = false;
            //Set the new image
            this.setImageName("sapling_dead");
            return new Stick();
        } else {
            return null;
        }
    }

}
