package org.xander.chessboard.figures;

import org.junit.Test;
import org.xander.chessboard.figuresPlacement.BishopsPlacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class FigureChainTest {
    @Test
    public void setNextFigure() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("k", 1);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);

        figuresChain.setNextFigure(figuresChain1);
        assertThat("object is null", figuresChain.chain != null, is(true));
        assertThat("object is null", figuresChain.chain != null, is(true));
        assertThat("object is null", figuresChain.figureQuantityMap != null, is(true));
        assertThat("key is not present", figuresChain.figureQuantityMap.containsKey("k"), is(true));
        assertThat("value is not present", figuresChain.figureQuantityMap.containsValue(1), is(true));
        assertThat("object is of different type", figuresChain.placementBehavior instanceof BishopsPlacement, is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void placeFiguresBishopNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 4);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);

        Set<String> objects = new HashSet<>();
        objects.add("....\n");

        figuresChain.placeFigures(objects);
    }

    @Test
    public void placeFiguresNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 4);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);

        Set<String> objects = new HashSet<>();
        objects.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("bbbb..\n" +
                                                                                      "xxxxx.\n" +
                                                                                      "xxxxxx\n" +
                                                                                      "x..xxx\n" +
                                                                                      "....xx\n" +
                                                                                      ".....x\n"),
                is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains("k")
                        && !board.contains("q")
                        && !board.contains("r")
                        && !board.contains("n")
                        && board.contains("x")
                        && board.contains(".")
                        && board.contains("b")
                        && leftOnlyFigures(board).length() == 4
                        && boards.size() == 16684
                ));

    }

    @Test
    public void placeFiguresKnightAndKing() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 2);
        figureQuantityMap.put(KING.toString(), 2);

        FiguresChain figuresChain = new King(figureQuantityMap);
        figuresChain.setNextFigure(new Knight(figureQuantityMap));

        Set<String> objects = new HashSet<>();
        objects.add("......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n");
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("kxkxnn\n" +
                                                                                      "xxxx..\n" +
                                                                                      "...xxx\n" +
                                                                                      "......\n" +
                                                                                      "......\n" +
                                                                                      "......\n"),
                is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> board.contains("k")
                        && board.contains("n")
                        && !board.contains("q")
                        && !board.contains("r")
                        && !board.contains("b")
                        && board.contains("x")
                        && board.contains(".")
                        && leftOnlyFigures(board).length() == 4
                        && boards.size() == 112970
                ));
    }

    @Test
    public void placeFiguresKnight() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        FiguresChain figuresChain = new Knight(figureQuantityMap);

        Set<String> objects = new HashSet<>();
        objects.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("nnnn..\n" +
                        "xxxxxx\n" +
                        "xxxxx.\n" +
                        "......\n" +
                        "......\n" +
                        "......\n"),
                is(true));
        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> board.contains("n")
                        && !board.contains("k")
                        && !board.contains("q")
                        && !board.contains("r")
                        && !board.contains("b")
                        && board.contains("x")
                        && board.contains(".")
                        && leftOnlyFigures(board).length() == 4
                        && boards.size() == 26133
                ));
    }

    @Test(expected = IllegalStateException.class)
    public void placeFiguresKnightNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        Set<String> objects = new HashSet<>();
        objects.add("....\n");

        new Knight(figureQuantityMap).placeFigures(objects);
    }

    @Test
    public void placeFiguresKingQueenBishop() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);
        figureQuantityMap.put(BISHOP.toString(), 1);

        FiguresChain kingChain = new King(figureQuantityMap);
        FiguresChain queenChain = new Queen(figureQuantityMap);
        FiguresChain bishopChain = new Bishop(figureQuantityMap);
        kingChain.setNextFigure(queenChain);
        queenChain.setNextFigure(bishopChain);

        HashSet<String> strings = new HashSet<>();
        strings.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = kingChain.placeFigures(strings);

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> board.contains("k")
                        && board.contains("q")
                        && board.contains("b")
                        && !board.contains("r")
                        && !board.contains("n")
                        && board.contains("x")
                        && board.contains(".")
                        && leftOnlyFigures(board).length() == 3
                        && boards.size() == 15308
                ));
    }
}
