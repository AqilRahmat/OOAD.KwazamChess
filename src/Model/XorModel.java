package Model;

//move set
// can move diagonally any distance
// after 2 turn turn to Tor
// cannot skip other pieces

//Programmer: Johan Ibrahim
//What is it: Constructor to  initialize position and board for Xor Piece
//Function:
public class XorModel extends Pieces {
    public XorModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    // Override move function to check if the move is valid
    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    // Check if the Xor piece's move is valid
    @Override
    public boolean isValid(int newRow, int newCol) {
        int rowDiff = Math.abs(this.getRow() - newRow);
        int colDiff = Math.abs(this.getCol() - newCol);

        // Valid move if moving diagonally
        return (rowDiff == colDiff) && (rowDiff + colDiff > 0);
    }

}