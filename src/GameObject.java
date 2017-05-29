/**
 * GameObject.java
 * Top-level class for all objects in Survive
 * Misha Larionov
 */

import java.awt.*;

public class GameObject {
    public int xPos;
    public int yPos;
    private int imageID; //TODO: Images!
    public Rectangle mouseHitbox;
    public Rectangle movementHitbox;

    GameObject() {
        this.xPos = -1;
        this.yPos = -1;
    }

    GameObject (int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }
}
