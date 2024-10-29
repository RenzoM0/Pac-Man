import java.awt.Image;
import java.util.HashSet;
import java.util.Random;

public class Ghost extends GameObject {

    char direction;
    int velocityX = 0;
    int velocityY = 0;
    private Random random = new Random();
    private int tileSize;
    private HashSet<Wall> walls;

    Ghost(Image image, int x, int y, int width, int height, int tileSize, HashSet<Wall> walls) {
        super(image, x, y, width, height);
        this.walls = walls;
        this.tileSize = tileSize;
        this.direction = getRandomDirection();
        updateVelocity();
    }

    char getRandomDirection() {
        char[] directions = {'U', 'D', 'L', 'R'};
        return directions[random.nextInt(directions.length)];
    }

    void updateDirection() {
        // Change direction randomly or based on game logic
        this.direction = getRandomDirection();
        updateVelocity();
    }

    void updateVelocity() {
        switch (direction) {
            case 'U':
                velocityX = 0;
                velocityY = -tileSize / 4; // Match player behaviour
                break;
            case 'D':
                velocityX = 0;
                velocityY = tileSize / 4; // Match player behaviour
                break;
            case 'L':
                velocityX = -tileSize / 4; // Match player behaviour
                velocityY = 0;
                break;
            case 'R':
                velocityX = tileSize / 4; // Match player behaviour
                velocityY = 0;
                break;
        }
    }

    public void move(int boardWidth) {
        int originalX = this.x;
        int originalY = this.y;

        this.x += velocityX;
        this.y += velocityY;

        // Check for boundary conditions
        if (this.y == tileSize * 9 && this.direction != 'U' && this.direction != 'D') {
            this.direction = 'U'; // Change direction to UP
            updateVelocity(); // Update velocity based on the new direction
        }

        // Check for collisions with walls
        for (Wall wall : walls) {
            if (collidesWith(wall) || this.x <= 0 || this.x + this.width >= boardWidth) {
                // Revert to the original position on collision
                this.x = originalX;
                this.y = originalY;

                this.direction = getRandomDirection(); // Randomly choose a new direction
                updateVelocity();
                break;
            }
        }
    }

    void reset(int startX, int startY) {
        super.reset(startX, startY);
        updateDirection();
    }
}