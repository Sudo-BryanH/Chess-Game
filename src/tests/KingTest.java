package tests;

import model.Board;
import model.King;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {

    King kingA1;
    King kingF4;
    King kingH4;
    King kingB2;
    King kingB1;
    King kingA2;
    King kingC3;
    Board board;

    @BeforeEach
    public void setUp() {
        newBoard();
        kingA1 = new King(board.searchSquare(1, 1), "white", board);
        kingF4 = new King(board.searchSquare(6, 4), "white", board);
        kingH4 = new King(board.searchSquare(8, 4), "white", board);
        kingB1 = new King(board.searchSquare(2, 1), "white", board);
        kingB2 = new King(board.searchSquare(2, 2), "white", board);
        kingA2 = new King(board.searchSquare(1, 2), "white", board);
        kingC3 = new King(board.searchSquare(3, 3), "white", board);
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
    public void testMoveNull() {
        assertEquals(kingA1.getMovableSquares().size(), 0);

    }

    @Test
    public void testMoveFreely() {
        assertEquals(kingF4.getMovableSquares().size(), 8);

    }

    @Test
    public void testMoveBlock() {
        assertEquals(kingC3.getMovableSquares().size(), 7);
        assertEquals(kingH4.getMovableSquares().size(), 5);
    }


}

