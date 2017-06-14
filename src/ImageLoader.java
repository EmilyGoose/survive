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

    ImageLoader() {
        try {
            Scanner input = new Scanner(new File("res/ImageLoadList.txt"));
            while (input.hasNext()) {
                String[] line = input.nextLine().split("//");
                try {
                    //Try to load the image
                    ImageIO.read(new File("res/" + line[1]));
                    images.put(line[0], ImageIO.read(new File("res/" + line[1])));
                    System.out.println(line[0] + " (" + line[1] + ") loaded");
                } catch (Exception e) {
                    //File doesn't exist
                    System.out.println(line[1] + " failed to load");
                }
            }
        } catch (Exception E){
            System.out.println("Image load list could not be found or is not properly formatted! Cannot proceed.");
            System.exit(1);
        }
    }

    public Image getImage(String name) {
        return images.get(name);
    }
}
