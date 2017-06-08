import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Inventory.java
 * Inventory holder for Survive
 *
 */

public class Inventory {
    private InventoryObject[] items;
    private Rectangle[] boxRects;
    private Rectangle mainBox;
    private int selectedBox;

    Inventory() {
        this.items = new InventoryObject[10];
        this.boxRects = new Rectangle[10];
        for (int box = 0; box < 10; box ++) {
            boxRects[box] = new Rectangle(1845, 25 + 55 * box, 50, 50);
        }
        //If the player is moused over the inventory but not a specific box, this will stop them from clicking behind it
        this.mainBox = new Rectangle(1840, 20, 60, 555);
        this.selectedBox = -1;
    }

    public InventoryObject[] getItems() {
        return this.items;
    }

    public boolean addItem(InventoryObject item) {
        //Returns false if the inventory is full
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                this.items[i] = item;
                return true;
            }
        }

        return false;
    }

    public Rectangle getSlotRectangle(int i) {
        return boxRects[i];
    }

    public Rectangle getMainBox() {
        return this.mainBox;
    }

    public int getSelectedBox() {
        return this.selectedBox;
    }

    public void setSelectedBox(int b) {
        this.selectedBox = b;
    }

}
