/**
 * FireStarter.java
 * The version of the Campfire that goes in the inventory before the fire is placed
 */

public class FireStarter extends InventoryObject{
    FireStarter() {
        super();
        this.setImageName("firestarter");
    }

    FireStarter(int x, int y) {
        super(x, y);
        this.setImageName("firestarter");
    }
}
