import javax.swing.*;
import java.awt.*;

public class Game {

    //Framerate-related variables
    public static final int MAX_FRAMES = 60;
    private static long lastFrame = 0;

    //Important game thingies there are only one of
    public static Player player;
    public static GameWorld world;
    public static ImageLoader images;
    public static GameObject actionableObject; //Object that the mouse is over
    public static boolean mouseClick; //Whether there's a new mouse click to process
    private static long age = 0; //Number of frames the game has run for
    public static final int WORLD_SIZE = 5000; //Size the world first generates at

    public static void main(String[] args) {

        //Make sure the system meets minimum requirements
        System.out.println("Available processor cores: " + Runtime.getRuntime().availableProcessors());
        if (Runtime.getRuntime().availableProcessors() > 0) { //You could probably run with 0 cores if you overclock
            System.out.println("This computer meets minimum requirements");
        } else { //What are you using, a toaster?
            System.out.println("This computer is too weak. Try again after installing a processor.");
        }
        System.out.println();

        //TODO: Launch start screen here

        //All the constructors run in the same thread so we don't need to wait for them

        //Load the images
        System.out.println("Loading images...");
        images = new ImageLoader();
        System.out.println("Images loaded!");

        //Generate the new world
        System.out.println("Loading world...");
        world = new GameWorld(WORLD_SIZE);
        System.out.println("World loaded with " + world.getArraySize() + " objects.");

        //Make a new instance of Player
        System.out.println("Spawning player...");
        player = new Player();
        System.out.println("Player spawned!");

        System.out.println("Launching game window...");
        JFrame gameWindow = new GameWindow();
        //Main game loop starts here
        do {
            //Process everything here

            //Move the player
            player.xPos += (player.getXMovement() * player.getSpeed());
            player.yPos += (player.getYMovement() * player.getSpeed());

            if (Game.mouseClick && (Game.actionableObject != null)) {
                //Pick up random items lying on the ground
                if (Game.actionableObject instanceof InventoryObject && Game.player.cursorItem == null) {
                    //Transfer the item to the cursor
                    Game.player.cursorItem = (InventoryObject)Game.actionableObject;
                    //Clear the item from the world
                    Game.world.removeItem(Game.actionableObject);
                    Game.actionableObject = null;
                } else if (Game.actionableObject instanceof ResourceGenerator &&
                        ((ResourceGenerator)Game.actionableObject).hasResource() && //Make sure the bush/sapling/whatever is full
                        Game.player.cursorItem == null //Make sure the player isn't holding anything
                        ) {
                    //Pick the generated resource from the item (No checks needed, returns null if empty)
                    Game.player.cursorItem = ((ResourceGenerator)Game.actionableObject).pick();
                } else if (Game.actionableObject instanceof Player && Game.player.cursorItem instanceof Berry) {
                    //The player eats the berry and it is destroyed
                    Game.player.cursorItem = null;
                    Game.player.addFood(256); //No one questions powers of 2 when used as arbitrary numbers
                }

            } else if (Game.mouseClick) { //Runs if mouse is not over anything
                //Get the world position at the top left of the screen
                int worldX = Game.player.xPos - 935;
                int worldY = Game.player.yPos - 490;

                //Get the mouse position
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b  = a.getLocation();
                int mouseX = (int)(b.getX());
                int mouseY = (int)(b.getY());

                //Make a new rectangle representing the mouse
                Rectangle mouseRectangle = new Rectangle(mouseX - 2, mouseY - 2, 4, 4);

                //Check to see if it's touching the inventory
                if (mouseRectangle.intersects(Game.player.inventory.getMainRectangle())) {
                    int slot = -1;
                    for (int box = 0; box < 10; box++) {
                        if (mouseRectangle.intersects(Game.player.inventory.getSlotRectangle(box))) {
                            slot = box;
                            break; //We don't need to loop anymore, you can only mouse over one at a time
                        }
                    }
                    if (slot > -1) {
                        if (Game.player.cursorItem == null) {
                            Game.player.cursorItem = Game.player.inventory.getItemAtSlot(slot, true);
                        } else {
                            //Swap the cursor and inventory item (Works even if one or both are null)
                            InventoryObject item = Game.player.inventory.getItemAtSlot(slot, true);
                            Game.player.inventory.putItemAtSlot(slot, Game.player.cursorItem);
                            Game.player.cursorItem = item;
                        }
                    }
                } else if (Game.player.cursorItem != null) { //Runs if there's an item in the cursor
                    //Spawn the item at the mouse
                    Game.player.cursorItem.xPos = worldX + mouseX;
                    Game.player.cursorItem.yPos = worldY + mouseY + 12; //+12 makes it spawn directly under instead of above the mouse

                    //Add the item to the world
                    Game.world.addItem(Game.player.cursorItem);
                    //Clear the item from the cursor
                    Game.player.cursorItem = null;
                }
            }

            //We no longer need to handle the click action or care about what's under the mouse
            Game.mouseClick = false;
            Game.actionableObject = null;

            //Iterate and update items such as bushes and saplings
            for (int i = 0; i < Game.world.getArraySize(); i++) {
                GameObject object = Game.world.getItemAtIndex(i);
                if (object instanceof  ResourceGenerator) {
                    ((ResourceGenerator) Game.world.getItemAtIndex(i)).update();
                } else if (object instanceof CampFire) {
                    //Find the distance between the fire and the player
                    int xDiff = Game.player.xPos - object.xPos;
                    int yDiff = Game.player.yPos - object.yPos;
                    double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                    //Warm the player if they are near enough to the campfire
                    if (distance <= ((CampFire) object).getRange()) {
                        Game.player.addWarmth(10);
                    }
                }
            }

            //Apply status effects such as hunger and warmth here
            Game.player.removeHunger(1);
            Game.player.removeWarmth(Game.world.getFreezeRate());
            //Punish the player for not eating
            if (Game.player.getHunger() == 0) {
                Game.player.takeDamage(1);
            }
            //Punish the player for not staying warm
            if (Game.player.getWarmth() == 0) {
                Game.player.takeDamage(2);
            }

            age += 1;

            //This happens last to ensure we're measuring the time taken by *everything*
            gameWindow.repaint();
            lastFrame = System.currentTimeMillis();

            //This happens after everything's done
            if (System.currentTimeMillis() - lastFrame < 1000 / MAX_FRAMES) {
                try {
                    Thread.sleep(1000 / MAX_FRAMES - (System.currentTimeMillis() - lastFrame));
                } catch (Exception e) {
                    System.out.println("Thread could not sleep. Game may become unstable.");
                    System.exit(1);
                }
            }

            //NO MORE LOOP CODE BEYOND THIS POINT
            //ALL CODE WILL BE DESTROYED ON SIGHT
        } while (Game.player.getHealth() > 0);

        //TODO: Make a "you die" screen here or something
        System.exit(0);
    }

    public static long getAge() {
        return age;
    }

}
