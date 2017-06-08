/**
 * Stick.java
 * Stick that comes from a sapling
 * Misha Larionov
 */

public class Stick extends InventoryObject {

    Stick() {
        super();
        this.setImageName("stick");
    }

    Stick(int x, int y) {
        super(x, y);
        this.setImageName("stick");
    }
}
