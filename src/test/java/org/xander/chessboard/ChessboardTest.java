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
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class ChessboardTest {
    public static final int DIMENSION_6 = 6;

    @Test
    public void chessBoardBasic() {
        Chessboard chessboard = Chessboard.newBuilder(null).withDimension(DIMENSION_6).build();

        assertNotNull(chessboard);
        assertEquals(DIMENSION_6, chessboard.getDimension());
    }

    @Test
    public void chessBoardFigures() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
        assertTrue(figureQuantityMap.equals(chessboard.getFigureQuantityMap()));
    }

    @Test
    public void drawAnEmptyBoard() {
        Chessboard chessboard = Chessboard.newBuilder(null).withDimension(DIMENSION_6).build();

        String emptyBoard = chessboard.drawEmptyBoard();
        assertEquals(EMPTY_BOARD_SIZE_6, emptyBoard);
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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

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

    //currently out of memory error, requires optimization
    @Ignore
    @Test
    public void readmeRequirement() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);
        figureQuantityMap.put(KNIGHT.toString(), 1);

        int dimension = 7;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        System.out.println();
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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

        chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
    }

    @Test(expected = IllegalStateException.class)
    public void placeAFigureOnBoardNegative() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withKing().build();

        chessboard.placeFiguresOnBoard("");
    }

    @Test
    public void multipleFigures() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(8).withKing().withRook().build();

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("r") && !board.contains("b"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 4);
        }
    }


    //check such a situation
    @Ignore
    @Test
    public void multipleFigures1() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 0);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(6).withBishop().build();

        Set<String> boards = chessboard.placeFiguresOnBoard( "bbbbbb\n" +
                "b....b\n" +
                "b....b\n" +
                "b....b\n" +
                "b....b\n" +
                "bbbbbb\n");
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("r") && !board.contains("b"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 4);
        }
    }
}
