package Model;

// moveset
// 1 step any direction
// game ends when piece is captured

//Programmer: Muhammad Aqil
//What is it:
//Function:
public class SauModel extends Pieces {
    public SauModel(int row, int col, BoardModel board) {
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

        return (rowDiff <= 1 && colDiff <= 1) && (rowDiff + colDiff > 0);
    }
}
