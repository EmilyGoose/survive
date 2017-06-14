/**
 * GameWorld.java
 * Stores all the objects in the world
 * Misha Larionov
 */

import java.util.ArrayList;

public class GameWorld {

    //All the objects in the world
    private static ArrayList<GameObject> worldItems;

    //How many units the player freezes per frame. Can be loosely interpreted as temperature
    private int freezeRate;

    //Constants (Maybe move to file?)sd
    private final double BUSH_DENSITY = 0.1;
    private final double SAPLING_DENSITY = 0.05;
    private final double STONE_DENSITY = 0.03;

    GameWorld(int size) {
        worldItems = new ArrayList<GameObject>(0);
        this.freezeRate = 5;

        //The world generates from (0,0) to (5000, 5000)
        for (int i = 0; i < size/5; i++) {
            //Spawn a bush
            if (Math.random() <= BUSH_DENSITY) {
                worldItems.add(new Bush((int)(Math.random() * size), (int)(Math.random() * size)));
            }
            //Spawn a sapling
            if (Math.random() <= SAPLING_DENSITY) {
                worldItems.add(new Sapling((int)(Math.random() * size), (int)(Math.random() * size)));
            }
            //Spawn a stone
            if (Math.random() <= STONE_DENSITY) {
                worldItems.add(new Stone((int)(Math.random() * size), (int)(Math.random() * size)));
            }
        }
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

    public int getFreezeRate() {
        return this.freezeRate;
    }

    public void setFreezeRate(int rate) {
        this.freezeRate = rate;
    }
}
