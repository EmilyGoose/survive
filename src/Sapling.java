/**
 * Sapling.java
 * This is where the player gets twigs
 * Misha larionov
 */

public class Sapling extends GameObject implements ResourceGenerator{
    private boolean full; //Tracks whether the sapling has sticks to gather
    private long lastPicked;
    //These two can safely be declared locally but are placed here for readability
    private final int maxRegrowthTime = 8; //Max regrowth time in minutes
    private final double regrowthChance = 0.001 / Game.MAX_FRAMES; //Chance each frame sticks will replenish

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
            //Remember when it was last picked
            lastPicked = Game.getAge();
            //Set the image to reflect the emptiness inside (me irl)
            this.setImageName("sapling_dead");
            return new Stick();
        } else {
            return null;
        }
    }

    public void update() {
        if (!this.full) {
            //Determine if we're going to spawn a new stick this tick
            if (Game.getAge() >= lastPicked + maxRegrowthTime * 60 * Game.MAX_FRAMES || Math.random() <= regrowthChance) {
                this.full = true;
                this.setImageName("sapling");
            }
        }
    }
}
