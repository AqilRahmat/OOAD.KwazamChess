package Model;

//move set
// can move diagonally any distance
// after 2 turn turn to Tor
// cannot skip other pieces

public class XorModel extends Pieces {
    public XorModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        int rowDiff = Math.abs(this.getRow() - newRow);
        int colDiff = Math.abs(this.getCol() - newCol);

        return (rowDiff == colDiff) && (rowDiff + colDiff > 0);
    }

}