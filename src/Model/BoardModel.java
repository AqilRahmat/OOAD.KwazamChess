package Model;

import javax.swing.JOptionPane;

public class BoardModel {
    private String[][] board;
    private boolean isBlueTurn = true; // blue team moves first
    private int turnCounter = 0; // track number of turns
    private String lastMove;

    public BoardModel() {
        board = new String[8][5];
        setInitialPieces();
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
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "";
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
        System.out.println(isBlueTurn ? "Blue's turn" : "Red's turn"); // print turn

        if (!canMoveThisTurn(board[oldRow][oldCol])) {
            System.out.println("It's not this piece's turn to move.");
            return;
        }

        if ("S".equals(board[row][col]) || "ES".equals(board[row][col])) {
            endGame(board[row][col]); // game-over
            return;
        }

        if (board[oldRow][oldCol] != null && !board[oldRow][oldCol].isEmpty()) {
            board[row][col] = board[oldRow][oldCol];
            board[oldRow][oldCol] = "";
            rotateBoard();
            turnCounter++;
            if (turnCounter % 2 == 0) {
                transformPieces(); // Xor and Tor transformation function
            }
            switchTurn();
        }
    }

    private boolean canMoveThisTurn(String pieceName) {
        if (pieceName == null || pieceName.isEmpty()) {
            return false;
        }

        boolean isBluePiece = pieceName.startsWith("E");
        return isBlueTurn == isBluePiece;
    }

    private void switchTurn() {
        isBlueTurn = !isBlueTurn; // toggle turn
    }

    private void endGame(String capturedPiece) {
        JOptionPane.showMessageDialog(null,
                "The Sau piece has been captured. Game over!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // terminate
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

    private void transformPieces() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && !board[i][j].isEmpty()) {
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
