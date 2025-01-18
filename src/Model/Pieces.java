package Model;

public abstract class Pieces {
    private int row, col;
    public BoardModel board;

    public Pieces(int row, int col, BoardModel board) {
        this.row = row;
        this.col = col;
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void Move(int newRow, int newCol) {
        if(!checkForTeam(newRow, newCol)) {
            board.movePiece(getRow(), getCol(), newRow, newCol);
            this.row = newRow;
            this.col = newCol;
        }
    }

    private boolean checkForTeam(int newRow, int newCol) {
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
