import java.awt.*;

public class Player extends GameObject{

    public Player(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
