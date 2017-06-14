/**
 * PlaneCrash.java
 * Oh no! Steve Ballmer's plane crashed!
 */

public class PlaneCrash extends CampFire{

    private double size = 1;

    PlaneCrash() {
        super(2500, 2500);
        this.setImageName("crashedplane");
    }

    PlaneCrash(int x, int y) {
        super(x, y);
        this.setImageName("crashedplane");
    }

    public boolean addFuel(GameObject fuel) {
        //The plane fire burns until it burns out and then no more
        return false;
    }

    public void update() {
        //Update the fire's size every frame
        this.size -= 1.0/(30 * 60); //Fire depletes from full in 30 seconds at 60 FPS
        //The plane does not delete itself
    }
}
