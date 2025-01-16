package Model;

public abstract class PiecesAbstractModel {
    private int row, col;
    private String label;
    private BoardModel board;

    public PiecesAbstractModel(int row, int col, String label, BoardModel board) {
        this.row = row;
        this.col = col;
        this.label = label;
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getLabel() {
        return label;
    }

    public abstract void Move(int x, int y, int newX, int newY);
    public abstract boolean isValid(int newRow, int newCol);
}

// RamPiece functionality
// Can only move one forward one step at a time
// Make a uturn when it reach the end of the board
// Cannot skip other pieces


// Sau Functionality
// Can only move 1 step in any direction
// Game ends when Sau is captured

// TorPiece Functionality
// Move orthogonally any ditance
// Cannot skip over other pieces
// after 2 turn, becomes Xor Piece

// XorPiece FUNCTIONS
// Move Diagonally any distance
// Cannot skip other pieces
// after 2 turns becomes Tor piece