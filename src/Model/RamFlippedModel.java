package Model;

//Programmer: Wan Muhammad Ilhan
//What is it: the backwards movement function for the ram
//Function: it will move backwards (down) once it reached the end of the board.
public class RamFlippedModel extends Pieces {
    public RamFlippedModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    // call the move function on the pieces class when the movement is valid
    @Override
    public void Move(int newRow, int newCol) {
        if (isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    // ensure the ram can only move 1 square behind them
    @Override
    public boolean isValid(int newRow, int newCol) {
        return (newRow == this.getRow() + 1) && (newCol == this.getCol());
    }
}