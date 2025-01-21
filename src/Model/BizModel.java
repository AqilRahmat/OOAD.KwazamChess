package Model;

// BizPiece Functionality
// Move in an L Shape.
// Can skip over other pieces

//Programmer: Muhammad Aqil
public class BizModel extends Pieces {
    private int row, col;

    //constructor for the piece
    public BizModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    //calling the Move function inside the Pieces class if the move is valid
    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        //make sure the piece can only move in an L pattern;
        int rowDiff = Math.abs(this.getRow() - newRow);
        int colDiff = Math.abs(this.getCol() - newCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
