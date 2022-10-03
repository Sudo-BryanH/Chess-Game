package model;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(Square square, String side, Board board) {
        super(square, side, board);

    }

    @Override
    public List<Square> getMovableSquares() {
        return null;
    }
}
