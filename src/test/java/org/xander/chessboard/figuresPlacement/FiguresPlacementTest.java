package org.xander.chessboard.figuresPlacement;

import org.junit.Test;
import org.xander.chessboard.figures.Bishop;
import org.xander.chessboard.figures.FiguresChain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class FiguresPlacementTest {
    @Test
    public void numberOfFigureOnBoard() {

        HashSet<String> boards = new HashSet<>();
        boards.add("nnnnnn\n" +
                "nnnnnn\n" +
                "nnnnnn\n" +
                "nnnnnn\n" +
                "nnnnnn\n" +
                "nnnnnn\n");

        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 3);

        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        Set<String> result = figuresChain.placeFigures(boards);

        assertThat("there are no boards", result.size() > 0, is(true));
        assertTrue("all elements are not present on each board", result.stream()
                .allMatch(board -> !board.contains("k")
                                && !board.contains("q")
                                && !board.contains("r")
                                && !board.contains("b")
                                && board.contains("n")
                                && leftOnlyFigures(board).length() == 36
        ));

    }

    @Test
    public void sequentialFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);
        assertThat("board is different", boards.contains("b.....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n"), is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains("k")
                                && !board.contains("q")
                                && !board.contains("r")
                                && !board.contains("n")
                                && board.contains("b")
                                && leftOnlyFigures(board).length() == 1
                                && boards.size() == 36
                ));

    }

    @Test
    public void randomFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);

        assertThat("boards are not full", boards.size() == 36, is(true));
        assertThat("board is different", boards.contains("......\n" +
                "......\n" +
                "......\n" +
                "...b..\n" +
                "......\n" +
                "......\n"), is(true));
        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains("k")
                                && !board.contains("q")
                                && !board.contains("r")
                                && !board.contains("n")
                                && board.contains("b")
                                && leftOnlyFigures(board).length() == 1
                                && boards.size() == 36
                ));
    }
}
