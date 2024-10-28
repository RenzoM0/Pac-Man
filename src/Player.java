import java.awt.*;

public class Player extends GameObject{

    private char direction = 'U';
    private int velocityX = 0;
    private int velocityY = 0;
    private PacMan game;

    public Player(Image image, int x, int y, int width, int height, int tileSize, PacMan game) {
        super(image, x, y, width, height, tileSize);
        this.game = game;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += this.velocityX;
        this.y += this.velocityY;
        for (GameObject wall : game.walls) {
            if (game.collision(this, wall)) {
                this.x -= this.velocityX;
                this.y -= this.velocityY;
                this.direction = prevDirection;
                updateVelocity();
            }
        }
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

        //check wall collisions
        for (GameObject wall : game.walls) {
            if (game.collision(this, wall)) {
                this.x -= velocityX;
                this.y -= velocityY;
                break;
            }
        }
    }

    void reset() {
        this.x = this.startX;
        this.y = this.startY;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
