package Model;

// moveset
// 1 step any direction
// game ends when piece is captured

//Programmer: Muhammad Aqil
public class SauModel extends Pieces {
    //constructor
    public SauModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    //call the move function inside the pieces function if the move is valid
    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }

    //make sure the piece can only move 1 step at a time to any direction
    @Override
    public boolean isValid(int newRow, int newCol) {
        int rowDiff = Math.abs(this.getRow() - newRow);
        int colDiff = Math.abs(this.getCol() - newCol);

        return (rowDiff <= 1 && colDiff <= 1) && (rowDiff + colDiff > 0);
    }
}
