package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected String side; // either "black" or "white"
    protected boolean eaten; // eaten if it has no square to be in
    protected Square square;
    protected boolean selected;
    protected Board board;


    // EFFECTS: sets a piece's square, not eaten, not selected and its side
    public Piece(Square square, String side, Board board) {
        this.square = square;
        square.setPiece(this);
        this.side = side;
        this.eaten = false;
        this.selected = false;
        this.board = board;

    }

    // EFFECTS: returns the Square the piece is on
    public Square getSquare() {
        return this.square;
    }

    // EFFECTS: returns whether eaten or not
    public boolean getEaten() {
        return square == null;
    }

    public String getSide() {
        return side;
    }


    // MODIFIES: this, square at (c, row)
    // EFFECTS: If the square isn't the current square, removes this piece from the current square.
    //  Then, moves this piece to coordinates (c, row) and sets the square to occupied.
    public void moveToSquare(Square square) {
        if (getSquare() == null || !getSquare().equals(square)) {
            removeSquare();
            this.square = square;
            square.setPiece(this);
        }
    }


    // MODIFIES: this, square at (c, row)
    // EFFECTS: If square is not null, removes the square from this.square
    // and removes this piece as the square's occupier. Will stop prodcing the list if it runs into another piece in
    // the path; if same side, does not inlcude that square, if not, includes that square
    public void removeSquare() {
        Square sq = this.square;
        if (this.square != null) {
            this.square = null;
            sq.removePiece();
        }


    }

    // EFFECTS: returns a list of squares that this can possibly move to
    public abstract List<Square> getMovableSquares();

    // TODO: showDiagonal<LR><UD>; show<UD>; show<LR>


    // EFFECTS: returns a list of squares that this can possibly move to by moving diagonally in a given direction
    // vertically (+/-) and horizontally (+/-). Will stop prodcing the list if it runs into another piece in the path;
    // if same side, does not inlcude that square, if not, includes that square
    public List<Square> moveDiagonal(String vert, String hor) {
        List<Square> diagonal = new ArrayList<>();
        Square nextSq;
        int vertDir; //column
        int horDir; //row


        for (int i = 1; i < 9; i++) {
            vertDir = getVertDir(vert, i);
            horDir = getHorDir(hor, i);

            nextSq = board.searchSquare(vertDir, horDir);

            if (checkBoundsAndAdd(diagonal, nextSq)) return diagonal;
        }

        return diagonal;
    }

    // EFFECTS: returns a list of squares that this can possibly move to by moving straight in a designated
    // direction vertically (V) or horizontally (H) with "+"/"-". Will stop prodcing the list if it runs
    // into another piece in the path; if same side, does not inlcude that square, if not, includes that square
    public List<Square> moveStraight(String vh, String dir) {
        List<Square> straights = new ArrayList<>();

        if (vh == "V") {
            straights = vertStraights(dir);
        } else {
            straights = horStraights(dir);
        }

        return straights;

    }

    private List<Square> horStraights(String dir) {

        List<Square> horizontals = new ArrayList<>();
        Square nextSq;

        for (int i = 1; i < 9; i++) {
            if (dir == "-") {
                nextSq = board.searchSquare(square.getColumn() - i, square.getRow());
            } else {
                nextSq = board.searchSquare(square.getColumn() + i, square.getRow());
            }

            if (checkBoundsAndAdd(horizontals, nextSq)) return horizontals;
        }


        return horizontals;
    }

    protected boolean checkBoundsAndAdd(List<Square> horizontals, Square nextSq) {
        if (nextSq == null || (nextSq.getOccupied() && nextSq.getPiece().getSide().equals(this.getSide()))) {
            return true;
        } else if (nextSq.getOccupied() && !nextSq.getPiece().getSide().equals(this.getSide())) {
            horizontals.add(nextSq);
            return true;
        } else {
            horizontals.add(nextSq);
        }
        return false;
    }

    private List<Square> vertStraights(String dir) {

        List<Square> verticals = new ArrayList<>();
        Square nextSq;

        for (int i = 1; i < 9; i++) {
            if (dir == "-") {
                nextSq = board.searchSquare(square.getColumn(), square.getRow() - i);
            } else {
                nextSq = board.searchSquare(square.getColumn(), square.getRow() + i);
            }

            if (checkBoundsAndAdd(verticals, nextSq)) return verticals;
        }
        return verticals;
    }

    public int getHorDir(String hor, int i) {
        int horDir;
        if (hor == "+") {
            horDir = square.getRow() + i;
        } else {
            horDir = square.getRow() - i;
        }
        return horDir;
    }

    public int getVertDir(String vert, int i) {
        int vertDir;
        if (vert == "+") {
            vertDir = square.getColumn() + i;
        } else {
            vertDir = square.getColumn() - i;
        }
        return vertDir;
    }


 /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return eaten == piece.eaten && selected == piece.selected && getSquare().equals(piece.getSquare()) && Objects.equals(game, piece.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eaten, getSquare(), selected, game);
    }*/
}

