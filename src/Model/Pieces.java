package Model;

public abstract class Pieces {
    private int row, col;
    public BoardModel board;

    //Programmer: Johan Ibrahim
    //What is it:
    //Function:
    public Pieces(int row, int col, BoardModel board) {
        this.row = row;
        this.col = col;
        this.board = board;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it: move function for the pieces
    //Function: moves piece to new position if not occupied by teammate
    public void Move(int newRow, int newCol) {
        if(!checkForTeam(newRow, newCol)) {
            board.movePiece(getRow(), getCol(), newRow, newCol);
            this.row = newRow;
            this.col = newCol;
        }
    }

    //Programmer: Muhammad Aqil
    //What is it: check for the colour of the piece
    //Function: this function is used to ensure that a piece cannot kill its ally.
    private boolean checkForTeam(int newRow, int newCol) {
        //get the selected piece and the piece on the target
        String team = board.getBoard()[getRow()][getCol()];
        String target = board.getBoard()[newRow][newCol];

        //check for empty cell in which case the move will always be valid
        if(target.equals("")) {
            return false;
        }

        //check if the pieces is on the same team
        boolean isTeam = team.startsWith("E");
        boolean isTarget = target.startsWith("E");

        if(isTeam == isTarget) {
            return true;
        }

        return false;
    }

    public abstract boolean isValid(int newRow, int newCol);
}
