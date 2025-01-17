package Model;

public class RamBlueModel extends Pieces {
    private boolean moveUp = true; //bottom to top

    public RamBlueModel(int row, int col, BoardModel board) {super(row, col, board);}

    @Override
    public void Move(int newRow, int newCol) {
        if (isValid(newRow, newCol)) {
            board.movePiece(getRow(), getCol(), newRow, newCol);
            super.Move(newRow, newCol);

            // turn around at the end
            if (moveUp && newRow == 0) {
                moveUp = false;
            } else if (!moveUp && newRow == board.getBoard().length - 1) {
                moveUp = true;
            }
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        if (newCol != getCol()) {
            return false;
        }

        int step = moveUp ? -1 : 1;
        return (newRow - getRow()) == step;
    }
}
