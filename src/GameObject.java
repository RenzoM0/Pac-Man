import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int tileSize;
    Image image;

    protected int startX;
    protected int startY;

    public GameObject(Image image, int x, int y, int width, int height, int tileSize) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
    }

    public abstract void render(Graphics g);
}
