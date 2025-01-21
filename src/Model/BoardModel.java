package Model;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BoardModel {
    private String[][] board;
    private boolean isBlueTurn = true;
    private int turnCounter = 0;

    //Programmer: Johan Ibrahim
    //What is it:
    //Function:
    public BoardModel() {
        board = new String[8][5];
        setInitialPieces();
    }

    //Programmer: Muhammad Aqil
    //What is it: The function to set the default board position
    //Function: this function will set the default position of each piece on the board.
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

        //all empty spaces are filled with ""
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

    //Programmer: Muhammad Aqil, Wan Muhammad Ilhan
    //What is it: move piece function
    //Function: this is the function that will control the movement on the board. It will go through a series of test
    // before moving a piece to ensure that the move is truly valid.
    public void movePiece(int oldRow, int oldCol, int row, int col) {
        //check for the colour of the piece, if it has moved on the previous turn. It cannot move in this turn.
        if (!canMoveThisTurn(board[oldRow][oldCol])) {
            return;
        }


        // Check for sau pieces on the board. If there is only 1 piece left, it will end the game.
        if (board[row][col].trim().equals("S")){
            endGameBlue();
            return;
        }

        // Sau for Blue captured, Red wins
        if (board[row][col].trim().equals("ES")){
            endGameRed();
            return;
        }

        //remove the old board
        board[row][col] = board[oldRow][oldCol];
        board[oldRow][oldCol] = "";


        //the function that will check if a ram piece has reached the end of the board.
        //if the piece's label matched any of the specified below, it will check the row and transform it accordingly.
        if (checkEndRow(row)) {
            if (board[row][col].equals("R") || board[row][col].equals("ER") || board[row][col].equals("FR") || board[row][col].equals("FER")) {
                transformRam();
                board[row][col] = board[row][col];
            }
        }

        //Rotate the board at the end of each turn
        rotateBoard();

        //plus the turn counter and check if the counter has reach 2, if it has, transform the Tor pieces into Xor piece and vice versa.
        turnCounter++;
        if (turnCounter % 2 == 0) {
            transformPieces();  // xor and tor transformation
        }

        //switch the turn
        switchTurn();
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it:
    //Function:
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

    //Programmer: Johan Ibrahim
    //What is it:
    //Function:
    private void endGameRed() {
        JOptionPane.showMessageDialog(null, "The Sau piece has been captured. Red wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void endGameBlue() {
        JOptionPane.showMessageDialog(null, "The Sau piece has been captured. Blue wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    //Programmer: Muhammad Aqil
    //What is it: rotate the board every turn
    //Function: everytime a player move, it will rotate the board. it does this by going through the board and swapping the places of each pieces.
    public void rotateBoard() {
        String[][] rotatedBoard = new String[8][5];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                rotatedBoard[7 - i][4 - j] = board[i][j];
            }
        }

        board = rotatedBoard;
    }

    //Programmer: Wan Muhammad Ilhan
    //What is it:
    //Function:
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

    //Programmer: Wan Muhammad ilhan, Muhammad Aqil
    //What is it: check the row for ram
    //Function: THis function is used everytime a ram moves to check if it has reach row 0 or 7.
    private boolean checkEndRow(int row) {
        if(row == 0) {
            return true;
        } else if(row == 7) {
            return true;
        }

        return false;
    }

    //Programmer: Wan Muhammad Ilhan, Muhammad Aqil
    //What is it: the function to transform the ram piece.
    //Function: This function is used to transform the ram piece into the flipped version whenever the piece reach the end of the board.
    //it does this by going through the board and seeing if any of the ram pieces denoted by "R" or "ER" has reached the end of the board
    //in which case it will be transformed to flipped version if they reach row 0 or the normal version if they reach row 7.
    private void transformRam() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && !board[i][j].isEmpty()) {
                    if (board[i][j].equals("R") && i == 0) {
                        board[i][j] = "FR";  // Red Ram goes downwards after reaching top
                    } else if (board[i][j].equals("ER") && i == 7) {
                        board[i][j] = "EFR"; // Blue Ram goes downwards after reaching top
                    } else if (board[i][j].equals("FR") && i == 7) {
                        board[i][j] = "R";   // Red Ram goes upwards after reaching bottom
                    } else if (board[i][j].equals("EFR") && i == 0) {
                        board[i][j] = "ER";  // Blue Ram goes upwards after reaching bottom
                    }
                }
            }
        }
    }

    //Programmer: Johan Ibrahim
    //What is it:
    //Function:
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
