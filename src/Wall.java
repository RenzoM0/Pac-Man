import java.awt.*;

public class Wall extends GameObject{

    public Wall(Image image, int x, int y, int width, int height, int tileSize) {
        super(image, x, y, width, height, tileSize);    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
