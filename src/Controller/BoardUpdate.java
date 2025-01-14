package Controller;

import Model.Board;
import View.ChessBoardDisplay;

import java.util.Map;

public class BoardUpdate {
    Board board;
    ChessBoardDisplay render;

    public BoardUpdate(Board board, ChessBoardDisplay chessBoardDisplay) {
        this.board = board;
        this.render = chessBoardDisplay;
    }

    //add the pieces into the board
    public void AddPiece(String position, String piece) {
        board.addPiece(position, piece);
    }

    //remove piece into the board
    public void RemovePiece(String position, String piece) {
        board.RemovePiece(position);
    }

    //Display the board
    public void DisplayMainBoard() {
        Map<String, String> pieces = board.getMap();
        render.displayChessBoard(pieces, 5, 8);
    }

}
