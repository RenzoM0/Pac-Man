import java.awt.*;

public class GameObject {

    int x, y;
    int width, height;
    Image image;

    GameObject(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    void reset(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public boolean collidesWith(GameObject other) {
        return this.x < other.x + other.width
                && this.x + this.width > other.x
                && this.y < other.y + other.height
                && this.y + this.height > other.y;
    }

}