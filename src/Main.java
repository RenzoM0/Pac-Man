import javax.swing.JFrame;

public class Main {

        public static void main(String[] args) {
            // Create a Window instance with desired rows, columns, and tile size
            Window window = new Window(21, 19, 32);

            // Create the JFrame and set its size using WINDOW_WIDTH and WINDOW_HEIGHT
            JFrame frame = new JFrame("Pac-Man");
            frame.setSize(window.getWINDOW_WIDTH(), window.getWINDOW_HEIGHT());
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setResizable(false); // Prevent resizing
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create the game instance (make sure PacMan constructor understands rowCount and columnCount)
            PacMan pacmanGame = new PacMan(window);

            // Add the game panel to the frame
            frame.add(pacmanGame);
            frame.pack(); //Frame fits game components
            pacmanGame.requestFocus(); // Request focus for key events
            frame.setVisible(true); // Show the frame after adding the components
        }
    }