/**
 * GameWorld.java
 * Stores all the objects in the world
 * Misha Larionov
 */

import java.util.ArrayList;

public class GameWorld {

    //All the objects in the world
    private static ArrayList<GameObject> worldItems;

    GameWorld() {
        worldItems = new ArrayList<GameObject>(0);

        //TODO: Remove this debug stuff
        worldItems.add(new Bush(2200, 2200));
        worldItems.add(new Bush(2650, 2400));
        worldItems.add(new Berry(2500, 2600));
    }

    public GameObject getItemAtIndex(int i) {
        if (i < worldItems.size() && i >= 0) {
            return worldItems.get(i);
        } else {
            return null; //Return null if the array index is out of bounds
            //tbh I could've wrapped the if block in a try/catch but I would rather do this
        }
    }

    public boolean addItem(GameObject item) {
        if (worldItems.indexOf(item) == -1) { //Make sure the element doesn't exist in the array
            worldItems.add(item);
            return true; //Return true to indicate that the item was added successfully
        } else {
            return false; //Return false to indicate the item could not be added
        }
    }

    public void removeItemAtIndex(int i) {
        if (i < worldItems.size() && i >= 0) {
            worldItems.remove(i);
        }
    }

    public boolean removeItem(GameObject o) {
        if (worldItems.indexOf(o) > -1) {
            this.removeItemAtIndex(worldItems.indexOf(o));
            return true;
        } else {
            return false;
        }
    }

    public int getArraySize() {
        return worldItems.size();
    }
}
