package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;

public class KingTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new King(new HashMap<>());
        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(KING.name()) , is(true));
    }

    @Test
    public void placeFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);

        FiguresChain figuresChain = new King(figureQuantityMap);
        FiguresChain figuresChain1 = new Bishop(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);
//        FiguresChain figuresChain = new King(figureQuantityMap);

        figuresChain.placeFigures("......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n");
        System.out.println();

    }

}