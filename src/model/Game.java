package model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Square> squares = new ArrayList<>(); //
    Board board;

    // EFFECTS: constructs new game by declaring a new board and new pieces
    public Game() {

        initiateBoard();
        initiatePieces();


    }

    // MODIFIES: this
    // EFFECTS: declares all 64 squares and the board
    private void initiateBoard() {
        Square s;

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                s = new Square(i, j);
                squares.add(s);
            }

        }

        board = new Board(squares);


    }



    // MODIFIES: this
    // EFFECtS: declares all 32 pieces
    private void initiatePieces() {

    }


}
