/**
 * ImageLoader.java
 * Basically a container class for a hash table with image IDs
 */

import javax.imageio.ImageIO;
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
            Scanner input = new Scanner(new File("res/ImageLoadList.txt"));
            while (input.hasNext()) {
                String[] line = input.nextLine().split("//");
                try {
                    //Try to load the image
                    ImageIO.read(new File("res/" + line[1]));
                    images.put(line[0], ImageIO.read(new File("res/" + line[1])));
                    this.imagesLoaded += 1;
                    System.out.println(line[0] + " (" + line[1] + ") loaded");
                } catch (Exception e) {
                    //File doesn't exist
                    System.out.println(line[1] + " failed to load");
                }
            }
        } catch (Exception E){
            System.out.println("Image load list could not be found! Cannot proceed.");
            System.exit(1);
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
