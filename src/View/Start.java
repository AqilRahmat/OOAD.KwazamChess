package View;

import Model.Board;

public class Start {
    public static void main(String[] args) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
    }
}