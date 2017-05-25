import javax.swing.*;

public class MainLoop {

    //Framerate-related variables
    private static final int MAX_FRAMES = 60;
    private static long lastFrame = 0;

    public static void main(String[] args) {

        //TODO: Launch start screen here

        //TODO: Initialize game frame
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
            //gameWindow.repaint();
            lastFrame = System.currentTimeMillis();
            System.out.println("Drawing something at " + System.currentTimeMillis());
        } while (true);
    }
}
