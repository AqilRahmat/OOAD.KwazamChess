package Controller;

import Model.*;
import View.BoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController implements MouseListener {
    BoardModel board;
    BoardView boardview;
    List<JPanel> panels;
    JPanel clickedPanel;
    JPanel selectedPanel;
    int selectedRow, selectedCol;
    public static List<String> moves = new ArrayList<>();

    //Programmer: Johan Ibrahim
    //What is it: Constructor for the BoardController
    //Function: to initialize the BoardController class.
    public BoardController(BoardModel board, BoardView chessBoardDisplay) {
        this.board = board;
        this.boardview = chessBoardDisplay;
        this.panels = boardview.getPanels();

        //Add mouse listener to all panels
        for (JPanel panel : panels) {
            panel.addMouseListener(this);
        }
    }

    //Programmer: Muhammad Aqil
    //What is it: event that will happen when the mouse is clicked
    //Function: This function will detect 2 clicks of the mouse. The first will set the initial square that the player wants to move from. The second will set the square the player want to move to.
    @Override
    public void mouseClicked(MouseEvent e) {

        //get the panel that are clicked.
        // clickedpanelx will give the row and clickedpanely will give the column
        clickedPanel = (JPanel) e.getSource();
        int clickedPanelX = panels.indexOf(clickedPanel) / 5;
        int clickedPanelY = panels.indexOf(clickedPanel) % 5;

        //first it will check if the panel that is selected is null
        if (selectedPanel == null) {
            //it will select the piece that is moving from the board and set the colour of the square to yellow.
            String piecetomove = board.getBoard()[clickedPanelX][clickedPanelY];
            if (!piecetomove.isEmpty()) {
                selectedPanel = clickedPanel;
                selectedRow = clickedPanelX;
                selectedCol = clickedPanelY;
                clickedPanel.setBackground(Color.YELLOW);
            }
        } else {
            //it will select the piece that is moving and create it at the target board. It will also record the movement in the text file.
            Pieces piece = createPiece(selectedRow, selectedCol);
            if (piece != null && piece.isValid(clickedPanelX, clickedPanelY)) {
                String move = String.format("%s moved from (%d, %d) to (%d, %d)",
                        board.getBoard()[selectedRow][selectedCol], selectedRow, selectedCol, clickedPanelX, clickedPanelY);
                moves.add(move);

                piece.Move(clickedPanelX, clickedPanelY);
                boardview.UpdateBoard();
            }

            selectedPanel.setBackground(null);
            selectedPanel = null;
        }
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it: function to createPiece
    //Function: this function is used to create the pieces on the board. It will take the location of the square and return Piece that corresponds to the label on the panel.
    public Pieces createPiece(int row, int col) {
        String piece = board.getBoard()[row][col].trim();
        switch (piece) {
            case "R": case "ER":
                return new RamModel(row, col, board);
            case "FR": case "EFR":
                return new RamFlippedModel(row,col,board);
            case "B": case "EB":
                return new BizModel(row, col, board);
            case "S": case "ES":
                return new SauModel(row, col, board);
            case "X": case "EX":
                return new XorModel(row, col, board);
            case "T": case "ET":
                return new TorModel(row, col, board);
            default:
                return null;
        }
    }

    //Programmer: Muhammad Aqil
    //What is it: New Game Function for button
    //Function: this is the function to start a new game when player clicked the "New Game" button on top of the window.
    public static void newGame(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //it will dispose the current game board and call a new one.
                frame.dispose();
                BoardModel board = new BoardModel();
                BoardView boardview = new BoardView(board);
                BoardController boardcontroller = new BoardController(board, boardview);
            }
        });
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it: Save Game Function for button
    //Function: save the current position of the pieces whenever player clicked the button
    public static void saveGame(String file, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    for (String move : moves) {
                        fileWriter.write(move + "\n");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Programmer: Johan Ibrahim
    //What is it: function to load the previous game.
    //Function: When user click the button, it will read the file where the final position of the pieces in the previous game are saved and create a new square with that position.
    public static void loadGame(String file, JButton button, BoardModel board, BoardView boardView) {
        button.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // check if the file is empty
                if (reader.readLine() == null) {
                    JOptionPane.showMessageDialog(null, "Error loading game: The save file is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // reset reader
                reader.close();
                BufferedReader newReader = new BufferedReader(new FileReader(file));

                String line;
                int row = 0;
                // read file and update board state
                while ((line = newReader.readLine()) != null) {
                    if (line.equals("Turn: Blue")) {
                        board.setBlueTurn(true);
                    } else if (line.equals("Turn: Red")) {
                        board.setBlueTurn(false);
                    } else if (line.startsWith("Turn Counter:")) {
                        board.setTurnCounter(Integer.parseInt(line.split(":")[1].trim()));
                    } else {
                        String[] cells = line.split(" ");
                        for (int col = 0; col < cells.length; col++) {
                            board.getBoard()[row][col] = cells[col].equals("#") ? "" : cells[col];
                        }
                        row++;
                    }
                }

                newReader.close();
                boardView.UpdateBoard();


            // display success or fail message
                JOptionPane.showMessageDialog(null, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading game: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Extra empty functions (program will not work if not here) (part of MouseListener)
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
