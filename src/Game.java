import javax.swing.*;
import java.awt.*;

public class Game {

    //Framerate-related variables
    private static final int MAX_FRAMES = 60;
    private static long lastFrame = 0;

    //Important game thingies there are only one of
    public static Player player;
    public static GameWorld world;
    public static ImageLoader images;

    public static void main(String[] args) {

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

            //Get the mouse position
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b  = a.getLocation();
            int mouseX = (int)(b.getX());
            int mouseY = (int)(b.getY());

            //Make a new rectangle representing the mouse
            Rectangle mouseRectangle = new Rectangle(mouseX - 2, mouseY - 2, 4, 4);

            //TODO: Iterate through all the world objects and check if the mouse is intersecting

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
