import minesweeper.Cell;
import minesweeper.Coordinates;
import minesweeper.Game;
import minesweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MineSweeper extends JFrame {
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;
    private final int BOMBS = 10;

    private JPanel panel;
    private JLabel label;
    private Game game;

    public static void main(String[] args) {
        new MineSweeper();
    }

    private MineSweeper () {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        Ranges.setSize(new Coordinates(COLS, ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates coord : Ranges.getAllCoords()) {
                    g.drawImage((Image)game.getCell(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinates coord = new Coordinates(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressedLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressedRightButton(coord);
                label.setText(message());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String message() {
        switch (game.getState()) {
            case PLAY:
                return "Good Luck!";
            case LOSE:
                return "You lose!";
            case WIN:
                return "CONGRATULATIONS!!";
            default:
                return "";
        }
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setImages() {
        for (Cell cell: Cell.values()) {
            cell.image = getImage(cell.name());
        }
    }

    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
        return icon.getImage();
    }
}
