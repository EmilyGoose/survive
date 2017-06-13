import java.awt.*;

/**
 * Player.java
 * Main player class for Survive
 * Misha Larionov
 */

public class Player extends GameObject {
    private int health;
    public final int maxHealth = 1000;
    private int hunger;
    public final int maxHunger = 7200; //Hunger runs out in 2 minutes at 60 FPS (2 * 60 * 60 = 7200)
    private int warmth;
    public final int maxWarmth = 7200; //Ditto
    private int speed = 5;
    public Inventory inventory;

    private int xMovement = 0;
    private int yMovement = 0;

    public InventoryObject cursorItem;
    private GameObject equippedTool; //TODO: Tool interface

    Player() {
        //Casting is somewhat redundant but I don't want to change this if WORLD_SIZE becomes odd
        super((int)(Game.WORLD_SIZE/2), (int)(Game.WORLD_SIZE/2) + 100);
        this.health = this.maxHealth;
        this.hunger = this.maxHunger;
        this.warmth = this.maxWarmth;
        this.inventory = new Inventory();
        this.inventory.addItem(new FireStarter());
        this.mouseHitbox = new Rectangle(910, 390, 50, 100);
        this.setImageName("player");
    }

    //Hunger-related functions
    public int getHunger() {
        return this.hunger;
    }

    public void addFood(int add) {
        this.hunger += add;
        if (this.hunger > this.maxHunger) {
            this.hunger = this.maxHunger;
        }
    }

    public void removeHunger(int i) {
        this.hunger -= i;
        if (this.hunger < 0) {
            this.hunger = 0;
        }
    }

    //Warmth-related functions
    public int getWarmth() {return this.warmth; }

    public void addWarmth(int add) {
        this.warmth += add;
        if (this.warmth > this.maxWarmth) {
            this.warmth = this.maxWarmth;
        }
    }

    public void removeWarmth(int i) {
        this.warmth -= i;
        if (this.warmth < 0) {
            this.warmth = 0;
        }
    }

    //Health-related functions
    public int getHealth() {
        return this.health;
    }

    public void takeDamage(int dam) {
        this.health -= dam;
        //Don't have to check if it's less than 0 because then the character dies anyway, game over
    }

    public void heal(int hp) {
        this.health += hp;
        if (this.health > 100) {
            this.health = 100;
        }
    }

    public void addXMovemement(int i) {
        if (Math.abs(this.xMovement + i) <= 1) { //Prevent inputs that are too large or small
            this.xMovement += i;
        }
    }

    public void addYMovemement(int i) {
        if (Math.abs(this.yMovement + i) <= 1) { //Prevent inputs that are too large or small
            this.yMovement += i;
        }
    }

    public int getXMovement() {
        return this.xMovement;
    }

    public int getYMovement() {
        return this.yMovement;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int newSpeed) {
        System.exit(1);
        this.speed = newSpeed;
    }

}
