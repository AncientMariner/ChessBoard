package org.xander.chessboard.figures;

import org.junit.Test;
import org.xander.chessboard.BishopsPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;

public class FigureChainTest {
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

    @Test
    public void placeFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 4);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);

        String placeFigures = figuresChain.placeFigures(".....");

        assertThat("figures are not placed", placeFigures.equals("bbbxx"), is(true));
    }

    @Test
    public void placeFiguresKnight() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 2);
        figureQuantityMap.put(KING.toString(), 2);

        FiguresChain figuresChain = new King(figureQuantityMap);
        figuresChain.setNextFigure(new Knight(figureQuantityMap));

        String placeFigures = figuresChain.placeFigures("......\n" +
                                                        "......\n" +
                                                        "......\n" +
                                                        "......\n" +
                                                        "......\n" +
                                                        "......\n");
        System.out.println(placeFigures);
        assertThat("figures are standing on different places", placeFigures.equals("kxkxnn\n" +
                                                                                          "xxxx..\n" +
                                                                                          "...xxx\n" +
                                                                                          "......\n" +
                                                                                          "......\n" +
                                                                                          "......\n"),
                is(true));
    }

}
