package model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Square square, String side, Board board) {
        super(square, side, board);

    }

    @Override
    public List<Square> getMovableSquares() {
        List<List<Square>> sets = new ArrayList<>();
        List<Square> movableSquares = new ArrayList<>();


        sets.add(moveStraight("V", "+"));
        sets.add(moveStraight("V", "-"));
        sets.add(moveStraight("H", "+"));
        sets.add(moveStraight("H", "-"));
        sets.add(moveDiagonal("+", "+"));
        sets.add(moveDiagonal("+", "-"));
        sets.add(moveDiagonal("-", "+"));
        sets.add(moveDiagonal("-", "-"));

        for (List<Square> l : sets) {
            for (Square s : l) {
                movableSquares.add(s);
            }
        }

        return movableSquares;
    }
}
