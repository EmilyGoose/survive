/**
 * GameWindow.java
 * Game window containing the whole game
 * Misha Larionov
 */

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
//import java.net.URL;

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

        GamePanel gamePanel = new GamePanel();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();
        this.addKeyListener(gamePanel);
        this.addMouseListener(gamePanel);

        getContentPane().add(gamePanel);
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



    static class GamePanel extends JPanel  implements KeyListener, MouseListener {

        public GamePanel() {
            this.setPreferredSize(new Dimension(1920, 1080));

            //Font loading code for later use
//            try {
//                URL fontUrl = new URL("https://fonts.gstatic.com/s/productsans/v9/HYvgU2fE2nRJvZ5JFAumwZS3E-kSBmtLoNJPDtbj2Pk.ttf");
//                Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
//                font = font.deriveFont(Font.PLAIN,20);
//                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                ge.registerFont(font);
//            } catch (Exception e) {
//                System.out.println("Could not load font");
//            }

            //TODO: Change focus so ALT-TAB isn't required first to move the player
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Declare a variable to be initialized later
            GameObject currentItem;

            int playerX = Game.player.xPos;
            int playerY = Game.player.yPos;

            //Get the mouse position
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b  = a.getLocation();
            int mouseX = (int)(b.getX());
            int mouseY = (int)(b.getY());

            //Make a new rectangle representing the mouse
            Rectangle mouseRectangle = new Rectangle(mouseX - 2, mouseY - 2, 4, 4);

            //Variables for current check
            GameObject object;
            Image objectImage;
            int objectX;
            int objectWidth;
            int objectY;
            int objectHeight;

            int worldX = 935 - Game.player.xPos;
            int worldY = 490 - Game.player.yPos;

            //Iterate through the world's ArrayList
            for (int i = 0; i < Game.world.getArraySize(); i ++) {
                //The player is 50 * 100 and their position is their bottom center
                //Find the position relative to the player, who is always drawn at 935, 490 but is positioned at 960, 590
                //Every GameObject is positioned at the bottom center because of the perspective
                object = Game.world.getItemAtIndex(i);
                try {
                    objectImage = Game.images.getImage(object.getImageName());
                } catch (Exception e) {
                    objectImage = Game.images.getImage("placeholder");
                }
                objectWidth = objectImage.getWidth(this);
                objectHeight = objectImage.getHeight(this);

                //Find where it should be drawn
                objectX = (object.xPos - objectWidth / 2) + worldX;
                objectY = (object.yPos - objectHeight) + worldY;

                //Create the mouse collision rectangle for the object
                object.mouseHitbox = new Rectangle(objectX, objectY, objectWidth, objectHeight);
                g.drawRect(objectX, objectY, objectWidth, objectHeight); //DEBUG

                //Create the 4x4 player collision rectangle for the object //TODO: Fix
                object.movementHitbox = new Rectangle(object.xPos - 2, object.yPos - 2, 4 ,4);
                g.drawRect(object.xPos - 2, object.yPos - 2, 4 ,4); //DEBUG

                //Check to see if the mouse is intersecting
                if (object.mouseHitbox.intersects(mouseRectangle)) {
                    Game.actionableObject = object;
                }

                //Draw the object
                g.drawImage(objectImage, objectX, objectY, this);

                //Extra step for berries on a bush
                if (object instanceof Bush && ((Bush)object).hasResource()) {
                    g.drawImage(Game.images.getImage("bushberry"), objectX, objectY, this);
                }
            }

            //Draw the player
            g.drawImage(Game.images.getImage("player"), 960-25, 540-50, this);
            g.drawRect(960-25, 540-50, 50, 100);

            //Draw the inventory
            //Background
            g.setColor(Color.gray);
            g.fillRect(1840, 20, 60, 555);
            //Squares
            for (int box = 0; box < 10; box ++) {
                g.setColor(Color.white);
                g.fillRect(1845, 25 + 55 * box, 50, 50);
                g.setColor(Color.black);
                g.drawRect(1845, 25 + 55 * box, 50, 50);
            }

            //Draw the item the player's holding in the cursor
            if(Game.player.cursorItem != null) {
                Image cursorItemImage = Game.images.getImage(Game.player.cursorItem.getImageName());
                g.drawImage(cursorItemImage, mouseX - 10, mouseY - 10, this);
            }

            //Draw the player's status
            //Background
            g.setColor(Color.white);
            g.fillRect(10, 10, 200, 25);
            g.fillRect(10, 40, 200, 25);
            g.fillRect(10, 70, 200, 25);
            //Actual percentages
            g.setColor(Color.green);
            g.fillRect(10, 10, Game.player.getHealth() * 2, 25);
            g.fillRect(10, 40, (int)(((double)Game.player.getHunger() / Game.player.maxHunger) * 200), 25);
            //Calculate the color for warmth
            double warmthPercentage = (double)Game.player.getWarmth() / Game.player.maxWarmth;
            g.setColor(new Color((int)(warmthPercentage * 255),0, (int)(255 - (warmthPercentage * 255)), (int)Math.min((warmthPercentage + 0.5) * 255, 175)));
            g.fillRect(10, 70, (int)(warmthPercentage * 200), 25);
            //Outlines
            g.setColor(Color.black);
            g.drawRect(10, 10, 200, 25);
            g.drawRect(10, 40, 200, 25);
            g.drawRect(10, 70, 200, 25);
            //Text
            g.drawString("HEALTH", 15, 30); //The coordinates for text are bottom left for some reason
            g.drawString("HUNGER", 15, 60); //The coordinates for text are bottom left for some reason
            g.drawString("WARMTH", 15, 90);



            //Check to see if the player is the actionable object
            if (Game.player.mouseHitbox.intersects(mouseRectangle)) {
                Game.actionableObject = Game.player;
            }

            //Check to see if the inventory is the actionable object
            if (mouseRectangle.intersects(Game.player.inventory.getMainBox())) {
                //Set the actionable object to null first so the player doesn't click on things behind the inventory
                Game.actionableObject = null;
                //We'll check specific boxes later in Game since Game.actionableObject cannot be Inventory
            }
        }

        public void keyTyped(KeyEvent e) {}

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

        public void mouseClicked(java.awt.event.MouseEvent e) {
            Game.mouseClick = true;
        }

        public void mousePressed(java.awt.event.MouseEvent e) {

        }

        public void mouseReleased(java.awt.event.MouseEvent e) {

        }

        public void mouseEntered(java.awt.event.MouseEvent e) {

        }

        public void mouseExited(java.awt.event.MouseEvent e) {

        }
    }
}

