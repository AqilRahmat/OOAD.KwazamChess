package Model;

//move set
// can move orthogonally any distance
// cannot skip over other pieces
// turns to Xor after 2 turns

public class TorModel extends Pieces {
    private int row, col;
    BoardModel board;

    public TorModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        if(board == null) {
            return false;
        }

        int currentRow = this.getRow();
        int currentCol = this.getCol();

        //check if the move is orthogonal
        boolean isOrthogonal = (currentRow == newRow && currentCol != newCol) || (currentRow != newRow && currentCol == newCol);

        if(!isOrthogonal) {
            return false;
        }

        //check if path is clear
        if(!checkPathClear(currentRow, currentCol, newRow, newCol)) {
            return false;
        }

        String newSquare = board.getBoard()[newRow][newCol];
        return newSquare.equals("") || CheckForPiece(newSquare);
    }

    public boolean checkPathClear(int StartRow, int StartCol, int EndRow, int EndCol) {
        //set direction
        int row = Integer.compare(EndRow, StartRow);
        int col = Integer.compare(EndCol, StartCol);

        int currentRow = StartRow + row;
        int currentCol = StartCol + col;

        while (currentRow != EndRow || currentCol != EndCol) {
            if(!board.getBoard()[currentRow][currentCol].equals("")) {
                return false;
            }
            currentRow = currentRow + row;
            currentCol = currentCol + col;
        }

        return true;
    }

    private boolean CheckForPiece(String newSquare) {
        return !newSquare.equals("") && board.getBoard()[row][col].equals(newSquare);
    }

    @Override
    public void Move(int newRow, int newCol) {
        if(isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
        }
    }
}
