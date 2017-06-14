/**
 * PlaneCrash.java
 * Oh no! Steve Ballmer's plane crashed!
 */

public class PlaneCrash extends CampFire{

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
}
