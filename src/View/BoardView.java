package View;

import Model.Board;

import javax.swing.*;
import java.awt.*;

//TODO : add functionality to update game board with pieces
public class BoardView {
    private Board board;
    static JFrame frame;

    public BoardView(Board board) {
        this.board = board;
        frame = new JFrame("Kwazam Chess - TT5L GD");
        frame.setLayout(new GridLayout(9,5, -1, -1));
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayMenus();
        displayInitialBoard();

        frame.setVisible(true);
    }

    public void displayMenus() {
        //Save Button
        JButton save = new JButton("Save");

        //Load
        JButton load = new JButton("Load");

        //New Game
        JButton newgame = new JButton("New Game");

        //add into frame
        frame.add(save);
        frame.add(load);
        frame.add(newgame);
        frame.add(new JLabel());
        frame.add(new JLabel());
    }

    public void displayInitialBoard() {
        String[][] boardArray = board.getBoard();

        for(String[] row : boardArray) {
            for(String cell : row) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel(cell, SwingConstants.CENTER);
                panel.add(label, BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setPreferredSize(new Dimension(100, 100));
                String addimg = label.getText();
                getPieceImages(addimg.trim(), label);
                frame.add(panel);
            }
        }
    }

    public void getPieceImages(String cell, JLabel label) {
        ImageIcon image = new ImageIcon();
        switch(cell) {
            case "R":
                image = new ImageIcon("src/Img/Ram.png");
                label.setIcon(image);
                break;
            case "B":
                image = new ImageIcon("src/Img/Biz.png");
                label.setIcon(image);
                break;
            case "T":
                image = new ImageIcon("src/Img/Tor.png");
                label.setIcon(image);
                break;
            case "S":
                image = new ImageIcon("src/Img/Sau.png");
                label.setIcon(image);
                break;
            case "X":
                image = new ImageIcon("src/Img/Xor.png");
                label.setIcon(image);
                break;
            default:
                label.setIcon(null);
                break;
        }
    }
}
