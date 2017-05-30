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
        if (worldItems.indexOf(item) > -1) { //Make sure the element exists in the array
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

    public int getArraySize() {
        return worldItems.size();
    }
}
