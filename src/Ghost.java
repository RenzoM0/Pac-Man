import java.awt.*;

public class Ghost extends GameObject {

    public Ghost(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
