import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import javax.swing.*;

public class PacMan extends JPanel implements ActionListener, KeyListener {
    private Window window;
    HashSet<GameObject> walls;
    HashSet<GameObject> foods;
    HashSet<GameObject> ghosts;
    Player player;

    private Timer timer;

    //Images
    private Image wallImage;
    //Ghost Images
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    //Player Images
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    PacMan(Window window) {
        this.window = window;

        setPreferredSize(new Dimension(window.getWINDOW_WIDTH(), window.getWINDOW_HEIGHT()));
        setBackground(Color.BLACK);
        initializeGameObjects();
        loadMap();

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(50, this); //20fps (1000ms/50ms)
        timer.start();
    }

    private void initializeGameObjects() {
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>();

        //Image Loader (using path:/assets/...)
        ImageLoader loader = ImageLoader.getInstance();

        wallImage = loader.loadImage("wall.png");

        //Ghost Images
        blueGhostImage = loader.loadImage("blueGhost.png");
        orangeGhostImage = loader.loadImage("orangeGhost.png");
        pinkGhostImage = loader.loadImage("pinkGhost.png");
        redGhostImage = loader.loadImage("redGhost.png");

        //Player Images
        pacmanUpImage = loader.loadImage("pacmanUp.png");
        pacmanDownImage = loader.loadImage("pacmanDown.png");
        pacmanLeftImage = loader.loadImage("pacmanLeft.png");
        pacmanRightImage = loader.loadImage("pacmanRight.png");
    }

    public void loadMap() {
        walls = new HashSet<GameObject>();
        foods = new HashSet<GameObject>();
        ghosts = new HashSet<GameObject>();

        for (int r = 0; r < window.getRowCount(); r++) {
            for (int c = 0; c < window.getColumnCount(); c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c * window.getTileSize();
                int y = r * window.getTileSize();

                if (tileMapChar == 'X') { //block wall
                    GameObject wall = new Wall(wallImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize());
                    walls.add(wall);
                } else if (tileMapChar == 'b') { //blue ghost
                    GameObject ghost = new Ghost(blueGhostImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize());
                    ghosts.add(ghost);
                } else if (tileMapChar == 'o') { //orange ghost
                    GameObject ghost = new Ghost(orangeGhostImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize());
                    ghosts.add(ghost);
                } else if (tileMapChar == 'p') { //pink ghost
                    GameObject ghost = new Ghost(pinkGhostImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize());
                    ghosts.add(ghost);
                } else if (tileMapChar == 'r') { //red ghost
                    GameObject ghost = new Ghost(redGhostImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize());
                    ghosts.add(ghost);
                } else if (tileMapChar == 'P') { //player
                    player = new Player(pacmanRightImage, x, y, window.getTileSize(), window.getTileSize(), window.getTileSize(), this);
                } else if (tileMapChar == ' ') { //food
                    GameObject food = new Food(null, x + 14, y + 14, 4, 4, window.getTileSize());
                    foods.add(food);
                }
            }
        }
    }

    /* X = wall, O = skip, P = pac man, ' ' = food
     * Ghosts: b = blue, o = orange, p = pink, r = red
     * */
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

    public boolean collision(GameObject a, GameObject b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        // Render all game objects
        for (GameObject wall : walls) wall.render(g);
        for (GameObject food : foods) food.render(g);
        for (GameObject ghost : ghosts) ghost.render(g);
        player.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ((Player) player).setDirection('U');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ((Player) player).setDirection('D');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ((Player) player).setDirection('L');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ((Player) player).setDirection('R');
        }

        if (player.getDirection() == 'U') {
            player.image = pacmanUpImage;
        }
        else if (player.getDirection() == 'D') {
            player.image = pacmanDownImage;
        }
        else if (player.getDirection() == 'L') {
            player.image = pacmanLeftImage;
        }
        else if (player.getDirection() == 'R') {
            player.image = pacmanRightImage;
        }
    }
}