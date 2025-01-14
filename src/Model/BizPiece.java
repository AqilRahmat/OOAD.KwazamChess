package Model;

import Model.Pieces;

// BizPiece Functionality
// Move in an L Shape.
// Can skip over other pieces
public class BizPiece extends Model.Pieces {
    public BizPiece(int x, int y, String label) {
        super(x, y, label);
    }

    public void Move(int x, int y, int newX, int newY) {
        isValid(this.getX(), this.getY(), newX, newY);
    }

    public boolean isValid(int x, int y, int newX, int newY) {
        if(newX == x && newY == y) {
            return false;
        }

        int rowDiff = newX - x;
        int colDiff = newY - y;

        //check for L pattern in movement
        boolean isValidLMove = (rowDiff == 2 && colDiff == 3 || rowDiff == 1 && colDiff == 2);
        if(!isValidLMove) {
            return false;
        }

        return true;
    }
}
