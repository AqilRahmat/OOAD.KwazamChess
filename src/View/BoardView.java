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

    //Programmer: Muhammad Aqil
    //What is it: the constructor of BoardView.
    //Function: This is where the main window of the program is initialized.
    public BoardView(BoardModel board) {

        //set the BoardModel object
        this.board = board;

        //initialising the panels that are used to hold the pieces in.
        panels = new ArrayList<>();

        //Main window initialization (This is the window where the game are shown)
        frame = new JFrame("Kwazam Chess - TT5L GD");
        frame.setLayout(new GridLayout(9, 5, -1, -1));
        frame.setSize(600, 700);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Call the function to display main window with the panels and the buttons on top of the window.
        displayMenus();
        displayInitialBoard();

        //Pop-up window to remind the user that Blue Piece starts first. Will only be displayed at the start of the program.
        JOptionPane.showMessageDialog(frame, "Blue Piece Starts First. Enjoy!", "Kwazam Chess", JOptionPane.INFORMATION_MESSAGE);

        frame.setVisible(true);
    }

    //Programmer: Johan Ibrahim
    //What is it: The Buttons at the top of the main window.
    //Function: Its used to give players options during the game. They can save the game, load the previous game, or start a new game. They can also check out the rules if they dont know.
    public void displayMenus() {
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            board.saveGame("save_file.txt");
            JOptionPane.showMessageDialog(frame, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        });
        BoardController.saveGame("saved_game.txt", save);

        // load previous saved game
        JButton load = new JButton("Load");
        BoardController.loadGame("save_file.txt", load, board, this);

        // start new game
        JButton newgame = new JButton("New Game");
        BoardController.newGame(newgame, frame);

        // open rules window
        JButton rules = new JButton("Rules");
        rules.addActionListener(e -> openRulesWindow());

        // add Kwazam Chess logo at the top
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Img/logo.png"));
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(130, 35, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);
        JLabel logoLabel = new JLabel(logoIcon);

        // add buttons and logo to frame
        frame.add(save);
        frame.add(load);
        frame.add(newgame);
        frame.add(rules);
        frame.add(logoLabel);
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it: The rules window.
    //Function: After player clicked the "Rules" button on top of the main window this frame will pop-up.
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
        JLabel ramImageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/Img/Ram.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule2 = new JLabel("   Biz: The Biz piece moves in a 3x2 L shape in any orientation. This is the only piece that can skip other pieces.");
        rule2.setFont(largerFont);
        JLabel bizImageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/Img/Biz.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule3 = new JLabel("   Tor: The Tor piece can move orthogonally only but can go any distance. It cannot skip over other pieces. After 2 turns, it transforms into the Xor piece.");
        rule3.setFont(largerFont);
        JLabel torImageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/Img/Tor.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule4 = new JLabel("   Xor: The Xor piece can move diagonally only but can go any distance. It cannot skip over other pieces. After 2 turns, it turns into the Tor piece.");
        rule4.setFont(largerFont);
        JLabel xorImageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/Img/Xor.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel rule5 = new JLabel("   Sau: The Sau piece can move only one step in any direction. The game ends when the Sau is captured by the other side.");
        rule5.setFont(largerFont);
        JLabel sauImageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/Img/Sau.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

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

    //Programmer: Muhammad Aqil
    //What is it: Function to display the initial state of the board.
    //Function: It will set the board according to the predetermined starting position of each pieces which are set in BoardModel.
    public void displayInitialBoard() {
        //Pulling the array of the board to be used to insert the panels and labels.
        String[][] boardArray = board.getBoard();

        //The for loop to insert the panel
        // The first loop will loop through the rows of the board. This loop will go down the row.
        for (String[] row : boardArray) {
            //the second loop is the one responsible to insert the panels and labels.
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

    //Programmer: Johan Ibrahim
    //What is it: Function to return the specific panel (square) of the board.
    //Function: This function is used when determining the piece that are standing on top of the square.
    public List<JPanel> getPanels() {
        return panels;
    }

    //Programmer: Johan Ibrahim
    //What is it: Function to insert images into the panel.
    //Function: This function is called when a move is done and the image for the piece needs to be inserted into the new panel.
    public void getPieceImages(String cell, JLabel label) {
        ImageIcon image = null;
        switch (cell) {
            case "R":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Ram.png"));
                break;
            case "B":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Biz.png"));
                break;
            case "T":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Tor.png"));
                break;
            case "S":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Sau.png"));
                break;
            case "X":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Xor.png"));
                break;
            case "ER":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/RamBlue.png"));
                break;
            case "EB":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/BizBlue.png"));
                break;
            case "ET":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/TorBlue.png"));
                break;
            case "ES":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/SauBlue.png"));
                break;
            case "EX":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/XorBlue.png"));
                break;
            case "EFR":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/RamBlue_Flipped.png"));
                break;
            case "FR":
                label.setText("");
                image = new ImageIcon(getClass().getResource("/Img/Ram_Flipped.png"));
                break;
            default:
                label.setIcon(null); // no image for empty cell
                break;
        }
        if (image != null) {
            Image img = image.getImage();
            Image newImage = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImage);
            label.setIcon(newIcon);
        } else {
            label.setIcon(null);
        }
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it: Function to update the board.
    //Function: This function is called at the end of every move action to update the board with the latest pieces position
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