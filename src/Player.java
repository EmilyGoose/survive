/**
 * Player.java
 * Main player class for Survive
 * Misha Larionov
 */

public class Player extends GameObject {
    private int health;
    private int hunger;
    private int warmth;
    private int speed = 5;
    private Inventory inventory;

    private int xMovement = 0;
    private int yMovement = 0;

    public GameObject cursorItem;
    private GameObject equippedTool; //TODO: Tool interface

    Player() {
        super(2500, 2500);
        this.health = 100;
        this.hunger = 100;
        this.warmth = 100;
        this.inventory = new Inventory();
        this.setImageName("player");
    }

    //Hunger-related functions
    public int getHunger() {
        return this.hunger;
    }

    public void addFood(int add) {
        this.hunger += add;
        if (this.hunger > 100) {
            this.hunger = 100;
        }
    }

    public void removeHunger(int i) {
        this.hunger -= i;
        if (this.hunger < 0) {
            this.hunger = 0;
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
