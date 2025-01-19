package Model;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BoardModel {
    private String[][] board;
    private boolean isBlueTurn = true;
    private int turnCounter = 0;

    public BoardModel() {
        board = new String[8][5];
        setInitialPieces();
    }

    public void setInitialPieces() {
        board[0][0] = "T";
        board[0][1] = "B";
        board[0][2] = "S";
        board[0][3] = "B";
        board[0][4] = "X";

        board[1][0] = "R";
        board[1][1] = "R";
        board[1][2] = "R";
        board[1][3] = "R";
        board[1][4] = "R";

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "";
            }
        }

        board[6][0] = "ER";
        board[6][1] = "ER";
        board[6][2] = "ER";
        board[6][3] = "ER";
        board[6][4] = "ER";

        board[7][0] = "EX";
        board[7][1] = "EB";
        board[7][2] = "ES";
        board[7][3] = "EB";
        board[7][4] = "ET";
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBlueTurn(boolean isBlueTurn) {
        this.isBlueTurn = isBlueTurn;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public void movePiece(int oldRow, int oldCol, int row, int col) {
        if (!canMoveThisTurn(board[oldRow][oldCol])) {
            return;
        }

        if ("S".equals(board[row][col]) || "ES".equals(board[row][col])) {
            endGame();
            return;
        }

        if (board[oldRow][oldCol] != null && !board[oldRow][oldCol].isEmpty()) {
            board[row][col] = board[oldRow][oldCol];
            board[oldRow][oldCol] = "";

            rotateBoard();
            turnCounter++;
            if (turnCounter % 2 == 0) {
                transformPieces();
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
        isBlueTurn = !isBlueTurn;
    }

    private void endGame() {
        JOptionPane.showMessageDialog(null, "The Sau piece has been captured. Game over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
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

    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    writer.write(board[i][j].isEmpty() ? "#" : board[i][j]);
                    if (j < board[i].length - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
            writer.write("Turn: " + (isBlueTurn ? "Blue" : "Red"));
            writer.newLine();
            writer.write("Turn Counter: " + turnCounter);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
