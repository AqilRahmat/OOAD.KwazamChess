package Model;

public class RamFlippedModel extends Pieces {
    public RamFlippedModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    @Override
    public void Move(int newRow, int newCol) {
        if (isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        return (newRow == this.getRow() + 1) && (newCol == this.getCol());
    }
}