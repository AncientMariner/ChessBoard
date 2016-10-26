package org.xander.chessboard.figuresPlacement;

import org.junit.Test;
import org.xander.chessboard.figures.Figure;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class FiguresPlacementTest {
    @Test
    public void numberOfFigureOnBoard() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();

        HashSet<String> boards = new HashSet<>();
        boards.add(EMPTY_BOARD_SIZE_6);
        Set<String> result = figuresPlacement.placeNumberOfFiguresOnBoard(3, boards);
        System.out.println();

        for (String board : result) {
            assertTrue("all elements are not present on each board", !board.contains("k") && !board.contains("q") && board.contains("b"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 3);
        }
    }

    @Test
    public void sequentialFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);
        assertThat("board is different", boards.contains("b.....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n"), is(true));
    }

    @Test
    public void randomFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);

        assertThat("boards are not full", boards.size() == 36, is(true));
        assertThat("board is different", boards.contains("......\n" +
                "......\n" +
                "......\n" +
                "...b..\n" +
                "......\n" +
                "......\n"), is(true));
    }
}
