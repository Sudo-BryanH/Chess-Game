package model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Square square, String side, Board board) {
        super(square, side, board);


    }

    @Override
    public List<Square> getMovableSquares() {

        List<Square> movableSquares = new ArrayList<>();
        Square nextSq;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                nextSq = board.searchSquare(this.getSquare().getColumn() + i,
                        this.getSquare().getRow() + j);
                if (nextSq == null || (nextSq.getOccupied() && nextSq.getPiece().getSide().equals(this.getSide()))) {

                } else {
                    movableSquares.add(nextSq);
                }

            }
        }

        return movableSquares;
    }
}
