import javax.swing.*;
import java.io.File;

public class Game {

    //Framerate-related variables
    private static final int MAX_FRAMES = 60;
    private static long lastFrame = 0;

    //Important game thingies there are only one of
    public static Player player;
    public static GameWorld world;
    public static ImageLoader images;
    public static GameObject actionableObject; //Object that the mouse is over
    public static boolean mouseClick; //Whether there's a new mouse click to process


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

        //Load the images
        images = new ImageLoader();

        //Make a new instance of Player
        player = new Player();

        //Make the new world
        world = new GameWorld();

        //TODO: Progress bar
        while(!(images.isReady())) {} //IntelliJ says this is confusing but whatever

        JFrame gameWindow = new GameWindow();
        //Main game loop starts here
        do {
            //Process everything here

            //This happens last
            if (System.currentTimeMillis() - lastFrame < 1000 / MAX_FRAMES) {
                try {
                    Thread.sleep(1000 / MAX_FRAMES - (System.currentTimeMillis() - lastFrame));
                } catch (Exception e) {
                    System.out.println("Thread could not sleep. Game may become unstable.");
                    System.exit(1);
                }
            }

            if (Game.mouseClick && (Game.actionableObject != null)) {
                //Pick up random items lying on the ground
                if (Game.actionableObject instanceof InventoryObject) {
                    player.cursorItem = Game.actionableObject;
                    Game.world.removeItem(Game.actionableObject);
                    Game.actionableObject = null;
                }

                //We no longer need to perform the click action
                Game.mouseClick = false;
            }// else if (Game.mouseClick)

            //Move the player
            player.xPos += (player.getXMovement() * player.getSpeed());
            player.yPos += (player.getYMovement() * player.getSpeed());

            //This happens last to ensure we're measuring the time taken by *everything*
            gameWindow.repaint();
            lastFrame = System.currentTimeMillis();
            //NO MORE CODE BEYOND THIS POINT
            //ALL CODE WILL BE DESTROYED ON SIGHT
        } while (true); // this lil guy gets an exception though because he appeases the compiler for now
    }

}
