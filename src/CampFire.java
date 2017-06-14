/**
 * CampFire.java
 * The fire that is placed on the ground by the player
 */

public class CampFire extends GameObject {

    private int baseRange = 100;
    private double size = 1;


    CampFire() {
        super(2500, 2500);
        this.setImageName("campfire");
    }

    CampFire(int x, int y) {
        super(x, y);
        this.setImageName("campfire");
    }

    public int getRange() {
        return (int)(this.size * baseRange);
    }

    public double getSize() {
        return this.size;
    }

    public void addFuel(GameObject fuel) {
        if (fuel instanceof Stick) {
            this.size += 0.1;
        }
    }

    public void update() {
        //Update the fire's size every frame
        this.size -= 1.0/(30 * 60); //Fire depletes from full in 30 seconds at 60 FPS todo
        if (this.size <= 0) {
            //Fire deletes itself
            Game.world.removeItem(this);
        }
    }
}
