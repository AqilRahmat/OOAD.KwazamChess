package Model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int column = 8;
    private int rows = 5;
    private Map<String, String> pieces;

    public Board() {
        pieces = new HashMap<>();
    }

    //Function to add pieces to the board
    public void addPiece(String position, String piece) {
        pieces.put(position, piece);
    }

    //remove piece from board when killed
    public void RemovePiece(String position) {
        pieces.remove(position);
    }

    //Get Piece at specific location to be used in kill function
    public String getPiece(String position) {
        return pieces.getOrDefault(position, "");
    }

    //return updated map
    public Map<String, String> getMap() {
        return new HashMap<>(pieces);
    }
}
