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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void displayMenus() {
        //logo image
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon("Img/logochess.png"));

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
        frame.add(logoLabel);
        frame.add(new JLabel());
    }

    public void displayInitialBoard() {
        String[][] boardArray = board.getBoard();

        for(String[] row : boardArray) {
            for(String cell : row) {
                JLabel label = new JLabel(cell, SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                frame.add(label);
            }
        }
    }
}
