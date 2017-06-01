/**
 * GameWindow.java
 * Game window containing the whole game
 * Misha Larionov
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame {

    GameWindow() {
        super("Survive");

        //This block of code makes it fullscreen
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screenDevice = gEnv.getDefaultScreenDevice();
        this.setUndecorated(true);
        this.setResizable(false);
        this.setFocusable(false);
        screenDevice.setFullScreenWindow(this);

        DisplayMode dm = new DisplayMode(1920, 1080, 32, 60);
        screenDevice.setDisplayMode(dm);
        setSize(new Dimension(dm.getWidth(), dm.getHeight()));
        validate();

        getContentPane().add( new GamePanel());
        pack(); //makes the frame fit the contents

        /*JButton closeButton = new JButton("EXIT");
        closeButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    System.exit(0);
                }
            }
        );

        this.setLayout(new BorderLayout());

        this.add(closeButton, BorderLayout.NORTH);*/


        this.setVisible(true);
    }



    static class GamePanel extends JPanel  implements KeyListener {

        public GamePanel() {
            this.setPreferredSize(new Dimension(1920, 1080));
            this.setFocusable(true);
            this.requestFocusInWindow();
            this.requestFocus();
            this.addKeyListener(this);
            //TODO: Change focus so ALT-TAB isn't required first to move the player
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Declare a variable to be initialized later
            GameObject currentItem;

            int playerX = Game.player.xPos;
            int playerY = Game.player.yPos;

            //Variables for current check
            GameObject object;
            Image objectImage;
            int objectX;
            int objectWidth;
            int objectY;
            int objectHeight;


            //Iterate through the world's ArrayList
            for (int i = 0; i < Game.world.getArraySize(); i ++) {
                //The player is 50 * 100 and their position is their bottom center
                //Find the position relative to the player, who is always drawn at 935, 490 but is positioned at 960, 590
                object = Game.world.getItemAtIndex(i);
                objectImage = Game.images.getImage(object.getImageName());

            }
            g.drawRect(960, 590, 1, 1);
            g.drawImage(Game.images.getImage("player"), 960-25, 540-50, this);
            g.drawString(playerX + ", " + playerY, 100, 100);

        }

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'a' ){
                Game.player.addXMovemement(-1);
            } else if(e.getKeyChar() == 'd' ){
                Game.player.addXMovemement(1);
            } else if(e.getKeyChar() == 'w' ){
                Game.player.addYMovemement(-1);
            } else if(e.getKeyChar() == 's' ){
                Game.player.addYMovemement(1);
            }
        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == 'a' ){
                Game.player.addXMovemement(1);
            } else if(e.getKeyChar() == 'd' ){
                Game.player.addXMovemement(-1);
            } else if(e.getKeyChar() == 'w' ){
                Game.player.addYMovemement(1);
            } else if(e.getKeyChar() == 's' ){
                Game.player.addYMovemement(-1);
            }
        }
    }
}

