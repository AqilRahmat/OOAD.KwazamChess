package Model;

//move set
// can move orthogonally any distance
// cannot skip over other pieces
// turns to Xor after 2 turns

public class TorModel extends Pieces {
    public TorModel(int row, int col, BoardModel board) {
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

        return (rowDiff == 0 || colDiff == 0) && (rowDiff + colDiff > 0);
    }
}
