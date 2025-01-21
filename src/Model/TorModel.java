package Model;

//move set
// can move orthogonally any distance
// cannot skip over other pieces
// turns to Xor after 2 turns

//Programmer: Johan Ibrahim
//What is it: Constructor to  initialize position and board for Tor Piece
//Function:
public class TorModel extends Pieces {
    public TorModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    // Override move function to check if the move is valid
    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    // Checks if the Tor piece's move is valid
    @Override
    public boolean isValid(int newRow, int newCol) {
        int rowDiff = Math.abs(this.getRow() - newRow);
        int colDiff = Math.abs(this.getCol() - newCol);

        // Valid move if moving orthogonally
        return (rowDiff == 0 || colDiff == 0) && (rowDiff + colDiff > 0);
    }
}
