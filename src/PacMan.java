import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class PacMan extends JPanel implements ActionListener, KeyListener {
    private Window window;
    private Player pacman;
    private HashSet<Wall> walls;
    private HashSet<Food> foods;
    private HashSet<Ghost> ghosts;
    private Timer gameLoop;
    private int score = 0;
    private int lives = 3;
    private boolean gameOver = false;

    // Images
    private Image wallImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    // ImageLoader instance
    private ImageLoader imageLoader;

    private int tileSize;
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    public PacMan(Window window) {
        this.window = window;
        setPreferredSize(new Dimension(window.getWINDOW_WIDTH(), window.getWINDOW_HEIGHT()));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        tileSize = window.getTileSize();

        // Initialize ImageLoader
        imageLoader = ImageLoader.getInstance();

        // Load images and the map
        loadImages();
        loadMap();

        // Initialize the game loop
        gameLoop = new Timer(50, this);
        gameLoop.start();
    }

    // Load images for Pac-Man, walls, and ghosts
    private void loadImages() {
        wallImage = imageLoader.loadImage("wall.png");
        pacmanUpImage = imageLoader.loadImage("pacmanUp.png");
        pacmanDownImage = imageLoader.loadImage("pacmanDown.png");
        pacmanLeftImage = imageLoader.loadImage("pacmanLeft.png");
        pacmanRightImage = imageLoader.loadImage("pacmanRight.png");
    }

    // Load the map from the tileMap array
    private void loadMap() {
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>();

        for (int r = 0; r < window.getRowCount(); r++) {
            for (int c = 0; c < window.getColumnCount(); c++) {
                String row = tileMap[r];
                char tile = row.charAt(c);

                int x = c * tileSize;
                int y = r * tileSize;

                switch (tile) {
                    case 'X':
                        walls.add(new Wall(wallImage, x, y, tileSize, tileSize));
                        break;
                    case 'P':
                        pacman = new Player(pacmanRightImage, x, y, tileSize, tileSize);
                        break;
                    case 'b': // Blue ghost
                        ghosts.add(new Ghost(imageLoader.loadImage("blueGhost.png"), x, y, tileSize, tileSize, tileSize, walls));
                        break;
                    case 'o': // Orange ghost
                        ghosts.add(new Ghost(imageLoader.loadImage("orangeGhost.png"), x, y, tileSize, tileSize, tileSize, walls));
                        break;
                    case 'p': // Pink ghost
                        ghosts.add(new Ghost(imageLoader.loadImage("pinkGhost.png"), x, y, tileSize, tileSize, tileSize, walls));
                        break;
                    case 'r': // Red ghost
                        ghosts.add(new Ghost(imageLoader.loadImage("redGhost.png"), x, y, tileSize, tileSize, tileSize, walls));
                        break;
                    case ' ':
                        foods.add(new Food(x + 14, y + 14)); // Center the food
                        break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            pacman.move();
            checkCollisions();
            updateGhosts();
        }
        repaint();
    }

    // Check for collisions with walls, ghosts, and food
    private void checkCollisions() {
        // Check wall collisions
        for (Wall wall : walls) {
            if (pacman.collidesWith(wall)) {
                pacman.reset((int) (pacman.x - pacman.velocityX), (int) (pacman.y - pacman.velocityY));
                break;
            }
        }

        // Check ghost collisions
        for (Ghost ghost : ghosts) {
            if (pacman.collidesWith(ghost)) {
                lives--;
                if (lives <= 0) {
                    gameOver = true;
                }
                pacman.reset(tileSize * 8, tileSize * 15); // Reset to starting position
                return;
            }
        }

        // Check food collisions
        Food foodEaten = null;
        for (Food food : foods) {
            if (pacman.collidesWith(food)) {
                foodEaten = food;
                score += 10;
                break;
            }
        }

        if (foodEaten != null) {
            foods.remove(foodEaten);
        }

        if (foods.isEmpty()) {
            // Reload the map if all foods are eaten
            loadMap();
            score += 100; // Bonus score for clearing the level
        }
    }

    // Update ghosts' positions
    private void updateGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move(window.getWINDOW_WIDTH()); // Call the move() method to update ghost's position
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Draw the Pac-Man, walls, ghosts, and foods
    private void draw(Graphics g) {
        // Draw walls
        for (Wall wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }

        // Draw foods
        for (Food food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }

        // Draw Pac-Man
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        // Draw ghosts
        for (Ghost ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        // Draw score and lives
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Lives: " + lives, 10, 40);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Game Over", getWidth() / 2 - 60, getHeight() / 2);
        }
    }

    // Reset the game
    public void resetGame() {
        lives = 3;
        score = 0;
        gameOver = false;
        loadMap();
        gameLoop.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameOver) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacman.updateDirection('U', tileSize);
                break;
            case KeyEvent.VK_DOWN:
                pacman.updateDirection('D', tileSize);
                break;
            case KeyEvent.VK_LEFT:
                pacman.updateDirection('L', tileSize);
                break;
            case KeyEvent.VK_RIGHT:
                pacman.updateDirection('R', tileSize);
                break;
        }

        if (pacman.direction == 'U') {
            pacman.image = pacmanUpImage;
        }
        else if (pacman.direction == 'D') {
            pacman.image = pacmanDownImage;
        }
        else if (pacman.direction == 'L') {
            pacman.image = pacmanLeftImage;
        }
        else if (pacman.direction == 'R') {
            pacman.image = pacmanRightImage;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}