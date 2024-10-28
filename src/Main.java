import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(21,19,32);

        JFrame frame = new JFrame("Pac-Man");
        frame.setSize(window.getWINDOW_WIDTH(), window.getWINDOW_HEIGHT());
        frame.setLocationRelativeTo(null); //set frame to center
        frame.setResizable(false); //fixed frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PacMan pacmanGame = new PacMan(window);
        frame.add(pacmanGame);
        frame.pack(); //full size of jpanel in window
        frame.setVisible(true);
    }
}