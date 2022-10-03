package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    Square A1;
    Square A8;
    Square E4;
    Square E5;
    Square D4;
    Square D5;
    Square H1;
    Square H2;
    Piece queen;
    Board board;
    List<Square> squares;



    @BeforeEach
    public void setUp() {
        A1 = new Square(1, 1);
        A8 = new Square(1, 8);
        E4 = new Square(5, 4);
        E5 = new Square(5, 5);
        D4 = new Square(4, 4);
        D5 = new Square(4, 5);
        H1 = new Square(8, 1);
        H2 = new Square(8, 2);
        queen = new Queen(A1, "black", board);
        board = new Board(squares);
    }


    @Test
    public void testConstructor() {

        assertEquals(A1.getColumn(), 1);
        assertEquals(A1.getRow(), 1);

        assertEquals(A8.getColumn(), 1);
        assertEquals(A8.getRow(), 8);

        assertEquals(E4.getColumn(), 5);
        assertEquals(E4.getRow(), 4);
        assertEquals(E4.getPiece(), null);

        assertEquals(queen.getSquare().getColumn(), 1);
        assertEquals(queen.getSquare().getRow(), 1);
    }

    @Test
    public void testGetters() {
        assertEquals(A1.getColumn(), 1);
        assertEquals(A1.getRow(), 1);
    }

    @Test
    public void testPiecePlaced() {

        assertEquals(E4.getOccupied(), false);
        E4.setOccupied();
        E4.setPiece(queen);
        assertEquals(queen.getSquare(), E4);
        assertEquals(E4.getOccupied(), true);
        assertEquals(A1.getOccupied(), false);
    }

    @Test
    public void testRemovePiece() {
        A1.removePiece();
        assertEquals(A1.getPiece(), null);
        assertEquals(queen.getSquare(), null);
        queen.moveToSquare(E4);
        queen.removeSquare();
        assertEquals(E4.getPiece(), null);
        assertEquals(queen.getSquare(), null);


    }

    @Test
    public void testSetPiece() {
        Piece pawn = new Pawn(H2, "white", board);
        assertEquals(H2.getPiece(), pawn);
        assertEquals(pawn.getSquare(), H2);

        pawn.moveToSquare(H1);
        assertEquals(H1.getPiece(), pawn);
        assertEquals(pawn.getSquare(), H1);

        E4.setPiece(pawn);
        assertEquals(E4.getPiece(), pawn);
        assertEquals(pawn.getSquare(), E4);

        queen.moveToSquare(E4);
        assertEquals(E4.getPiece(), queen);
        assertEquals(queen.getSquare(), E4);
        assertEquals(pawn.getSquare(), null);

        queen.moveToSquare(H1);
        assertEquals(E4.getPiece(), null);
        assertEquals(H1.getPiece(), queen);
        assertEquals(queen.getSquare(), H1);

    }

}
