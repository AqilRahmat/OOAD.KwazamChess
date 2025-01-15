package Model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private String[][] board;

    public Board() {
        board = new String[8][5];
        setInitialPieces();
    }

    public void setInitialPieces() {
        //starting from the top
        //1st row
        board[0][0] = "X";
        board[0][1] = "B";
        board[0][2] = "S";
        board[0][3] = "B";
        board[0][4] = "X";

        //2nd row
        board[1][0] = "R";
        board[1][1] = "R";
        board[1][2] = "R";
        board[1][3] = "R";
        board[1][4] = "R";

        //empty spaces in the middle
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(i != 0 && i != 1 && i !=6 && i != 7) {
                    board[i][j] = "#";
                }
            }
        }

        //bottom two row
        //7th row
        board[6][0] = "R";
        board[6][1] = "R";
        board[6][2] = "R";
        board[6][3] = "R";
        board[6][4] = "R";

        //8th row
        board[7][0] = "S";
        board[7][1] = "B";
        board[7][2] = "S";
        board[7][3] = "B";
        board[7][4] = "X";
    }

    public String[][] getBoard() {
        return board;
    }
}
