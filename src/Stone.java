/**
 * Stone.java
 * Stone, found randomly on the ground
 */

public class Stone extends InventoryObject {

    Stone() {
        super();
        this.setImageName("stone");
    }

    Stone(int x, int y) {
        super(x, y);
        this.setImageName("stone");
    }

}
