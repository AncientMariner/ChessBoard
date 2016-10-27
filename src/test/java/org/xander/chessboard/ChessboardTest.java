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
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class ChessboardTest {
    public static final int DIMENSION_6 = 6;

    @Test
    public void chessBoardBasic() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(ROOK.toString(), 1);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(DIMENSION_6).build();

        assertNotNull(chessboard);
        assertEquals(DIMENSION_6, chessboard.getDimension());
    }

    @Test(expected = IllegalStateException.class)
    public void chessBoardBasicNegative() {
        Chessboard.newBuilder(null).withDimension(DIMENSION_6).build();
    }

    @Test(expected = IllegalStateException.class)
    public void nonExistingFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("a", 1);
        Chessboard.newBuilder(figureQuantityMap).build().placeFiguresOnBoard("   ");
    }

    @Test(expected = IllegalStateException.class)
    public void nonExistingFigure() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("PAWN", 1);
        Chessboard.newBuilder(figureQuantityMap).build().placeFiguresOnBoard("......");
    }

    @Test(expected = IllegalStateException.class)
    public void moreThanExpectedFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        figureQuantityMap.put("PAWN", 8);

        Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
    }

    @Test(expected = IllegalStateException.class)
    public void notDesiredFiguresWithPawn() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        figureQuantityMap.put("PAWN", 8);

        Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
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
        figureQuantityMap.put(KING.toString(), 22);
        figureQuantityMap.put(QUEEN.toString(), 23);
        figureQuantityMap.put(BISHOP.toString(), 24);
        figureQuantityMap.put(ROOK.toString(), 25);
        figureQuantityMap.put(KNIGHT.toString(), 26);

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
    public void multipleFigures2() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(8).withKing().withRook().build();

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());

        assertThat("there is no boards", boards.size() > 0, is(true));
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("r") && !board.contains("b"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 4);
        }
    }

    @Test
    public void multipleFigures3() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        figureQuantityMap.put(KNIGHT.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(5).withKing().withRook().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());

        assertThat("there is no boards", boards.size() > 0, is(true));
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("r") && board.contains("n"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 6);
        }
    }

    //todo check such a situation
    @Test
    public void multipleFigures1() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(6).withBishop().build();

        Set<String> boards = chessboard.placeFiguresOnBoard("bbbbbb\n" +
                "b....b\n" +
                "b....b\n" +
                "b....b\n" +
                "b....b\n" +
                "bbbbbb\n");
        assertThat("there is no boards", boards.size() > 0, is(true));
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("b") && !board.contains("r") && !board.contains("k"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 21);
        }
    }

    @Test
    public void smallBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);

        int dimension = 2;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnBoard(chessboard.drawEmptyBoard());
        assertThat("more than 1 figure is present",
                boards.contains("kx\n" + "xx\n"), is(true));

    }
    //todo check for board figures legal placement before calculation

}
