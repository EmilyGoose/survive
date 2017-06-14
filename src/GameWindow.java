/**
 * GameWindow.java
 * Game window containing the whole game
 * Misha Larionov
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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
        this.setFocusTraversalKeysEnabled(false); //Allows us to capture TAB key presses

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

        private boolean tabMenuOpen;

        public GamePanel() {
            this.setPreferredSize(new Dimension(1920, 1080));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(Game.images.getImage("grass"), 0, 0, this);
            g.drawImage(Game.images.getImage("grass"), 0, 900, this);
            g.drawImage(Game.images.getImage("grass"), 900, 0, this);
            g.drawImage(Game.images.getImage("grass"), 900, 900, this);

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
                if (object instanceof CampFire) {
                    //Fires are positioned at the center, not the bottom middle
                    objectY = (object.yPos - objectHeight / 2) + worldY;
                } else {
                    objectY = (object.yPos - objectHeight) + worldY;
                }

                //To save resources, only do this if the object is within 100 pixels of the screen
                if (objectX > -100 && objectX < 2020 && objectY > -100 && objectY < 1180) {
                    //Create the mouse collision rectangle for the object
                    object.mouseHitbox = new Rectangle(objectX, objectY, objectWidth, objectHeight);

                    //Check to see if the mouse is intersecting
                    if (object.mouseHitbox.intersects(mouseRectangle)) {
                        //Make sure we're getting the topmost object in case of overlap
                        if (Game.actionableObject == null || Game.actionableObject.yPos < object.yPos) {
                            Game.actionableObject = object;
                        }
                    }
                }

                //Draw the object
                g.drawImage(objectImage, objectX, objectY, this);

                //Additional step for campfires: Draw the flame
                if (object instanceof CampFire) {
                    int flameSize = (int)(((CampFire) object).getSize() * 50);
                    int flameX = objectX + objectWidth/2 - flameSize/2;
                    int flameY = objectY + objectHeight/2 - flameSize;
                    g.drawImage(Game.images.getImage("flame"), flameX, flameY, flameSize, flameSize, this);
                }
            }

            //Draw the player (Never changes, everything is drawn relative to this)
            g.drawImage(Game.images.getImage(Game.player.getImageName()), 910, 390, this);

            //Draw the inventory
            //Background
            g.setColor(Color.gray);
            g.fillRect(1840, 20, 60, 580);
            //Squares
            for (int box = 0; box < 10; box ++) {
                g.setColor(Color.white);
                g.fillRect(1845, 25 + 55 * box, 50, 50);
                g.setColor(Color.black);
                g.drawRect(1845, 25 + 55 * box, 50, 50);
                InventoryObject boxObject = Game.player.inventory.getItemAtSlot(box, false);
                if (boxObject != null) {
                    //Draw the object in the center of the box
                    g.drawImage(Game.images.getImage(boxObject.getImageName()), 1858, 38 + 55 * box, this);
                }
            }
            //Draw the craft buttons
            if (Game.player.inventory.canCraftFire()) {
                g.drawImage(Game.images.getImage("firestarter"), 1845, 575, this);
            } else {
                g.drawImage(Game.images.getImage("firestarter_BW"), 1845, 575, this);
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
            g.fillRect(10, 10, (int)(((double)Game.player.getHealth() / Game.player.maxHealth) * 200), 25);
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

            //Build number
            g.setFont(Game.fonts.getFont("roboto"));
            g.drawString("Version 0.2", 10, 1070);

            //Check to see if the player is the actionable object
            if (Game.player.mouseHitbox.intersects(mouseRectangle)) {
                Game.actionableObject = Game.player;
            }

            //Check to see if the inventory is the actionable object
            if (mouseRectangle.intersects(Game.player.inventory.getMainRectangle())) {
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
            } else if (e.getKeyCode() == 9) { //TAB key
                this.tabMenuOpen = !(this.tabMenuOpen); //Toggle the tab menu being open
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

        }

        public void mousePressed(java.awt.event.MouseEvent e) {
            if (e.getButton() == 1) { //Make sure it's the left mouse button
                Game.mouseClick = true;
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent e) {

        }

        public void mouseEntered(java.awt.event.MouseEvent e) {

        }

        public void mouseExited(java.awt.event.MouseEvent e) {

        }

        public boolean isTabMenuOpen() {
            return this.tabMenuOpen;
        }
    }
}

