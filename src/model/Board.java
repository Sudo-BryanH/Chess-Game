package model;

import java.util.List;

public class Board {

    List<Square> board;


    // EFFECTS: creates new Board with new squares
    public Board(List<Square> board) {
        this.board = board;

    }

    // EFFECTS: looks for a square given its column and row
    public Square searchSquare(int column, int row) {
        for (Square s : board) {
            if (s.getColumn() == column && s.getRow() == row) {
                return s;
            }
        }

        return null;
    }
}

