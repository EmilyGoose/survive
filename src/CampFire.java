/**
 * CampFire.java
 * The fire that is placed on the ground by the player
 */

public class CampFire extends GameObject {

    private int range;

    CampFire() {
        super(2500, 2500);
        this.setImageName("campfire");
        this.range = 50;
    }

    CampFire(int x, int y) {
        super(x, y);
        this.setImageName("campfire");
        this.range = 50;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int newRange) {
        this.range = newRange;
    }
}
