package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

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
        figureQuantityMap.put("King", 2);
        figureQuantityMap.put("Queen", 3);
        figureQuantityMap.put("Bishop", 4);
        figureQuantityMap.put("Rook", 5);
        figureQuantityMap.put("Knight", 6);
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

    @Test
    public void placeAFigureOnBoardTest() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        String emptyBoard = chessboard.drawABoard();

        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("King", 2);
        figureQuantityMap.put("Queen", 3);
        figureQuantityMap.put("Bishop", 4);
        figureQuantityMap.put("Rook", 5);
        figureQuantityMap.put("Knight", 6);
        chessboard.setFigureQuantityMap(figureQuantityMap);

        String boardWithFigures = chessboard.placeFiguresOnBoard(figureQuantityMap, emptyBoard);
        assertEquals("nnnnn\n" +
                     "nrrrr\n" +
                     "rbbbb\n" +
                     "qqqkk\n" +
                     ".....\n", boardWithFigures);
    }

    @Test(expected = IllegalStateException.class)
    public void placeAFigureOnBoardNegativeTest() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("King", 2);
        chessboard.setFigureQuantityMap(figureQuantityMap);

        chessboard.placeFiguresOnBoard(figureQuantityMap, chessboard.drawABoard());
    }
}
