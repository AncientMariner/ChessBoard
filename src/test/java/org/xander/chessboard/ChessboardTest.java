package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.xander.chessboard.Figure.*;

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
    public void chessBoardFiguresTest() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        chessboard.setFigureQuantityMap(figureQuantityMap);

        assertTrue(figureQuantityMap.equals(chessboard.getFigureQuantityMap()));
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

    @Test(expected = IllegalStateException.class)
    public void placeAFigureOnBoardNegativeTest() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        chessboard.setFigureQuantityMap(figureQuantityMap);

        chessboard.placeFiguresOnBoard(figureQuantityMap, chessboard.drawABoard());
    }
}
