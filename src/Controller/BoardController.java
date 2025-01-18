package Controller;

import Model.*;
import View.BoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController implements MouseListener {
    BoardModel board;
    BoardView boardview;
    List<JPanel> panels;
    JPanel clickedPanel;
    JPanel selectedPanel;
    int selectedRow, selectedCol;
    public static List<String> movesHistory = new ArrayList<>();

    public BoardController(BoardModel board, BoardView chessBoardDisplay) {
        this.board = board;
        this.boardview = chessBoardDisplay;
        this.panels = boardview.getPanels();

        for (JPanel panel : panels) {
            panel.addMouseListener(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickedPanel = (JPanel) e.getSource();
        int clickedPanelX = panels.indexOf(clickedPanel) / 5;
        int clickedPanelY = panels.indexOf(clickedPanel) % 5;

        if(selectedPanel == null) {
            //function to highlight the box clicked
            String piecetomove = board.getBoard()[clickedPanelX][clickedPanelY];
            if(!piecetomove.equals("#")) {
                selectedPanel = clickedPanel;
                selectedRow = clickedPanelX;
                selectedCol = clickedPanelY;
                clickedPanel.setBackground(Color.YELLOW);
            }
        } else {
            //second click
            Pieces piece = createPiece(selectedRow, selectedCol);
            if(piece != null && piece.isValid(clickedPanelX, clickedPanelY)) {
                //write to file
                String move = String.format("%s moved from (%d, %d) to (%d, %d)", board.getBoard()[selectedRow][selectedCol], selectedRow, selectedCol, clickedPanelX, clickedPanelY);
                movesHistory.add(move);

                piece.Move(clickedPanelX, clickedPanelY);
                boardview.UpdateBoard();
            }

            //clear selection
            selectedPanel.setBackground(null);
            selectedPanel = null;
        }
    }

    //function to create piece (without it whenever a piece is moved it wont show up on the new square)
    public Pieces createPiece(int row, int col) {
        String piece = board.getBoard()[row][col].trim();
        switch(piece) {
            case "R": case "ER":
                return new RamModel(row, col, board);
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

    public static void newGame(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BoardModel board = new BoardModel();
                BoardView boardview = new BoardView(board);
                BoardController boardcontroller = new BoardController(board, boardview);
            }
        });
    }

    public static void saveGame(String file, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    for(String move : movesHistory) {
                        fileWriter.write(move + "\n");
                    }
                    JOptionPane.showMessageDialog(null, "Game Saved!1");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
