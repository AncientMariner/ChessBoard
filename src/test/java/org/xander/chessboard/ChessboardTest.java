package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChessboardTest {
    Chessboard chessboard;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
    }

    @Test
    public void chessBoardBasicTest() {
        assertNotNull(chessboard);
        assertTrue(chessboard.getDimension() == 0);

        int dimension = 5;
        chessboard.setDimension(dimension);

        assertEquals(dimension, chessboard.getDimension());
    }

    @Test
    public void drawAnEmptyBoardTest() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        String emptyBoard = chessboard.drawABoard();
        assertEquals(".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n", emptyBoard);
    }
}
