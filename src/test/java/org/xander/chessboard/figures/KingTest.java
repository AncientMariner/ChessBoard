package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.QUEEN;

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
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);
        figureQuantityMap.put(BISHOP.toString(), 1);

        FiguresChain kingChain = new King(figureQuantityMap);
        FiguresChain queenChain = new Queen(figureQuantityMap);
        FiguresChain bishopChain = new Bishop(figureQuantityMap);
        kingChain.setNextFigure(queenChain);
        queenChain.setNextFigure(bishopChain);

        HashSet<String> strings = new HashSet<>();
        strings.add("......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n");
        Set<String> boards = kingChain.placeFigures(strings);
        for (String board : boards) {
            assertTrue("all elements are not present on each board", board.contains("k") && board.contains("q") && board.contains("b"));
            assertTrue("all elements are not present on each board", board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "").length() == 3);
        }
    }

}