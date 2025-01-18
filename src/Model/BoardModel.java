package Model;

public class BoardModel {
    private String[][] board;
    private int counter = 1; // Keeps track of turns
    private String lastmove;

    public BoardModel() {
        board = new String[8][5];
        setInitialPieces();
    }

    // Return counter for Xor and Tor piece transformation
    public int getCounter() {
        return counter;
    }

    public void setInitialPieces() {
        // Starting from the top
        // 1st row
        board[0][0] = "T";
        board[0][1] = "B";
        board[0][2] = "S";
        board[0][3] = "B";
        board[0][4] = "X";

        // 2nd row
        board[1][0] = "R";
        board[1][1] = "R";
        board[1][2] = "R";
        board[1][3] = "R";
        board[1][4] = "R";

        // Empty spaces in the middle
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i != 0 && i != 1 && i != 6 && i != 7) {
                    board[i][j] = "";
                }
            }
        }

        // Bottom two rows
        // 7th row
        board[6][0] = "ER";
        board[6][1] = "ER";
        board[6][2] = "ER";
        board[6][3] = "ER";
        board[6][4] = "ER";

        // 8th row
        board[7][0] = "EX";
        board[7][1] = "EB";
        board[7][2] = "ES";
        board[7][3] = "EB";
        board[7][4] = "ET";
    }

    public String[][] getBoard() {
        return board;
    }

    public void movePiece(int oldRow, int oldCol, int row, int col) {
        System.out.println(counter); // Print the turn counter for debugging

        // Check if the moving piece is the same as before
        if (!whomovelast(oldRow, oldCol, row, col)) {
            return;
        }

        if (board[oldRow][oldCol] != null) {
            board[row][col] = board[oldRow][oldCol];
            board[oldRow][oldCol] = "";

            rotateBoard();
        }

        counter++;

        if (counter % 2 == 0) {
            transformPieces(); // Transform pieces every 2 turns
        }
    }

    public void rotateBoard() {
        String[][] rotatedBoard = new String[8][5];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                rotatedBoard[7 - i][4 - j] = board[i][j];
            }
        }

        board = rotatedBoard;
    }

    public boolean whomovelast(int oldRow, int oldCol, int row, int col) {
        String currentmove = board[oldRow][oldCol];

        if (currentmove == null || currentmove.isEmpty()) {
            return false;
        }

        String currentTeam = currentmove.substring(0, 1);
        String lastTeam = (lastmove == null || lastmove.isEmpty()) ? null : lastmove.substring(0, 1);

        if (lastTeam != null && lastTeam.equals(currentTeam)) {
            return false;
        }

        lastmove = currentmove;
        return true;
    }

    // xor to tor, tor to xor for all pieces after 2 turns
    private void transformPieces() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && !board[i][j].isEmpty()) {
                    // check x and t pieces
                    if (board[i][j].equals("X")) {
                        board[i][j] = "T";
                    } else if (board[i][j].equals("T")) {
                        board[i][j] = "X";
                    } else if (board[i][j].equals("EX")) {
                        board[i][j] = "ET";
                    } else if (board[i][j].equals("ET")) {
                        board[i][j] = "EX";
                    }
                }
            }
        }
    }
}