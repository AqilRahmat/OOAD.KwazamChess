package Model;

public abstract class Pieces {
    private int newX, x;
    private int newY, y;
    private String label;
    Board board;

    public Pieces(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public String getPiece() {
        return label;
    }

    //get the location of the piece
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    //set the new location when moved
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public abstract void Move(int x, int y, int newX, int newY);
    public abstract boolean isValid(int x, int y, int newX, int newY);
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