package Model;

import javax.swing.*;

public abstract class Pieces {
    private int row, col;
    private BoardModel board;

    public Pieces(int row, int col, BoardModel board) {
        this.row = row;
        this.col = col;
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void Move(int newRow, int newCol) {
        if(!board.getBoard()[newRow][newCol].equals("")) {
            return;
        }
        if(isValid(newRow, newCol)) {
            board.movePiece(row, col, newRow, newCol);
            this.row = newRow;
            this.col = newCol;
            System.out.println("Moved from :" + row + col + " To " + newRow + newCol);
        }
    }

    public abstract boolean isValid(int newRow, int newCol);
}
