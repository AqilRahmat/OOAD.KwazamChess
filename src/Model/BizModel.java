package Model;

// BizPiece Functionality
// Move in an L Shape.
// Can skip over other pieces
public class BizModel extends PiecesAbstractModel {
    public BizModel(int row, int col,String label, BoardModel board) {
        super(row, col, label, board);
    }

    @Override
    public void Move(int x, int y, int newX, int newY) {

    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        int rowDiff = this.getRow() - newRow;
        int colDiff = this.getCol() - newCol;
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
