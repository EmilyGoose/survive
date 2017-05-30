/**
 * GameWindow.java
 * Game window containing the whole game
 * Misha Larionov
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Declare a variable to be initialized later
            GameObject currentItem;

            //Iterate through the world's ArrayList
            for (int i = 0; i < MainLoop.world.getArraySize(); i ++) {
                //TODO: This
            }

            g.drawString(MainLoop.player.xPos + ", " + MainLoop.player.yPos, 100, 100);

        }

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            if(e.getKeyChar() == 'a' ){
                MainLoop.player.addXMovemement(-1);
            } else if(e.getKeyChar() == 'd' ){
                MainLoop.player.addXMovemement(1);
            } else if(e.getKeyChar() == 'w' ){
                MainLoop.player.addYMovemement(-1);
            } else if(e.getKeyChar() == 's' ){
                MainLoop.player.addYMovemement(1);
            }
        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == 'a' ){
                MainLoop.player.addXMovemement(1);
            } else if(e.getKeyChar() == 'd' ){
                MainLoop.player.addXMovemement(-1);
            } else if(e.getKeyChar() == 'w' ){
                MainLoop.player.addYMovemement(1);
            } else if(e.getKeyChar() == 's' ){
                MainLoop.player.addYMovemement(-1);
            }
        }
    }
}

