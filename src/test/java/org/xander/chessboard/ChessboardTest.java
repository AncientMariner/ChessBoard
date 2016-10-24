package org.xander.chessboard;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.Figure.BISHOP;
import static org.xander.chessboard.Figure.KING;
import static org.xander.chessboard.Figure.KNIGHT;
import static org.xander.chessboard.Figure.QUEEN;
import static org.xander.chessboard.Figure.ROOK;

public class ChessboardTest {
    @Test
    public void chessBoardBasic() {
        Chessboard chessboard = new Chessboard(null);
        assertNotNull(chessboard);
        assertTrue(chessboard.getDimension() == 0);

        int dimension = 5;
        chessboard.setDimension(dimension);

        assertEquals(dimension, chessboard.getDimension());
    }

    @Test
    public void chessBoardFigures() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        Chessboard chessboard = new Chessboard(figureQuantityMap);
        assertTrue(figureQuantityMap.equals(chessboard.getFigureQuantityMap()));
    }

    @Test
    public void drawAnEmptyBoard() {
        int dimension = 5;
        Chessboard chessboard = new Chessboard(null);

        chessboard.setDimension(dimension);

        String emptyBoard = chessboard.drawEmptyBoard();
        assertEquals(".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n", emptyBoard);
    }

    @Test
    public void placeFiguresOnBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        int dimension = 8;
        Chessboard chessboard = new Chessboard(figureQuantityMap);
        chessboard.setDimension(dimension);

        String s = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        assertThat("more than 1 figure is present", s, is("n.......\n........\n........\n........\n........\n........\n........\n........\n"));
    }


    @Test(expected = IllegalStateException.class)
    public void placeAFigureOnBoardNegative() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        Chessboard chessboard = new Chessboard(figureQuantityMap);

        chessboard.placeFiguresOnBoard("");
    }

    @Test
    public void multipleFigures() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        Chessboard chessboard = new Chessboard(figureQuantityMap);
        chessboard.setDimension(8);

        chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
    }

}
