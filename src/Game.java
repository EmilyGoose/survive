import javax.swing.*;

public class Game {

    //Framerate-related variables
    private static final int MAX_FRAMES = 60;
    private static long lastFrame = 0;

    //Config values
    //TODO: Move into custom file
    private static final int PLAYER_SPEED = 5; //Pixels the player moves every frame

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
