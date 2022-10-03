package tests;

import model.Board;
import model.Piece;
import model.Queen;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    Queen queenH8;
    Piece queenA1;
    Piece queenD4;
    Piece queenC2;
    Queen queenB6;
    Queen queenB7;
    Queen queenD8;
    Queen queenD1;
    Queen queenA8;
    Board board;


    @BeforeEach
    public void setUp() {

        newBoard();
        queenH8 = new Queen(board.searchSquare(8, 8), "black", board);
        queenA1 = new Queen(board.searchSquare(1, 1), "white", board);
        queenD4 = new Queen(board.searchSquare(4, 4), "white", board);
        queenC2 = new Queen(board.searchSquare(3, 2), "black", board);
        queenB6 = new Queen(board.searchSquare(2, 6), "black", board);
        queenB7 = new Queen(board.searchSquare(2, 7), "black", board);
        queenD1 = new Queen(board.searchSquare(4, 1), "black", board);
        queenD8 = new Queen(board.searchSquare(4, 8), "white", board);
        queenA8 = new Queen(board.searchSquare(1, 8), "white", board);



    }

    public void newBoard() {
        List<Square> squares = new ArrayList<>();
        Square s;

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                s = new Square(i, j);
                squares.add(s);
            }

        }

        board = new Board(squares);

    }

    @Test
    public void testConstructor() {
        assertEquals(queenH8.getSquare(), board.searchSquare(8, 8));
        assertEquals(queenC2.getSquare(), board.searchSquare(3, 2));
        assertEquals(board.searchSquare(3, 2).getPiece(), queenC2);
        assertEquals(board.searchSquare(8, 8).getPiece(), queenH8);
        assertEquals(queenB7.getSquare(), board.searchSquare(2, 7));
        assertEquals(board.searchSquare(2, 7).getPiece(), queenB7);

    }

    @Test
    public void testMoveToSquareEmptyDestination() {
        queenH8.moveToSquare(board.searchSquare(7, 4));
        assertEquals(queenH8.getSquare(), board.searchSquare(7, 4));
        assertEquals(board.searchSquare(7, 4).getPiece(), queenH8);
        assertTrue(board.searchSquare(7, 4).getOccupied());
        assertFalse(board.searchSquare(8, 8).getOccupied());

    }


    @Test
    public void testMoveToSquareEat() {
        assertEquals(queenH8.getSquare(), board.searchSquare(8, 8));
        assertEquals(queenD4.getSquare(), board.searchSquare(4, 4));

        queenD4.moveToSquare(board.searchSquare(8,8));

        assertEquals(queenH8.getSquare(), null);
        assertEquals(queenD4.getSquare(), board.searchSquare(8, 8));
        assertEquals(board.searchSquare(8, 8).getPiece(), queenD4);
        assertTrue(board.searchSquare(8, 8).getOccupied());
        assertFalse(board.searchSquare(4, 4).getOccupied());
        assertEquals(board.searchSquare(4, 4).getPiece(), null);

    }

    @Test
    public void testDiagonalNull() {
        assertEquals(queenH8.moveDiagonal("+", "+").size(), 0);
        assertEquals(queenA1.moveDiagonal("-", "-").size(), 0);
    }

    @Test
    public void testDiagonalBlockByPiece() {
        assertEquals(queenH8.moveDiagonal("-", "-").size(), 4);
        assertEquals(queenH8.moveDiagonal("-", "-").get(0), board.searchSquare(7, 7));
        assertEquals(queenH8.moveDiagonal("-", "-").get(1), board.searchSquare(6, 6));
        assertEquals(queenH8.moveDiagonal("-", "-").get(2), board.searchSquare(5, 5));
        assertEquals(queenH8.moveDiagonal("-", "-").get(3), board.searchSquare(4, 4));
        assertEquals(queenA1.moveDiagonal("+", "+").size(), 2);
        assertEquals(queenA1.moveDiagonal("+", "+").get(0), board.searchSquare(2, 2));
        assertEquals(queenA1.moveDiagonal("+", "+").get(1), board.searchSquare(3, 3));
        assertEquals(queenB6.moveDiagonal("+", "-").size(), 2);
        assertEquals(queenB6.moveDiagonal("+", "-").get(0), board.searchSquare(3, 5));


    }

    @Test
    public void testDiagonalBlockByEdge() {

        assertEquals(queenC2.moveDiagonal("-", "-").size(), 1);
        assertEquals(queenC2.moveDiagonal("-", "-").get(0), board.searchSquare(2, 1));

        assertEquals(queenB7.moveDiagonal("-", "+").size(), 1);
        assertEquals(queenB7.moveDiagonal("-", "+").get(0), board.searchSquare(1, 8));

    }

    @Test
    void testDiagonal() {
        assertEquals(queenB7.moveDiagonal("+", "-").size(), 6);
    }

    @Test
    public void testVertDir() {
        assertEquals(queenB7.getHorDir("+", 1), 8);
        assertEquals(queenB7.getHorDir("-", 1), 6);

        assertEquals(queenB7.getVertDir("-", 1), 1);
        assertEquals(queenB7.getVertDir("+", 1), 3);
    }

    @Test
    public void testStraightNull() {

        assertEquals(queenD8.moveStraight("V", "+").size(), 0);
        assertEquals(queenD1.moveStraight("V", "-").size(), 0);
        assertEquals(queenA1.moveStraight("H", "-").size(), 0);
        assertEquals(queenH8.moveStraight("H", "+").size(), 0);
    }

    @Test
    public void testStraightBlockByPiece() {
        assertEquals(queenD8.moveStraight("V", "-").size(), 3);
        assertEquals(queenD8.moveStraight("V", "-").get(0), board.searchSquare(4, 7));
        assertEquals(queenD8.moveStraight("V", "-").get(1), board.searchSquare(4, 6));
        assertEquals(queenD8.moveStraight("V", "-").get(2), board.searchSquare(4, 5));

        assertEquals(queenD1.moveStraight("V", "+").size(), 3);
        assertEquals(queenD1.moveStraight("V", "+").get(0), board.searchSquare(4, 2));
        assertEquals(queenD1.moveStraight("V", "+").get(1), board.searchSquare(4, 3));
        assertEquals(queenD1.moveStraight("V", "+").get(2), board.searchSquare(4, 4));

        assertEquals(queenH8.moveStraight("H", "-").size(), 4);
        assertEquals(queenH8.moveStraight("H", "-").get(0), board.searchSquare(7, 8));
        assertEquals(queenH8.moveStraight("H", "-").get(1), board.searchSquare(6, 8));
        assertEquals(queenH8.moveStraight("H", "-").get(2), board.searchSquare(5, 8));
        assertEquals(queenH8.moveStraight("H", "-").get(3), board.searchSquare(4, 8));

        assertEquals(queenA1.moveStraight("H", "+").size(), 3);
    }

}
