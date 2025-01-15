package View;

import Model.Board;

import java.util.Map;

//TODO : add functionality to update game board with pieces
public class BoardView {
    private Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    public void displayBoard() {
        String[][] boardArray = board.getBoard();

        for(String[] row : boardArray) {
            for(String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
