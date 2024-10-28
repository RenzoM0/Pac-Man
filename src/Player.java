import java.awt.*;

public class Player extends GameObject{

    private char direction = 'U';
    private int velocityX = 0;
    private int velocityY = 0;

    public Player(Image image, int x, int y, int width, int height, int tileSize) {
        super(image, x, y, width, height, tileSize);
    }

    public void setDirection(char direction) {
        this.direction = direction;
        updateVelocity();
    }

    private void updateVelocity() {
        if (this.direction == 'U') {
            this.velocityX = 0;
            this.velocityY = -tileSize/4;
        }
        else if (this.direction == 'D') {
            this.velocityX = 0;
            this.velocityY = tileSize/4;
        }
        else if (this.direction == 'L') {
            this.velocityX = -tileSize/4;
            this.velocityY = 0;
        }
        else if (this.direction == 'R') {
            this.velocityX = tileSize/4;
            this.velocityY = 0;
        }
    }

    public void move() {
        this.x += velocityX;
        this.y += velocityY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
