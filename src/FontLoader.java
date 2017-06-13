import java.awt.*;
import java.net.URL;
import java.util.Hashtable;

/**
 * FontLoader.java
 * Loads fonts required for Survive
 */

public class FontLoader {
    private Hashtable<String, Font> fonts = new Hashtable<>();

    FontLoader() {
        //Load fonts
        try {
            URL fontUrl = new URL("https://fonts.gstatic.com/s/productsans/v9/HYvgU2fE2nRJvZ5JFAumwZS3E-kSBmtLoNJPDtbj2Pk.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
            font = font.deriveFont(Font.PLAIN, 36);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            fonts.put("productSans", font);
            System.out.println("Product Sans loaded");
        } catch (Exception e) {
            System.out.println("Could not load Product Sans");
        }
        try {
            URL fontUrl = new URL("https://github.com/google/fonts/blob/master/apache/roboto/Roboto-Light.ttf?raw=true");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
            font = font.deriveFont(Font.PLAIN, 12);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            fonts.put("roboto", font);
            System.out.println("Roboto loaded");
        } catch (Exception e) {
            System.out.println("Could not load Roboto");
        }
    }

    public Font getFont(String name) {
        return fonts.get(name);
    }
}
