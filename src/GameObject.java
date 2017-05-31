/**
 * GameObject.java
 * Top-level class for all objects in Survive
 * Misha Larionov
 */

import java.awt.Rectangle;

public class GameObject {
    public int xPos;
    public int yPos;
    private String imageName;
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

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String name) {
        this.imageName = name;
    }
}
