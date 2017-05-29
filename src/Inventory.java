/**
 * Inventory.java
 * Inventory holder for Survive
 *
 */

public class Inventory {
    private InventoryObject[] items;

    Inventory() {
        this.items = new InventoryObject[10];
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

}
