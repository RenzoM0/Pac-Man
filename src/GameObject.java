import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    Image image;

    protected int startX;
    protected int startY;

    public GameObject(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Graphics g);
}
