package View;

import Model.BoardModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//TODO : add functionality to update game board with pieces
public class BoardView {
    private BoardModel board;
    static JFrame frame;
    private List<JPanel> panels;//to store all the panels inside for easy access by the controller

    public BoardView(BoardModel board) {
        this.board = board;
        panels = new ArrayList<>();
        frame = new JFrame("Kwazam Chess - TT5L GD");
        frame.setLayout(new GridLayout(9,5, -1, -1));
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayMenus();
        displayInitialBoard();

        //popup to remind user that blue starts first
        JOptionPane.showMessageDialog(frame, "Blue Piece Starts First. Enjoy!");

        frame.setVisible(true);
    }

    public void displayMenus() {
        //Save Button
        JButton save = new JButton("Save");

        //Load
        JButton load = new JButton("Load");

        //New Game
        JButton newgame = new JButton("New Game");


        //Logo
        ImageIcon logoIcon = new ImageIcon("src/Img/logo.png");
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(130, 35, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);
        JLabel logoLabel = new JLabel(logoIcon);

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
                JPanel panel = new JPanel();
                JLabel label = new JLabel(cell, SwingConstants.CENTER);

                //add the panels
                panel.add(label, BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setPreferredSize(new Dimension(100, 100));

                //set the images for the pieces & set access names for easier calls
                String addimg = label.getText();
                getPieceImages(addimg.trim(), label);
                panel.getAccessibleContext().setAccessibleName(addimg);

                //add into panels list for controller to handle adding mouselistener
                panels.add(panel);

                //add panel to frame
                frame.add(panel);
            }
        }
    }

    public List<JPanel> getPanels() {
        return panels;
    }

    public void getPieceImages(String cell, JLabel label) {
        ImageIcon image = null;
        switch(cell) {
            case "R":
                label.setText("");
                image = new ImageIcon("src/Img/Ram.png");
                label.setIcon(image);
                break;
            case "B":
                label.setText("");
                image = new ImageIcon("src/Img/Biz.png");
                label.setIcon(image);
                break;
            case "T":
                label.setText("");
                image = new ImageIcon("src/Img/Tor.png");
                label.setIcon(image);
                break;
            case "S":
                label.setText("");
                image = new ImageIcon("src/Img/Sau.png");
                label.setIcon(image);
                break;
            case "X":
                label.setText("");
                image = new ImageIcon("src/Img/Xor.png");
                label.setIcon(image);
                break;
            case "ER":
                label.setText("");
                image = new ImageIcon("src/Img/RamBlue.png");
                label.setIcon(image);
                break;
            case "EB":
                label.setText("");
                image = new ImageIcon("src/Img/BizBlue.png");
                label.setIcon(image);
                break;
            case "ET":
                label.setText("");
                image = new ImageIcon("src/Img/TorBlue.png");
                label.setIcon(image);
                break;
            case "ES":
                label.setText("");
                image = new ImageIcon("src/Img/SauBlue.png");
                label.setIcon(image);
                break;
            case "EX":
                label.setText("");
                image = new ImageIcon("src/Img/XorBlue.png");
                label.setIcon(image);
                break;
            default:
                label.setIcon(null);
                break;
        }
        if(image != null){
            Image img = image.getImage();
            Image newImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImage);
            label.setIcon(newIcon);
        } else {
            label.setIcon(null);
        }
    }

    public void UpdateBoard() {
        String[][] boardArray = board.getBoard();
        for(int i = 0; i < panels.size(); i++) {
            int row = i / 5;
            int col = i % 5;
            JLabel label = (JLabel) panels.get(i).getComponent(0);
            String cell = boardArray[row][col];

            label.setText(cell.equals("#") ? "" : cell);
            getPieceImages(cell.trim(), label);
        }
    }
}
