import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static ImageLoader instance;
    private Map<String, BufferedImage> imageCache;

    private ImageLoader() {
        imageCache = new HashMap<>();
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public BufferedImage loadImage(String path) {
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/assets/" + path)) {
            if (inputStream == null) {
                System.err.println("Image not found: " + path);
                return null;
            }
            BufferedImage img = ImageIO.read(inputStream);
            imageCache.put(path, img);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}