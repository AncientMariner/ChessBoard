package org.xander.chessboard.figures;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xander.chessboard.figures.Figure.KNIGHT;

public class KnightTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Knight(new HashMap<String, Integer>());
        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.KNIGHT.name()) , is(true));
    }

    @Test
    public void placeFiguresKnight() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        FiguresChain figuresChain = new Knight(figureQuantityMap);

        String placeFigures = ((Knight) figuresChain).placeFigures("......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n");
        assertThat("figures are standing on different places", placeFigures.equals("nnnn..\n" +
                                                                                          "xxxxxx\n" +
                                                                                          "xxxxx.\n" +
                                                                                          "......\n" +
                                                                                          "......\n" +
                                                                                          "......\n"),
                is(true));
    }

    @Ignore
    @Test
    public void placeFiguresKnightNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        FiguresChain figuresChain = new Knight(figureQuantityMap);

        ((Knight)figuresChain).placeFigures("....\n");
    }

}