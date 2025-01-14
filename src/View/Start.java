package View;

import Controller.BoardUpdate;
import Model.BizPiece;
import Model.Board;
import Model.Pieces;

public class Start {
    public static void main(String[] args) {
        Board board = new Board();
        ChessBoardDisplay chessBoardDisplay = new ChessBoardDisplay();
        BoardUpdate boardUpdate = new BoardUpdate(board, chessBoardDisplay);

        BizPiece piece1 = new BizPiece(1,2, "Biz");
        board.addPiece(String.valueOf(2), piece1.getPiece());

        boardUpdate.DisplayMainBoard();
    }
}