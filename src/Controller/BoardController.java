package Controller;

import Model.BizModel;
import Model.BoardModel;
import Model.Pieces;
import Model.RamModel;
import Model.TorModel;
import View.BoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class BoardController implements MouseListener {
    BoardModel board;
    BoardView boardview;
    List<JPanel> panels;
    JPanel clickedPanel;
    JPanel selectedPanel;
    int selectedRow, selectedCol;
    String selectedPiece;

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
            case "R":
                return new RamModel(row, col, board);
            case "B":
                return new BizModel(row, col, board);
            case "S":
                return null;
            case "X":
                return null;
            case "T":
                return new TorModel(row, col, board);
            case "ER":
                return new RamModel(row, col, board);
            case "EB":
                return new BizModel(row, col, board);
            case "ES":
                return null;
            case "EX":
                return null;
            case "ET":
                return new TorModel(row, col, board);
            default:
                return null;
        }
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
