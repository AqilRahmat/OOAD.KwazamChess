package Model;

// BizPiece Functionality
// Move in an L Shape.
// Can skip over other pieces
public class BizModel extends Pieces {
    private int row, col;
    BoardModel board;

    public BizModel(int row, int col, BoardModel board) {
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
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
