package View;

import Controller.BoardController;
import Model.BoardModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView {
    private BoardModel board;
    static JFrame frame;
    private List<JPanel> panels;

    public BoardView(BoardModel board) {
        this.board = board;
        panels = new ArrayList<>();
        frame = new JFrame("Kwazam Chess - TT5L GD");
        frame.setLayout(new GridLayout(9, 5, -1, -1));
        frame.setSize(600, 700);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayMenus();
        displayInitialBoard();

        JOptionPane.showMessageDialog(frame, "Blue Piece Starts First. Enjoy!", "Kwazam Chess", JOptionPane.INFORMATION_MESSAGE);

        frame.setVisible(true);
    }

    public void displayMenus() {
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            board.saveGame("save_file.txt");
            JOptionPane.showMessageDialog(frame, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton load = new JButton("Load");
        JButton newgame = new JButton("New Game");
        BoardController.newGame(newgame, frame);

        JButton rules = new JButton("Rules");
        rules.addActionListener(e -> openRulesWindow());

        ImageIcon logoIcon = new ImageIcon("src/Img/logo.png");
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(130, 35, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);
        JLabel logoLabel = new JLabel(logoIcon);

        frame.add(save);
        frame.add(load);
        frame.add(newgame);
        frame.add(rules);
        frame.add(logoLabel);
    }

    public void openRulesWindow() {
        JFrame rulesFrame = new JFrame("Rules");
        rulesFrame.setLayout(new BoxLayout(rulesFrame.getContentPane(), BoxLayout.Y_AXIS));
        rulesFrame.setSize(1000, 750);
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font largerFont = new Font("Arial", Font.PLAIN, 14);
        Font boldFont = new Font("Arial", Font.BOLD, 14);

        JLabel piecesRules = new JLabel("   Pieces Movement Rules:");
        piecesRules.setFont(boldFont);

        JLabel rule1 = new JLabel("   Ram: The Ram piece can only move forward, 1 step. Turns around at the end of the board. It cannot skip over other pieces.");
        rule1.setFont(largerFont);
        JLabel ramImageLabel = new JLabel(new ImageIcon(new ImageIcon("src/Img/Ram.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule2 = new JLabel("   Biz: The Biz piece moves in a 3x2 L shape in any orientation. This is the only piece that can skip other pieces.");
        rule2.setFont(largerFont);
        JLabel bizImageLabel = new JLabel(new ImageIcon(new ImageIcon("src/Img/Biz.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule3 = new JLabel("   Tor: The Tor piece can move orthogonally only but can go any distance. It cannot skip over other pieces. After 2 turns, it transforms into the Xor piece.");
        rule3.setFont(largerFont);
        JLabel torImageLabel = new JLabel(new ImageIcon(new ImageIcon("src/Img/Tor.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule4 = new JLabel("   Xor: The Xor piece can move diagonally only but can go any distance. It cannot skip over other pieces. After 2 turns, it turns into the Tor piece.");
        rule4.setFont(largerFont);
        JLabel xorImageLabel = new JLabel(new ImageIcon(new ImageIcon("src/Img/Xor.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule5 = new JLabel("   Sau: The Sau piece can move only one step in any direction. The game ends when the Sau is captured by the other side.");
        rule5.setFont(largerFont);
        JLabel sauImageLabel = new JLabel(new ImageIcon(new ImageIcon("src/Img/Sau.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel winnerRules = new JLabel("   Whoever can capture the Sau piece first wins!");
        winnerRules.setFont(boldFont);

        // Add rules and images to frame
        rulesFrame.add(piecesRules);
        rulesFrame.add(Box.createVerticalStrut(15));
        rulesFrame.add(rule1);
        rulesFrame.add(ramImageLabel);
        rulesFrame.add(Box.createVerticalStrut(75));
        rulesFrame.add(rule2);
        rulesFrame.add(bizImageLabel);
        rulesFrame.add(Box.createVerticalStrut(75));
        rulesFrame.add(rule3);
        rulesFrame.add(torImageLabel);
        rulesFrame.add(Box.createVerticalStrut(75));
        rulesFrame.add(rule4);
        rulesFrame.add(xorImageLabel);
        rulesFrame.add(Box.createVerticalStrut(75));
        rulesFrame.add(rule5);
        rulesFrame.add(sauImageLabel);
        rulesFrame.add(winnerRules);

        rulesFrame.setVisible(true);
    }

    public void displayInitialBoard() {
        String[][] boardArray = board.getBoard();

        for (String[] row : boardArray) {
            for (String cell : row) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel(cell, SwingConstants.CENTER);

                panel.add(label, BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setPreferredSize(new Dimension(100, 100));

                String addimg = label.getText();
                getPieceImages(addimg.trim(), label);
                panel.getAccessibleContext().setAccessibleName(addimg);

                panels.add(panel);
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
        for (int i = 0; i < panels.size(); i++) {
            int row = i / 5;
            int col = i % 5;
            JLabel label = (JLabel) panels.get(i).getComponent(0);
            String cell = boardArray[row][col];

            label.setText(cell.equals("#") ? "" : cell);
            getPieceImages(cell.trim(), label);
        }
    }
}
