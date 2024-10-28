import java.awt.Image;

public class Player extends GameObject {

    char direction = 'U'; // Up, Down, Left, Right
    int velocityX = 0;
    int velocityY = 0;

    Player(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    void updateDirection(char direction, int tileSize) {
        this.direction = direction;
        switch (direction) {
            case 'U':
                velocityX = 0;
                velocityY = -tileSize / 4;
                break;
            case 'D':
                velocityX = 0;
                velocityY = tileSize / 4;
                break;
            case 'L':
                velocityX = -tileSize / 4;
                velocityY = 0;
                break;
            case 'R':
                velocityX = tileSize / 4;
                velocityY = 0;
                break;
        }

        // Update position
        move();
    }

    void move() {
        this.x += velocityX;
        this.y += velocityY;
    }

    void reset(int startX, int startY) {
        super.reset(startX, startY);
        velocityX = 0;
        velocityY = 0;
    }

}
