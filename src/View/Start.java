package View;

import Controller.BoardController;
import Model.BoardModel;

public class Start {
    public static void main(String[] args) {
        BoardModel board = new BoardModel();
        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);
    }
}