/**
 * CampFire.java
 * The fire that is placed on the ground by the player
 */

public class CampFire extends GameObject {

    //These are up here for easy balance tweaking, although they can be made local
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

    public boolean addFuel(GameObject fuel) {
        //Takes in a GameObject as fuel and outputs a boolean of whether it accepts it or not
        //Theoretical support for multiple types of fuel (Not implemented)
        if (fuel instanceof Stick) {
            this.size += 0.1;
            //Make sure the size doesn't grow above 1
            this.size = this.size >= 1 ? this.size : 1;
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        //Update the fire's size every frame
        this.size -= 1.0/(30 * 60); //Fire depletes from full in 30 seconds at 60 FPS
        if (this.size <= 0) {
            //Fire deletes itself
            Game.world.removeItem(this);
        }
    }
}
