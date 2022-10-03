package model;

import java.util.List;

public class Knight extends Piece {

    public Knight(Square square, String side, Board board) {
        super(square, side, board);

    }

    @Override
    public List<Square> getMovableSquares() {
        return null;
    }
}
