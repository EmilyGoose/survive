/**
 * Berry.java
 * Berry that comes from a bush
 */

public class Berry extends InventoryObject {

    Berry() {
        super();
        this.setImageName("berry");
    }

    Berry(int x, int y) {
        super(x, y);
        this.setImageName("berry");
    }
}
