package Controller;

import Model.Board;
import View.BoardView;

import java.util.Map;

public class BoardController {
    Board board;
    BoardView boardview;

    public BoardController(Board board, BoardView chessBoardDisplay) {
        this.board = board;
        this.boardview = chessBoardDisplay;
    }
}
