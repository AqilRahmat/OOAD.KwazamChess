package Model;

//Programmer: Muhammad Aqil
//What is it: the movement function for the ram
//Function: it will move if the movement is valid.
public class RamModel extends Pieces {
    public RamModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    //call the move function on the pieces class when the movement is valid
    @Override
    public void Move(int newRow, int newCol) {
        if (isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    //ensure that the ram can only move 1 square ahead of them.
    @Override
    public boolean isValid(int newRow, int newCol) {
        return (newRow == this.getRow() - 1) && (newCol == this.getCol());
    }
}