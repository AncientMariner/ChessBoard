package org.xander.chessboard;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;

public class ChessboardTest {

    public static final String EMPTY_BOARD_SIZE_5 = ".....\n" +
                                                    ".....\n" +
                                                    ".....\n" +
                                                    ".....\n" +
                                                    ".....\n";

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
        assertEquals(EMPTY_BOARD_SIZE_5, emptyBoard);
    }

//    runs too long, for now ignored
    @Ignore
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

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        assertThat("more than 1 figure is present",
                boards.contains("kxkxqxxx\n" +
                   "xxxxxxqx\n" +
                   "qxxxxxxx\n" +
                   "xxbbxbxx\n" +
                   "xxxxxbxr\n" +
                   "xxxxxxxx\n" +
                   "xxxxxxxx\n" +
                   "xxxrxxxx\n"), is(true));
    }


    @Test(expected = IllegalStateException.class)
    public void placeALotOfFiguresOnBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 12);
        figureQuantityMap.put(QUEEN.toString(), 13);
        figureQuantityMap.put(BISHOP.toString(), 14);
        figureQuantityMap.put(ROOK.toString(), 15);
        figureQuantityMap.put(KNIGHT.toString(), 16);

        int dimension = 8;
        Chessboard chessboard = new Chessboard(figureQuantityMap);
        chessboard.setDimension(dimension);

        chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
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

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("r") && !board.contains("b"));
            assertTrue("all elements are not present on each board", board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "").length() == 4);
        }
    }
}
