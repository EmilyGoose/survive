/**
 * Bush.java
 * It's a bush with berries on it, but sometimes no berries
 * Misha larionov
 */

public class Bush extends GameObject implements ResourceGenerator{
    //Important variables and such
    private boolean full; //Tracks whether the bush has berries to gather
    private long lastPicked;
    //These two can safely be declared locally but are placed here for readability
    private final int maxRegrowthTime = 5; //Max regrowth time in minutes
    private final double regrowthChance = 0.01 / Game.MAX_FRAMES; //Chance each frame berries will replenish

    //Constructors start here
    Bush() {
        super(2500, 2500);
        this.full = true;
        this.setImageName("bushberries");
    }

    Bush(int x, int y) {
        super(x, y);
        this.full = true;
        this.setImageName("bushberries");
    }

    public boolean hasResource() {
        return this.full;
    }

    public InventoryObject pick() {
        if (this.full) {
            this.full = false;
            //Remember when it was last picked
            lastPicked = Game.getAge();
            //Change the bush to display as empty
            this.setImageName("bush");
            return new Berry();
        } else {
            return null;
        }
    }

    public void update() {
        if (!this.full) {
            //Determine if we're going to spawn a new berry this tick
            if (Game.getAge() >= lastPicked + maxRegrowthTime * 60 * Game.MAX_FRAMES || Math.random() <= regrowthChance) {
                this.full = true;
                this.setImageName("bushberries");
            }
        }
    }
}
