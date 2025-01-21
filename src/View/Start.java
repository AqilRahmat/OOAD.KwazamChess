package View;

import Controller.BoardController;
import Model.BoardModel;

public class Start {
    public static void main(String[] args) {

        //Programmer: Muhammad Aqil
        //What is it: The initialization of the program
        //Function: basically starts the whole program by initializing the BoardModel, BoardView and BoardController.

        //calls both the BoardModel and BoardView object to be used inside the BoardController where the main functionality of the
        // program are coded.
        BoardModel board = new BoardModel();
        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);
    }
}