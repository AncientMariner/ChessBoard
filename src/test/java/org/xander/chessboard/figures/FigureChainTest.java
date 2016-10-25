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

public class FigureChainTest {

    public static final String EMPTY_BOARD_SIZE_6 = "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "......\n";

    @Test
    public void setNextFigure() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("1", 1);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);

        figuresChain.setNextFigure(figuresChain1);
        assertThat("object is null", figuresChain.chain != null, is(true));
        assertThat("object is null", figuresChain.chain != null, is(true));
        assertThat("object is null", figuresChain.figureQuantityMap != null, is(true));
        assertThat("key is not present", figuresChain.figureQuantityMap.containsKey("1"), is(true));
        assertThat("value is not present", figuresChain.figureQuantityMap.containsValue(1), is(true));
        assertThat("object is of different type", figuresChain.placementBehavior instanceof BishopsPlacement, is(true));

    }

    @Test(expected = IllegalStateException.class)
    public void placeFigures() {
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

        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("b") && !board.contains("q"));
            assertTrue("all elements are not present on each board", board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "").length() == 4);
        }
    }

    @Test
    public void placeFiguresKnight() {
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
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("n"));
            assertTrue("all elements are not present on each board", board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "").length() == 4);
        }
    }

}
