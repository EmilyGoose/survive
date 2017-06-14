import java.awt.*;

/**
 * Inventory.java
 * Inventory holder for Survive
 *
 */

public class Inventory {
    private InventoryObject[] items;
    private Rectangle[] boxRects;
    private Rectangle mainRectangle;
    private Rectangle fireCraftRect;

    Inventory() {
        this.items = new InventoryObject[10];
        this.boxRects = new Rectangle[10];
        for (int box = 0; box < 10; box ++) {
            boxRects[box] = new Rectangle(1845, 25 + 55 * box, 50, 50);
        }
        //If the player is moused over the inventory but not a specific box, this will stop them from clicking behind it
        this.mainRectangle = new Rectangle(1840, 20, 60, 580);
        this.fireCraftRect = new Rectangle(1845, 575, 25, 25);
    }

    public InventoryObject getItemAtSlot(int s, boolean take) {
        InventoryObject item = this.items[s];
        if (take) { //Tracks whether the item is removed or kept in the slot
            this.items[s] = null;
        }
        return item;
    }

    public boolean putItemAtSlot(int s, InventoryObject item) {
        if (this.items[s] == null) {
            this.items[s] = item;
            return true; //Adding the object was a success
        } else {
            return false; //The slot is taken, doesn't work that way
        }
    }

    public boolean addItem(InventoryObject item) {
        //Returns false if the inventory is full
        //Find an empty space and drop the item there
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                this.items[i] = item;
                return true;
            }
        }

        return false;
    }

    public Rectangle getSlotRectangle(int i) {
        return boxRects[i];
    }

    public Rectangle getMainRectangle() {
        return this.mainRectangle;
    }

    public boolean canCraftFire() {
        int sticks = 0;
        int stones = 0;
        for (InventoryObject o : this.items) {
            if (o instanceof Stick) {
                sticks += 1;
            } else if (o instanceof Stone) {
                stones += 1;
            }
        }
        return (sticks >= 2 && stones >= 4);
    }

    public boolean canCraftAxe() {
        boolean stick = false;
        boolean stone = false;
        for (InventoryObject o : this.items) {
            if (o instanceof Stick) {
                stick = true;
            } else if (o instanceof Stone) {
                stone = false;
            }
        }
        return (stick && stone);
    }

    public Rectangle getFireCraftRect() {
        return fireCraftRect;
    }

    public void setFireCraftRect(Rectangle fireCraftRect) {
        this.fireCraftRect = fireCraftRect;
    }

    public void craftFire() {
        if (this.canCraftFire()) {
            //Remove 2 sticks and 4 rocks
            int sticksRemoved = 0;
            int stonesRemoved = 0;
            for (int i = 0; i < this.items.length; i++) {
                if (this.getItemAtSlot(i, false) instanceof Stick && sticksRemoved < 2) {
                    this.getItemAtSlot(i, true);
                    sticksRemoved += 1;
                } else if (this.getItemAtSlot(i, false) instanceof Stone && stonesRemoved < 4) {
                    this.getItemAtSlot(i, true);
                    stonesRemoved += 1;
                }
            }
            this.addItem(new FireStarter());
        }
    }
}
