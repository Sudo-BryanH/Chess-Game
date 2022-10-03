package model;

public class Square {


    protected int column;
    protected int row;
    protected boolean occupied;
    protected Piece piece;

    // EFFECTS: declares a square with a column and row; no occupying pieces
    public Square(int column, int row) {
        this.column = column;
        this.row = row;
        this.occupied = false;
        this.piece = null;

    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    // MODIFIES: this, p
    // EFFECTS: If p is not already in this square, sets this piece as this square's occupier
    //  and removes p from its original square
    public void setPiece(Piece p) {
        if (getPiece() == null || !this.piece.equals(p)) {
            if (this.piece != null) {
                removePiece();
            }

            this.piece = p;
            p.moveToSquare(this);

        }
        setOccupied();

    }

    public Piece getPiece() {
        return piece;
    }

    // MODIFIES: this, p
    // EFFECTS: changes hasPiece to true if false and false if true
    public void setOccupied() {
        occupied = getPiece() != null;

    }

    public boolean getOccupied() {
        return occupied;

    }

    // MODIFIES: this, p
    // EFFECTS: If p is not null, removes this piece as this square's occupier and remove this square from the piece; else nothing
    public void removePiece() {
        Piece p = this.getPiece();

        if (this.getPiece() != null) {
            this.piece = null;
            p.removeSquare();

        }

        setOccupied();
    }
}
