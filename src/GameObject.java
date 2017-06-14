/**
 * GameObject.java
 * Top-level class for all objects in Survive
 * Misha Larionov
 */

import java.awt.Rectangle;
import java.io.Serializable;

public class GameObject implements Serializable {
    public int xPos;
    public int yPos;
    private String imageName;
    public Rectangle mouseHitbox;

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
