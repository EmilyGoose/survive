/**
 * ImageLoader.java
 * Basically a container class for a hash table with image IDs
 */

import java.awt.*;
import java.io.*; // S T A R I M P O R T S
import java.util.Hashtable;
import java.util.Scanner;

public class ImageLoader {
    //Declare the hash table
    private Hashtable<String, Image> images = new Hashtable<>();

    //This is for the loading screen
    private int imagesLoaded = 0;
    private boolean ready = false;

    ImageLoader() {
        try {
            File f = new File("res/ImageLoadList.txt");
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String[] line = input.nextLine().split("//");
                try {
                    images.put(line[1], Toolkit.getDefaultToolkit().getImage("res/" + line[1]));
                    this.imagesLoaded += 1;
                } catch (Exception E) {
                    //File doesn't exist
                    System.out.println(line[1] + " could not be loaded");
                }
            }
        } catch (Exception E){
            System.out.println("Images could not be loaded! Using placeholder...");
        }
        this.ready = true;
    }

    public int getImagesLoaded() {
        return this.imagesLoaded;
    }

    public boolean isReady() {
        return this.ready;
    }

    public Image getImage(String name) {
        if (this.isReady()) {
            return images.get(name);
        } else {
            return null;
        }
    }
}
