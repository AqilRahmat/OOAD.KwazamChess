package View;

import java.util.Map;

//TODO : add functionality to update game board with pieces
public class ChessBoardDisplay {
    public void displayChessBoard(Map<String, String> pieces, int rows, int cols) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
