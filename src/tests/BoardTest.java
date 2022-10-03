package tests;

import model.Board;
import model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    List<Square> squares = new ArrayList<>();
    Board board;


    @BeforeEach
    public void setUp() {
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

        assertEquals(squares.size(), 64);
        assertEquals(squares.get(0).getColumn(), 1);
        assertEquals(squares.get(0).getRow(), 1);
        assertEquals(squares.get(63).getColumn(), 8);
        assertEquals(squares.get(63).getRow(), 8);
        assertEquals(squares.get(31).getColumn(), 4);
        assertEquals(squares.get(31).getRow(), 8);

    }

    @Test
    public void testSearchSquare() {

        assertEquals(board.searchSquare(1, 1), squares.get(0));
        assertEquals(board.searchSquare(8, 6), squares.get(61));
        assertEquals(board.searchSquare(4, 8), squares.get(31));

    }
}
