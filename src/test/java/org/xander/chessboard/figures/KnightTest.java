package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.FigureChainTest.EMPTY_BOARD_SIZE_6;

public class KnightTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Knight(new HashMap<>());
        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.KNIGHT.name()) , is(true));
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
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("n"));
            assertTrue("all elements are not present on each board", board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "").length() == 4);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void placeFiguresKnightNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        Set<String> objects = new HashSet<>();
        objects.add("....\n");

        new Knight(figureQuantityMap).placeFigures(objects);
    }
}